package co.kr.iwaz.driver;

import co.kr.iwaz.document.Category;
import co.kr.iwaz.document.Result;
import co.kr.iwaz.document.hits.Source;
import co.kr.iwaz.option.SearchOption;
import co.kr.iwaz.option.PropertiesOption;
import co.kr.iwaz.option.Field;
import co.kr.iwaz.response.ElasticResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.TopHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.*;
import java.util.*;

public class ElasticsearchDriver {

    private RestHighLevelClient restHighLevelClient;
    private Properties properties;
    private String host;
    private int port;
    private String scheme;
    private String userName;
    private String password;
    private String index;
    private int resultSize;
    private int countSize;
    private String[] fields;
    private String searchOption;
    private String[] categories;
    private String dataCategory;
    private Operator operator;

    public ElasticsearchDriver(String propertiesFilePath) {
        loadPropertiesFile(propertiesFilePath);

        this.host = properties.getProperty(PropertiesOption.HOST.label());
        this.port = Integer.valueOf(properties.getProperty(PropertiesOption.PORT.label()));
        this.scheme = properties.getProperty(PropertiesOption.SCHEME.label());
        this.userName = properties.getProperty(PropertiesOption.USER_NAME.label());
        this.password = properties.getProperty(PropertiesOption.PASSWORD.label());
        connect();
    }

    public ElasticsearchDriver(String host, int port, String scheme) {
        new ElasticsearchDriver(host, port, scheme, null, null);
    }

    public ElasticsearchDriver(String host, int port, String scheme, String userName, String password) {
        this.host = host;
        this.port = port;
        this.scheme = scheme;
        this.userName = userName;
        this.password = password;
        connect();
    }


    public void setSchemeInfo(){
        this.index = properties.getProperty(PropertiesOption.INDEX.label());
        try{
            String resultSize_ = properties.getProperty(PropertiesOption.RESULT_SIZE.label());
            this.resultSize = Optional.ofNullable(resultSize_).isPresent() && !"".equals(resultSize_) ? Integer.parseInt(resultSize_) : 5;
        }catch (Exception e){
            throw new RuntimeException("properties 설정파일의 elastic.result.size 값을 숫자로 변경할 수 없습니다. 확인해주세요.");
        }
        try{
            String countSize_ = this.properties.getProperty(PropertiesOption.COUNT_SIZE.label());
            this.countSize = Optional.ofNullable(countSize_).isPresent() && !"".equals(countSize_) ? Integer.parseInt(countSize_) : 5;
        }catch (Exception e){
            throw new RuntimeException("properties 설정파일의 elastic.count.size 값을 숫자로 변경할 수 없습니다. 확인해주세요.");
        }

        this.searchOption = this.properties.getProperty(PropertiesOption.SEARCH_OPTION.label());
        this.fields = this.properties.getProperty(PropertiesOption.SEARCH_FIELD.label()).split(",");
        if(fields.length == 1 && "".equals(fields[0]))
            this.fields = new String[]{Field.SEARCH.label()};
        this.categories = this.properties.getProperty(PropertiesOption.CATEGORY.label()).split(",");
        this.dataCategory = this.properties.getProperty(PropertiesOption.DATA_CATEGORY.label());
        this.operator = SearchOption.AND.label().equals(this.properties.getProperty(PropertiesOption.OPERATOR.label()).toUpperCase()) ? Operator.AND : Operator.OR;
    }

    public void setSchemeInfo(String index, int resultSize, int countSize, String[] fields, String searchOption, String[] categories, String dataCategory, String operator){
        this.index = index;
        this.resultSize = resultSize;
        this.countSize = countSize;
        this.searchOption = searchOption;
        this.fields = fields;
        if(fields.length == 1 && "".equals(fields[0]))
            this.fields = new String[]{Field.SEARCH.label()};
        this.categories = categories;
        this.dataCategory = dataCategory;
        this.operator = operator.equals(SearchOption.AND.label()) ? Operator.AND : Operator.OR;
    }

    public void connect(){
        if(this.restHighLevelClient == null) this.restHighLevelClient = new RestHighLevelClient(setting());
    }

    public void exit(){
        if (this.restHighLevelClient != null) {
            try {
                this.restHighLevelClient.close();
                this.restHighLevelClient = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private RestClientBuilder setting(){
        if(Optional.ofNullable(this.userName).isPresent() && Optional.ofNullable(this.password).isPresent() && !"".equals(this.userName) && !"".equals(this.password)){
            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(this.userName, this.password));
            return RestClient.builder(new HttpHost(this.host, this.port , this.scheme)).setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));
        }
        else{
            return RestClient.builder(new HttpHost(this.host, this.port , this.scheme));
        }
    }

    private void loadPropertiesFile(String propertiesFilePath){
        this.properties = new Properties();
        try (InputStream input = new FileInputStream(propertiesFilePath)) {
            this.properties.load(input);
            // 이제 config 객체를 사용하여 설정 값을 읽을 수 있습니다.
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Result search(String searchWord){
        return search(searchWord, null);
    }

    public Result search(String searchWord, String noSearchWords){
        boolean isNoSearch = false;
        if(Optional.ofNullable(noSearchWords).isPresent()) isNoSearch = true;

        String queryString = createQueryString(searchWord , isNoSearch , noSearchWords);
        SearchRequest searchRequest = createQuery(queryString);
        Aggregations aggregations = getResult(searchRequest);
        Result result = parsingResult(aggregations);
        return result;
    }

    private String createQueryString(String searchKeyword, boolean isNoSearch , String noSearchWords){
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        if(this.searchOption.equals(SearchOption.DEFAULT.label())){
            sb.append(String.format("%s", searchKeyword));
        }
        else{
            String[] s = searchKeyword.split(" ");
            for(int i=0;i<s.length;i++){
                sb.append(String.format("%s", s[i]));
                if(i != s.length-1) sb.append(String.format(" %s ",this.searchOption.toUpperCase()));
            }
        }
        sb.append(")");
        if(isNoSearch) sb.append(String.format(" AND NOT %s", noSearchWords));
        return sb.toString();
    }

    private SearchRequest createQuery(String queryString) {
        SearchRequest searchRequest = new SearchRequest(this.index);
        QueryStringQueryBuilder queryStringQueryBuilder = setQueryString(queryString);
        SearchSourceBuilder searchSourceBuilder = setSourceBuilder(queryStringQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        return searchRequest;
    }

    private QueryStringQueryBuilder setQueryString(String searchKeyword){
        QueryStringQueryBuilder builder = QueryBuilders.queryStringQuery(searchKeyword);
        for(int i=0;i<this.fields.length;i++){
            String[] fields_ = this.fields[i].split(":");
            float boost = 1.0F;
            if(fields_.length != 1) boost = Float.valueOf(fields_[1]);
            builder.field(fields_[0] , boost);
        }
        builder.defaultOperator(this.operator);
        return builder;
    }

    private SearchSourceBuilder setSourceBuilder(QueryStringQueryBuilder builder){
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(builder);
        searchSourceBuilder.size(0);
        for(int i=0;i<this.categories.length;i++){
            searchSourceBuilder.aggregation(AggregationBuilders.terms(this.categories[i]).field(this.categories[i]).size(this.countSize));
        }
//        builder.aggregation(AggregationBuilders.terms(dataCategory).field(dataCategory).subAggregation(AggregationBuilders.topHits("result").size(size).fetchSource(null , Field.SEARCH.label())));
        searchSourceBuilder.aggregation(AggregationBuilders.terms(this.dataCategory).field(this.dataCategory).subAggregation(AggregationBuilders.topHits("result").size(this.resultSize).fetchSource(null , Field.SEARCH.label())));
        System.out.println(searchSourceBuilder.toString());
        return searchSourceBuilder;
    }

    private Aggregations getResult(SearchRequest request){
        Aggregations result = null;
        try {
            SearchResponse returnResponse = this.restHighLevelClient.search(request, RequestOptions.DEFAULT);
            result = returnResponse.getAggregations();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Result parsingResult(Aggregations aggregations){

        Terms terms = aggregations.get(this.dataCategory);

        ObjectMapper objectMapper = new ObjectMapper();

        List<Source> dataCategories;
        List<Category> categoryList;
        Result result = new Result();

        dataCategories = new ArrayList<>();
        for (Terms.Bucket entry : terms.getBuckets()) {
            String key = entry.getKeyAsString();
            long docCount = entry.getDocCount();
            TopHits topHits = entry.getAggregations().get("result");
            for (SearchHit hit : topHits.getHits().getHits()) {
                // 중간 변환
                Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                ElasticResponse elasticResponse = objectMapper.convertValue(sourceAsMap, ElasticResponse.class);
                // 각 데이터별 객체화
                Source source = elasticResponse.createSource(key,docCount,this.dataCategory);
                dataCategories.add(source);
            }
        }

        result.setDataCategory(dataCategories);

        if(!"".equals(this.categories[0])){
            for(int i=0;i<this.categories.length;i++){
                categoryList = new ArrayList<>();
                terms = aggregations.get(this.categories[i]);
                String categoryName = terms.getName();
                for (Terms.Bucket entry : terms.getBuckets()) {
                    String key = entry.getKeyAsString();
                    long docCount = entry.getDocCount();
                    categoryList.add(new Category(key, docCount));
                }
                result.setCountCategory(categoryName,categoryList);
            }
        }

        return result;
    }

}

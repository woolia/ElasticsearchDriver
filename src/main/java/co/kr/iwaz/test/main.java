package co.kr.iwaz.test;

import co.kr.iwaz.document.Result;
import co.kr.iwaz.driver.ElasticsearchDriver;
import co.kr.iwaz.option.SearchOption;
import co.kr.iwaz.option.Field;

public class main {

    public static void main(String[] args) {
//    private void test(){

        String searchKeyword = "안동시의 세계적인 박물관";
        String noSearchWords = "안동";
        boolean isNoSearch = true;

//        [1] properties 파일 설정값으로 사용하기
        String propertiesFilePath = "D:\\WorkSpace\\Java\\ElasticsearchDriver\\target\\settings.properties";
        ElasticsearchDriver driver1 = new ElasticsearchDriver(propertiesFilePath);
        driver1.setSchemeInfo();
        Result result = driver1.search(searchKeyword, noSearchWords);
        System.out.println(result);
        driver1.exit();

        driver1.connect();
        result = driver1.search(searchKeyword);
        System.out.println(result);
        driver1.exit();
//        [2] 자바 변수로 스키마 정보 입력하여 사용하기
//        String host = "192.168.1.161";
//        int port = 9200;
//        String scheme = "http";
//        String userName = "elastic";
//        String password = "Q!tjsqja915";
//        String index = "type_1";
//        int resultSize = 5;
//        int countSize = 4;
//        String[] fields = new String[]{Field.TEXTBOOK_CONTENTS.label(2.3), Field.QUESTION_TITLE.label()};
//        String searchOption = SearchOption.DEFAULT.label();
//        String[] categories = new String[]{Field.CONTENTS_CATEGORY.label(), Field.SUBJECT_CATEGORY.label(), Field.SERVICE_CATEGORY.label()};
//        String dataCategory = Field.TOTAL_CATEGORY.label();
//        String operator = SearchOption.OR.label();
//        ElasticsearchDriver driver2 = new ElasticsearchDriver(host, port, scheme, userName, password);
//
//        driver2.setSchemeInfo(index, resultSize, countSize, fields, searchOption, categories, dataCategory , operator);
//        Result search = driver2.search(searchKeyword, noSearchWords);
//        System.out.println(search);
//        search = driver2.search(searchKeyword);
//        System.out.println(search);
//        driver2.exit();

    }
}

package co.kr.iwaz.option;

public enum PropertiesOption {

    HOST("elastic.host"),
    PORT("elastic.port"),
    SCHEME("elastic.scheme"),
    USER_NAME("elastic.userName"),
    PASSWORD("elastic.password"),
    INDEX("elastic.index"),
    RESULT_SIZE("elastic.result.size"),
    COUNT_SIZE("elastic.count.size"),
    SEARCH_FIELD("elastic.search.field"),
    SEARCH_OPTION("elastic.search.option"),
    CATEGORY("elastic.aggs.category.field.name"),
    DATA_CATEGORY("elastic.aggs.dataCategory.field.name"),
    EXCLUDE_FIELD("elastic.agg.field.excludes"),
    NOSEARCH_WORDS("elastic.noSearch.words"),
    OPERATOR("elastic.search.operator")
    ;

    private final String label;

    PropertiesOption(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }

}

package co.kr.iwaz.option;

public enum SearchOption {
    OR("OR"),
    AND("AND"),
    DEFAULT("")
    ;

    private final String label;

    SearchOption(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }

}

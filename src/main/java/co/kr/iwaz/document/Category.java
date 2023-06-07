package co.kr.iwaz.document;

public class Category {

    private String key;
    private long value;

    public Category() {
    }

    public Category(String key, long value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Category{" +
                ", key='" + key + '\'' +
                ", value=" + value +
                '}';
    }
}

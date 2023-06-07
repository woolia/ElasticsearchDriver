package co.kr.iwaz.document;

import co.kr.iwaz.document.hits.Source;

import java.util.*;

public class Result {

    List<Source> dataCategory;
    Map<String,List<Category>> countCategory;

    public List<Source> getDataCategory() {
        return dataCategory;
    }

    public void setDataCategory(List<Source> dataCategory) {
        this.dataCategory = dataCategory;
    }

    public Map<String,List<Category>> getCountCategory() {
        return countCategory;
    }

    public void setCountCategory(String categoryName , List<Category> category) {
        if(this.countCategory == null){
            this.countCategory = new HashMap<>();
        }
        this.countCategory.put(categoryName,category);
    }

    @Override
    public String toString() {
        return "Result{" +
                "dataCategory=" + dataCategory +
                ", category=" + countCategory +
                '}';
    }
}

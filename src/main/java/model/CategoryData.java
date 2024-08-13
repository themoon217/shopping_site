package model;

import java.util.List;

public class CategoryData {
	private List<String> categoryList;

    public CategoryData(List<String> categoryList) {
        this.categoryList = categoryList;
    }

    public List<String> getCategoryList() {
        return categoryList;
    }
}

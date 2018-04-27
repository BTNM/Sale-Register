package Oblig3.TableViewClass;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CategoryObservable {
    private final SimpleIntegerProperty categoryId;
    private final SimpleStringProperty categoryName;

    public CategoryObservable(CategoryObservable categoryObservable) {
        this.categoryId = new SimpleIntegerProperty(categoryObservable.getCategoryId());
        this.categoryName = new SimpleStringProperty(categoryObservable.getCategoryName());
    }

    public CategoryObservable(int categoryId, String categoryName) {
        this.categoryId = new SimpleIntegerProperty(categoryId);
        this.categoryName = new SimpleStringProperty(categoryName);
    }

    public int getCategoryId() {
        return categoryId.get();
    }

    public void setCategoryId(int categoryId) {
        this.categoryId.set(categoryId);
    }

    public String getCategoryName() {
        return categoryName.get();
    }

    public void setCategoryName(String categoryName) {
        this.categoryName.set(categoryName);
    }
}

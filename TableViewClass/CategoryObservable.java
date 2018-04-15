package Oblig3.TableViewClass;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CategoryObservable {
    private final SimpleIntegerProperty categoryId;
    private final SimpleStringProperty categoryName;

    public CategoryObservable(int categoryId, String categoryName) {
        this.categoryId = new SimpleIntegerProperty(categoryId);
        this.categoryName = new SimpleStringProperty(categoryName);
    }

}

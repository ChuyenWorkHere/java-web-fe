package servlet.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private int categoryId;
    private String categoryName;
    private String categoryDescription;
    private int isActive;

    public Category(String categoryName, String categoryDescription,  int isActive) {
        this.categoryDescription = categoryDescription;
        this.categoryName = categoryName;
        this.isActive = isActive;
    }
}

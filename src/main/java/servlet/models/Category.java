package servlet.models;

import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private int categoryId;
    private String categoryName;
    private String categoryDescription;


}

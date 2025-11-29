package servlet.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    private Long reviewId;
    private short rate;
    private String comment;
    private Date createdAt;
    private User user;
    private Product product;
}

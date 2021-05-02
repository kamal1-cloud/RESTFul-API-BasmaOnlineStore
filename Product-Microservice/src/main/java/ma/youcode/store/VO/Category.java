package ma.youcode.store.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private Long categoryId;
    private String categoryName;
    private String categoryDescription;
    private boolean categoryStatus;
    private String categoryImageUrl;
}

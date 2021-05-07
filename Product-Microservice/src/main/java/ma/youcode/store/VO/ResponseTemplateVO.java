package ma.youcode.store.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.youcode.store.Model.Products;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {
    private Products products;
    private Category category;
}

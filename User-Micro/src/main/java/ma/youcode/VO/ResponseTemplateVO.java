package ma.youcode.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.youcode.models.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {
	private User user;
	private Order order;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}

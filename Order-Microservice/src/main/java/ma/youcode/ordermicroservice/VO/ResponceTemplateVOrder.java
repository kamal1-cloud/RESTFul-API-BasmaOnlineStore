package ma.youcode.ordermicroservice.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.youcode.ordermicroservice.Models.Cart;
import ma.youcode.ordermicroservice.Models.Orders;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponceTemplateVOrder {
	private Cart cart;
	private Orders order;

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

}

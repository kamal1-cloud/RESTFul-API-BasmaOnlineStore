package ma.youcode.VO;

import ma.youcode.ordermicroservice.Models.Orders;

public class ResponseTempleteProduct {
	private Product product;
	private Orders oder;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Orders getOder() {
		return oder;
	}

	public void setOder(Orders oder) {
		this.oder = oder;
	}


}

package ma.youcode.VO;

import ma.youcode.ordermicroservice.Models.Orders;

public class ResponseTempleteProduct {
	private Products product;
	private Orders oder;

	public Products getProduct() {
		return product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}

	public Orders getOder() {
		return oder;
	}

	public void setOder(Orders oder) {
		this.oder = oder;
	}

}

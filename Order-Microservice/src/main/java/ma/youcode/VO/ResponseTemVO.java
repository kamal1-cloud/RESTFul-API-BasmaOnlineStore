package ma.youcode.VO;

import ma.youcode.ordermicroservice.Models.Orders;

public class ResponseTemVO {
	private User user;
	private Orders oder;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Orders getOder() {
		return oder;
	}

	public void setOder(Orders oder) {
		this.oder = oder;
	}

}

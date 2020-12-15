package com.areteans.pojo;

public class RestroItems
{
	 int id;
	 int  restroId ;
	 int itemId ;
	 float price ;  
	 float discount ;
	 int  status ;
	
	 public RestroItems() {
		super();
	}

	public RestroItems(int id, int restroId, int itemId, float price, float discount, int status) {
		super();
		this.id = id;
		this.restroId = restroId;
		this.itemId = itemId;
		this.price = price;
		this.discount = discount;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRestroId() {
		return restroId;
	}

	public void setRestroId(int restroId) {
		this.restroId = restroId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "RestroItems [id=" + id + ", restroId=" + restroId + ", itemId=" + itemId + ", price=" + price
				+ ", discount=" + discount + ", status=" + status + "]";
	}
	 
	 
	 
	 
	 
}
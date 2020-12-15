package com.areteans.pojo;

public class Items
{
	int id; 
	String  name;
	int  categoryId;
	int restroId;
	float price;
	float discount;
	
	public Items() {
		// TODO Auto-generated constructor stub
	}

	public Items(int id, String name, int categoryId, int restroId, float price, float discount) {
		super();
		this.id = id;
		this.name = name;
		this.categoryId = categoryId;
		this.restroId = restroId;
		this.price = price;
		this.discount = discount;
	}

	public Items(int id, String name, float price, float discount) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.discount = discount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getRestroId() {
		return restroId;
	}

	public void setRestroId(int restroId) {
		this.restroId = restroId;
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

	@Override
	public String toString() {
		return "Items [id=" + id + ", name=" + name + ", categoryId=" + categoryId + ", restroId=" + restroId
				+ ", price=" + price + ", discount=" + discount + "]";
	}
}


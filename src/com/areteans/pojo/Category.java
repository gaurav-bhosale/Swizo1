package com.areteans.pojo;

public class Category 

{
	int catagoryId;
	String catagoryName;
	
	public Category(){
		super();
	}

	public Category(int catagoryId, String catagoryName) {
		super();
		this.catagoryId = catagoryId;
		this.catagoryName = catagoryName;
	}

	public int getCatagoryId() {
		return catagoryId;
	}
	public void setCatagoryId(int catagoryId) {
		this.catagoryId = catagoryId;
	}

	public String getCatagoryName() {
		return catagoryName;
	}
	public void setCatagoryName(String catagoryName) {
		this.catagoryName = catagoryName;
	}

	@Override
	public String toString() {
		return "Category [catagoryId=" + catagoryId + ", catagoryName=" + catagoryName + "]";
	}
}



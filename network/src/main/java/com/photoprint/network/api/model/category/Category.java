package com.photoprint.network.api.model.category;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Category{

	@SerializedName("data")
	@Expose
	private List<ItemCategory> itemCategoryList;

	public void setData(List<ItemCategory> data){
		this.itemCategoryList = data;
	}

	public List<ItemCategory> getListCategory(){
		return itemCategoryList;
	}

	@Override
 	public String toString(){
		return 
			"Category{" + 
			"data = '" + itemCategoryList + '\'' +
			"}";
		}
}
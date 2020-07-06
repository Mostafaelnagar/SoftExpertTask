package com.m.softexperttask.home.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class HomeResponse{

	@SerializedName("data")
	private List<HomeData> data;

	@SerializedName("status")
	private int status;

	public List<HomeData> getData(){
		return data;
	}

	public int getStatus(){
		return status;
	}
}
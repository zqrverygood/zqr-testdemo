package com.zqr.snake.mytest.Retrofit;

import java.util.List;

public class ApiListResponse<T> {

	public int count;
	public String previous;
	public String next;
	public List<T> results;
	
	
}

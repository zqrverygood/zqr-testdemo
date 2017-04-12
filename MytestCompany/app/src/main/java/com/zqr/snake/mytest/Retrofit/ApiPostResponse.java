package com.zqr.snake.mytest.Retrofit;

public class ApiPostResponse<T> {

	public int code;
	public T	result;
	public String error;
	
	public boolean isSuccess(){
		return code == 200;
	}
	
}

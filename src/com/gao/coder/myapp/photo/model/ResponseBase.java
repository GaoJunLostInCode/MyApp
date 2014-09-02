package com.gao.coder.myapp.photo.model;

public abstract class ResponseBase
{
	public static final int FLAG_SUCCESS = 200;
	public static final int FLAG_NOTLOGINED = 403;

	protected int flag = 0;

	public boolean isLogined()
	{
		return flag == FLAG_NOTLOGINED ? false : true;
	}

	public boolean isSuccess()
	{
		return flag == FLAG_SUCCESS ? true : false;
	}
}

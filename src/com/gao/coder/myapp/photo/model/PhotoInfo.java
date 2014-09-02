package com.gao.coder.myapp.photo.model;

public class PhotoInfo
{
	public String url = null;

	public String getWholeUrl()
	{
		return "http://192.168.1.5/photo/" + url;
	}
}

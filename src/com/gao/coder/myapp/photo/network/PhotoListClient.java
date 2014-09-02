package com.gao.coder.myapp.photo.network;

import com.gao.coder.myapp.photo.model.ParamsBase;
import com.gao.coder.myapp.photo.model.ResponseBase;
import com.gao.coder.myapp.photo.model.ResponsePhotoList;
import com.google.gson.Gson;

public class PhotoListClient extends CUHttpClient
{

	@Override
	protected String createActionName()
	{
		return "list";
	}

	@Override
	protected ResponseBase parseResponse(String response)
	{
		return new Gson().fromJson(response, ResponsePhotoList.class);
	}

	public void getPhotoList()
	{
		postAsnyc(new ParamsBase());
	}

}

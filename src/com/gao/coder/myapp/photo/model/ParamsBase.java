package com.gao.coder.myapp.photo.model;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;

/**
 * 参数基类
 * 
 * @author Gaojun on Aug 20, 2014
 */
public class ParamsBase
{
	public String usercode = null;

	public String toJson()
	{
		Gson gson = new Gson();

		return gson.toJson(this);
	}

	public HttpEntity toHttpEntity()
	{
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		HttpEntity entity = null;

		BasicNameValuePair nameValuePair = new BasicNameValuePair("data",
				toJson());
		parameters.add(nameValuePair);
		try
		{
			entity = new UrlEncodedFormEntity(parameters, "utf8");
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}

		return entity;
	}
}

package com.gao.coder.myapp.photo.network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.gao.coder.myapp.photo.model.ParamsBase;
import com.gao.coder.myapp.photo.model.ResponseBase;

public abstract class CUHttpClient
{
	// localhost/eSchool_svn/trunk/eschool/takeaway.php?
	public static final String URL_ROOT = "http://192.168.1.5/photo/index.php?action=";

	public static final String CHAR_SET = "UTF-8";
	public static String PHPSESSID = null;

	protected CallBack mCallBack = null;

	protected abstract String createActionName();

	protected abstract ResponseBase parseResponse(String response);

	private boolean mIsCanceled = false;

	public void cancel()
	{
		mIsCanceled = true;
	}

	public static interface CallBack
	{
		public void onCallback(ResponseBase response);

		public void onNotLoginedBack(ResponseBase error);
	}

	public void setCallback(CallBack callBack)
	{
		mCallBack = callBack;
	}

	protected String createUrl()
	{
		StringBuffer buffer = new StringBuffer(URL_ROOT);
		String action = createActionName();
		buffer.append(action);
		String strUrl = buffer.toString();

		return strUrl;
	}

	public void postAsnyc(final ParamsBase params)
	{
		mIsCanceled = false;

		Thread thread = new Thread(new Runnable()
		{
			public void run()
			{
				String ret = doPost(params);

				if (mCallBack != null && !mIsCanceled)
				{
					try
					{
						final ResponseBase result = parseResponse(ret);
						Handler handlerMain = new Handler(
								Looper.getMainLooper());
						handlerMain.post(new Runnable()
						{
							public void run()
							{
								if (!result.isLogined())
								{
									mCallBack.onNotLoginedBack(result);
								}
								else
								{
									mCallBack.onCallback(result);
								}
							}
						});

					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		});

		thread.start();
	}

	

	protected String doPost(ParamsBase params)
	{
		HttpPost post = new HttpPost(createUrl());
		HttpEntity entity = params.toHttpEntity();
		HttpClient client = new DefaultHttpClient();
		String strRet = null;
		try
		{
			Log.d("gao.takeaway", "gao.doPost action==" + createActionName()
					+ "\n params====" + EntityUtils.toString(entity));

			post.setEntity(entity);
			if (null != PHPSESSID)
			{
				post.setHeader("Cookie", "PHPSESSID=" + PHPSESSID);
			}

			HttpResponse response = client.execute(post);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
			{
				HttpEntity entityRes = response.getEntity();
				strRet = EntityUtils.toString(entityRes, CHAR_SET);

				CookieStore mCookieStore = ((DefaultHttpClient) client)
						.getCookieStore();
				List<Cookie> cookies = mCookieStore.getCookies();
				for (int i = 0; i < cookies.size(); i++)
				{
					if ("PHPSESSID".equals(cookies.get(i).getName()))
					{
						PHPSESSID = cookies.get(i).getValue();
						break;
					}
				}

				Log.d("gao.takeaway", "phpsessid==" + PHPSESSID
						+ " \ndoPost result=====" + strRet);
			}
			else
			{
				System.out.println("gao.doPost StatusLine=="
						+ response.getStatusLine().getStatusCode());
			}
		}
		catch (UnsupportedEncodingException e1)
		{
			e1.printStackTrace();
		}
		catch (ClientProtocolException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return strRet;
	}
}

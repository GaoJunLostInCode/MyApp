package com.gao.coder.myapp.photo.util;

import java.lang.ref.SoftReference;
import java.util.HashMap;

import android.graphics.Bitmap;

public class ImageCacheManager
{
	private static HashMap<String, SoftReference<Bitmap>> mBitmaps = new HashMap<String, SoftReference<Bitmap>>();

	public static SoftReference<Bitmap> getBitmap(String url)
	{
		return mBitmaps.get(url);
	}

	public static void putBitmap(String url, Bitmap bitmap)
	{
		SoftReference<Bitmap> mapReference = new SoftReference<Bitmap>(bitmap);
		mBitmaps.put(url, mapReference);
	}
}

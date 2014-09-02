package com.gao.coder.myapp.photo.widget;

import java.lang.ref.SoftReference;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.gao.coder.myapp.photo.network.Downloader;
import com.gao.coder.myapp.photo.util.BitmapUtil;
import com.gao.coder.myapp.photo.util.ImageCacheManager;

public class AutoLoadImageView extends ImageView
{
	public AutoLoadImageView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public void loadUrl(final String url)
	{
		setImageBitmap(null);
		SoftReference<Bitmap> reference = ImageCacheManager.getBitmap(url);
		if (null != reference && reference.get() != null)
		{
			setImageBitmap(reference.get());
			return;
		}

		Thread thread = new Thread(new Runnable()
		{
			public void run()
			{
				Downloader downloader = new Downloader();
				try
				{
					final Bitmap bitmapNew = BitmapFactory
							.decodeStream(downloader.getImageStream(url));
					final Bitmap bitmapIcon = BitmapUtil.comp(bitmapNew);
					
					ImageCacheManager.putBitmap(url, bitmapIcon);

					Handler handler = new Handler(Looper.getMainLooper());
					handler.post(new Runnable()
					{
						public void run()
						{
							setImageBitmap(bitmapNew);
						}
					});
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		thread.start();

	}
}

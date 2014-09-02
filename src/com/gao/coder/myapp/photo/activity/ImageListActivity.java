package com.gao.coder.myapp.photo.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ListView;

import com.gao.coder.myapp.R;
import com.gao.coder.myapp.photo.activity.adapter.PhotoListAdapter;
import com.gao.coder.myapp.photo.model.ResponseBase;
import com.gao.coder.myapp.photo.model.ResponsePhotoList;
import com.gao.coder.myapp.photo.network.CUHttpClient.CallBack;
import com.gao.coder.myapp.photo.network.PhotoListClient;

public class ImageListActivity extends FragmentActivity
{
	private PhotoListClient mClient = new PhotoListClient();
	private ListView mListView = null;
	private PhotoListAdapter mAdapter = null;

	@Override
	protected void onCreate(Bundle arg0)
	{
		super.onCreate(arg0);

		setContentView(R.layout.photo_activity_imagelist);
		mListView = (ListView) findViewById(R.id.listView_photo_imageListActivity);
		mAdapter = new PhotoListAdapter(LayoutInflater.from(this));
		mListView.setAdapter(mAdapter);

		mClient.setCallback(new CallBack()
		{
			@Override
			public void onNotLoginedBack(ResponseBase error)
			{

			}

			@Override
			public void onCallback(ResponseBase response)
			{
				mAdapter.setPhotoInfos(((ResponsePhotoList) response).files);
				mAdapter.notifyDataSetChanged();
			}
		});
		mClient.getPhotoList();
	}
}

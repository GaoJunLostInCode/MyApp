package com.gao.coder.myapp.photo.activity.adapter;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.gao.coder.myapp.R;
import com.gao.coder.myapp.photo.model.PhotoInfo;
import com.gao.coder.myapp.photo.widget.AutoLoadImageView;

public class PhotoListAdapter extends BaseAdapter
{
	private LayoutInflater mInflater = null;
	private List<PhotoInfo> mPhotoInfos = null;

	public void setPhotoInfos(List<PhotoInfo> infos)
	{
		mPhotoInfos = infos;
	}

	public PhotoListAdapter(LayoutInflater inflater)
	{
		mInflater = inflater;
	}

	@Override
	public int getCount()
	{
		return mPhotoInfos == null ? 0 : mPhotoInfos.size();
	}

	@Override
	public Object getItem(int position)
	{
		return mPhotoInfos.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		PhotoInfo info = (PhotoInfo) getItem(position);
		ViewHolder tag = null;
		if (convertView == null)
		{
			convertView = mInflater.inflate(R.layout.photo_adapter_photolist,
					null);
			tag = new ViewHolder(convertView);
			convertView.setTag(tag);
		}
		else
		{
			tag = (ViewHolder) convertView.getTag();
		}

		tag.showPhotoInfo(info);

		return convertView;
	}

	private class ViewHolder
	{
		private AutoLoadImageView mImgIcon = null;

		public ViewHolder(View view)
		{
			mImgIcon = (AutoLoadImageView) view
					.findViewById(R.id.imageView_photo_adapterPhotoList_icon);
		}

		private void showPhotoInfo(PhotoInfo info)
		{
			mImgIcon.loadUrl(info.getWholeUrl());
		}
	}
}

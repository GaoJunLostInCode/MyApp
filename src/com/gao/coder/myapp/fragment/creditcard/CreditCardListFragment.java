package com.gao.coder.myapp.fragment.creditcard;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gao.coder.myapp.R;
import com.gao.coder.myapp.adapter.creditcard.CreditCardAdapter;
import com.gao.coder.myapp.database.creditcard.SQLiteOperate;
import com.gao.coder.myapp.database.creditcard.SQLiteOperateIml;
import com.gao.coder.myapp.model.creditcard.CreditCard;

/**
 * A placeholder fragment containing a simple view.
 */
public class CreditCardListFragment extends Fragment
{
	public interface ItemClickListener
	{
		public void onItemClicked(CreditCard card);
	}

	private List<CreditCard> mCreditCards = null;
	private CreditCardAdapter mAdapter = null;
	private ItemClickListener mListener = null;

	public CreditCardListFragment()
	{
	}

	public void setItemClickListener(ItemClickListener listener)
	{
		mListener = listener;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		mCreditCards = getAllCards();

		View rootView = inflater.inflate(R.layout.fragment_creditcard_list,
				container, false);
		ListView listView = (ListView) rootView
				.findViewById(R.id.listView_fragment_main);
		mAdapter = new CreditCardAdapter(getActivity().getApplicationContext(),
				mCreditCards);
		listView.setAdapter(mAdapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				CreditCard card = mCreditCards.get(position);
				if(null != mListener)
				{
					mListener.onItemClicked(card);
				}
			}

		});

		return rootView;
	}

	public void freshList()
	{
		mCreditCards = getAllCards();
		mAdapter.setCreditCards(mCreditCards);
		mAdapter.notifyDataSetChanged();
	}

	private List<CreditCard> getAllCards()
	{
		SQLiteOperate mSqLiteOperate = new SQLiteOperateIml(getActivity()
				.getApplicationContext());
		return mSqLiteOperate.listAllCreditCards();
	}
}

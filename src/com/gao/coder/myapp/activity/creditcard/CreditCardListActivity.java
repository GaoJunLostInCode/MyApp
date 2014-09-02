package com.gao.coder.myapp.activity.creditcard;

import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.gao.coder.myapp.R;
import com.gao.coder.myapp.database.creditcard.SQLiteOperate;
import com.gao.coder.myapp.database.creditcard.SQLiteOperateIml;
import com.gao.coder.myapp.fragment.creditcard.CreditCardDetailFragment;
import com.gao.coder.myapp.fragment.creditcard.CreditCardListFragment;
import com.gao.coder.myapp.fragment.creditcard.CreditCardListFragment.ItemClickListener;
import com.gao.coder.myapp.model.creditcard.CreditCard;

public class CreditCardListActivity extends CreditCardBaseActivity
{
	private CreditCardListFragment mFragmentList = null;
	private CreditCardDetailFragment mFragmentDetail = null;

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	static int count = 0;

	public void addCreditCard(View view)
	{
		CreditCard card = new CreditCard();
		card.setmBankName("xxxx" + count);
		count++;
		card.setmCardNum("8800");

		SQLiteOperate mSqLiteOperate = new SQLiteOperateIml(
				this.getApplicationContext());
		mSqLiteOperate.addCreditCard(card);

		mFragmentList.freshList();
	}
	

	@Override
	protected String title()
	{
		return "信用卡";
	}

	@Override
	protected View onCreateContentView()
	{
		LayoutInflater inflater = LayoutInflater.from(this);
		View view = inflater.inflate(R.layout.activity_creditcard_list, null);
		

		FragmentManager fragmentManager = getSupportFragmentManager();
		mFragmentList = (CreditCardListFragment) fragmentManager
				.findFragmentById(R.id.fragment_mainActivity_list);
		mFragmentList.setItemClickListener(new ItemClickListener()
		{
			@Override
			public void onItemClicked(CreditCard card)
			{
				mFragmentDetail.showCreditCard(card);
			}
		});
		
		mFragmentDetail = (CreditCardDetailFragment) fragmentManager
				.findFragmentById(R.id.fragment_mainActivity_detail);
		
		return view;
	}

}

package com.gao.coder.myapp.fragment.creditcard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gao.coder.myapp.R;
import com.gao.coder.myapp.model.creditcard.CreditCard;

public class CreditCardDetailFragment extends Fragment
{
	private CreditCard mCreditCard = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.fragment_creditcard_detail,
				null);

		return rootView;
	}

	public void showCreditCard(final CreditCard card)
	{
		mCreditCard = card;
	}
}

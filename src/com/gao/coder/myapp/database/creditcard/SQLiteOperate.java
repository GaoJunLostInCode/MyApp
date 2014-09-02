package com.gao.coder.myapp.database.creditcard;

import java.util.List;

import com.gao.coder.myapp.model.creditcard.CreditCard;

public interface SQLiteOperate
{
	public long addCreditCard(CreditCard card);
	public int deleteCreditCard(CreditCard card);
	public int updateCrditCard(CreditCard card);
	public List<CreditCard> listAllCreditCards();
}

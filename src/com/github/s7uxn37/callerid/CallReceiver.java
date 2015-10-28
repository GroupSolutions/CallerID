package com.github.s7uxn37.callerid;

import java.util.Date;

import android.content.Context;

public class CallReceiver extends PhonecallReceiver {
	public static boolean isEnabled = false;
	
	@Override
	protected void onIncomingCallStarted(Context context, String number, Date start) {
		SearchInterface.lookup(number, context);
	}
	
	@Override
	protected void onOutgoingCallStarted(Context context, String number, Date start) {
	}
	
	@Override
	protected void onIncomingCallEnded(Context context, String number, Date start, Date end) {
	}
	
	@Override
	protected void onOutgoingCallEnded(Context context, String number, Date start, Date end) {
	}
	
	@Override
	protected void onMissedCall(Context context, String number, Date start) {
	}
}

package com.github.s7uxn37.callerid;

import java.util.Date;

import android.content.Context;
import android.util.Log;

public class CallReceiver extends PhonecallReceiver {
	private static final String TAG = "CallerID:CallReceiver";
	public static boolean isEnabled = false;
	
	@Override
	protected void onIncomingCallStarted(Context context, String number, Date start) {
		SearchInterface.lookup(number, context);
		Log.d(TAG, "Detected Incoming Call started");
	}
	
	@Override
	protected void onOutgoingCallStarted(Context context, String number, Date start) {
		Log.d(TAG, "Detected Outgoing Call started");
	}
	
	@Override
	protected void onIncomingCallEnded(Context context, String number, Date start, Date end) {
		Log.d(TAG, "Detected Incoming Call ended");
	}
	
	@Override
	protected void onOutgoingCallEnded(Context context, String number, Date start, Date end) {
		Log.d(TAG, "Detected Outgoing Call ended");
	}
	
	@Override
	protected void onMissedCall(Context context, String number, Date start) {
		Log.d(TAG, "Detected Missed Call");
	}
}

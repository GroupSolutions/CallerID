package com.github.s7uxn37.callerid;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class SearchInterface {
	public static void lookup(final String number, final Context context) {
		// Instantiate the RequestQueue.
		RequestQueue queue = Volley.newRequestQueue(context);
		String url ="http://tel.search.ch/api/?was=" + number.replaceAll(" ", "");

		// Request a string response from the provided URL.
		StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
		            new Response.Listener<String>() {
		    @Override
		    public void onResponse(String anwser) {
		    	String response = anwser;
		    	response = response.replaceAll("\n", "").replaceAll("\r", "");
				response = afterSubstring(response, "<entry>");
				response = afterSubstring(response, "<title type=\"text\">");
				
				int closeIndex = response.indexOf("</title>");
				String name = response.substring(0, closeIndex);
				
				PendingIntent contentIntent = PendingIntent.getActivity(context, 0, new Intent(context,  InfoScreen.class), PendingIntent.FLAG_CANCEL_CURRENT);
				
				Notification noti = new Notification.Builder(context)
					.setSmallIcon(R.drawable.ic_launcher)
					.setContentTitle("CallerID")
					.setContentText(number + " -> " + name)
					.setContentIntent(contentIntent)
					.setAutoCancel(false)
					.setOngoing(true)
					.build();
				
				((NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE)).notify(1, noti);
		    }
		}, new Response.ErrorListener() {
		    @Override
		    public void onErrorResponse(VolleyError error) {
		    	
		    }
		});
		// Add the request to the RequestQueue.
		queue.add(stringRequest);
	}
	
	public static String afterSubstring(String str, String substr) {
		int index;
		if( (index = str.indexOf(substr)) != -1){
			return str.substring(index + substr.length());
		} else {
			return null;
		}
	}
}

package com.github.s7uxn37.callerid;

public class SearchInterface {
	public static void getName(String number, final Context context) {
		// Instantiate the RequestQueue.
		RequestQueue queue = Volley.newRequestQueue(this);
		String url ="http://tel.search.ch/api/?was=" + number.replaceAll(" ", "");

		// Request a string response from the provided URL.
		StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
		            new Response.Listener<String>() {
		    @Override
		    public void onResponse(String anwser) {
		    	String response = anwser.clone();
		    	response = response.replaceAll("\n", "").replaceAll("\r", "");
				response = afterSubstring(response, "<entry>");
				response = afterSubstring(response, "<title type=\"text\">");
				
				int closeIndex = response.firstIndexOf("</title>");
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
		if( (index = str.firstIndexOf(substr)) != -1){
			str = str.substring(index + substr.length);
		} else {
			return null;
		}
	}
}

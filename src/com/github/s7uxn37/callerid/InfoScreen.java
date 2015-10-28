package com.github.s7uxn37.callerid;

import android.app.Activity;
import android.app.Fragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

public class InfoScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_screen);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new InfoFragment())
                    .commit();
        }
        
        final Intent seeLicenseIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.license_link)));
        ((Button)findViewById(R.id.SeeLicense)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(seeLicenseIntent);
			}
		});
        
        ((ToggleButton)findViewById(R.id.Switch)).setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				CallReceiver.isEnabled = isChecked;
				if(isChecked) {
					PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, new Intent(getApplicationContext(),  InfoScreen.class), PendingIntent.FLAG_CANCEL_CURRENT);
					
					Notification noti = new Notification.Builder(getApplicationContext())
						.setSmallIcon(R.drawable.ic_launcher)
						.setContentTitle("CallerID")
						.setContentText("Waiting for call...")
						.setContentIntent(contentIntent)
						.setAutoCancel(false)
						.setOngoing(true)
						.build();
					((NotificationManager)getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE)).notify(1, noti);
				} else {
					((NotificationManager)getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE)).cancelAll();
				}
			}
		});
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.info_screen, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class InfoFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_info_screen, container, false);
            return rootView;
        }
    }
}

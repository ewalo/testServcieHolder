package com.example.testserviceholder;


import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;

public class TestService extends Service {

	private static final String TAG = "TestService";
	private NotificationManager _nm;
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		Log.e(TAG, "=============> TestService.onBind");
		return null;
	}

	public class LocalBinder extends Binder{
		TestService getService() {
			return TestService.this;
		}
	}
	
	@Override
	public boolean onUnbind(Intent i) {
		Log.e(TAG, "=============> TestService.onUnbind");
		return false;
	}
	
	@Override
	public void onRebind(Intent i) {
		Log.e(TAG, "=============> TestService.onRebind");
	}
	
	@Override
	public void onCreate() {
		Log.e(TAG, "=============> TestService.onCreate");
		_nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		showNotification();
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		Log.e(TAG, "=============> TestService.onStart");
	}
	
	@Override
	public void onDestroy() {
		_nm.cancel(R.string.Service_started);
		Log.e(TAG, "=============> TestService.onDestroy");
	}
	
	@SuppressWarnings("deprecation")
	private void showNotification() {
		Notification notification = new Notification(R.drawable.ic_launcher, 
		"Service started", System.currentTimeMillis());
		
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, TestServiceHolder.class),  0);
		
		notification.setLatestEventInfo(this, "Test Service", "Service started", contentIntent);
		
		_nm.notify(R.string.Service_started, notification);
				
				
	}
}

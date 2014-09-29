import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.app.Notification;
import android.app.NotificationManager;

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
		
	}
	
	@Override
	public void onCreate() {
		_nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		showNotification();
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		
	}
	
	@Override
	public void 
}

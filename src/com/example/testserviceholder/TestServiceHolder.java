package com.example.testserviceholder;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class TestServiceHolder extends Activity {

	private boolean _isBound;
	private TestService _boundService;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle("Service Test");
		
		initButtons();
	}

	private ServiceConnection _connection = new ServiceConnection() {
		public void onServiceConnected (ComponentName className, IBinder Service) {
			_boundService = ((TestService.LocalBinder)Service).getService();
			Toast.makeText(TestServiceHolder.this, "Service connected",
			Toast.LENGTH_SHORT).show();	
		}
		
		public void onServiceDisconnected(ComponentName className) {
			_boundService = null;
			Toast.makeText(TestServiceHolder.this, "Service disconnected",
			Toast.LENGTH_SHORT).show();
		}
	};
	
	private void initButtons() {
		Button buttonStart = (Button)findViewById(R.id.start_Service);
		buttonStart.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				startService();
			}
		});
		
		Button buttonStop = (Button)findViewById(R.id.stop_Service);
		buttonStop.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				stopService();
			}
		});
		
		Button buttonBind = (Button)findViewById(R.id.bind_Service);
		buttonBind.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				bindService();
			}
		});
		
		Button buttonUnbind = (Button)findViewById(R.id.unbind_Service);
		buttonUnbind.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				unbindService();
			}
		});
		
		
	}
	
	private void startService() {
		Intent i = new Intent(this, TestService.class);
		this.startService(i);
	}
	
	private void stopService() {
		Intent i = new Intent(this, TestService.class);
		this.stopService(i);
	}
	
	private void bindService() {
		Intent i = new Intent(this, TestService.class);
		bindService(i, _connection, Context.BIND_AUTO_CREATE);
		_isBound = true;
	}
	
	private void unbindService() {
		if (_isBound) {
			unbindService(_connection);
			_isBound = false;
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test_service_holder, menu);
		return true;
	}

}

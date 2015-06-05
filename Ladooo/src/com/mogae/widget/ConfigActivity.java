package com.mogae.widget;



import com.example.ladooo.R;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class ConfigActivity extends Activity implements OnClickListener {
	
	private int appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.configactivity);
		assignAppWidgetId();
		startWidget();
		findViewById(R.id.widgetStartButton).setOnClickListener(this);
	}

	private void assignAppWidgetId() {
		Bundle extras = getIntent().getExtras();
		if (extras != null)
			appWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
					AppWidgetManager.INVALID_APPWIDGET_ID);
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
	
	private void startWidget() {

		// this intent is essential to show the widget
		// if this intent is not included,you can't show
		// widget on homescreen
		Intent intent = new Intent();
		intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
		setResult(Activity.RESULT_OK, intent);

		// start your service
		// to fetch data from web
		Intent serviceIntent = new Intent(this, RemoteFetchService.class);
		serviceIntent
				.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
		startService(serviceIntent);
		System.out.println(">>>>>>>>>>>> configuratin acti");
		// finish this activity
		this.finish();

	}


}

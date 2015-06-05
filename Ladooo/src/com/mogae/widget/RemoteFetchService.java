package com.mogae.widget;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.IBinder;

public class RemoteFetchService extends Service {
	
	private int appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
	private AQuery aQuery;
	private String remoteJsonUrl = "http://admin.appnext.com/offerWallApi.aspx?id=4d980772-4b4e-4e87-ba75-3808dfbd2289&cnt=5&type=json";
	public static ArrayList<OffersItem> offerItemList;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (intent.hasExtra(AppWidgetManager.EXTRA_APPWIDGET_ID))
			appWidgetId = intent.getIntExtra(
					AppWidgetManager.EXTRA_APPWIDGET_ID,
					AppWidgetManager.INVALID_APPWIDGET_ID);
		aQuery = new AQuery(getBaseContext());
		fetchDataFromWeb();
		return super.onStartCommand(intent, flags, startId);
	}
	
	private void fetchDataFromWeb() {
		// TODO Auto-generated method stub
		aQuery.ajax(remoteJsonUrl, String.class, new AjaxCallback<String>(){
			
			@Override
			public void callback(String url, String object, AjaxStatus status) {
				// TODO Auto-generated method stub
				processResult(result);
				super.callback(url, object, status);
			}
			
		});
	}
	
protected void processResult(String result) {
		
		offerItemList = new ArrayList<OffersItem>();
		
		try {
			JSONObject jsonObj = new JSONObject(result);
			
			JSONArray jsonArray = jsonObj.getJSONArray("apps");
			int length = jsonArray.length();
			
			for( int i = 0; i < length; i++){
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				
				OffersItem offersItem = new OffersItem();
				offersItem.title = jsonObject.getString("title");
				offersItem.desc = jsonObject.getString("desc");
				offersItem.urlImg = jsonObject.getString("urlImg");
				offersItem.urlApp = jsonObject.getString("urlApp");
				offersItem.androidPackage = jsonObject.getString("androidPackage");
				offersItem.revenueType = jsonObject.getString("revenueType");
				offersItem.revenueRate = jsonObject.getString("revenueRate");
				
				offerItemList.add(offersItem);
				
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		populateWidget();
		
	}

private void populateWidget() {
	
	Intent widgetUpdateIntent = new Intent();
	widgetUpdateIntent.setAction(WidgetProvider.DATA_FETCHED);
	widgetUpdateIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
	
	sendBroadcast(widgetUpdateIntent);
	this.stopSelf();
}

}

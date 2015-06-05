package com.mogae.widget;



import com.example.ladooo.R;


import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RemoteViews;

public class WidgetProvider extends AppWidgetProvider {
	
	public static final String DATA_FETCHED = "com.mogae.widget.DATA_FETCHED";
	public final static String ITEM_CLICK_ACTION = "ItemClick";
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		final int N = appWidgetIds.length;
		for (int i = 0; i < N; i++) {
			Intent serviceIntent = new Intent(context, RemoteFetchService.class);
			serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
					appWidgetIds[i]);
			//context.startService(serviceIntent);
			
			RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            rv.setRemoteAdapter(R.id.listViewWidget, serviceIntent);
            
            Intent itemClickIntent = new Intent(context, WidgetProvider.class);
            itemClickIntent.setAction(ITEM_CLICK_ACTION);
            itemClickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds[i]);
            PendingIntent itemClickPendingIntent = PendingIntent.getBroadcast(context, 0, itemClickIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            rv.setPendingIntentTemplate(R.id.listViewWidget, itemClickPendingIntent);
            appWidgetManager.updateAppWidget(appWidgetIds[i], rv);
		}
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}

	private RemoteViews updateWidgetListView(Context context, int appWidgetId) {

		// which layout to show on widget
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
				R.layout.widget_layout);

		// RemoteViews Service needed to provide adapter for ListView
		Intent svcIntent = new Intent(context, WidgetService.class);
		// passing app widget id to that RemoteViews Service
		svcIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
		// setting a unique Uri to the intent
		// don't know its purpose to me right now
		svcIntent.setData(Uri.parse(svcIntent.toUri(Intent.URI_INTENT_SCHEME)));
		// setting adapter to listview of the widget
		remoteViews.setRemoteAdapter(appWidgetId, R.id.listViewWidget,
				svcIntent);
		// setting an empty view in case of no data
		remoteViews.setEmptyView(R.id.listViewWidget, R.id.empty_view);
		return remoteViews;
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);
		if (intent.getAction().equals(DATA_FETCHED)) {
			int appWidgetId = intent.getIntExtra(
					AppWidgetManager.EXTRA_APPWIDGET_ID,
					AppWidgetManager.INVALID_APPWIDGET_ID);
			AppWidgetManager appWidgetManager = AppWidgetManager
					.getInstance(context);
			RemoteViews remoteViews = updateWidgetListView(context, appWidgetId);
			appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
		} 
		else if(intent.getAction().equals(ITEM_CLICK_ACTION)) {
            /*Intent actIntent = new Intent();
            actIntent.setClass(context,NewsContent .class); 
            actIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Bundle extras = new Bundle();
            extras.putString("text", intent.getExtras().getString("text"));
            extras.putString("newId", intent.getExtras().getString("pos"));
            actIntent.putExtras(extras);
            context.startActivity(actIntent); */           
        }

	}

	
}

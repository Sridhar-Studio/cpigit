package com.mogae.widget;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.example.ladooo.R;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService.RemoteViewsFactory;

public class ListProvider implements RemoteViewsFactory {
	
	private ArrayList<OffersItem> offersItemList = new ArrayList<OffersItem>();
	private Context context = null;
	private int appWidgetId;
	
	public ListProvider(Context context, Intent intent) {
		// TODO Auto-generated constructor stub
		
		this.context = context;
		appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
				AppWidgetManager.INVALID_APPWIDGET_ID);

		populateListItem();
		
		//populateNewsItem();
	}
	
	private void populateListItem() {
		if(RemoteFetchService.offerItemList !=null )
			offersItemList = (ArrayList<OffersItem>) RemoteFetchService.offerItemList
					.clone();
			else
				offersItemList = new ArrayList<OffersItem>();
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return offersItemList.size();
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public RemoteViews getLoadingView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RemoteViews getViewAt(int position) {
		
		final RemoteViews remoteView = new RemoteViews(
				context.getPackageName(), com.example.ladooo.R.layout.list_row);
		
		OffersItem offersItem = offersItemList.get(position);
		remoteView.setTextViewText(R.id.txtTime, offersItem.title);
		remoteView.setTextViewText(R.id.txtTitle, offersItem.desc);
		String newId=offersItem.idx;
		
		String imgPicasso = offersItem.urlImg;
		Bitmap bitmap;
		try {
			bitmap = BitmapFactory.decodeStream((InputStream)new URL(imgPicasso).getContent());
			
			Matrix mat = new Matrix();
	        mat.postRotate(90);
	        Bitmap bMapRotate = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), mat, true);
           
          // Bitmap rotatedBitmap = Bitmap.createBitmap(newWidth, newHeight, original.getConfig());
            
			remoteView.setImageViewBitmap((R.id.imgUrl), bMapRotate);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Bundle extras = new Bundle();
        extras.putString("text", "Item " + position + " is clicked");
        
        extras.putString("pos", String.valueOf(newId));
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);
        remoteView.setOnClickFillInIntent(R.id.item, fillInIntent);
		return remoteView;
	}

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDataSetChanged() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		
	}

}

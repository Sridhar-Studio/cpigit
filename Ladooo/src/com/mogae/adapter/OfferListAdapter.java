package com.mogae.adapter;

import java.io.InputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ladooo.R;
import com.mogae.model.OfferListEntity;

public class OfferListAdapter extends ArrayAdapter<OfferListEntity> {
	 ArrayList<OfferListEntity> actorsList;
	 LayoutInflater vi;
	 int Resource;
	 ViewHolder holder;
	
		// Buttons
		
		Activity a;
	 public OfferListAdapter(Context context, int resource, ArrayList<OfferListEntity> objects,Activity a) {
	  super(context, resource, objects);
	  this.a=a;
	  vi = (LayoutInflater) context
	    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	  Resource = resource;
	  actorsList = objects;
	 }
	 
	 
	 @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	   // convert view = design
	   View v = convertView;
	   if (v == null) {
	    holder = new ViewHolder();
	    v = vi.inflate(Resource, null);
	    
	    holder.offerMoney = (TextView) v.findViewById(R.id.cur);
	    
	    v.setTag(holder);
	   } else {
	    holder = (ViewHolder) v.getTag();
	   }
	   
	  
	 /* String Money=offerList.get(position).getOfferMoney();
	   Double i=Double.parseDouble(Money);
	  Double d=i*60/2;
	 Integer s=d.intValue();
	*/
	   holder.offerMoney.setText(actorsList.get(position).getOfferMoney());
	   return v;
	   
	  }
	 
	 

	static class ViewHolder {
	  
	  public TextView offerMoney;
	 
	 }

	 private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
	  ImageView bmImage;

	  public DownloadImageTask(ImageView bmImage) {
	   this.bmImage = bmImage;
	  }

	  protected Bitmap doInBackground(String... urls) {
	   String urldisplay = urls[0];
	   Bitmap mIcon11 = null;
	   try {
	    InputStream in = new java.net.URL(urldisplay).openStream();
	    mIcon11 = BitmapFactory.decodeStream(in);
	   } catch (Exception e) {
	    Log.e("Error", e.getMessage());
	    e.printStackTrace();
	   }
	   return mIcon11;
	  }

	  protected void onPostExecute(Bitmap result) {
	   bmImage.setImageBitmap(result);
	  }

	 }
}

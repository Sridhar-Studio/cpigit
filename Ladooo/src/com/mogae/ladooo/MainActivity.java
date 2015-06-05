package com.mogae.ladooo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.ladooo.R;


public class MainActivity extends Activity  {

	private static boolean splashLoaded = false;
	private ProgressDialog pDialog;
	private static String url = "http://admin.appnext.com/offerWallApi.aspx?id=3a10c40a-b6f2-46a1-85c4-8d9d571c58cb&cnt=5&type=json";
	private static String url1 ="http://192.168.10.101:8080/ladoo/offersList/getOffersList";
	private static final String TAG_DATA = "apps";
	private static final String TAG_DATA1 = "data";
	private static final String TAG_MONEY2 = "offerMoney";
	private static final String TAG_DISCRIPTION = "desc";
	private static final String TAG_MONEY = "revenueRate";
	private static final String TAG_IMAGE = "urlImg";
	private static final String TAG_APPURL = "urlApp";
	
	
	
	
	// contacts JSONArray
	JSONArray data,data1 = null;
	Handler mHandler;
	Context context;

	ArrayList<HashMap<String, String>> dataList;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);	
	
		if (!splashLoaded) {
			setContentView(R.layout.activity_main);
			splashLoaded = true;

			int secondsDelayed = 1;
			new Handler().postDelayed(new Runnable() {
				public void run() {
					try {

						ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
						NetworkInfo wifiNetwork = connectivityManager
								.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

						NetworkInfo mobileNetwork = connectivityManager
								.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

						if (wifiNetwork != null && wifiNetwork.isConnected()
								|| mobileNetwork != null
								&& mobileNetwork.isConnected()) {
							Toast.makeText(getApplicationContext(),
									"Connected", Toast.LENGTH_LONG).show();
							startActivity(new Intent(MainActivity.this,
									OfferWall.class));

						} else {
							showAlertDialog(MainActivity.this,
									"No Internet Connection",
									"You don't have internet connection.",
									false);
						}

					}

					catch (Exception e) {

						showAlertDialog(MainActivity.this,
								"No Internet Connection",
								"You don't have internet connection.", false);

						System.out.println("Status" + e.getMessage());
					}

				}

			}, secondsDelayed * 2000);
		}
		new GetContacts().execute();

	}
	
	
	
	

	public void showAlertDialog(Context context, String title, String message,
			Boolean status) {
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();

		// Setting Dialog Title
		alertDialog.setTitle(title);

		// Setting Dialog Message
		alertDialog.setMessage(message);

		// Setting alert dialog icon
		alertDialog.setIcon(R.drawable.ic_launcher);

		// Setting OK Button
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {

				finish();
			}
		});

		alertDialog.show();
	}
	
		
		/*private OnClickListener mCorkyListener = new OnClickListener() {
		    public void onClick(View v) {
	            MonetizationManager.showAd(MainActivity.this, NativeXAdPlacement.Game_Launch);
		    }
		};
*/
		
	
	
	
	private class GetContacts extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			pDialog = new ProgressDialog(MainActivity.this);
			pDialog.setMessage("Please wait...");
			pDialog.setCancelable(false);
			pDialog.setCanceledOnTouchOutside(true);
			pDialog.show();

			long delayInMillis = 2000;
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					pDialog.dismiss();
				}
			}, delayInMillis);

		}

		@Override
		protected Void doInBackground(Void... arg0) {
			// Creating service handler class instance
			ServiceHandler sh = new ServiceHandler();

			// Making a request to url and getting response
			String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
			String jsonStr1 = sh.makeServiceCall(url1, ServiceHandler.GET);

			Log.d("Response: ", "> " + jsonStr);
			Log.d("Response: ", "> " + jsonStr1);

			if (jsonStr != null && jsonStr1!=null) {
				try {
					JSONObject jsonObj = new JSONObject(jsonStr);

					// Getting JSON Array node
					data = jsonObj.getJSONArray(TAG_DATA);
					data = jsonObj.getJSONArray(TAG_DATA1);
					System.out.println("???>>>>>>>???????///"+data);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				Log.e("ServiceHandler", "Couldn't get any data from the url");

			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// Dismiss the progress dialog
			if (pDialog.isShowing())
				pDialog.dismiss();
		}
	}
	

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}*/

	/*@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}*/
}

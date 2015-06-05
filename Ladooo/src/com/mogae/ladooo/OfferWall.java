package com.mogae.ladooo;


import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ladooo.R;
import com.mogae.adapter.OfferListAdapter;
import com.mogae.adapter.OfferWallAdapter;
import com.mogae.model.OfferWallEntity;

import de.timroes.swipetodismiss.SwipeDismissList;

@SuppressLint("NewApi")
public class OfferWall extends ListActivity{
	ArrayList<OfferWallEntity> actorsList;
	//ArrayList<OfferListEntity> actorsList1;

	
	 OfferWallAdapter adapter;
	 OfferListAdapter adapter1;
	 Context context;
	 ListView listview;
		String YourApiKey = "aakanshapnd";
		String SecretKey = "l4hOt8Sb";
		String LOCALYTICS_APP_KEY ="f470fd6125a60d95a6188da-d42cf414-e8cd-11e4-b230-009c5fda0a25";
		  private final static String PLAYER_LEVEL = "Player Level";
			String MY_FLURRY_APIKEY ="ZQNCTP55MDWGMS756FB6";
		    private String adSpaceName = "Win Gold";
		  //  private FlurryAdInterstitial mFlurryAdInterstitial = null;
		    private Button displayAd;




		
	// private ArrayAdapter<String> mAdapter;
		/**
		 * The {@link SwipeDismissList} you generate during your
		 * {@link #onCreate(android.os.Bundle)} method.
		 */
		private SwipeDismissList mSwipeList;
		/**
		 * The key of the {@link Bundle} extra we store the mode of the list the
		 * user selected.
		 */
		private final static String EXTRA_MODE = "MODE";
		
		
		
		
		
		
		
	 @Override
	 public void onResume(){
	     super.onResume();
	     // put your code here...

	 }
	 
	
	        
	        /*//  displayAd = (Button)findViewById(R.id.display);
	        mFlurryAdInterstitial = new FlurryAdInterstitial(this, adSpaceName);
	        mFlurryAdInterstitial.fetchAd();
	        mFlurryAdInterstitial.displayAd();
	       
	        */
	        
	              
	        
	        /**
	         * Invoke a takeover at a natural pause in your app. For example, when a
	         * level is completed, an article is read or a button is pressed. Here we
	         * mock the display of a takeover when a button is pressed.
	         */ 
	        
	   

	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  context= this;
	  
	 


	  
	  
    /*  System.out.println("Session Time Out Interval....."+	      Localytics.getSessionTimeoutInterval());
   //   System.out.println("App Key is :::::::::::::::::::::::::::::::::::::::::::::::::::::::");

	
	  getApplication().registerActivityLifecycleCallbacks(new LocalyticsActivityLifecycleCallbacks(getApplicationContext(), LOCALYTICS_APP_KEY));
	  
	 
	  
	  List custom_dimensions = new ArrayList();
	custom_dimensions.add("Trial");
	   */
	 
	   
	  //Events
	/*  Map  values = new HashMap ();
	  values.put(PLAYER_LEVEL, String.valueOf(this.PLAYER_LEVEL ));
	 
	   */
	  setContentView(R.layout.activity_list_view1);
	  
	  ActionBar actionBar = getActionBar();
	 // actionBar.setIcon(R.drawable.menu);
		// getActionBar().setDisplayShowTitleEnabled(false);
		 
		   /*
			Apsalar.setFBAppId("411699832323109");
			Apsalar.startSession(context,YourApiKey,SecretKey);
			Apsalar.event("Completed....");
			
			Apsalar.event("__iap__",
					   "ps", "MyAccountName",
					   "pk", "MyProductSKU",
					   "pn", "MyProductName",
					   "pc", "MyProductCategory",
					   "pcc", "EUR",
					   "pq", 2,
					   "pp", 123.45,
					   "r", 296.28);
			try {
				  JSONArray contents = new JSONArray();
				  JSONObject item1 = new JSONObject();

				  item1.put("sku", "UPC-018627610014");
				  item1.put("qty",				  contents.put(item2);

				  JSONObject item3 = new JSONObject(); 2);
				  item1.put("unit_price", 8.99);
				  item1.put("currency", "USD");
				  contents.put(item1);

				  JSONObject item2 = new JSONObject();
				  item2.put("sku", "UPC-070271003758");
				  item2.put("qty", 1);
				  item2.put("unit_price", 15.99);
				  item2.put("currency", "USD");

				  item3.put("sku", "UPC-070271003758");
				  item3.put("qty", 1);
				  item3.put("unit_price", 15.99);
				  item3.put("currency", "USD");
				  contents.put(item3);

				  JSONObject args = new JSONObject();
				  args.put("contents", contents);
				  args.put("total", 63.96);
				  args.put("currency", "USD");
				  args.put("member_id", "A556740089");

				  // Record the event with Apsalar
				  Apsalar.eventJSON("Purchase_Complete", args);
				}
				catch(JSONException e) {
				  android.util.Log.e("Now", "JSON Exception in cart");
				}
			
			Apsalar.endSession();
	  */
	  
	  
	  actorsList = new ArrayList<OfferWallEntity>();
	 // actorsList1=new ArrayList<OfferListEntity>();
	  new JSONAsyncTask().execute();
	  
     // ListView listview = (ListView)findViewById(R.id.list);
	 listview=getListView();
	  
	  adapter = new OfferWallAdapter(getApplicationContext(), R.layout.new_list_view, actorsList,this);
	//  adapter1 = new OfferListAdapter(getApplicationContext(), R.layout.new_list_view, actorsList1,this);

	 // listview.setAdapter(adapter);
	  int modeInt = getIntent().getIntExtra("MODE", 0);
		SwipeDismissList.UndoMode mode = SwipeDismissList.UndoMode.values()[modeInt];

		

		mSwipeList = new SwipeDismissList(
				listview,
			
			new SwipeDismissList.OnDismissCallback() {
			
			public SwipeDismissList.Undoable onDismiss(AbsListView listView, final int position) {

				// Get item that should be deleted from the adapter.
				final OfferWallEntity item = adapter.getItem(position);
				// Delete that item from the adapter.
				adapter.remove(item);

				
				return new SwipeDismissList.Undoable() {
					
					@Override
					public String getTitle() {
						return item + " deleted";
					}

					
					@Override
					public void undo() {
						// Reinsert the item at its previous position.
						adapter.insert(item, position);
					}

					@Override
					public void discard() {
						// Just write a log message (use logcat to see the effect)
						Log.w("DISCARD", "item " + item + " now finally discarded");
					}
				};

			}
		},
			mode);

		if (mode == SwipeDismissList.UndoMode.MULTI_UNDO) {
			mSwipeList.setUndoMultipleString(null);
		}

		// Just reset the adapter.
	//	resetAdapter();
		 //listview.setAdapter(adapter);
		// SeparatedListAdapter adapter = new SeparatedListAdapter(this);
		 listview.setAdapter(adapter);

	 /* listview.setOnItemClickListener(new OnItemClickListener() {

	   @Override
	   public void onItemClick(AdapterView<?> arg0, View arg1, int position,
	     long id) {
	    // TODO Auto-generated method stub
	    Intent myIntent = new Intent(context, OfferDescription.class);
	    
	    myIntent.putExtra("offerName",actorsList.get(position).getOfferName());
	    myIntent.putExtra("offerImage",actorsList.get(position).getOfferImage());
	    myIntent.putExtra("offerMoney",actorsList.get(position).getOfferMoney());
	    //myIntent.putExtra("dob", actorsList.get(position).getDob());
	    System.out.println("::::::::::::::"+myIntent.putExtra("dob", actorsList.get(position).getOfferMoney()));
	    //System.out.println(dob);
	    startActivity(myIntent);
	   }
	  });*/
	  
	 }


	 class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {
	  
	  ProgressDialog dialog;
	  
	  @Override
	  protected void onPreExecute() {
	   super.onPreExecute();
	   dialog = new ProgressDialog(OfferWall.this);
	   dialog.setMessage("Loading, please wait");
	   dialog.setTitle("Connecting server");
	   dialog.show();
	   dialog.setCancelable(false);
	  }
	  
	  @Override
	  protected Boolean doInBackground(String... urls) {
	   try {
	    
	    //------------------>>
	    HttpGet httppost = new HttpGet("http://admin.appnext.com/offerWallApi.aspx?id=4d980772-4b4e-4e87-ba75-3808dfbd2289&cnt=5&type=json");
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpResponse response = httpclient.execute(httppost);
	    

	    // StatusLine stat = response.getStatusLine();
	    int status = response.getStatusLine().getStatusCode();

	    if (status == 200) {
	     HttpEntity entity = response.getEntity();
	     String data = EntityUtils.toString(entity);
	     
	    
	     JSONObject jsono = new JSONObject(data);
	     JSONArray jarray = jsono.getJSONArray("apps");
	     System.out.println("Json Array is::::::"+jarray);
	     
	     
	     
	     for (int i = 0; i < jarray.length(); i++) {
	    	 JSONObject object = jarray.getJSONObject(i);
		     
		      OfferWallEntity actor = new OfferWallEntity();
		      actor.setUrlImg(object.getString("urlImg"));
		      actor.setDesc(object.getString("desc"));
		     /* actor.setRevenueRate(object.getString("revenueRate"));*/
		      actor.setUrlApp(object.getString("urlApp"));

		      
		      actorsList.add(actor);
	      
	      
	     }
	     return true;
	    }
	    
	    //------------------>>
	    
	   } catch (ParseException e1) {
	    e1.printStackTrace();
	   } catch (IOException e) {
	    e.printStackTrace();
	   } catch (JSONException e) {
	    e.printStackTrace();
	   }
	   try {
		    
		    //------------------>>
		    HttpGet httppost = new HttpGet("192.168.10.101:8080/ladoo/offersList/getOffersList");
		    HttpClient httpclient = new DefaultHttpClient();
		    HttpResponse response = httpclient.execute(httppost);
		    

		    // StatusLine stat = response.getStatusLine();
		    int status = response.getStatusLine().getStatusCode();

		    if (status == 200) {
		     HttpEntity entity = response.getEntity();
		     String data = EntityUtils.toString(entity);
		     
		    
		     JSONObject jsono = new JSONObject(data);
		     JSONArray jarray = jsono.getJSONArray("data");
		     System.out.println("Json Array is::::::"+jarray);
		     
		     
		     
		     for (int i = 0; i < jarray.length(); i++) {
		      JSONObject object = jarray.getJSONObject(i);
		     
		      OfferWallEntity actor = new OfferWallEntity();
		      actor.setOfferMoney(object.getString("offerMoney"));
System.out.println("????????????"+object.getString("offerMoney"));
		      
		      actorsList.add(actor);

		     }
		     return true;
		    }
		    
		    //------------------>>
		    
		   } catch (ParseException e1) {
		    e1.printStackTrace();
		   } catch (IOException e) {
		    e.printStackTrace();
		   } catch (JSONException e) {
		    e.printStackTrace();
		   }
	   return false;
	  }
	  
	  
	  protected void onPostExecute(Boolean result) {
	   dialog.cancel();
	   adapter.notifyDataSetChanged();
	   if(result == false)
	    Toast.makeText(getApplicationContext(), "Unable to fetch data from server", Toast.LENGTH_LONG).show();

	  }
	  
	
	 }
	 
	 @Override
	    public boolean onOptionsItemSelected(MenuItem item) {

	        int id = item.getItemId();
	        switch (id) {
	            case R.id.action_call:
	                Intent dialer= new Intent(Intent.ACTION_DIAL);
	                startActivity(dialer);
	                return true;
	            /*case R.id.action_speech:
	                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
	                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
	                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
	                startActivityForResult(intent, 1234);l

	                return true;*/
	          
	            default:
	                return super.onOptionsItemSelected(item);
	        }
	    }
	 @Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.main, menu);
			return true;
		}

	    @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        if (requestCode == 1234 && resultCode == RESULT_OK) {
	            String voice_text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).get(0);
	            Toast.makeText(getApplicationContext(),voice_text,Toast.LENGTH_LONG).show();

	        }
	    }
	}
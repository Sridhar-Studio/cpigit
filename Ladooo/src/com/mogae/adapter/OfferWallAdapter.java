package com.mogae.adapter;

import java.io.InputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ladooo.R;
import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;
import com.mogae.ladooo.FacebookShare;
import com.mogae.model.OfferWallEntity;

public class OfferWallAdapter extends ArrayAdapter<OfferWallEntity>{
	 ArrayList<OfferWallEntity> actorList1;
	 LayoutInflater vi;
	 int Resource;
	 ViewHolder holder;
	 private static String APP_ID = "411699832323109"; // Replace with your App ID
		


		// Instance of Facebook Class
		private Facebook facebook = new Facebook(APP_ID);
		private AsyncFacebookRunner mAsyncRunner;
		String FILENAME = "AndroidSSO_data";
		private SharedPreferences mPrefs;

		// Buttons
		Button btnFbLogin;
		Button btnFbGetProfile;
		Button btnPostToWall;
		Button btnShowAccessTokens;
		Activity a;
	 
	 
	 
	 

	 public OfferWallAdapter(Context context, int resource, ArrayList<OfferWallEntity> objects,Activity a) {
	  super(context, resource, objects);
	  this.a=a;
	  vi = (LayoutInflater) context
	    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	  Resource = resource;
	  actorList1 = objects;
	 }
	 
	 
	 @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	   // convert view = design
	   View v = convertView;
	   if (v == null) {
	    holder = new ViewHolder();
	    v = vi.inflate(Resource, null);
	    holder.offerImage = (ImageView) v.findViewById(R.id.images);
	    holder.offerName = (TextView) v.findViewById(R.id.txt);
	    holder.offerMoney = (TextView) v.findViewById(R.id.cur);
	    holder.offerShare = (ImageView) v.findViewById(R.id.images1);
	    holder.offerShare1 = (ImageView) v.findViewById(R.id.facebook);
	    v.setTag(holder);
	   } else {
	    holder = (ViewHolder) v.getTag();
	   }
	   holder.offerImage.setImageResource(R.drawable.ic_launcher);
	   new DownloadImageTask(holder.offerImage).execute(actorList1.get(position).getUrlImg());
	   holder.offerName.setText(actorList1.get(position).getDesc());
	   final String url=actorList1.get(position).getUrlApp();

	   holder.offerMoney.setText(actorList1.get(position).getOfferMoney());
	   
		  System.out.println("}}}}}}}}}}}}"+url);
	   holder.offerShare1.setImageResource(R.drawable.fb_ico);
	   holder.offerShare1.setId(position);
	   holder.offerImage.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			 Uri uri = Uri.parse(url);
			 System.out.println("/////////"+uri);
			  /* Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			   intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			  getContext().startActivity(intent);*/
			  Intent i = new Intent();
              i.putExtra(Intent.EXTRA_TEXT, uri);
              i.setAction(Intent.ACTION_VIEW);
              i.setData(Uri.parse(url));
              i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
              getContext().startActivity(i);
		}
	});
	   holder.offerShare1.setOnClickListener(new OnClickListener() {
	  
	  @Override
	  public void onClick(View v) {
	   facebook.dialog(a, "feed", new DialogListener() {


	          @Override

	          public void onFacebookError(FacebookError e) {

	          }


	          @Override

	          public void onError(DialogError e) {

	          }


	          @Override

	          public void onComplete(Bundle values) {

	          }


	          @Override

	          public void onCancel() {

	          }

	      });
	   
	   
	  /* 
	   Intent shareIntent = new Intent(Intent.ACTION_SEND);
	     shareIntent.setType("text/plain");
	     shareIntent.putExtra(Intent.EXTRA_TEXT, "https://www.facebook.com/sharer/sharer.php?u=");
	     shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	     getContext().startActivity(shareIntent);
	   */
	   
	   
	   

	  }
	 });
	   
	   
	   
	   
	   
	   
	   
	   holder.offerShare.setImageResource(R.drawable.twitter);
	   holder.offerShare.setId(position);
	   holder.offerShare.setOnClickListener(new OnClickListener() {
	 FacebookShare fb=new FacebookShare();

	  public void onClick(View v) {
	   

	   String message = "Your message to post";
	          try {
	               
	           Intent sharingIntent = new Intent(Intent.ACTION_SEND);
	              sharingIntent.setClassName("com.twitter.android","com.twitter.android.composer.ComposerActivity");

	              String sAux = "\nLet me recommend you this application\n\n";
	           sAux = sAux + "https://play.google.com/store/ \n\n";
	           sharingIntent.putExtra(Intent.EXTRA_TEXT, sAux);
	           sharingIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	              getContext().startActivity(sharingIntent);
	          } catch (Exception e) {
	              Log.e("In Exception", "Comes here");
	              Intent i = new Intent();
	              i.putExtra(Intent.EXTRA_TEXT, message);
	              i.setAction(Intent.ACTION_VIEW);
	              i.setData(Uri.parse("https://mobile.twitter.com/compose/tweet"));
	              i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	              getContext().startActivity(i);
	          }
	   
	  }
	   
	  
	  /* 
	   final ComponentName name = new ComponentName("com.whatsapp", "com.whatsapp.ContactPicker");
	   Intent oShareIntent = new Intent();
	   oShareIntent.setComponent(name);
	   oShareIntent.setType("text/plain");
	    String sAux = "\nLet me recommend you this application\n\n";
	           sAux = sAux + "https://play.google.com/ \n\n";
	   oShareIntent.putExtra(android.content.Intent.EXTRA_TEXT, sAux);
	   oShareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	   getContext().startActivity(oShareIntent);
	  }*/
	   });
	     return v;
	   
	   
	   /*Intent f = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/TheHoustonBusinessCoach"));
	   f.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	   getContext().startActivity(F);
	   */
	   /*Intent i =new Intent(getContext(),FacebookShare.class);
	   //i.putExtra("offershare", id);
	   i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	   getContext().startActivity(i);*/
	   // TODO Auto-generated method stub
	   //Toast.makeText(getContext(), "clicked", Toast.LENGTH_LONG).show();
	   /*int id = v.getId();
	   Intent i =new Intent(getContext(),FacebookShare.class);
	   i.putExtra("offershare", id);
	   i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	   getContext().startActivity(i);*/
	   
	  
	 
	 

	  }
	 
	 public void loginToFacebook() {

		 mPrefs = a.getPreferences(a.MODE_PRIVATE);
			String access_token = mPrefs.getString("access_token", "CAAF2cFZACfCUBAG5C4WDK12ldyeOueXgWZCZCVfwZA8cWcRDHVlZCOZBkn8KWzw1UpD8ZASRZCKx88L8Cz0WrGMLy2INxqroqbbqLluJ48Va5q8RCb8Or4KHXl8bya2iEw0mADhyOOaKFAlrD1ogYZAWJZA6ZAmwjawL8Ac2GCOr8Aqp5SppywCfsCiLeW5W4U1p11kJm490fzqARobXg0jz08vQ2F86AyyLPG8DTYEGOkVYyJKC3ipZCQZB1");
			System.out.println("access_token"+ access_token);
			long expires = mPrefs.getLong("access_expires", 0);

			if (access_token != null) {
				facebook.setAccessToken(access_token);
				
			

				Log.d("FB Sessions", "" + facebook.isSessionValid());
			}

			if (expires != 0) {
				facebook.setAccessExpires(expires);
			}

			if (!facebook.isSessionValid()) {
				facebook.authorize(a,
						new String[] { "email", "publish_stream" },
						new DialogListener() {
					
					

							@Override
							public void onCancel() {
								// Function to handle cancel event
							}

							@Override
							public void onComplete(Bundle values) {
								// Function to handle complete event
								// Edit Preferences and update facebook acess_token
								SharedPreferences.Editor editor = mPrefs.edit();
								editor.putString("access_token",
										"CAAF2cFZACfCUBAG5C4WDK12ldyeOueXgWZCZCVfwZA8cWcRDHVlZCOZBkn8KWzw1UpD8ZASRZCKx88L8Cz0WrGMLy2INxqroqbbqLluJ48Va5q8RCb8Or4KHXl8bya2iEw0mADhyOOaKFAlrD1ogYZAWJZA6ZAmwjawL8Ac2GCOr8Aqp5SppywCfsCiLeW5W4U1p11kJm490fzqARobXg0jz08vQ2F86AyyLPG8DTYEGOkVYyJKC3ipZCQZB1");
								editor.putLong("access_expires",
										facebook.getAccessExpires());
								editor.commit();
postToWall();
							}

							@Override
							public void onError(DialogError error) {
								// Function to handle error

							}

							@Override
							public void onFacebookError(FacebookError fberror) {
								// Function to handle Facebook errors

							}

						});
			}
		}
	 
	 
	 public void postToWall() {
			// post on user's wall.
			facebook.dialog(getContext(), "feed", new DialogListener() {

				@Override
				public void onFacebookError(FacebookError e) {
				}

				@Override
				public void onError(DialogError e) {
				}

				@Override
				public void onComplete(Bundle values) {
				}

				@Override
				public void onCancel() {
				}
			});

		}

	 

	 
	/* 
	 public void loginToFacebook() {

			mPrefs = a.getPreferences(a.MODE_PRIVATE);
			postToWall();
			String access_token = mPrefs.getString("access_token", null);
			long expires = mPrefs.getLong("access_expires", 0);

			if (access_token != null) {
				facebook.setAccessToken(access_token);
				
				btnFbLogin.setVisibility(View.INVISIBLE);
				
				// Making get profile button visible
				btnFbGetProfile.setVisibility(View.VISIBLE);

				// Making post to wall visible
				btnPostToWall.setVisibility(View.VISIBLE);

				// Making show access tokens button visible
				btnShowAccessTokens.setVisibility(View.VISIBLE);

				Log.d("FB Sessions", "" + facebook.isSessionValid());
			}

			if (expires != 0) {
				facebook.setAccessExpires(expires);
			}

			if (!facebook.isSessionValid()) {
				facebook.authorize(a,
						new String[] { "publish_stream" },
						new DialogListener() {

							@Override
							public void onCancel() {
								// Function to handle cancel event
							}

							@Override
							public void onComplete(Bundle values) {
								// Function to handle complete event
								// Edit Preferences and update facebook acess_token
								SharedPreferences.Editor editor = mPrefs.edit();
								editor.putString("access_token",
										facebook.getAccessToken());
								editor.putLong("access_expires",
										facebook.getAccessExpires());
								editor.commit();

								// Making Login button invisible
								btnFbLogin.setVisibility(View.INVISIBLE);

								// Making logout Button visible
								btnFbGetProfile.setVisibility(View.VISIBLE);

								// Making post to wall visible
								btnPostToWall.setVisibility(View.VISIBLE);

								// Making show access tokens button visible
								btnShowAccessTokens.setVisibility(View.VISIBLE);
							}

							@Override
							public void onError(DialogError error) {
								// Function to handle error

							}

							@Override
							public void onFacebookError(FacebookError fberror) {
								// Function to handle Facebook errors

							}

						});
			}
		}

	 public void onActivityResult(int requestCode, int resultCode, Intent data) {
			//super.onActivityResult(requestCode, resultCode, data);
			facebook.authorizeCallback(requestCode, resultCode, data);
		}


	 public void showAccessTokens() {
			String access_token = facebook.getAccessToken();

			Toast.makeText(getContext(),
					"Access Token: " + access_token, Toast.LENGTH_LONG).show();
		}
		*//**
		 * Get Profile information by making request to Facebook Graph API
		 * *//*
		public void getProfileInformation() {
			mAsyncRunner.request("me", new RequestListener() {
				@Override
				public void onComplete(String response, Object state) {
					Log.d("Profile", response);
					String json = response;
					try {
						// Facebook Profile JSON data
						JSONObject profile = new JSONObject(json);
						
						// getting name of the user
						final String name = profile.getString("name");
						
						// getting email of the user
						final String email = profile.getString("email");
						
						runOnUiThread(new Runnable() {

							@Override
							public void run() {
								Toast.makeText(getContext(), "Name: " + name + "\nEmail: " + email, Toast.LENGTH_LONG).show();
							}

						});

						
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}

				@Override
				public void onIOException(IOException e, Object state) {
				}

				@Override
				public void onFileNotFoundException(FileNotFoundException e,
						Object state) {
				}

				@Override
				public void onMalformedURLException(MalformedURLException e,
						Object state) {
				}

				@Override
				public void onFacebookError(FacebookError e, Object state) {
				}
			});
		}
	 
	 public void postToWall() {
			// post on user's wall.
			facebook.dialog(getContext(), "feed", new DialogListener() {

				@Override
				public void onFacebookError(FacebookError e) {
				}

				@Override
				public void onError(DialogError e) {
				}

				@Override
				public void onComplete(Bundle values) {
				}

				@Override
				public void onCancel() {
				}
			});

		}
	
	 protected void runOnUiThread(Runnable runnable) {
		// TODO Auto-generated method stub
		
	}*/



	static class ViewHolder {
	  public ImageView offerImage;
	  public TextView offerName;
	  public TextView offerMoney;
	  public ImageView offerShare;
	  public ImageView offerShare1;
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



package project.mstorm.spidertask3;


import java.util.List;
/*
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
*/
//import com.google.android.maps.GeoPoint;

import android.location.Address;
import android.location.Geocoder;
//import android.location.Location;
import android.os.Bundle;
import android.text.InputType;
//import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
//import android.content.IntentSender;

public class MainActivity extends Activity /*implements GooglePlayServicesClient.ConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener*/ {
	//private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
	//LocationClient mLocationClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mLocationClient = new LocationClient(this, this, this);
        setContentView(R.layout.activity_main);
    }
    
    public void passLatLongs(View v) {
    	RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup1);
    	LinearLayout ll = (LinearLayout) findViewById(R.id.LLay);
    	int idChecked = rg.getCheckedRadioButtonId();
    	if (idChecked==R.id.radioButton1) {//asking for LatLongs
    		v.setOnClickListener(null);
    		try {
	    		final EditText etLat = new EditText(this);
	    		final EditText etLong = new EditText(this);
	    		etLat.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
	    		etLong.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
	    		etLat.setText("0");
	    		etLong.setText("0");
	    		ll.addView(etLat);
	    		ll.addView(etLong);
	    		Button bt = new Button(this);
	    		bt.setText("Generate Map");
	    		bt.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Intent it = new Intent(MainActivity.this, MapActivity.class);
						it.putExtra("lat", Double.parseDouble(etLat.getText().toString()));
						it.putExtra("long", Double.parseDouble(etLong.getText().toString()));
						startActivity(it);
					}
				});
	    		ll.addView(bt);
    		}
    		catch(Exception e) {
    			//Log.d("SPIDER", "Error here");
    			//Log.e("SPIDER", e.toString());
    		}
    		
    	}
    	else if(idChecked==R.id.radioButton2) { //asking for address
    		final EditText addT = new EditText(this);
    		v.setOnClickListener(null);
    		addT.setHint("Dubai");
    		Button bt = new Button(this);
    		bt.setText("Generate Map");
    		ll.addView(addT, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
    		bt.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Geocoder coder = new Geocoder(MainActivity.this);
					List<Address> address;
					try {
						address = coder.getFromLocationName(addT.getText().toString(), 4);
						if (address==null) return;
						Address location = address.get(0);
						double lat = location.getLatitude();
						double lon = location.getLongitude();
						Intent it = new Intent(MainActivity.this, MapActivity.class);
						it.putExtra("lat", lat);
						it.putExtra("long", lon);
						startActivity(it);
					}
					catch (Exception e) {
						Toast.makeText(MainActivity.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
					}
				}
			});
    		ll.addView(bt);
    	}
    	else {
    		/*mLocationClient.connect();
    		Location mCurrentLocation = mLocationClient.getLastLocation();
    		double lat = mCurrentLocation.getLatitude();
    		double lon = mCurrentLocation.getLongitude();
    		mLocationClient.disconnect();
    		Intent it = new Intent(this, MapActivity.class);
			it.putExtra("lat", lat);
			it.putExtra("long", lon);
			startActivity(it);*/
    		Toast.makeText(this, "Feature unavailable", Toast.LENGTH_SHORT).show();
    	}
    }
/*
	@Override
	public void onConnectionFailed(ConnectionResult connectionResult) {
		if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(
                        this,
                        CONNECTION_FAILURE_RESOLUTION_REQUEST);
            } catch (IntentSender.SendIntentException e) {
                // Log the error
                e.printStackTrace();
            }
        } else {
            
            //showErrorDialog(connectionResult.getErrorCode());
        	Toast.makeText(this, "Something's not right", Toast.LENGTH_SHORT).show();
        }
	}

	@Override
	public void onConnected(Bundle arg0) {
		Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onDisconnected() {
		Toast.makeText(this, "Disconnected. Please re-connect.",Toast.LENGTH_SHORT).show();
	}
	*/
}
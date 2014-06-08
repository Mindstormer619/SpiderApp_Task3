package project.mstorm.spidertask3;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

public class MapActivity extends Activity {
	private GoogleMap mMap;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		setUpMapIfNeeded();
		Intent it = getIntent();
		double lat = it.getDoubleExtra("lat", 0);
		double lon = it.getDoubleExtra("long", 0);
		mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), (float)10.0));
    }
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                                .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                // The Map is verified. It is now safe to manipulate the map.
            	mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(24.95, 55.33), (float) 10.0));
            }
        }
    }
    
}

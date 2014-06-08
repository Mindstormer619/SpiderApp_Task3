package project.mstorm.spidertask3;

import java.util.List;

//import com.google.android.maps.GeoPoint;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void passLatLongs(View v) {
    	RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup1);
    	LinearLayout ll = (LinearLayout) findViewById(R.id.LLay);
    	v.setOnClickListener(null);
    	int idChecked = rg.getCheckedRadioButtonId();
    	if (idChecked==R.id.radioButton1) {//asking for LatLongs
    		final EditText etLat = new EditText(this);
    		final EditText etLong = new EditText(this);
    		etLat.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
    		etLong.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
    		etLat.setText(0);
    		etLong.setText(0);
    		ll.addView(etLat);
    		ll.addView(etLong);
    		Button bt = new Button(this);
    		bt.setText("Generate Map");
    		bt.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent it = new Intent(MainActivity.this, MapActivity.class);
					it.putExtra("lat", Double.valueOf(etLat.getText().toString()));
					it.putExtra("long", Double.valueOf(etLong.getText().toString()));
					startActivity(it);
				}
			});
    		ll.addView(bt);
    	}
    	else if(idChecked==R.id.radioButton2) { //asking for address
    		final EditText addT = new EditText(this);
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
    }
}
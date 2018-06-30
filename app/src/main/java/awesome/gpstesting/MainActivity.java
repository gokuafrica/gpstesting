package awesome.gpstesting;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Locale;

import im.delight.android.location.SimpleLocation;

public class MainActivity extends AppCompatActivity {
    private SimpleLocation location;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        location=new SimpleLocation(this);
        if (!location.hasLocationEnabled()) {
            // ask the user to enable location access
            SimpleLocation.openSettings(this);
        }
        final double latitude=location.getLatitude();
        final double longitude=location.getLongitude();
        Toast.makeText(this,"Longitude: "+longitude+"Lattitude: "+latitude,Toast.LENGTH_LONG).show();
        String address=getAddressFromLatLng(latitude,longitude);
        t=(TextView)findViewById(R.id.tex);
        t.setText(address);
        String strUri = "http://maps.google.com/maps?q=loc:" + latitude + "," + longitude + " (" + "Label which you want" + ")";
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(strUri));

        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");

        startActivity(intent);
    }
    private String getAddressFromLatLng(double latitude,double longitude) {
        Geocoder geocoder = new Geocoder(this);

        String address = "";
        try {
            address = geocoder
                    .getFromLocation(latitude, longitude, 1)
                    .get(0).getAddressLine(0);
        } catch (Exception e) {
        }
        return address;
    }
}
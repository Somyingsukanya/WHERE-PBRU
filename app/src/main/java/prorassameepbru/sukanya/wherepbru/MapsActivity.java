package prorassameepbru.sukanya.wherepbru;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.util.Log;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String[] resultStrings;
    private int[] iconInts = {R.drawable.build1, R.drawable.build2,
            R.drawable.build3, R.drawable.build4, R.drawable.build5,
            R.drawable.build6};
    private LocationManager locationManager;
    private Criteria criteria;
    private double myLatADoulble, myLngADouble;
    private boolean gpsABoolean, networkABoolean;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        resultStrings = getIntent().getStringArrayExtra("Result");

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }  //Main Method

    @Override
    protected void onResume() {
        super.onResume();

        locationManager.removeUpdates(locationListener);

        myLatADoulble = Double.parseDouble(resultStrings[3]);
        myLatADoulble = Double.parseDouble(resultStrings[4]);

        Location ispLocation =myFindLocation(LocationManager.NETWORK_PROVIDER,"NO Connect");
        if (ispLocation != null) {

            myLatADoulble = ispLocation.getLatitude();
            myLatADoulble=ispLocation.getLongitude();
        }

        Location gpsLocation =myFindLocation(locationManager.GPS_PROVIDER,"NO Card GPS");
        if (gpsLocation != null) {
            myLatADoulble =gpsLocation.getLatitude();
            myLatADoulble=gpsLocation.getLongitude();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        locationManager.removeUpdates(locationListener);
    }

    public Location myFindLocation(String strProvider,
                                   String strError) {

        Location location = null;

        if (locationManager.isProviderEnabled(strProvider)) {

            locationManager.requestLocationUpdates(strProvider,
                    1000, 10, locationListener);
            location = locationManager.getLastKnownLocation(strProvider);

        } else {

            Log.d("WhereV1", " My Error ==> " + strError);

        }

        return location;
    }


    public LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

            myLatADoulble = location.getLatitude();
            myLatADoulble = location.getLongitude();

        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        setupCenterMap();

        //Setup Center Map
        setupCenterMap();
        createMarkkerUser();

    } //OnMapReady

    private void createMarkkerUser() {

        LatLng latLng=new LatLng(myLatADoulble,myLngADouble);
        mMap.addMarker(new MarkerOptions()
        .position(latLng)
        .icon(BitmapDescriptorFactory
        .fromResource(R.drawable.doremon48)));


    }

    private void setupCenterMap() {

        double douLat = Double.parseDouble(resultStrings[3]);
        double douLng = Double.parseDouble(resultStrings[4]);

        LatLng latLng = new LatLng(douLat, douLng);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18));

        mMap.addMarker(new MarkerOptions()
                .position(latLng)
                .icon(BitmapDescriptorFactory
                        .fromResource(iconInts[Integer.parseInt(resultStrings[5])]))
                .title("ห้อง" + resultStrings[2])
                .snippet("อยู่ที่อาคาร" + resultStrings[1]));

    } //setupCenter




} //Main Class


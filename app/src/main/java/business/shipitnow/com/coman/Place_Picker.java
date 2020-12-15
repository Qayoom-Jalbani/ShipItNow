package business.shipitnow.com.coman;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import business.shipitnow.com.R;
import business.shipitnow.com.module.ComanMethod;

import static business.shipitnow.com.module.ComanMethod.getMarker;

public class Place_Picker {


    private AlertDialog alertDialog;
    private Activity activity;
    private GoogleMap mMap;
    private MapView mMapView;
    private Locations locations;
    private TextView Address;
    private TextView LatLog;

    public Place_Picker(Activity activity) {
        this.activity = activity;
        this.locations = new Locations() {
            @Override
            public void onLatLng(LatLng latLng) {
                if (mMap != null) {
                    mMap.addMarker(
                            new MarkerOptions()
                                    .position(latLng)
                                    .title("Your Current Location")
                                    .icon(BitmapDescriptorFactory.fromBitmap(getMarker(activity, R.drawable.ic_pin_location, 25, 35)))
                    );
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13.5f));

                    Geocoder geocoder;
                    List<Address> addresses;
                    geocoder = new Geocoder(activity, Locale.getDefault());

                    try {
                        addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                        String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                        String city = addresses.get(0).getLocality();
                        String state = addresses.get(0).getAdminArea();
                        String country = addresses.get(0).getCountryName();
                        String postalCode = addresses.get(0).getPostalCode();
                        String knownName = addresses.get(0).getFeatureName();

                        Address.setText(address + " " + city + " " + state + " " + country);
                        LatLog.setText(latLng.toString());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            }
        };
    }

    public void ShowLoading(TextView text, String title) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater Inflater = LayoutInflater.from(activity);
        View view2 = Inflater.inflate(R.layout.place_picker, null);
        TextView Title = view2.findViewById(R.id.title);

        Title.setText(title);

        try {
            MapsInitializer.initialize(activity.getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("QWE", "MapsInitializer Error " + e.getMessage());
        }
        mMapView = (MapView) view2.findViewById(R.id.map);
        Address = view2.findViewById(R.id.address);
        LatLog = view2.findViewById(R.id.latlng);
        TextView OK = view2.findViewById(R.id.ok);
        TextView Cancel = view2.findViewById(R.id.cancel);

        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText(Address.getText());
                text.setTag(LatLog.getText());
                CloseLoading();
            }
        });
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CloseLoading();
            }
        });

        EditText Search = view2.findViewById(R.id.autocomplete_fragment);
        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Places.initialize(activity,"AIzaSyBvBWRpSX_hYpc9YL6ECV_Ws_gMhya9i2g");
                List<Place.Field> places = Arrays.asList(Place.Field.NAME,Place.Field.ADDRESS,Place.Field.LAT_LNG);
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN,places).build(activity);
                activity.startActivityForResult(intent,100);
            }
        });

        builder.setView(view2);

        builder.setCancelable(false);
        alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();


        mMapView.onCreate(alertDialog.onSaveInstanceState());
        mMapView.onResume();
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Log.e("QWE", "OnMap Ready");
                mMap = googleMap;
                ComanMethod.getCurrentLocation(activity, locations);

                mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        mMap.clear();
                        mMap.addMarker(
                                new MarkerOptions()
                                        .position(latLng)
                                        .title("Your Current Location")
                                        .icon(BitmapDescriptorFactory.fromBitmap(getMarker(activity, R.drawable.ic_pin_location, 45, 55)))
                        );

                        Geocoder geocoder;
                        List<Address> addresses;
                        geocoder = new Geocoder(activity, Locale.getDefault());

                        try {
                            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                            String city = addresses.get(0).getLocality();
                            String state = addresses.get(0).getAdminArea();
                            String country = addresses.get(0).getCountryName();
                            String postalCode = addresses.get(0).getPostalCode();
                            String knownName = addresses.get(0).getFeatureName();

                            Address.setText(address + " " + city + " " + state + " " + country);
                            LatLog.setText("LatLng: " + latLng.latitude + "/" + latLng.longitude);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });


    }

    public void CloseLoading() {
        if (alertDialog != null) {
            alertDialog.dismiss();
        } else {
            Log.e("QWE", "Alret Null");
        }
    }
}

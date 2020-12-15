package business.shipitnow.com.ui.courier.tab.api_services.tracking;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;

import business.shipitnow.com.R;
import business.shipitnow.com.adapters.PicUpAdapters;
import business.shipitnow.com.coman.Loading;
import business.shipitnow.com.module.AddPakage;
import business.shipitnow.com.ui.courier.tab.api_services.mypackage.ViewModels;

import static business.shipitnow.com.module.ComanMethod.getMarker;

public class tab_tracking extends Fragment {

    private GoogleMap mMap;
    private MapView mMapView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_tab_tracking, container, false);
        ((AppCompatActivity) requireActivity()).getSupportActionBar().setTitle("Real Time Tracking");


        Bundle bundle = getArguments();
        AddPakage item = (AddPakage) bundle.getSerializable("item");

        String PLatLng = item.getPickupLat() + "," + item.getPickupLong();
        String DLatLng = item.getDropoffLat() + "," + item.getDropoffLong();

        Log.e("LAT", "PLAT " + PLatLng + " DLat " + DLatLng);
        Log.e("LAT", "Item " + item.toString());

        double plat = Double.parseDouble(item.getPickupLat());
        double plng = Double.parseDouble(item.getPickupLong());

        double dlat = Double.parseDouble(item.getDropoffLat());
        double dlng = Double.parseDouble(item.getDropoffLong());


        mMapView = (MapView) root.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();
        try {
            MapsInitializer.initialize(requireActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e("QWE", "On Map Fragment Call");
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                Log.e("QWE", "On Map Ready");
                // Add a marker in Sydney and move the camera
                //US Shicago lat long   41.789374, -87.725023


                LatLng StartPoint = new LatLng(plat, plng);
                LatLng midlePoint = new LatLng(36.123061, -115.154971);
                LatLng EndPoint = new LatLng(dlat, dlng);

                LatLng path1 = new LatLng(36.129594, -115.155388);
                LatLng path2 = new LatLng(36.114574, -115.154137);


               /* mMap.addMarker(
                        new MarkerOptions()
                                .position(midlePoint)
                                .title("Here Your Package")
                                .icon(BitmapDescriptorFactory.fromBitmap(getMarker(requireActivity(), R.drawable.ic_color_pin_location, 30, 45)))
                );*/

                mMap.addMarker(
                        new MarkerOptions()
                                .position(StartPoint)
                                .title("Package Pickup Location ")
                                .icon(BitmapDescriptorFactory.fromBitmap(getMarker(requireActivity(), R.drawable.ic_pin_location, 35, 45)))
                );

                mMap.addMarker(
                        new MarkerOptions()
                                .position(EndPoint)
                                .title("Package Drop Location ")
                                .icon(BitmapDescriptorFactory.fromBitmap(getMarker(requireActivity(), R.drawable.marker, 35, 45)))
                );

               /* Polyline line = mMap.addPolyline(new PolylineOptions()
                        .add(StartPoint, path2, midlePoint, path1, EndPoint)
                        .width(4)
                        .zIndex(12f)
                        .color(Color.BLACK));
                line.setZIndex(12);*/
                setUpMapIfNeeded();

            }
        });

        return root;
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap != null) {
            // Try to obtain the map from the SupportMapFragment.
            //mMap = ((SupportMapFragment) requireActivity().getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
            if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            mMap.setMyLocationEnabled(true);
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
                    @Override
                    public void onMyLocationChange(Location arg0) {
                        mMap.addMarker(new MarkerOptions().position(new LatLng(arg0.getLatitude(), arg0.getLongitude())).title("Rider Location"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(arg0.getLatitude(), arg0.getLongitude()), 13.5f));
                    }
                });
            }
        }
    }

}

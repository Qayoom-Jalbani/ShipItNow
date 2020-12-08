package business.shipitnow.com.ui.courier.tab;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import business.shipitnow.com.R;
import business.shipitnow.com.adapters.PicUpAdapters;

import static business.shipitnow.com.module.ComanMethod.getMarker;

public class tab_tracking extends Fragment {

    private GoogleMap mMap;
    private MapView mMapView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_tab_tracking, container, false);
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
                LatLng sydney = new LatLng(41.789374, -87.725023);

                LatLng StartPoint = new LatLng(36.114114, -115.173053);
                LatLng midlePoint = new LatLng(36.123061, -115.154971);
                LatLng EndPoint = new LatLng(36.129594, -115.137793);

                LatLng path1 = new LatLng(36.129594, -115.155388);
                LatLng path2 = new LatLng(36.114574, -115.154137);


                mMap.addMarker(
                        new MarkerOptions()
                                .position(midlePoint)
                                .title("Here Your Package")
                                .icon(BitmapDescriptorFactory.fromBitmap(getMarker(requireActivity(), R.drawable.ic_color_pin_location, 30, 45)))
                );

                mMap.addMarker(
                        new MarkerOptions()
                                .position(StartPoint)
                                .title("Here Your Package")
                                .icon(BitmapDescriptorFactory.fromBitmap(getMarker(requireActivity(), R.drawable.ic_pin_location, 25, 35)))
                );

                mMap.addMarker(
                        new MarkerOptions()
                                .position(EndPoint)
                                .title("Here Your Package")
                                .icon(BitmapDescriptorFactory.fromBitmap(getMarker(requireActivity(), R.drawable.marker, 25, 35)))
                );

                Polyline line = mMap.addPolyline(new PolylineOptions()
                        .add(StartPoint, path2, midlePoint, path1, EndPoint)
                        .width(4)
                        .zIndex(12f)
                        .color(Color.BLACK));
                line.setZIndex(12);

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(midlePoint, 13.5f));
            }
        });

        return root;
    }

}

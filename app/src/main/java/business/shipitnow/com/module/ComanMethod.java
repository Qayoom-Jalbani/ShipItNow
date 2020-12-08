package business.shipitnow.com.module;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.maps.model.LatLng;

import business.shipitnow.com.coman.Locations;

import static android.content.Context.LOCATION_SERVICE;

public class ComanMethod {


    public static void getCurrentLocation(Activity context, Locations locations) {

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Log.e("QWE", "Geting Location ....");
        LocationManager mLocationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);

        Location location = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (location != null) {
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            LatLng latLng = new LatLng(lat, lng);
            locations.onLatLng(latLng);
            return;
        }

        mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                double lat = location.getLatitude();
                double lng = location.getLongitude();
                LatLng latLng = new LatLng(lat, lng);
                locations.onLatLng(latLng);
            }
        });


    }

    public static Bitmap getMarker(Activity context, int id, int w, int h) {
        int height = 45;
        int width = 30;
        BitmapDrawable bitmapdraw = (BitmapDrawable) context.getResources().getDrawable(id);
        Bitmap b = bitmapdraw.getBitmap();

        return Bitmap.createScaledBitmap(b, w, h, false);
    }
}

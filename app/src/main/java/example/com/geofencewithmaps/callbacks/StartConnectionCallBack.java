package example.com.geofencewithmaps.callbacks;


import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import example.com.geofencewithmaps.GeofenceController;
import example.com.geofencewithmaps.geo.Geo;


public class StartConnectionCallBack implements GoogleApiClient.ConnectionCallbacks {

    private Location lastLocation;

    @Override
    public void onConnected(Bundle bundle) {
        Log.i("DEBUG", "CONNECTED");
        lastLocation = LocationServices.FusedLocationApi.getLastLocation(GeofenceController.getInstance().getGoogleApiClient());
        if (lastLocation != null) {
            Geo geo = new Geo();
            geo.id = "1";
            geo.name = "Area to trigger";
            geo.latitude = lastLocation.getLatitude();
            geo.longitude = lastLocation.getLongitude();
            geo.radius = 100;
            GeofenceController.getInstance().addGeofence(geo);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}
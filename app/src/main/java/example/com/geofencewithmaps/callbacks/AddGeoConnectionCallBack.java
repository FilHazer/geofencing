package example.com.geofencewithmaps.callbacks;


import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationServices;

import example.com.geofencewithmaps.GeofenceController;
import example.com.geofencewithmaps.application.GeofenceApplication;
import example.com.geofencewithmaps.geo.GeoIntentService;

public class AddGeoConnectionCallBack implements  GoogleApiClient.ConnectionCallbacks{

    private String LOG_TAG = "DEBUG";

    @Override
    public void onConnected(Bundle bundle) {
        Intent intent = new Intent(GeofenceApplication.CONTEXT, GeoIntentService.class);
        PendingIntent pendingIntent = PendingIntent.getService(GeofenceApplication.CONTEXT, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingResult<Status> result = LocationServices.GeofencingApi.addGeofences(GeofenceController.getInstance().getGoogleApiClient(), GeofenceController.getInstance().getAddGeofencingRequest(), pendingIntent);
        result.setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(Status status) {
                if (status.isSuccess()) {
                    Log.i(LOG_TAG, "SUCCESS" + status);
                } else {
                    Log.i(LOG_TAG, "FAILED: " + status.getStatusMessage() + " : " + status.getStatusCode());
                }
            }
        });
    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}

package example.com.geofencewithmaps;


import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.List;

import example.com.geofencewithmaps.application.GeofenceApplication;
import example.com.geofencewithmaps.callbacks.AddGeoConnectionCallBack;
import example.com.geofencewithmaps.callbacks.ConnectionFailedCallBack;
import example.com.geofencewithmaps.geo.Geo;

public class GeofenceController {

    private static GeofenceController instance;
    private GoogleApiClient googleApiClient;
    //new
    private Geo geo;
    private Geofence geofence;

    public static GeofenceController getInstance() {
        if (instance == null) {
            instance = new GeofenceController();
        }
        return instance;
    }

    public void startGoogleApiClient(GoogleApiClient.ConnectionCallbacks callback, ConnectionFailedCallBack connectionFailedListener) {
        googleApiClient = new GoogleApiClient.Builder(GeofenceApplication.CONTEXT)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(callback)
                .addOnConnectionFailedListener(connectionFailedListener)
                .build();
        googleApiClient.connect();
    }

    public void stopGoogleApiClient() {
        googleApiClient.disconnect();
    }

    public GoogleApiClient getGoogleApiClient(){
        return googleApiClient;
    }

    public void addGeofence(Geo geo) {
        this.geo = geo;
        this.geofence = geo.geofence();
        startGoogleApiClient(new AddGeoConnectionCallBack(), new ConnectionFailedCallBack());
    }

    public GeofencingRequest getAddGeofencingRequest() {
        List<Geofence> geofencesToAdd = new ArrayList<>();
        geofencesToAdd.add(geofence);
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER);
        builder.addGeofences(geofencesToAdd);
        return builder.build();
    }
}

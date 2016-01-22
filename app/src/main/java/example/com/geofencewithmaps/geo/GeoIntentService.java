package example.com.geofencewithmaps.geo;


import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

public class GeoIntentService extends IntentService{

    private String LOG_TAG = "DEBUG";
    public GeoIntentService() {
        super("GeoIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(LOG_TAG, "onHandleIntent");
        GeofencingEvent event = GeofencingEvent.fromIntent(intent);
        if (event == null) {
            return;
        }

        if (event.hasError()) {
            Log.i(LOG_TAG, "ERROR");
            return;
        }

        int transition = event.getGeofenceTransition();
        if (transition == Geofence.GEOFENCE_TRANSITION_ENTER){
            Log.i(LOG_TAG, "ENTER");
        } else if(transition == Geofence.GEOFENCE_TRANSITION_DWELL ){
            Log.i(LOG_TAG, "DWELL");
        }else if(transition == Geofence.GEOFENCE_TRANSITION_EXIT) {
            Log.i(LOG_TAG, "EXIT");
        }

    }
}

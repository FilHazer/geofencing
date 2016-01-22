package example.com.geofencewithmaps.callbacks;

import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

public class ConnectionFailedCallBack implements GoogleApiClient.OnConnectionFailedListener {

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i("DEBUG", "CONNECTION FAILED");
    }
}
package apc.edu.ph.commuteaid;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class CheckGooglePlayServices extends AppCompatActivity {

    private static final String TAG = "CheckGooglePlayServices";
    private static final int ERROR_DIALOG_REQUEST = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_google_play_services);


        if(isServicesOK()){
            init();
        }
    }

    private void init(){
        Button btnCommute = (Button) findViewById(R.id.btnCommute);
        btnCommute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckGooglePlayServices.this, MapActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button btnNavigate = (Button) findViewById(R.id.btnNavigate);
        btnNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent naviIntent = new Intent(CheckGooglePlayServices.this, NavigationActivity.class);
                startActivity(naviIntent);
                finish();
            }
        });
    }

    public boolean isServicesOK(){
        Log.d(TAG, "isServicesOK: checking google services version");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(CheckGooglePlayServices.this);
        if(available == ConnectionResult.SUCCESS){
            Log.d(TAG, "isServicesOK: Google Play Services is working.");
            return true;
        }else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            Log.d(TAG, "isServicesOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(CheckGooglePlayServices.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }else{
            Toast.makeText(this, "Sorry, you can't make map requests.", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public void reserveSeat(View v) {
        startActivity(new Intent(CheckGooglePlayServices.this, PuvListActivity.class));
    }

}

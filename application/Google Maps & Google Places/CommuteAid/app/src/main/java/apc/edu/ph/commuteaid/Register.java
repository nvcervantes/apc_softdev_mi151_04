package apc.edu.ph.commuteaid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Map;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
<<<<<<< HEAD
    public void register(View v) {
        startActivity(new Intent(Register.this, CheckGooglePlayServices.class));
=======
    public void registerUser(View v) {
        startActivity(new Intent(Register.this, MapActivity.class));
>>>>>>> d2318205fd3d8f4d1f95c2bdfa509fdd2bbe6eca
    }
}

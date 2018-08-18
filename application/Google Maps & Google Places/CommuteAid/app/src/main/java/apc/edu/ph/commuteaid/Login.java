package apc.edu.ph.commuteaid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    public void login(View v) {
        startActivity(new Intent(Login.this,CheckGooglePlayServices.class));
    }
    public void register(View v) {
        startActivity(new Intent(Login.this, Register.class));
    }

}

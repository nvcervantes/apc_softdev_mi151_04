package apc.edu.ph.commuteaid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
<<<<<<< HEAD

    public void login(View v) {
        startActivity(new Intent(Login.this,CheckGooglePlayServices.class));
    }
    public void register(View v) {
        startActivity(new Intent(Login.this, Register.class));
    }
=======
>>>>>>> d2318205fd3d8f4d1f95c2bdfa509fdd2bbe6eca
}

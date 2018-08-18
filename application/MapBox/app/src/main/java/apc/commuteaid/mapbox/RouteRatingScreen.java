package apc.commuteaid.mapbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class RouteRatingScreen extends AppCompatActivity {
    private EditText routeStart, routeDestination, ratingSuggestion;
    private RatingBar ratingTime, ratingCost, ratingAccess;
    private Button submitRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.route_rating_screen);
        routeStart = (EditText)findViewById(R.id.route_start);
        routeDestination = (EditText)findViewById(R.id.route_destination);
        ratingSuggestion = (EditText)findViewById(R.id.rating_suggestions);
        ratingTime = (RatingBar)findViewById(R.id.rating_time);
        ratingCost = (RatingBar)findViewById(R.id.rating_cost);
        ratingAccess = (RatingBar)findViewById(R.id.rating_access);
        submitRating = (Button)findViewById(R.id.button_submit);
        /*
        * Enter code here to get the value of the intent from the Navigation Activity
        */

        submitRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitRating();
            }
        });
    }
    private void submitRating(){
        //String startPoint = (Fill this up)
        //String endPoint = (Fill this up)
        String resultTime = Float.toString(ratingTime.getRating());
        String resultCost = Float.toString(ratingCost.getRating());
        String resultAccess = Float.toString(ratingAccess.getRating());
        String resultSuggestions = ratingSuggestion.getText().toString();

        if (!resultTime.equals("0") && !resultCost.equals("0") && !resultAccess.equals("0")){
            /*
            * Enter code here to submit the values to a database
            */
            createToast("Rating submitted successfully! Thank you!");
            finish();
        } else {
            createToast("Please rate all of the fields with an asterisk");
        }

    }
    private void createToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

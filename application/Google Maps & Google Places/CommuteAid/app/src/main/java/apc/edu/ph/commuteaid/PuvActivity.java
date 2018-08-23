package apc.edu.ph.commuteaid;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PuvActivity extends AppCompatActivity {

    private static final String TAG = "PuvActivity";

    private SectionsPageAdapter sectionsPageAdapter;

    private ViewPager viewPager;

    public String puvKey;

    private DatabaseReference puvsRef;

    private static Puv puv;

    private ImageView toolbarImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puv);
        Log.d(TAG, "onCreate: Starting.");

        puvKey = getIntent().getStringExtra("PUV_KEY");

        puvsRef = FirebaseDatabase.getInstance().getReference("puvs");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        sectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        toolbarImage = (ImageView) findViewById(R.id.tiv_bg);

        preparePuv(puvKey);


    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new PuvInfoFragment(), "Info");
        adapter.addFragment(new PuvQuestionsFragment(), "Questions");
        adapter.addFragment(new PuvPollsFragment(), "Polls");
        viewPager.setAdapter(adapter);
    }

    private void preparePuv(final String puvKey) {

        puvsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot puvSnapshot : dataSnapshot.getChildren()) {
                    if (puvSnapshot.getKey().equals(puvKey)) {
                        puv = puvSnapshot.getValue(Puv.class);
                        puv.setId(puvSnapshot.getKey());

                        viewPager = (ViewPager) findViewById(R.id.container);
                        setupViewPager(viewPager);

                        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
                        tabLayout.setupWithViewPager(viewPager);


                        if (puv.getCategory().equals("Jeepney")) {
                            toolbarImage.setImageDrawable(getDrawable(R.drawable.jeepney));
                        } else if (puv.getCategory().equals("Bus")) {
                            toolbarImage.setImageDrawable(getDrawable(R.drawable.bus));
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public Puv getPuv() {
        return puv;
    }


}

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

    public String seminarKey;

    private DatabaseReference seminarsRef;

    private static Puv seminar;

    private ImageView toolbarImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puv);
        Log.d(TAG, "onCreate: Starting.");

        seminarKey = getIntent().getStringExtra("SEMINAR_KEY");

        seminarsRef = FirebaseDatabase.getInstance().getReference("seminars");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        sectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        toolbarImage = (ImageView) findViewById(R.id.tiv_bg);

        prepareSeminar(seminarKey);


    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new PuvInfoFragment(), "Info");
        adapter.addFragment(new PuvQuestionsFragment(), "Questions");
        adapter.addFragment(new PuvPollsFragment(), "Polls");
        viewPager.setAdapter(adapter);
    }

    private void prepareSeminar(final String seminarKey) {

        seminarsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot seminarSnapshot : dataSnapshot.getChildren()) {
                    if (seminarSnapshot.getKey().equals(seminarKey)) {
                        seminar = seminarSnapshot.getValue(Puv.class);
                        seminar.setId(seminarSnapshot.getKey());

                        viewPager = (ViewPager) findViewById(R.id.container);
                        setupViewPager(viewPager);

                        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
                        tabLayout.setupWithViewPager(viewPager);


                        if (seminar.getCategory().equals("Technology")) {
                            toolbarImage.setImageDrawable(getDrawable(R.drawable.jeepney));
                        } else if (seminar.getCategory().equals("Media Arts")) {
                            toolbarImage.setImageDrawable(getDrawable(R.drawable.arts));
                        } else if (seminar.getCategory().equals("Business")) {
                            toolbarImage.setImageDrawable(getDrawable(R.drawable.business));
                        } else if (seminar.getCategory().equals("General")) {
                            toolbarImage.setImageDrawable(getDrawable(R.drawable.general));
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public Puv getSeminar() {
        return seminar;
    }


}

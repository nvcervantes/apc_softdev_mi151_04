package apc.edu.ph.commuteaid;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PuvListActivity extends AppCompatActivity {

    private List<Puv> puvList = new ArrayList<>();
    private RecyclerView rv;
    private PuvAdapter puvAdapter;
    private DatabaseReference puvsRef;
    private ProgressBar spinner;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puv_list);

        spinner = (ProgressBar) findViewById(R.id.progressCircle);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);
        rv = (RecyclerView) findViewById(R.id.rv);
        puvsRef = FirebaseDatabase.getInstance().getReference("puvs");

        puvAdapter = new PuvAdapter(this, puvList);
        LinearLayoutManager tLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(tLayoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());

        swipeRefreshLayout.setColorSchemeResources(R.color.primaryColor, R.color.primaryLightColor, R.color.primaryDarkColor);

        preparePuvs();

      //  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_add);
       // fab.setOnClickListener(new View.OnClickListener() {
         //   @Override
           // public void onClick(View v) {
             //   Intent i = new Intent(PuvListActivity.this, PuvEditActivity.class);
               // startActivity(i);
           // }
        //});



        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rv.removeAllViewsInLayout();
                        puvList.clear();
                        preparePuvs();
                        swipeRefreshLayout.setRefreshing(false);
                    }

                }, 2500);
            }
        });

    }



    private void preparePuvs() {
        spinner.setVisibility(View.VISIBLE);
        puvsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Puv puv;
                for (DataSnapshot puvSnapshot : dataSnapshot.getChildren()) {
                    puv = puvSnapshot.getValue(Puv.class);
                    puv.setId(puvSnapshot.getKey());
                    puvList.add(puv);
                    rv.setAdapter(puvAdapter);
                }
                spinner.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



}
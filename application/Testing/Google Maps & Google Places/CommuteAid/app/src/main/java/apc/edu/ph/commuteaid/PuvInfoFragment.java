package apc.edu.ph.commuteaid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



public class PuvInfoFragment extends Fragment {
    private static final String TAG = "PuvInfoFragment";

    private TextView platenumber, date, venue, availableseats, remarks;
    private PuvActivity puvActivity;
    private Puv puv;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        puvActivity = new PuvActivity();

        platenumber = (TextView) view.findViewById(R.id.stv_title);
        date = (TextView) view.findViewById(R.id.stv_date);
        venue = (TextView) view.findViewById(R.id.stv_venue);
        availableseats = (TextView) view.findViewById(R.id.stv_speaker);
        remarks = (TextView) view.findViewById(R.id.stv_agenda);

        puvActivity = (PuvActivity)getActivity();
        puv = puvActivity.getPuv();

        platenumber.setText(puv.getPlatenumber());
        date.setText(puv.getDate() + " " + puv.getStart() + " — " + puv.getEnd());
        venue.setText(puv.getVenue());
        availableseats.setText(puv.getAvailableseats());
        remarks.setText(puv.getRemarks());


        return view;
    }
}

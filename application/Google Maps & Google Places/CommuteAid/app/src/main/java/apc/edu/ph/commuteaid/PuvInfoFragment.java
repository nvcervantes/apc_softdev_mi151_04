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

    private TextView title, date, venue, speaker, agenda;
    private PuvActivity seminarActivity;
    private Puv seminar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        seminarActivity = new PuvActivity();

        title = (TextView) view.findViewById(R.id.stv_title);
        date = (TextView) view.findViewById(R.id.stv_date);
        venue = (TextView) view.findViewById(R.id.stv_venue);
        speaker = (TextView) view.findViewById(R.id.stv_speaker);
        agenda = (TextView) view.findViewById(R.id.stv_agenda);

        seminarActivity = (PuvActivity)getActivity();
        seminar = seminarActivity.getSeminar();

        title.setText(seminar.getTitle());
        date.setText(seminar.getDate() + " " + seminar.getStart() + " — " + seminar.getEnd());
        venue.setText(seminar.getVenue());
        speaker.setText(seminar.getSpeaker());
        agenda.setText(seminar.getAgenda());


        return view;
    }
}

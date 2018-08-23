package apc.edu.ph.commuteaid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;



public class PuvPollsFragment extends Fragment {
    private static final String TAG = "PuvPollsFragment";

    private TextView platenumber;
    private Button bt_newoption, bt_start;
    private PuvActivity puvActivity;
    private Puv puv;
    private LinearLayout ll;
    private ViewGroup optionLayout;
    private List<TextInputEditText> pollOption = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_polls, container, false);

        puvActivity = (PuvActivity) getActivity();

        ll = (LinearLayout) view.findViewById(R.id.csp_choices);
        bt_newoption = (Button) view.findViewById(R.id.csp_addoption);
        bt_start = (Button) view.findViewById(R.id.csp_send);

        bt_newoption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPoll();

            }
        });

        bt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (EditText et : pollOption) {
                    Toast.makeText(puvActivity, et.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void addPoll() {
        LayoutInflater inflater = puvActivity.getLayoutInflater();
        final View views = inflater.inflate(R.layout.list_poll_option_item, ll, false);

        TextInputLayout til = (TextInputLayout) views.findViewById(R.id.etl_option);
        Button bt_remove = (Button) views.findViewById(R.id.bt_remove);
        final TextInputEditText tiet = (TextInputEditText) views.findViewById(R.id.et_option);

        pollOption.add(tiet);


        ll.addView(views);

        bt_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll.removeView(views);
                pollOption.remove(tiet);
            }
        });
    }

}

package apc.edu.ph.commuteaid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;


public class PuvAdapter extends RecyclerView.Adapter<PuvAdapter.MyViewHolder> {

    private List<Puv> seminarList;
    private DatabaseReference categoriesRef = FirebaseDatabase.getInstance().getReference("categories");
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, category, date, time, venue;
        public ImageView bg;
        public CardView cv;

        public MyViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            cv = (CardView)  itemView.findViewById(R.id.cv);
            title = (TextView) itemView.findViewById(R.id.ctv_title);
            category = (TextView) itemView.findViewById(R.id.ctv_cat);
            bg = (ImageView) itemView.findViewById(R.id.civ_bg);
            date = (TextView) itemView.findViewById(R.id.ctv_date);
            time = (TextView) itemView.findViewById(R.id.ctv_time);
            venue = (TextView) itemView.findViewById(R.id.ctv_venue);



        }
    }


    public PuvAdapter(Context context, List<Puv> seminarList) {
        this.seminarList = seminarList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                 .inflate(R.layout.card_puv_row, parent, false);



        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Puv seminar = seminarList.get(position);
        holder.title.setText(seminar.getTitle());

        if (seminar.getCategory().equals("Technology")) {
            holder.bg.setImageDrawable(context.getDrawable(R.drawable.jeepney));
        } else if (seminar.getCategory().equals("Media Arts")) {
            holder.bg.setImageDrawable(context.getDrawable(R.drawable.arts));
        } else if (seminar.getCategory().equals("Business")) {
            holder.bg.setImageDrawable(context.getDrawable(R.drawable.business));
        } else if (seminar.getCategory().equals("General")) {
            holder.bg.setImageDrawable(context.getDrawable(R.drawable.general));
        } else {
            holder.bg.setBackgroundColor(Color.parseColor("#000000"));
        }
        holder.category.setText(seminar.getCategory());
        holder.date.setText(seminar.getDate());
        holder.time.setText(seminar.getStart() + " — " + seminar.getEnd());
        holder.venue.setText(seminar.getVenue());

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, PuvActivity.class);
                i.putExtra("SEMINAR_KEY", seminar.getId());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return seminarList.size();
    }

}

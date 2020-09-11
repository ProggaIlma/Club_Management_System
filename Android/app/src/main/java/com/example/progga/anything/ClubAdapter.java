package com.example.progga.anything;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

/**
 * Created by progga on 9/8/18.
 */

public class ClubAdapter extends RecyclerView.Adapter<ClubAdapter.MyViewHolder> {

    Context c;
    ArrayList<String> clubs;
    ArrayList<String> clubid;
    public ClubAdapter(Context c, ArrayList clubs, ArrayList clubid) {
        this.c = c;
        this.clubs = clubs;
        this.clubid = clubid;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.club_listitem, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        //BIND DATA
        holder.nameTxt.setText(clubs.get(position));
        holder.clubid1.setText(clubid.get(position));

    }

    @Override
    public int getItemCount() {
        return clubs.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView nameTxt,clubid1;

        ToggleButton toggleButton;
        Context c;
        public MyViewHolder(View itemView) {
            super(itemView);
            clubid1 = (TextView) itemView.findViewById(R.id.text1);

            nameTxt = (TextView) itemView.findViewById(R.id.text);
            toggleButton=(ToggleButton)itemView.findViewById(R.id.togglebutton);
            nameTxt.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();

            String club_id = clubid.get(position);
            Intent intent=new Intent(v.getContext(),event1.class);
            intent.putExtra("Clubid",club_id);

            v.getContext().startActivity(intent);
        }
    }}
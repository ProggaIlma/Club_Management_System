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
 * Created by progga on 9/11/18.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {

    Context context;
    ArrayList<String> events;
    ArrayList<String> eventid;
    public EventAdapter(Context c, ArrayList events, ArrayList eventid) {
        this.context = c;
        this.events =events;
        this.eventid = eventid;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        //BIND DATA
        holder.eventName.setText(events.get(position));
        holder.event_id.setText(eventid.get(position));

    }

    @Override
    public int getItemCount() {
        return events.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView eventName,event_id;
        Context c;
        public MyViewHolder(View itemView) {
            super(itemView);
            eventName= (TextView) itemView.findViewById(R.id.listitem);
            event_id = (TextView) itemView.findViewById(R.id.text2);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();

            String event_id1 = eventid.get(position);
            Intent intent=new Intent(v.getContext(),events.class);
            intent.putExtra("EventId",event_id1);

            v.getContext().startActivity(intent);
        }
    }}
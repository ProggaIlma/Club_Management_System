package com.example.progga.anything;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by progga on 8/27/18.
 */

public class myClubs extends Fragment implements View.OnClickListener {
    ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.myclubs, container, false);
        listView=rootView.findViewById(R.id.listview);
        String[] clubs={"Math Club","Debate Club","Science Club","English Club","Tour Club","Anything Club"};
        ArrayAdapter arrayAdapter=new ArrayAdapter(getActivity(),R.layout.listitem,R.id.listitem,clubs);
        listView.setAdapter(arrayAdapter);
        TextView textView = rootView.findViewById(R.id.textview);
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        textView.setOnClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(),clubinfo.class);
                startActivity(intent);

            }
        });
        return rootView;
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(getActivity(),CreateClub.class);
        startActivity(intent);

    }
}

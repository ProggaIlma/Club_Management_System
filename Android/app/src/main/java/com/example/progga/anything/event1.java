package com.example.progga.anything;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class event1 extends AppCompatActivity {
RecyclerView mRecyclerView;
EventAdapter mAdapter;
public static String URL=database.eventURL;
    public static String KEY_CLUBID="club_id_fk";
    String club_id_fk;
    ArrayList<String> eventname=new ArrayList<>();
    ArrayList<String> eventid=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event1);
        mRecyclerView= (RecyclerView)findViewById(R.id.recyclerview);
        club_id_fk=getIntent().getStringExtra ("Clubid");
        //  mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        loadRecyclerviewData();
       // listView=findViewById(R.id.listview);
       // String[] clubs={"Math Fest","Debate Fest","Science Fest","English Fest","Tour Fest","Anything Fest"};
       /* ArrayAdapter arrayAdapter=new ArrayAdapter(event1.this,R.layout.listitem,R.id.listitem,clubs);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(event1.this,events.class);
                startActivity(intent);

            }
        });*/
    }
    private void loadRecyclerviewData()
    {
        //Toast.makeText(event1.this,club_id_fk,Toast.LENGTH_LONG).show();

        final ProgressDialog progressDialog=new ProgressDialog(this);

        progressDialog.setMessage("Loading Data.........");
        progressDialog.show();
        //Toast.makeText(getApplicationContext(),"2",Toast.LENGTH_LONG).show();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject= new JSONObject(s);
                    // Toast.makeText(getApplicationContext(),"3",Toast.LENGTH_LONG).show();

                    JSONArray array=jsonObject.getJSONArray("events");
                    //Toast.makeText(getApplicationContext(),"3.1",Toast.LENGTH_LONG).show();

                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject o=array.getJSONObject(i);
                        eventname.add(o.getString("event_name"));
                        eventid.add(o.getString("event_id"));

                        //  Toast.makeText(getApplicationContext(),o.getString("district").toString(),Toast.LENGTH_LONG).show();

                    }
                    mAdapter = new EventAdapter(event1.this,eventname,eventid);
                    mRecyclerView.setAdapter(mAdapter);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(event1.this,error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }
        ){

            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put(KEY_CLUBID,club_id_fk);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

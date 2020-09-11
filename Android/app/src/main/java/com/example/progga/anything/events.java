package com.example.progga.anything;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class events extends AppCompatActivity {
    public static String KEY_EVENTID="event_id";
    String event_id;
    public static String URL=database.eventinfoURL;
    String eventlocation=null,eventname=
    null,eventdate=null;
    TextView eventlocation1,eventname1,eventdate1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        eventname1=findViewById(R.id.clubname2);
        eventlocation1=findViewById(R.id.address2);
        eventdate1=findViewById(R.id.date2);
        event_id=getIntent().getStringExtra ("EventId");

        loadRecyclerviewData();
    }
    private void loadRecyclerviewData()
    {
        Toast.makeText(getApplicationContext(),event_id,Toast.LENGTH_LONG).show();

        final ProgressDialog progressDialog=new ProgressDialog(this);

        progressDialog.setMessage("Loading Data.........");
        progressDialog.show();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject= new JSONObject(s);
                     Toast.makeText(getApplicationContext(),"3",Toast.LENGTH_LONG).show();

                    JSONArray array=jsonObject.getJSONArray("eventinfo");
                    Toast.makeText(events.this,"2",Toast.LENGTH_LONG).show();

                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject o=array.getJSONObject(i);
                        eventname=o.getString("event_name");
                        eventlocation=o.getString("event_location");
                        eventdate=o.getString("event_date");

                         // Toast.makeText(events.this,o.getString("district").toString(),Toast.LENGTH_LONG).show();

                    }
                    eventname1.setText(eventname);
                    eventlocation1.setText(eventlocation);
                    eventdate1.setText(eventdate);
                    Toast.makeText(getApplicationContext(),eventname,Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(),eventlocation,Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(),eventdate,Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }
        ){

            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put(KEY_EVENTID,event_id);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

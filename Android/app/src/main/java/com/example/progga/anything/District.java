package com.example.progga.anything;

import android.app.LauncherActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class District extends AppCompatActivity {
    String division;
    ListView listView;
   ArrayList<String> district2=new ArrayList<>();
    static final String KEY_DIVISION = "division";

    private static  String URL=database.districtURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district);
        division =getIntent().getStringExtra ("Division");
//String district2[]={"Dhaka","Barisal","Hhhhh"};
        listView=findViewById(R.id.list);
  // volley();
loadRecyclerviewData();
       // ArrayAdapter arrayAdapter=new ArrayAdapter(District.this,R.layout.listitem,R.id.listitem,district2);
       // listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String district = (String) listView.getItemAtPosition(position).toString();
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("District",district);
                startActivity(intent);

            }
        });

    }
    private void loadRecyclerviewData()
    {
        Toast.makeText(getApplicationContext(),division,Toast.LENGTH_LONG).show();

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

                    JSONArray array=jsonObject.getJSONArray("district1");
                    //Toast.makeText(getApplicationContext(),"3.1",Toast.LENGTH_LONG).show();

                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject o=array.getJSONObject(i);
                       district2.add(o.getString("district"));
                      //  Toast.makeText(getApplicationContext(),o.getString("district").toString(),Toast.LENGTH_LONG).show();

                    }
                    ArrayAdapter arrayAdapter=new ArrayAdapter(District.this,R.layout.listitem,R.id.listitem,district2);
                   listView.setAdapter(arrayAdapter);
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
                params.put(KEY_DIVISION,division);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}

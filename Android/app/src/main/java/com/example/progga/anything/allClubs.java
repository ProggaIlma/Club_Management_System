package com.example.progga.anything;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * Created by progga on 8/27/18.
 */

public class allClubs extends Fragment {
    RecyclerView mRecyclerView;
    ClubAdapter mAdapter;
    ArrayList<String> club=new ArrayList();
    ArrayList<String> clubid=new ArrayList<String>();
    String district=MainActivity.district;
    public static String KEY_DISTRICT="district";
    public static String URL=database.clubURL;
    RecyclerView.LayoutManager mLayoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.allclubs, container, false);
        mRecyclerView= (RecyclerView) rootView.findViewById(R.id.recyclerview);
      //  mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //String[] spacecrafts={"Math Club","Science Club","History Club","Debate Club"};
        // specify an adapter (see also next example)

Toast.makeText(getActivity(),district,Toast.LENGTH_LONG).show();
        //ArrayAdapter arrayAdapter=new ArrayAdapter(getActivity(),R.layout.club_listitem,R.id.text,array);
      // listView.setAdapter(arrayAdapter);
/*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), events.class);
        startActivity(intent);

    }
});*/
loadRecyclerviewData();

        return rootView;
    }
    private void loadRecyclerviewData()
    {
        Toast.makeText(getActivity(),district,Toast.LENGTH_LONG).show();

        final ProgressDialog progressDialog=new ProgressDialog(getActivity());

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

                    JSONArray array=jsonObject.getJSONArray("clubs");
                    //Toast.makeText(getApplicationContext(),"3.1",Toast.LENGTH_LONG).show();

                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject o=array.getJSONObject(i);
                        club.add(o.getString("clubname"));
                        clubid.add(o.getString("id_fk"));
                        //  Toast.makeText(getApplicationContext(),o.getString("district").toString(),Toast.LENGTH_LONG).show();

                    }
                    mAdapter = new ClubAdapter(getActivity(),club,clubid);
                    mRecyclerView.setAdapter(mAdapter);                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }
        ){

            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put(KEY_DISTRICT,district);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
}

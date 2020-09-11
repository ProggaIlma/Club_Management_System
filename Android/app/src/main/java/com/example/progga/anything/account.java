package com.example.progga.anything;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
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

import java.util.HashMap;
import java.util.Map;

/**
 * Created by progga on 8/27/18.
 */

public class account extends Fragment{
    int check =0;
    TextView name1,email1,school1;
    String Id=login.userno;
    public static String KEY_ID="Id";
    public static String URL=database.myprofile;
    String name,email,school;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.account, container, false);
        String[] planets_array={"Math Club","Debate Club","Science Club","History Club"};
         name1=rootView.findViewById(R.id.username2);
         email1=rootView.findViewById(R.id.email);
         school1=rootView.findViewById(R.id.school);
        Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item,planets_array);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

       // spinner.setSelection(0,false);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                if (++check > 1) {
                    Intent intent = new Intent(getActivity(), events.class);

                    startActivity(intent);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });
        loadRecyclerviewData();
        return rootView;
    } private void loadRecyclerviewData()
    {
        Toast.makeText(getActivity(),Id,Toast.LENGTH_LONG).show();

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
                     Toast.makeText(getActivity(),Id,Toast.LENGTH_LONG).show();

                    JSONArray array=jsonObject.getJSONArray("studentinfo");
                    //Toast.makeText(getApplicationContext(),"3.1",Toast.LENGTH_LONG).show();

                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject o=array.getJSONObject(i);
                        name=o.getString("username");
                        email=o.getString("email");
                        school=o.getString("Institution");
                        Toast.makeText(getActivity(),name,Toast.LENGTH_LONG).show();
                        Toast.makeText(getActivity(),email,Toast.LENGTH_LONG).show();


                        Toast.makeText(getActivity(),school,Toast.LENGTH_LONG).show();

                    }
                    name1.setText(name);
                    email1.setText(email);
                    school1.setText(school);

                } catch (Exception e) {
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
                params.put(KEY_ID,Id);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

}

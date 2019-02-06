package com.form.user.pharmacy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DeleteMedicineActivity extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4,ed5;
    Button search,delete,back;
    String name,cmpanyname,expiry,price,quantity;
    String SearchApiUrl="http://192.168.43.44/Pharmacy/medicineSearchApi.php";
    String DeleteApiUrl="http://192.168.43.44/Pharmacy/medicineDeleteApi.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_medicine);
        ed1=(EditText)findViewById(R.id.nm);
        ed2=(EditText)findViewById(R.id.compnm);
        ed3=(EditText)findViewById(R.id.exp);
        ed4=(EditText)findViewById(R.id.prc);
        ed5=(EditText)findViewById(R.id.quan);

        search=(Button)findViewById(R.id.srch);

        delete=(Button)findViewById(R.id.dlt);
        back=(Button)findViewById(R.id.menu);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MenuActivity.class);
                startActivity(i);
            }
        });



        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=ed1.getText().toString();
                searchToDb();
            }
        });



        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteToDb();
            }
        });


    }

    private void deleteToDb() {

        StringRequest stringRequest=new StringRequest(Request.Method.POST, DeleteApiUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String a=jsonObject.getString("status");

                    if(a.equals("successfully deleted")){
                        Toast.makeText(getApplicationContext(),"Successfully Deleted",Toast.LENGTH_LONG).show();
                        Intent i=new Intent(getApplicationContext(),MenuActivity.class);
                        startActivity(i);
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                    }
                }
                catch (JSONException ob){
                    Toast.makeText(getApplicationContext(),ob.toString(),Toast.LENGTH_LONG).show();
                }
//                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String, String>();
                params.put("nm",name);
                return params;
            }
        };
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }






    private void searchToDb() {

        StringRequest stringRequest=new StringRequest(Request.Method.POST, SearchApiUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try{
                    JSONObject jsonObject=new JSONObject(response);
                    String a=jsonObject.getString("company");
                    String b=jsonObject.getString("expirydate");
                    String c=jsonObject.getString("price");
                    String d=jsonObject.getString("quantity");
                    ed2.setText(a);
                    ed3.setText(b);
                    ed4.setText(c);
                    ed5.setText(d);

                }
                catch (JSONException ob){
                    Toast.makeText(getApplicationContext(),ob.toString(),Toast.LENGTH_LONG).show();
                }
//                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String, String>();
                params.put("nm",name);
                return params;
            }
        };
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}

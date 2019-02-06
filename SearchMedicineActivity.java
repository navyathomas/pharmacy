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

public class SearchMedicineActivity extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4,ed5;
    Button search,menu;
    String medicname,companyname,expdate,prc,quant;
    String SearchApiUrl="http://192.168.43.44/Pharmacy/medicineSearchApi.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_medicine);
        ed1=(EditText)findViewById(R.id.nm);
        ed2=(EditText)findViewById(R.id.compnm);
        ed3=(EditText)findViewById(R.id.date);
        ed4=(EditText)findViewById(R.id.prc);
        ed5=(EditText)findViewById(R.id.quan);


        search=(Button)findViewById(R.id.srch);
        menu=(Button)findViewById(R.id.bck);



        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MenuActivity.class);
                startActivity(i);
            }
        });



        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medicname = ed1.getText().toString();
                searchToDb();
            }
        });


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
//                    Toast.makeText(getApplicationContext(),a,Toast.LENGTH_LONG).show();

                }
                catch (JSONException ob){
                    Toast.makeText(getApplicationContext(),ob.toString(),Toast.LENGTH_LONG).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("nm",medicname);
                return params;
            }
        };
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

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

public class AddmedicineActivity extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4,ed5;
    Button add,backmenu;
    String medicname,compname,expirydate,price,quantity;
    String AddUrl="http://192.168.43.44/Pharmacy/medicineInsertionApi.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmedicine);
        ed1=(EditText)findViewById(R.id.nm);
        ed2=(EditText)findViewById(R.id.comp);
        ed3=(EditText)findViewById(R.id.exp);
        ed4=(EditText)findViewById(R.id.prc);
        ed5=(EditText)findViewById(R.id.quan);

        add=(Button)findViewById(R.id.ad);
        backmenu=(Button)findViewById(R.id.menu);

        backmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MenuActivity.class);
                startActivity(i);
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medicname=ed1.getText().toString();
                compname=ed2.getText().toString();
                expirydate=ed3.getText().toString();
                price=ed4.getText().toString();
                quantity=ed5.getText().toString();

//                Toast.makeText(getApplicationContext(),"Successfully added",Toast.LENGTH_LONG).show();


                addToDb();
            }
        });

    }

    private void addToDb() {

        StringRequest stringRequest=new StringRequest(Request.Method.POST, AddUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject=new JSONObject(response);
                    String a=jsonObject.getString("status");

                    if(a.equals("successfully added")){
                        Toast.makeText(getApplicationContext(),"medicine details added",Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
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
                params.put("nm",medicname);
                params.put("comp",compname);
                params.put("exp",expirydate);
                params.put("prce",price);
                params.put("quan",quantity);
                return params;
            }
        };
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

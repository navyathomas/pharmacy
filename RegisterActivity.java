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

public class RegisterActivity extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4,ed5,ed6;
    Button register,backlogin;
    String namee,mobile,email,username,password,confirmpassword;
    String ApiUrl="http://192.168.43.44/Pharmacy/registerInsertionApi.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ed1=(EditText)findViewById(R.id.nm);
        ed2=(EditText)findViewById(R.id.mob);
        ed3=(EditText)findViewById(R.id.mail);
        ed4=(EditText)findViewById(R.id.usr);
        ed5=(EditText)findViewById(R.id.pass);
        ed6=(EditText)findViewById(R.id.conf);

        register=(Button)findViewById(R.id.reg);
        backlogin=(Button)findViewById(R.id.bck);


        backlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),FirstActivity.class);
                startActivity(i);
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                namee=ed1.getText().toString();
                mobile=ed2.getText().toString();
                email=ed3.getText().toString();
                username=ed4.getText().toString();
                password=ed5.getText().toString();
                confirmpassword=ed6.getText().toString();

                if(password.equals(confirmpassword)){
                    Toast.makeText(getApplicationContext(),"password and confirm password match",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Password or Confirm Password doesnt match",Toast.LENGTH_LONG).show();
                }

                sendToDb();
            }
        });
    }

    private void sendToDb() {

        StringRequest stringRequest=new StringRequest(Request.Method.POST, ApiUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try{
                    JSONObject jsonObject=new JSONObject(response);
                    String a=jsonObject.getString("status");

                    if(a.equals("successfully inserted")){
                        Toast.makeText(getApplicationContext(),"successfully registered ",Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
                    }


                }
                catch (JSONException ob){
                    Toast.makeText(getApplicationContext(),ob.toString(),Toast.LENGTH_LONG).show();
                }




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
                params.put("nm",namee);
                params.put("mb",mobile);
                params.put("em",email);
                params.put("usr",username);
                params.put("pass",password);
                return params;
            }
        };
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}

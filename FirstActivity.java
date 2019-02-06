package com.form.user.pharmacy;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class FirstActivity extends AppCompatActivity {
    EditText ed1,ed2;
    Button log,reg;
    String usernamee,passwordd;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String LoginApiUrl="http://192.168.43.44/Pharmacy/loginApi.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        ed1=(EditText)findViewById(R.id.usr);
        ed2=(EditText)findViewById(R.id.pass);
        log=(Button)findViewById(R.id.log);
        reg=(Button)findViewById(R.id.reg);
        pref=getApplicationContext().getSharedPreferences("mypref",0);
        editor=pref.edit();




        String a=pref.getString("usr",null);
        if(a!=null){
            Intent i=new Intent(getApplicationContext(),MenuActivity.class);
            startActivity(i);
        }




        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernamee=ed1.getText().toString();
                passwordd=ed2.getText().toString();

                if((usernamee).equals("xyz")&&(passwordd).equals("123")){

                    editor.putString("usr",usernamee);
                    editor.putString("pass",passwordd);
                    editor.commit();

                    Intent i=new Intent(getApplicationContext(),MenuActivity.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Invalid username or password",Toast.LENGTH_LONG).show();
                }
                sendToDb();
            }
        });




        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(i);
            }
        });
    }




    private void sendToDb() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, LoginApiUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
//                try {
//                    JSONObject jsonObject=new JSONObject(response);
////
//                }
//                catch (JSONException ob){
//                    Toast.makeText(getApplicationContext(),ob.toString(),Toast.LENGTH_LONG).show();
//                }
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
                params.put("usr",usernamee);
                params.put("pass",passwordd);
                return params;
            }
        };
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

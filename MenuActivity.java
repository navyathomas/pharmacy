package com.form.user.pharmacy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {
    Button addd,editt,searchh,deletee,logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        addd=(Button)findViewById(R.id.add);
        editt=(Button)findViewById(R.id.edit);
        searchh=(Button)findViewById(R.id.srch);
        deletee=(Button)findViewById(R.id.dlt);
        logout=(Button)findViewById(R.id.log);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref=getApplicationContext().getSharedPreferences("mypref",0);
                SharedPreferences.Editor editor=pref.edit();

                editor.remove("usr");
                editor.remove("pass");
                editor.commit();

                Intent i=new Intent(getApplicationContext(),FirstActivity.class);
                startActivity(i);

            }
        });


          addd.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent i=new Intent(getApplicationContext(),AddmedicineActivity.class);
                  startActivity(i);
              }
          });



          editt.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent i=new Intent(getApplicationContext(),EditMedicineActivity.class);
                  startActivity(i);
              }
          });


          searchh.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent i=new Intent(getApplicationContext(),SearchMedicineActivity.class);
                  startActivity(i);
              }
          });



          deletee.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent i=new Intent(getApplicationContext(),DeleteMedicineActivity.class);
                  startActivity(i);
              }
          });

    }
}

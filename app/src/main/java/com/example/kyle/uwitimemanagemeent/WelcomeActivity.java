package com.example.kyle.uwitimemanagemeent;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.IOException;


public class WelcomeActivity extends AppCompatActivity {

    // private  View v;
    private Button back,calendar;
    private TextView tv;
    private UserSessionActivity session;
    private ImageView image;
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_welcome);
        image=(ImageView)findViewById(R.id.imageView);
        back=(Button)findViewById(R.id.button3);
        back.setOnClickListener(new  Button_Clicker());
        myDb = new DatabaseHelper(getApplicationContext());
        calendar=(Button)findViewById(R.id.button2);
        calendar.setOnClickListener(new  Button_Clicker());
        // v=getWindow().getDecorView();
        FacultyPicked f=new FacultyPicked(getApplicationContext());
        int icon=f.getIcon();
        image.setImageResource(icon);

        String text="";
        try{
            FileInputStream i= openFileInput("name.txt");
            int size=i.available();
            byte[] buffer= new  byte[size];
            i.read(buffer);
            i.close();
            text=new  String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        tv=(TextView)findViewById(R.id.textView);
        tv.setText("Welcome "+ text);
        session = new UserSessionActivity(getApplicationContext());



    }
    class Button_Clicker implements Button.OnClickListener

    {

        @Override

        public void onClick(View v) {
            if (v == back) {
                myDb.deleteAllDataTip();
                session.writeLoginStatus(false);
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
                finish();

            }

            if(v==calendar){
                Intent i2 = new Intent(getApplicationContext(), DefaultMonthlyCalendarActivity.class);
                startActivity(i2);
            }
        }
    }

}

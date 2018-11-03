package com.example.kyle.uwitimemanagemeent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.IOException;


public class EngineActivity extends AppCompatActivity {

    // private  View v;
    private Button back;
    private TextView tv;
    private UserSessionActivity session;
    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_engine);
        image=(ImageView)findViewById(R.id.imageView);
        back=(Button)findViewById(R.id.button3);
        back.setOnClickListener(new  Button_Clicker());
        // v=getWindow().getDecorView();
        String b="";
        try{
            FileInputStream i1= openFileInput("faculty.txt");
            int size1=i1.available();
            byte[] buffer1= new  byte[size1];
            i1.read(buffer1);
            i1.close();
            b=new  String(buffer1);
        } catch (IOException e) {
            e.printStackTrace();
           }

           if(b.equals("Faculty of Engineering")){
              image.setImageResource(R.drawable.engine);
           }
        if(b.equals("Faculty of Food and Agriculture")){
            image.setImageResource(R.drawable.food);
        }
        if(b.equals("Faculty of Science and Technology")){
            image.setImageResource(R.drawable.tech);
        }
        if(b.equals("Faculty of Medical Sciences")){
            image.setImageResource(R.drawable.med);
        }
        if(b.equals("Faculty of Social Sciences")){
            image.setImageResource(R.drawable.oc);
        }
        if(b.equals("aculty of Humanities and Education")){
            image.setImageResource(R.drawable.human);
        }
        if(b.equals("Faculty of Law")){
            image.setImageResource(R.drawable.la);
        }

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
                session.writeLoginStatus(false);
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
                finish();

            }
        }
    }

}

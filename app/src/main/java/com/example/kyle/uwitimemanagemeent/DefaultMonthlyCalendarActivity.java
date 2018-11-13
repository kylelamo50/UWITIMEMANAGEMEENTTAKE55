package com.example.kyle.uwitimemanagemeent;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class DefaultMonthlyCalendarActivity extends AppCompatActivity {
    ArrayList<String> text;
    Dialog myDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.default_monthly_calendar);
        myDialog=new Dialog(this);
    }
    public void showPopUp(View v){
        TextView ClosePopUp;
        EditText e;
        text.add("a");
        text.add("b");
        text.add("c");
        text.add("d");
        Random rand=new Random();
        int num=rand.nextInt(4) +1;
        myDialog.setContentView(R.layout.popup_xml);

        e=(EditText) myDialog.findViewById(R.id.editText);
        e.setText(text.get(num));
        ClosePopUp=(TextView)myDialog.findViewById(R.id.textView3);
        ClosePopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }
}

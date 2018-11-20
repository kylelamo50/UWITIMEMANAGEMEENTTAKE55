package com.example.kyle.uwitimemanagemeent;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
        TextView t;

        text=new ArrayList<>();
        text.add("Don't drink and drive.Stay in school an party hard");
        text.add("Make sure you’re engaging in activities that support your business goals, both short- and long-term. Everything else is a potential time-waster");
        text.add("Prioritize wisely.Looking at what goes into making up your day, where do your activities fit into these categories?");
        text.add("You’re the boss. If you have to decline a request in order to attend to what’s truly important and urgent, do not hesitate to do so.");
        text.add("One of the worst things you can do is jump into the workday with no clear idea about what needs to get done.Hence, use this app everyday :)");
        text.add("Start paying attention to the number of times someone interrupts you when you’re in the midst of an important task. Track self-induced interruptions, too, particularly those of the social media variety. Your smartphone is extremely useful, but it’s also addictive.");
        text.add("Running a successful small business depends upon the owner’s ability to think about what lies ahead and not get mired in day-to-day operations");
        text.add("Be sure to get plenty of sleep and exercise. An alert mind is a high-functioning mind and one that’s less tolerant of time-wasting activities.");
        text.add("Don't focus on gyal.Let gyal focus on u");
        text.add("Fellas be who u want to be.You are more than a 3.6 gpa");
        Random rand=new Random();
        int num=rand.nextInt(10);
        myDialog.setContentView(R.layout.popup_xml);

        t=(TextView) myDialog.findViewById(R.id.textView4);
        t.setText(text.get(num));
        ClosePopUp=(TextView)myDialog.findViewById(R.id.textView3);
        ClosePopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
}

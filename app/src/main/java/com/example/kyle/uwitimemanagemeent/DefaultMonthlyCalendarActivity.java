package com.example.kyle.uwitimemanagemeent;

import android.app.Dialog;
import android.app.usage.UsageEvents;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import java.util.ArrayList;
import java.util.Random;

public class DefaultMonthlyCalendarActivity extends AppCompatActivity {
    ArrayList<String> text;
    Dialog myDialog;
    Button add;
    String s = "";
    Intent i;
    DatabaseHelper myDb;
    CalendarView calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.default_monthly_calendar);
        myDialog=new Dialog(this);
        calendar=(CalendarView) findViewById(R.id.calendarView);
        add = (Button)findViewById(R.id.add_task_button);
        myDb = new DatabaseHelper(getApplicationContext());
        myDb.getAllData();
        myDb = new DatabaseHelper(getApplicationContext());
        myDb.getAllData();

        //myDb.deleteData("1");
        initializeCalendar();
       // Cursor res = myDb.getAllData();
       // while (res.moveToNext()) {
        //    res.getLong(2);
       // }
        


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == add) {
                    i = new Intent(getApplicationContext(), AddTask.class);
                    startActivity(i);

                } }});
    }

    public void initializeCalendar() {
        calendar = (CalendarView) findViewById(R.id.calendarView);

        // sets whether to show the week number.
        calendar.setShowWeekNumber(false);

        // sets the first day of week according to Calendar.
        // here we set Monday as the first day of the Calendar
        calendar.setFirstDayOfWeek(2);

        //The background color for the selected week.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            calendar.setSelectedWeekBackgroundColor(getResources().getColor(R.color.green));
        }

        //sets the color for the dates of an unfocused month.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            calendar.setUnfocusedMonthDateColor(getResources().getColor(R.color.transparent));
        }

        //sets the color for the separator line between weeks.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            calendar.setWeekSeparatorLineColor(getResources().getColor(R.color.transparent));
        }

        //sets the color for the vertical bar shown at the beginning and at the end of the selected date.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            calendar.setSelectedDateVerticalBar(R.color.darkgreen);
        }

        //sets the listener to be notified upon selected date change.
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            //show the selected date as a toast
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
                Toast.makeText(getApplicationContext(), day + "/" + month + "/" + year, Toast.LENGTH_LONG).show();
            }
        });
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

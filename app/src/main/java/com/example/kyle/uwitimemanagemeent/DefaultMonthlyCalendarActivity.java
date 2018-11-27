package com.example.kyle.uwitimemanagemeent;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


import android.content.Intent;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;

import java.util.Calendar;


import java.util.ArrayList;
import java.util.Random;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DefaultMonthlyCalendarActivity extends AppCompatActivity {

   private ArrayList<String> text;
    Dialog myDialog,myDialog2;

    ImageButton add;
    String s = "";
    Intent i;
    DatabaseHelper myDb;
    SchoolDataBaseHelper myDb2;
    CompactCalendarView compactCalendar;
    int count=0;
    int count2=0;

    ArrayList<Event> ev1;
    ArrayList<Event> ev2;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.default_monthly_calendar);
        myDialog = new Dialog(this);
        myDialog2 = new Dialog(this);
        compactCalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendar.setUseThreeLetterAbbreviation(true);
        add = (ImageButton) findViewById(R.id.add_task_button);

        myDb = new DatabaseHelper(getApplicationContext());
        myDb.getAllData();
        myDb2 = new SchoolDataBaseHelper(getApplicationContext());
        myDb2.getAllData();
        //myDb.deleteData("1");

       final ActionBar actionBar = getSupportActionBar();
       actionBar.setDisplayHomeAsUpEnabled(false);
       actionBar.setTitle("November - 2018");

        Cursor res = myDb.getAllData();
        Cursor res2 = myDb2.getAllData();
       // random();

        ev1=new ArrayList<>();
        ev2=new ArrayList<>();
        String buffer = " ";
        String buffer2 = " ";
        //int id=1;
        while (res.moveToNext()) {

            buffer+=("Id :"+ res.getString(0)+"\n");
            buffer+=("Title :"+ res.getString(1)+"\n");
            buffer+=("Start Date :"+ getDate(res.getLong(2))+"\n");
            buffer+=("End Date :"+ getDate(res.getLong(3))+"\n");
            buffer+=("StarT Time: "+ getTime(res.getLong(4))+"\n");
            buffer+=("End Time: "+ getTime(res.getLong(5))+"\n");
            buffer+=("Note: "+ res.getString(6)+"\n\n");

            ev1.add(new Event(Color.RED, res.getLong(2), buffer));
            compactCalendar.addEvent(ev1.get(count));
            count++;
            buffer= " ";
        }

        while (res2.moveToNext()) {

            buffer2+=("Id :"+ res2.getString(0)+"\n");
            buffer2+=("Title :"+ res2.getString(1)+"\n");
            buffer2+=("Start Date :"+ getDate(res2.getLong(2))+"\n");
            buffer2+=("End Date :"+ getDate(res2.getLong(3))+"\n");
            buffer2+=("StarT Time: "+ getTime(res2.getLong(4))+"\n");
            buffer2+=("End Time: "+ getTime(res2.getLong(5))+"\n");
            buffer2+=("Location: "+ res2.getString(6)+"\n");
            buffer2+=("Class: "+ res2.getString(7)+"\n");
            buffer2+=("Note: "+ res2.getString(8)+"\n\n");

            ev2.add(new Event(Color.RED, res2.getLong(2), buffer2));
            compactCalendar.addEvent(ev2.get(count2));
            count2++;
            buffer2= " ";
        }

        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {

            @Override
            public void onDayClick(Date dateClicked) {
                Context context = getApplicationContext();
                DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
                Date date = null;
                try {
                    date = (Date)formatter.parse(dateClicked.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                String formatedDate = cal.get(Calendar.DATE) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" +cal.get(Calendar.YEAR);
                Toast.makeText(context,  formatedDate, Toast.LENGTH_SHORT).show();

                String display=" ";
                boolean isEvent=false;
                int k=0;
                String DataInPopUp=" ";
                while(k<count){
                    display=ev1.get(k).getData().toString();

                    if(display.contains(formatedDate)){
                        DataInPopUp+=display;
                        isEvent=true;
                    }
                    display=" ";
                    k++;
                }

                String display2=" ";
                boolean isEvent2=false;
                int k2=0;
                String DataInPopUp2=" ";
                while(k2<count2){
                    display2=ev2.get(k2).getData().toString();

                    if(display2.contains(formatedDate)){
                        DataInPopUp2+=display2;
                        isEvent2=true;
                    }
                    display2=" ";
                    k2++;
                }


                if (isEvent==true || isEvent2==true){
                    myDialog2.setContentView(R.layout.task_pop_up);
                    TextView dataInTask;
                    TextView ClosePopUp2;
                    dataInTask=(TextView) myDialog2.findViewById(R.id.data2);
                    dataInTask.setText(DataInPopUp +"\n" + DataInPopUp2);
                    ClosePopUp2=(TextView)myDialog2.findViewById(R.id.close2);
                    ClosePopUp2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            myDialog2.dismiss();
                        }
                    });
                    myDialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    myDialog2.show();
                }
                if(isEvent==false){
                    Toast.makeText(context,  "NO EVENT ADDED", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                actionBar.setTitle(dateFormatMonth.format(firstDayOfNewMonth));
            }
        });



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == add) {
                    i = new Intent(getApplicationContext(), AddTask.class);
                    startActivity(i);

                } }});
    }



    public String getDate(long timeStamp){

        try{
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date netDate = (new Date(timeStamp));
            return sdf.format(netDate);
        }
        catch(Exception ex){
            return "xx";
        }
    }

    public String getTime(long timeStamp){

        try{
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            Date netDate = (new Date(timeStamp));
            return sdf.format(netDate);
        }
        catch(Exception ex){
            return "xx";
        }
    }

      public void showPopUp(View v){
   // public void random(){
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

        t=(TextView) myDialog.findViewById(R.id.data);
        t.setText(text.get(num));
        ClosePopUp=(TextView)myDialog.findViewById(R.id.close);
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

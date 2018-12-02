package com.example.kyle.uwitimemanagemeent;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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

import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

public class DefaultMonthlyCalendarActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private ArrayList<String> text;
    Dialog myDialog,myDialog2;
    ImageButton add;
    String s = "";
    Intent i;
    DatabaseHelper myDb;
    //SchoolDataBaseHelper myDb2;
    CompactCalendarView compactCalendar;
    int count=0;
    int count2=0;
    String formattedDate="";

    ArrayList<Event> ev1;
    ArrayList<Event> ev2;
    ArrayList<Event> compareStartDate1;
    ArrayList<Event> compareStartDate2;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.default_monthly_calendar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView =  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setTitle("November - 2018");
        myDialog = new Dialog(this);
        myDialog2 = new Dialog(this);
        compactCalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendar.setUseThreeLetterAbbreviation(true);
        add = (ImageButton) findViewById(R.id.add_task_button);

        myDb = new DatabaseHelper(getApplicationContext());
       // myDb.getAllData();
        //myDb2 = new SchoolDataBaseHelper(getApplicationContext());
       // myDb2.getAllData();
       // myDb.deleteData("1");
        //myDb2.deleteData("2");

     //  final ActionBar actionBar = getSupportActionBar();
     //  actionBar.setDisplayHomeAsUpEnabled(false);
      // actionBar.setTitle("November - 2018");

        Cursor res = myDb.getAllDataGeneral();
        Cursor res2 = myDb.getAllDataSchool();
       // random();

        ev1=new ArrayList<>();
        ev2=new ArrayList<>();
        compareStartDate1=new ArrayList<>();
        compareStartDate2=new ArrayList<>();
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
            compareStartDate1.add(new Event(Color.TRANSPARENT, res.getLong(2),getDate(res.getLong(2))));

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

            ev2.add(new Event(Color.BLUE, res2.getLong(2), buffer2));
            compareStartDate2.add(new Event(Color.TRANSPARENT, res2.getLong(2),getDate(res2.getLong(2))));
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
                formattedDate = cal.get(Calendar.DATE) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" +cal.get(Calendar.YEAR);
                Toast.makeText(context,  formattedDate, Toast.LENGTH_SHORT).show();

                String display=" ";
                boolean isEvent=false;
                int k=0;
                String DataInPopUp=" ";
                while(k<count){
                    display=ev1.get(k).getData().toString();

                    if(compareStartDate1.get(k).getData().toString().contains(formattedDate) ){
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

                    if(compareStartDate2.get(k2).getData().toString().contains(formattedDate)){
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
                else
                    {
                    Toast.makeText(context,  "NO EVENT ADDED", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent (getApplicationContext(),AddTask.class);
                    i.putExtra("Extra", formattedDate);
                    startActivity(i);
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
                    String date="Start Date";
                    i = new Intent(getApplicationContext(), AddTask.class);
                    i.putExtra("Extra", date);
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

        Cursor r=myDb.getAllDataTip();
        int countTip=0;
        while (r.moveToNext()){
            countTip++;
        }
       //   Toast.makeText(DefaultMonthlyCalendarActivity.this, "f"+ countTip, Toast.LENGTH_SHORT).show();
        Random rand=new Random();
        int num=rand.nextInt(countTip-1 + 1) + 1;
        myDialog.setContentView(R.layout.popup_xml);
        t=(TextView) myDialog.findViewById(R.id.data);

        Cursor rTip=myDb.getTipDataBasedOnId(num);
        rTip.moveToFirst();
       // Toast.makeText(DefaultMonthlyCalendarActivity.this, "f"+ num, Toast.LENGTH_SHORT).show();

        t.setText(rTip.getString(rTip.getColumnIndex("TIP")));
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
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_general) {
            Intent i = new Intent(getApplicationContext(), CardActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_school) {
            //Snackbar.make(findViewById(R.id.drawer_layout) , "Replace with your own action", Snackbar.LENGTH_LONG)
                   // .setAction("Action", null).show();
            Intent i2 = new Intent(getApplicationContext(), SchoolCardActivity.class);
            startActivity(i2);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}


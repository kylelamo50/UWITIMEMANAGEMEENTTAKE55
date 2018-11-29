package com.example.kyle.uwitimemanagemeent;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CardActivity extends AppCompatActivity {

    private RecyclerView rv;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager lm;
    DatabaseHelper myDb;
    ArrayList<Card> cards;
    private static String LOG_TAG = "CardViewActivity";
    private ImageButton delete;
    private Button open;
    private int posID=0;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        delete = (ImageButton)findViewById(R.id.delete_button);
        open = (Button)findViewById(R.id.open_button);
        myDb = new DatabaseHelper(getApplicationContext());

        Cursor res = myDb.getAllDataGeneral();
        if(res.getCount() == 0) {
            // show message
            Toast.makeText(getApplicationContext(),"Database Empty!",Toast.LENGTH_LONG).show();
            Intent i=new Intent(getApplicationContext(),AddTask.class);
            startActivity(i);
        }

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


        cards = new ArrayList<Card>();
        FacultyPicked f=new FacultyPicked(getApplicationContext());
        int icon=f.getIcon();

        while (res.moveToNext()) {
            cards.add(new Card(res.getString(0), res.getString(1),getDate(res.getLong(2)),icon));

        }
       // cards.add(new Card(null," "," ",0));

        rv = (RecyclerView) findViewById(R.id.my_recycler_view);
        rv.setHasFixedSize(true);
        lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        mAdapter = new MyRecyclerViewAdapter(cards);
        rv.setAdapter(mAdapter);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Card c;
            //    Toast.makeText(getApplicationContext(), "ccc" + posID + "ccc"+cards.size(), Toast.LENGTH_LONG).show();
              //  if(posID== cards.size()-2){
                    c = cards.get(posID);
                   // Toast.makeText(getApplicationContext(), "ccc" +posID+"eee"+ c.getId(), Toast.LENGTH_LONG).show();
                    boolean a=myDb.deleteItemGeneral(c.getId());
                   // Toast.makeText(getApplicationContext(), "ccc" + a, Toast.LENGTH_LONG).show();
                   // cards.remove(posID+1);
                    cards.remove(posID);

                //     }
                 //    else {
                //    cards.remove(posID);
                //    c = cards.get(posID);
               //       Toast.makeText(getApplicationContext(), "ccc" + posID + "ggg " + c.getId(), Toast.LENGTH_LONG).show();
                   // boolean a = myDb.deleteItemGeneral(c.getId());
                    // Toast.makeText(getApplicationContext(), "ccc" + a, Toast.LENGTH_LONG).show();
              //  }

                mAdapter.notifyItemRemoved(posID);
            }});
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == open) {
                    Intent i = new Intent(getApplicationContext(), ShowTaskDetails.class);
                    startActivity(i);

                } }});

    }

    @Override
    protected void onResume() {
        super.onResume();
        ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                posID = position;
                delete.setVisibility(View.VISIBLE);
                open.setVisibility(View.VISIBLE);
                Snackbar.make(findViewById(R.id.my_recycler_view) , Integer.toString(position+1)+") "+cards.get(position).Title+" selected", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .setDuration(250)
                        .show();
            }
        });

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

}



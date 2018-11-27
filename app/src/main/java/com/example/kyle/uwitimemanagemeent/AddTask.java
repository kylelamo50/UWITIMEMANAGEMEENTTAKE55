package com.example.kyle.uwitimemanagemeent;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddTask extends AppCompatActivity {
    private Button school;
    private Button gym;
    private Button party;
    private Button general;
    Intent i,i2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        school = findViewById(R.id.school_btn);
        gym = findViewById(R.id.gym_btn);
        party = findViewById(R.id.party_btn);
        general = findViewById(R.id.general_btn);

        i = new Intent(this,AddTaskDetails.class);
        i2= new Intent(this,AddSchoolTask.class);

        school.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                      startActivity(i2);
            }
        });
        gym.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //      startActivity(i);
            }
        });
        party.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //      startActivity(i);
            }
        });
        general.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(i);
            }
        });
    }
}

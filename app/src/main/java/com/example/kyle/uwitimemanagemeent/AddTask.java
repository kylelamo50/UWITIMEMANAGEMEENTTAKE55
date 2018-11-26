package com.example.kyle.uwitimemanagemeent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddTask extends AppCompatActivity {
    private Button school;
    private Button gym;
    private Button party;
    private Button general;
    Intent i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        school = findViewById(R.id.school_btn);
        gym = findViewById(R.id.gym_btn);
        party = findViewById(R.id.party_btn);
        general = findViewById(R.id.general_btn);

        i = new Intent(this,AddTaskDetails.class);
        i.putExtra("key","");

        school.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //      startActivity(i);
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

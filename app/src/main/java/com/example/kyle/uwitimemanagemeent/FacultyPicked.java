package com.example.kyle.uwitimemanagemeent;

import android.content.Context;
import android.view.View;

import java.io.FileInputStream;
import java.io.IOException;

public class FacultyPicked {

    Context c;

    public FacultyPicked(Context context) {
        this.c = context;
    }

    public int getIcon() {
        int i=0;
        String b = "";
        try {
            FileInputStream i1 = c.openFileInput("faculty.txt");
            int size1 = i1.available();
            byte[] buffer1 = new byte[size1];
            i1.read(buffer1);
            i1.close();
            b = new String(buffer1);
        } catch (IOException e) {
            e.printStackTrace();
        }


        if(b.equals("Faculty of Engineering")){
            i=R.drawable.engine;
        }
        if(b.equals("Faculty of Food and Agriculture")){
            i=R.drawable.food;
        }
        if(b.equals("Faculty of Science and Technology")){
            i=R.drawable.tech;
        }
        if(b.equals("Faculty of Medical Sciences")){
            i=R.drawable.med;
        }
        if(b.equals("Faculty of Social Sciences")){
            i=R.drawable.oc;
        }
        if(b.equals("aculty of Humanities and Education")){
            i=R.drawable.human;
        }
        if(b.equals("Faculty of Law")){
            i=R.drawable.la;
        }
        return i;
    }
}




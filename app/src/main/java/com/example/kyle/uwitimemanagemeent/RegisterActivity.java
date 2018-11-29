package com.example.kyle.uwitimemanagemeent;



import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {
    private Button reg;
    private RadioButton rb;
    private RadioGroup rg;
    UserSessionActivity session;
    private EditText userName;
    private static final String FILE_NAME = "name.txt";
    private static final String FILE_NAME2 = "faculty.txt";
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        reg = (Button) findViewById(R.id.button);
        rg = (RadioGroup) findViewById(R.id.radioGroup);
        userName = (EditText) findViewById(R.id.editText);
        reg.setOnClickListener(new Button_Clicker());
        session = new UserSessionActivity(getApplicationContext());
        myDb = new DatabaseHelper(getApplicationContext());

        if (session.readLoginStatus()==false) {
            preLoadTips();
            Toast.makeText(RegisterActivity.this, "LOADING TIPS", Toast.LENGTH_SHORT).show();
            Cursor res = myDb.getAllDataTip();
            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {
                buffer.append("Tip :" + res.getString(1) + "\n");
            }
            showMessage("Data",buffer.toString());
        }


        if (session.readLoginStatus()) {
            Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);


            startActivity(intent);
            finish();

        }

    }

    void preLoadTips(){
        myDb.insertTipData("Don't drink and drive.Stay in school an party hard");
        myDb.insertTipData("Make sure you’re engaging in activities that support your business goals, both short- and long-term. Everything else is a potential time-waster");
        myDb.insertTipData("Prioritize wisely.Looking at what goes into making up your day, where do your activities fit into these categories?");
        myDb.insertTipData("You’re the boss. If you have to decline a request in order to attend to what’s truly important and urgent, do not hesitate to do so.");
        myDb.insertTipData("One of the worst things you can do is jump into the workday with no clear idea about what needs to get done.Hence, use this app everyday :)");
        myDb.insertTipData("Start paying attention to the number of times someone interrupts you when you’re in the midst of an important task. Track self-induced interruptions, too, particularly those of the social media variety. Your smartphone is extremely useful, but it’s also addictive.");
        myDb.insertTipData("Running a successful small business depends upon the owner’s ability to think about what lies ahead and not get mired in day-to-day operations");
        myDb.insertTipData("Be sure to get plenty of sleep and exercise. An alert mind is a high-functioning mind and one that’s less tolerant of time-wasting activities.");
        myDb.insertTipData("Don't focus on gyal.Let gyal focus on u");
        myDb.insertTipData("Fellas be who u want to be.You are more than a 3.6 gpa");
        return;
    }
    public void showMessage(String title,String Message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void checkButton(View v) {
        int r1;
        r1 = rg.getCheckedRadioButtonId();
        rb = findViewById(r1);
        Toast.makeText(RegisterActivity.this, rb.getText() + " Selected", Toast.LENGTH_SHORT).show();

    }

    class Button_Clicker implements Button.OnClickListener

    {

        @Override

        public void onClick(View v) {


            if (v == reg) {

                if ((userName.getText().toString().trim().length() > 0) && (rg.getCheckedRadioButtonId() != -1)) {

                    FileOutputStream fos = null;
                    try {
                        fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
                        fos.write(userName.getText().toString().getBytes());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (fos != null) {
                            try {
                                fos.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                            int r1;
                            r1 = rg.getCheckedRadioButtonId();
                            rb = findViewById(r1);
                            String b=rb.getText().toString();

                            FileOutputStream fos2 = null;
                            try {
                                fos2 = openFileOutput(FILE_NAME2, MODE_PRIVATE);
                                fos2.write(b.getBytes());
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                if (fos2 != null) {
                                    try {
                                        fos2.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                                Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);

                                startActivity(intent);
                                session.writeLoginStatus(true);
                                finish();



                } else {
                    Toast.makeText(RegisterActivity.this, "Please enter user name and select faculty", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }
}

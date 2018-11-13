package com.example.kyle.uwitimemanagemeent;



import android.content.Intent;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        reg = (Button) findViewById(R.id.button);
        rg = (RadioGroup) findViewById(R.id.radioGroup);
        userName = (EditText) findViewById(R.id.editText);
        reg.setOnClickListener(new Button_Clicker());
        session = new UserSessionActivity(getApplicationContext());


        if (session.readLoginStatus()) {
            Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);


            startActivity(intent);
            finish();

        }

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

package com.upitt.ruc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;

public class Reg_Code extends AppCompatActivity implements BackgroundHelper.AsyncResponse {

    String orig_reg_code = "test";
    EditText editText;
    Button submit;
    TextView textView;
    String memail;
    TextView no_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg__code);

        editText = (EditText)findViewById(R.id.reg_code);
        submit = (Button)findViewById(R.id.reg_code_submit);
        textView = (TextView)findViewById(R.id.reg_code_message);
        no_code = (TextView)findViewById(R.id.no_code);


        no_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Reg_Code.this,No_Reg_Code.class);
                intent.putExtra("email",getIntent().getStringExtra("email"));
                intent.putExtra("givenName",getIntent().getStringExtra("givenName"));
                startActivity(intent);
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String reg_code = editText.getText().toString();

                if(reg_code.equals(orig_reg_code))
                {
                    String type = "insert_user_account";
                    memail = getIntent().getStringExtra("email");
                    BackgroundHelper backGroundHelper = new BackgroundHelper(getApplicationContext(),Reg_Code.this);
                    backGroundHelper.execute(type,memail);
                }
                else
                {
                    textView.setText("Please check your Registration code!!");
                    textView.setVisibility(View.VISIBLE);
                    editText.getText().clear();
                    Landing_Activity.hideKeyboard(Reg_Code.this);
                }

            }
        });
    }


    @Override
    public void processFinish(String output) {

        if(output.equals("user inserted successfully"))
        {
            Intent intent = new Intent(Reg_Code.this,Home.class);
            intent.putExtra("email",memail);
            //Log.i("Test","Here");
            startActivity(intent);
        }
        else
        {

        }
    }
}

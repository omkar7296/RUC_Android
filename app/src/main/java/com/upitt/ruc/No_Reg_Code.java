package com.upitt.ruc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.upitt.ruc.AsyncTasks.Reg_code_Request_Insert;

public class No_Reg_Code extends AppCompatActivity implements Reg_code_Request_Insert.Reg_code_Request_Insert_AsyncResponse {

    EditText phone_no;
    Button submit;
    String mphone;
    String memail;
    String mdisplayName;

    LinearLayout activity_no_reg_code_parentLayout;
    ProgressBar activity_no_reg_code_progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no__reg__code);


        activity_no_reg_code_progressbar = (ProgressBar) findViewById(R.id.activity_no_reg_code_progressbar);
        activity_no_reg_code_parentLayout = (LinearLayout) findViewById(R.id.activity_no_reg_code_parentLayout);
        phone_no = (EditText)findViewById(R.id.contact_no);
        submit = (Button)findViewById(R.id.contact_no_submit);

        memail = getIntent().getStringExtra("email");
        mdisplayName = getIntent().getStringExtra("displayName");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mphone = phone_no.getText().toString();
                String type = "insert_reg_code_request";
                Reg_code_Request_Insert reg_code_request_insert = new Reg_code_Request_Insert(getApplicationContext(), No_Reg_Code.this, activity_no_reg_code_parentLayout, activity_no_reg_code_progressbar);
                reg_code_request_insert.execute(type, memail, mdisplayName, mphone);
            }
        });




    }

    @Override
    public void processFinish(String output) {

        Log.i("output", output);
        if (output.equals("requestsent"))
        {
            Intent intent = new Intent(No_Reg_Code.this,Reg_Code_Req_Received.class);
            //Log.i("Test","Here");
            startActivity(intent);
        }
        else
        {
            Log.i("TEst", "Hahaha");
        }
    }
}

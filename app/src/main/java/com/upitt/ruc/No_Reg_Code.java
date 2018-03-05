package com.upitt.ruc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class No_Reg_Code extends AppCompatActivity implements BackgroundHelper.AsyncResponse{

    EditText phone_no;
    Button submit;
    String mphone;
    String memail;
    String mgivenName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no__reg__code);

        phone_no = (EditText)findViewById(R.id.contact_no);
        submit = (Button)findViewById(R.id.contact_no_submit);

        memail = getIntent().getStringExtra("email");
        mgivenName = getIntent().getStringExtra("givenName");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mphone = phone_no.getText().toString();
                String type = "insert_reg_code_request";
                BackgroundHelper backGroundHelper = new BackgroundHelper(getApplicationContext(),No_Reg_Code.this);
                backGroundHelper.execute(type,memail,mgivenName,mphone);
            }
        });




    }

    @Override
    public void processFinish(String output) {

        if(output.equals("user inserted successfully"))
        {
            Intent intent = new Intent(No_Reg_Code.this,Reg_Code_Req_Received.class);
            //Log.i("Test","Here");
            startActivity(intent);
        }
        else
        {

        }
    }
}

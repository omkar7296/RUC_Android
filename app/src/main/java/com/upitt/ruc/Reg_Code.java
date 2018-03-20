package com.upitt.ruc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.upitt.ruc.AsyncTasks.Reg_Code_Validation;

import java.io.BufferedReader;

public class Reg_Code extends AppCompatActivity implements Reg_Code_Validation.Reg_Code_Validation_AsyncResponse {

    String orig_reg_code = "test";
    EditText editText;
    Button submit;
    TextView textView;
    String memail;
    String mdisplayName;
    String mImgURL;
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
                intent.putExtra("displayName", getIntent().getStringExtra("displayName"));
                startActivity(intent);
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String reg_code = editText.getText().toString();

                memail = getIntent().getStringExtra("email");
                mImgURL = getIntent().getStringExtra("ImgURL");
                mdisplayName = getIntent().getStringExtra("displayName");

                Reg_Code_Validation reg_code_validation = new Reg_Code_Validation(getApplicationContext(), Reg_Code.this);
                reg_code_validation.execute(memail, mImgURL, mdisplayName, reg_code);



            }
        });
    }


    @Override
    public void processFinish(String output) {

        Log.i("Test", output);
        if (output.equals("accountcreated"))
        {
            Intent intent = new Intent(Reg_Code.this,Home.class);
            intent.putExtra("email",memail);
            //Log.i("Test","Here");
            startActivity(intent);
        } else if (output.equals("codeerror")) {
            textView.setText("Please check your Registration code!!");
            textView.setVisibility(View.VISIBLE);
            editText.getText().clear();
            Landing_Activity.hideKeyboard(Reg_Code.this);
        } else {
            Toast.makeText(this, "Service Temporarily down", Toast.LENGTH_SHORT).show();
        }
    }
}

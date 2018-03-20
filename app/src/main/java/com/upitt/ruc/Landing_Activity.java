package com.upitt.ruc;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

public class Landing_Activity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,BackgroundHelper.AsyncResponse{

    private SignInButton signin;
    static private GoogleApiClient googleApiClient;
    String memail;
    String mImgURL;
    //String mfamilyName;
    String mdisplayName;

    public static GoogleApiClient getGoogleApiClient() {
        return googleApiClient;
    }

    private static final int REQ_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_);

        signin = (SignInButton)findViewById(R.id.login);


        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this,this).addApi(Auth.GOOGLE_SIGN_IN_API,signInOptions).build();

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent,REQ_CODE);
            }
        });

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQ_CODE)
        {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);
        }

    }

    private void handleResult(GoogleSignInResult googleSignInResult)
    {
        if(googleSignInResult.isSuccess()) {
            GoogleSignInAccount account = googleSignInResult.getSignInAccount();

            memail = account.getEmail();
            mImgURL = account.getPhotoUrl().toString();
            //mfamilyName = account.getFamilyName();
            mdisplayName = account.getGivenName();

            String type = "check_user_account";

            BackgroundHelper backGroundHelper = new BackgroundHelper(Landing_Activity.this,this);
            backGroundHelper.execute(type,memail);


//            Intent intent = new Intent(Landing_Activity.this,Home.class);
//            intent.putExtra("displayName",displayName);
//            intent.putExtra("email",email);
//            intent.putExtra("imgURL",imgURL);
//            intent.putExtra("familyName",familyName);
//            intent.putExtra("givenName",givenName);
//            startActivity(intent);


        }
        else
        {
            Toast.makeText(this, "Google Signin Failed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void processFinish(String output) {

        Log.i("Test", output);
        if (output.equals("usernotpresent"))
        {
            Log.i("Test", output);
            Intent intent = new Intent(Landing_Activity.this,Reg_Code.class);
            intent.putExtra("email",memail);
            intent.putExtra("displayName", mdisplayName);
            intent.putExtra("ImgURL", mImgURL);

            startActivity(intent);
            finish();
        } else if (output.equals("userpresent")) {
            Intent intent = new Intent(Landing_Activity.this,Home.class);
            intent.putExtra("email",memail);
            Log.i("Test","Here");
            startActivity(intent);
            finish();

        } else {
            Toast.makeText(this, "Service Temporarily down", Toast.LENGTH_SHORT).show();
        }
    }

    public static void hideKeyboard(AppCompatActivity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}

package com.upitt.ruc;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.upitt.ruc.Views.MainActivityViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    @BindView(R.id.activity_main_viewPager)
    ViewPager mainViewPager;

    @BindView(R.id.activity_main_tabLayout)
    TabLayout tabLayout;


    NavigationView navigationView;
    Toolbar toolbar;
    DrawerLayout drawerLayout;

    CircleImageView profile_pic;
    TextView textView_name;
    TextView textView_email;

    GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);

        ButterKnife.bind(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout1);
        navigationView = (NavigationView) findViewById(R.id.navigation_view1);


        textView_name = (TextView) navigationView.getHeaderView(0).findViewById(R.id.navigation_header_textView_name);
        textView_email = (TextView) navigationView.getHeaderView(0).findViewById(R.id.navigation_header_textView_email);
        profile_pic = (CircleImageView) navigationView.getHeaderView(0).findViewById(R.id.navigation_header_imageView);


        String email = getIntent().getStringExtra("email");
        String name = getIntent().getStringExtra("name");
        String ImgURL = getIntent().getStringExtra("ImgURL");
        //String email = "Test";

        MainActivityViewPagerAdapter adapter = new MainActivityViewPagerAdapter(getSupportFragmentManager(), email);
        mainViewPager.setAdapter(adapter);


        textView_name.setText(name);
        textView_email.setText(email);

        Glide.with(this).load(ImgURL).into(profile_pic);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.setDrawerListener(toggle);

        toggle.syncState();

        googleApiClient = Landing_Activity.getGoogleApiClient();

        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions).build();


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                switch (id) {

                    case R.id.refer_id:


                        break;


                    case R.id.logout_id:

                        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
                            @Override
                            public void onResult(@NonNull Status status) {

                            }

                        });

                        Intent intent = new Intent(MainActivity.this, Landing_Activity.class);
                        startActivity(intent);
                        finish();

                        break;

                }

                drawerLayout.closeDrawer(GravityCompat.START);

                return true;


            }
        });

        tabLayout.setupWithViewPager(mainViewPager);
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {

            drawerLayout.closeDrawer(GravityCompat.START);

        } else {

            super.onBackPressed();

        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}

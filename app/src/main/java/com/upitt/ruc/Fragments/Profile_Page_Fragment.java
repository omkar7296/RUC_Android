package com.upitt.ruc.Fragments;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.upitt.ruc.Entities.Profile;
import com.upitt.ruc.MainActivity;
import com.upitt.ruc.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by omkar on 3/9/2018.
 */

public class Profile_Page_Fragment extends android.support.v4.app.Fragment {

    String url = MainActivity.url;
    String mname = MainActivity.mname;

    private Context mcontext;
    private Profile profile;
    private List<String> locations_list;
    private List<String> lived_years_list;

    private String mlocation_spinner_item_selected;
    private String mlived_years_item_selected;
    private String maboutMe;
    private String msports_activities;
    private String mhobbies_interests;
    private String mplaces;

    @BindView(R.id.profile_page_header_cardView)
    CardView profile_page_header_cardView;

    @BindView(R.id.profile_page_name_textView)
    TextView profile_page_name_textView;

    @BindView(R.id.profile_page_profile_pic)
    ImageView profile_page_profile_pic;

    @BindView(R.id.profile_page_location_spinner)
    Spinner profile_page_location_spinner;

    @BindView(R.id.profile_page_location_textView)
    TextView profile_page_location_textView;

    @BindView(R.id.profile_page_location_edit)
    ImageView profile_page_location_edit;

    @BindView(R.id.profile_page_location_save)
    ImageView profile_page_location_save;

    @BindView(R.id.profile_page_lived_textView)
    TextView profile_page_lived_textView;

    @BindView(R.id.profile_page_lived_spinner)
    Spinner profile_page_lived_spinner;

    @BindView(R.id.profile_page_lived_edit)
    ImageView profile_page_lived_edit;

    @BindView(R.id.profile_page_lived_save)
    ImageView profile_page_lived_save;

    @BindView(R.id.profile_page_points_textView)
    TextView profile_page_points_textView;

    @BindView(R.id.profile_page_badges_cardView)
    CardView profile_page_badges_cardView;

    @BindView(R.id.profile_page_badge1_imageView)
    ImageView profile_page_badge1_imageView;

    @BindView(R.id.profile_page_badge2_imageView)
    ImageView profile_page_badge2_imageView;

    @BindView(R.id.profile_page_badge3_imageView)
    ImageView profile_page_badge3_imageView;

    @BindView(R.id.profile_page_aboutMe_cardView)
    CardView profile_page_aboutMe_cardView;

    @BindView(R.id.profile_page_aboutMe_textView)
    TextView profile_page_aboutMe_textView;


    @BindView(R.id.profile_page_aboutMe_editText)
    EditText profile_page_aboutMe_editText;

    @BindView(R.id.profile_page_aboutMe_edit)
    ImageView profile_page_aboutMe_edit;

    @BindView(R.id.profile_page_aboutMe_save)
    ImageView profile_page_aboutMe_save;

    @BindView(R.id.profile_page_sports_activities_cardView)
    CardView profile_page_sports_activities_cardView;

    @BindView(R.id.profile_page_sports_activities_textView)
    TextView profile_page_sports_activities_textView;

    @BindView(R.id.profile_page_sports_activities_editText)
    EditText profile_page_sports_activities_editText;

    @BindView(R.id.profile_page_sports_activities_edit)
    ImageView profile_page_sports_activities_edit;

    @BindView(R.id.profile_page_sports_activities_save)
    ImageView profile_page_sports_activities_save;

    @BindView(R.id.profile_page_hobbies_interests_cardView)
    CardView profile_page_hobbies_interests_cardView;

    @BindView(R.id.profile_page_hobbies_interests_textView)
    TextView profile_page_hobbies_interests_textView;

    @BindView(R.id.profile_page_hobbies_interests_editText)
    EditText profile_page_hobbies_interests_editText;

    @BindView(R.id.profile_page_hobbies_interests_edit)
    ImageView profile_page_hobbies_interests_edit;

    @BindView(R.id.profile_page_hobbies_interests_save)
    ImageView profile_page_hobbies_interests_save;

    @BindView(R.id.profile_page_places_cardView)
    CardView profile_page_places_cardView;

    @BindView(R.id.profile_page_places_textView)
    TextView profile_page_places_textView;

    @BindView(R.id.profile_page_places_editText)
    EditText profile_page_places_editText;

    @BindView(R.id.profile_page_places_edit)
    ImageView profile_page_places_edit;

    @BindView(R.id.profile_page_places_save)
    ImageView profile_page_places_save;

    @BindView(R.id.profile_page_save_changes_permanantly)
    Button profile_page_save_changes_permanantly;


    public static Fragment newInstance() {
        return new Profile_Page_Fragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_profile_page, container, false);
        mcontext = rootView.getContext();

        ButterKnife.bind(this, rootView);

        //Here make the async call
        profile = new Profile(mname,
                //"https://s3.amazonaws.com/plonlinecontent/online/ronaldo7-1489414339",
                url,
                "Pittsburgh",
                "5 years",
                "96",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS74QOys_CZy3Gvwn8J41L6lmpUH7leOq9kO3eC5A_amPsJpyo1",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS74QOys_CZy3Gvwn8J41L6lmpUH7leOq9kO3eC5A_amPsJpyo1",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS74QOys_CZy3Gvwn8J41L6lmpUH7leOq9kO3eC5A_amPsJpyo1",
                "Hello, this is a test description about me. I am agood at lot of things.Hello, this is a test description about me. I am agood at lot of things.",
                "Football \nBasketball\nBaseball",
                "Swimming \nReading Mystery Novels",
                "Test place 1\nTest place 2"
                );

        // Spinner Drop down elements
        locations_list = new ArrayList<String>();
        locations_list.add("Pittsburgh");
        locations_list.add("Test Place 1");
        locations_list.add("Test Place 2");
        locations_list.add("Test Place 3");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter_location = new ArrayAdapter<String>(mcontext, android.R.layout.simple_spinner_item, locations_list);

        // Drop down layout style - list view with radio button
        dataAdapter_location.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        profile_page_location_spinner.setAdapter(dataAdapter_location);

        profile_page_location_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                mlocation_spinner_item_selected = adapterView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                mlocation_spinner_item_selected = profile.getLocation();
            }
        });

        // Spinner Drop down elements
        lived_years_list = new ArrayList<String>();
        lived_years_list.add("< 1 year");
        lived_years_list.add("1 year");
        lived_years_list.add("2 years");
        lived_years_list.add("3 years");
        lived_years_list.add("4 years");
        lived_years_list.add("5+ years");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter_lived = new ArrayAdapter<String>(mcontext, android.R.layout.simple_spinner_item, lived_years_list);

        // Drop down layout style - list view with radio button
        dataAdapter_lived.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        profile_page_lived_spinner.setAdapter(dataAdapter_lived);

        profile_page_lived_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                mlived_years_item_selected = adapterView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                mlived_years_item_selected = profile.getLocation();
            }
        });

        profile_page_name_textView.setText(profile.getName());
        profile_page_location_textView.setText(profile.getLocation());
        profile_page_lived_textView.setText(profile.getLived());
        profile_page_points_textView.setText(profile.getPoints());

        profile_page_aboutMe_textView.setText(profile.getAbout_me());
        profile_page_sports_activities_textView.setText(profile.getSports_activities());
        profile_page_hobbies_interests_textView.setText(profile.getHobbies_interests());
        profile_page_places_textView.setText(profile.getPlaces());

        Glide.with(this).load(profile.getProfile_pic_url()).into(profile_page_profile_pic);
        Glide.with(this).load(profile.getBadge1_url()).into(profile_page_badge1_imageView);
        Glide.with(this).load(profile.getBadge2_url()).into(profile_page_badge2_imageView);
        Glide.with(this).load(profile.getBadge3_url()).into(profile_page_badge3_imageView);


        profile_page_header_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile_page_header_cardView.requestFocus();
            }
        });

        profile_page_aboutMe_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile_page_aboutMe_cardView.requestFocus();
            }
        });

        profile_page_badges_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile_page_badges_cardView.requestFocus();
            }
        });

        profile_page_aboutMe_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile_page_aboutMe_edit.setVisibility(View.INVISIBLE);
                profile_page_aboutMe_save.setVisibility(View.VISIBLE);

                maboutMe = profile_page_aboutMe_textView.getText().toString();
                profile_page_aboutMe_textView.setVisibility(View.INVISIBLE);

                profile_page_aboutMe_editText.setVisibility(View.VISIBLE);
                profile_page_aboutMe_editText.requestFocus();
                profile_page_aboutMe_editText.setText(maboutMe);
            }
        });

        profile_page_aboutMe_editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b)
                {
                    profile_page_aboutMe_save.setVisibility(View.INVISIBLE);
                    profile_page_aboutMe_edit.setVisibility(View.VISIBLE);

                    profile_page_aboutMe_editText.setVisibility(View.GONE);
                    profile.setAbout_me(maboutMe);

                    profile_page_aboutMe_textView.setText(profile.getAbout_me());
                    profile_page_aboutMe_textView.setVisibility(View.VISIBLE);
                }
            }
        });

        profile_page_aboutMe_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile_page_aboutMe_save.setVisibility(View.INVISIBLE);
                profile_page_aboutMe_edit.setVisibility(View.VISIBLE);

                String content = profile_page_aboutMe_editText.getText().toString();
                profile_page_aboutMe_editText.setVisibility(View.GONE);
                profile.setAbout_me(content);

                profile_page_aboutMe_textView.setText(profile.getAbout_me());
                profile_page_aboutMe_textView.setVisibility(View.VISIBLE);

                profile_page_save_changes_permanantly.setVisibility(View.VISIBLE);
            }
        });



        //
        profile_page_sports_activities_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile_page_sports_activities_cardView.requestFocus();
            }
        });

        profile_page_sports_activities_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile_page_sports_activities_edit.setVisibility(View.INVISIBLE);
                profile_page_sports_activities_save.setVisibility(View.VISIBLE);

                msports_activities = profile_page_sports_activities_textView.getText().toString();
                profile_page_sports_activities_textView.setVisibility(View.INVISIBLE);

                profile_page_sports_activities_editText.setVisibility(View.VISIBLE);
                profile_page_sports_activities_editText.requestFocus();
                profile_page_sports_activities_editText.setText(msports_activities);
            }
        });


        profile_page_sports_activities_editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    profile_page_sports_activities_save.setVisibility(View.INVISIBLE);
                    profile_page_sports_activities_edit.setVisibility(View.VISIBLE);

                    profile_page_sports_activities_editText.setVisibility(View.GONE);
                    profile.setSports_activities(msports_activities);

                    profile_page_sports_activities_textView.setText(profile.getSports_activities());
                    profile_page_sports_activities_textView.setVisibility(View.VISIBLE);
                }
            }
        });

        profile_page_sports_activities_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile_page_sports_activities_save.setVisibility(View.INVISIBLE);
                profile_page_sports_activities_edit.setVisibility(View.VISIBLE);

                String content = profile_page_sports_activities_editText.getText().toString();
                profile_page_sports_activities_editText.setVisibility(View.GONE);
                profile.setSports_activities(content);

                profile_page_sports_activities_textView.setText(profile.getSports_activities());
                profile_page_sports_activities_textView.setVisibility(View.VISIBLE);

                profile_page_save_changes_permanantly.setVisibility(View.VISIBLE);
            }
        });

        //
        profile_page_hobbies_interests_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile_page_hobbies_interests_cardView.requestFocus();
            }
        });

        profile_page_hobbies_interests_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile_page_hobbies_interests_edit.setVisibility(View.INVISIBLE);
                profile_page_hobbies_interests_save.setVisibility(View.VISIBLE);

                mhobbies_interests = profile_page_hobbies_interests_textView.getText().toString();
                profile_page_hobbies_interests_textView.setVisibility(View.INVISIBLE);

                profile_page_hobbies_interests_editText.setVisibility(View.VISIBLE);
                profile_page_hobbies_interests_editText.requestFocus();
                profile_page_hobbies_interests_editText.setText(mhobbies_interests);
            }
        });

        profile_page_hobbies_interests_editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    profile_page_hobbies_interests_save.setVisibility(View.INVISIBLE);
                    profile_page_hobbies_interests_edit.setVisibility(View.VISIBLE);

                    profile_page_hobbies_interests_editText.setVisibility(View.GONE);
                    profile.setHobbies_interests(mhobbies_interests);

                    profile_page_hobbies_interests_textView.setText(profile.getHobbies_interests());
                    profile_page_hobbies_interests_textView.setVisibility(View.VISIBLE);
                }
            }
        });

        profile_page_hobbies_interests_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile_page_hobbies_interests_save.setVisibility(View.INVISIBLE);
                profile_page_hobbies_interests_edit.setVisibility(View.VISIBLE);

                String content = profile_page_hobbies_interests_editText.getText().toString();
                profile_page_hobbies_interests_editText.setVisibility(View.GONE);
                profile.setHobbies_interests(content);

                profile_page_hobbies_interests_textView.setText(profile.getHobbies_interests());
                profile_page_hobbies_interests_textView.setVisibility(View.VISIBLE);

                profile_page_save_changes_permanantly.setVisibility(View.VISIBLE);
            }
        });


        //
        profile_page_places_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile_page_places_cardView.requestFocus();
            }
        });

        profile_page_places_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile_page_places_edit.setVisibility(View.INVISIBLE);
                profile_page_places_save.setVisibility(View.VISIBLE);

                mplaces = profile_page_places_textView.getText().toString();
                profile_page_places_textView.setVisibility(View.INVISIBLE);

                profile_page_places_editText.setVisibility(View.VISIBLE);
                profile_page_places_editText.requestFocus();
                profile_page_places_editText.setText(mplaces);
            }
        });

        profile_page_places_editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    profile_page_places_save.setVisibility(View.INVISIBLE);
                    profile_page_places_edit.setVisibility(View.VISIBLE);

                    profile_page_places_editText.setVisibility(View.GONE);
                    profile.setPlaces(mplaces);

                    profile_page_places_textView.setText(profile.getPlaces());
                    profile_page_places_textView.setVisibility(View.VISIBLE);
                }
            }
        });

        profile_page_places_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile_page_places_save.setVisibility(View.INVISIBLE);
                profile_page_places_edit.setVisibility(View.VISIBLE);

                String content = profile_page_places_editText.getText().toString();
                profile_page_places_editText.setVisibility(View.GONE);
                profile.setPlaces(content);

                profile_page_places_textView.setText(profile.getPlaces());
                profile_page_places_textView.setVisibility(View.VISIBLE);

                profile_page_save_changes_permanantly.setVisibility(View.VISIBLE);
            }
        });

        profile_page_location_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile_page_location_edit.setVisibility(View.INVISIBLE);
                profile_page_location_save.setVisibility(View.VISIBLE);

                profile_page_location_textView.setVisibility(View.GONE);
                profile_page_location_spinner.setVisibility(View.VISIBLE);
            }
        });

        profile_page_location_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile_page_location_save.setVisibility(View.INVISIBLE);
                profile_page_location_edit.setVisibility(View.VISIBLE);

                profile_page_location_spinner.setVisibility(View.GONE);
                profile.setLocation(mlocation_spinner_item_selected);

                profile_page_location_textView.setText(profile.getLocation());
                profile_page_location_textView.setVisibility(View.VISIBLE);

                profile_page_save_changes_permanantly.setVisibility(View.VISIBLE);


            }
        });

        profile_page_lived_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile_page_lived_edit.setVisibility(View.GONE);
                profile_page_lived_save.setVisibility(View.VISIBLE);

                profile_page_lived_textView.setVisibility(View.GONE);
                profile_page_lived_spinner.setVisibility(View.VISIBLE);
            }
        });

        profile_page_lived_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile_page_lived_save.setVisibility(View.GONE);
                profile_page_lived_edit.setVisibility(View.VISIBLE);

                profile_page_lived_spinner.setVisibility(View.GONE);
                profile.setLived(mlived_years_item_selected);

                profile_page_lived_textView.setText(profile.getLived());
                profile_page_lived_textView.setVisibility(View.VISIBLE);

                profile_page_save_changes_permanantly.setVisibility(View.VISIBLE);


            }
        });

        profile_page_save_changes_permanantly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                new AlertDialog.Builder(mcontext)
                        .setTitle("Update Profile?")
                        .setMessage("Are you sure you want to update the changes???")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {


                                Toast.makeText(mcontext, "Profile updated successfully", Toast.LENGTH_SHORT).show();
                                profile_page_save_changes_permanantly.setVisibility(View.GONE);

                            }



                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {



                            }
                        })
                        .show();


            }
        });








        return rootView;

    }
}

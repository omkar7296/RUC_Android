package com.upitt.ruc.Views;


import android.os.Bundle;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.upitt.ruc.Fragments.Events_Fragment;
import com.upitt.ruc.Fragments.Messaging_Fragment;
import com.upitt.ruc.Fragments.News_Feed_Fragment;
import com.upitt.ruc.Fragments.Profile_Page_Fragment;

/**
 * Created by omkar on 3/9/2018.
 */

public class MainActivityViewPagerAdapter extends FragmentStatePagerAdapter {

    String email;

    public MainActivityViewPagerAdapter(FragmentManager fm, String email) {
        super(fm);
        this.email = email;
    }

    public MainActivityViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {

        Fragment returnFragment;

        Bundle bundle = new Bundle();
        bundle.putString("email", email);

        //Log.i("EMail m:",email);

        switch(position)
        {
            case 0: returnFragment = Profile_Page_Fragment.newInstance();
                returnFragment.setArguments(bundle);
                break;
            case 1: returnFragment = News_Feed_Fragment.newInstance();
                break;
            case 2: returnFragment = Events_Fragment.newInstance();
                break;
            case 3: returnFragment = Messaging_Fragment.newInstance();
                break;
            default:return null;
        }
        return returnFragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        CharSequence title;

        switch (position)
        {
            case 0: title = "My Profile";
                break;
            case 1: title = "News Feed";
                break;
            case 2: title = "Events";
                break;
            case 3: title = "Message";
                break;
            default:return null;
        }

        return title;
    }
}


package com.bara.submission1.adapter;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.bara.submission1.R;
import com.bara.submission1.fragment.AccountFragment;
import com.bara.submission1.fragment.FollowerFragment;
import com.bara.submission1.fragment.FollowingFragment;

public class TabAdapter extends FragmentPagerAdapter {

    private final Context mContext;
    String nameExtra;

    public TabAdapter(Context context, FragmentManager fm){
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
    }

    public void getName(String username){
        nameExtra = username;
    }

    @StringRes
    private final int[] TAB_NAME = new int[]{
            R.string.tab1_account,
            R.string.tab2_follower,
            R.string.tab3_following
    };

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        Bundle bundle = new Bundle();
        bundle.putString("name", nameExtra);
        switch (position){
            case 0:
                fragment = new AccountFragment();
                fragment.setArguments(bundle);
                break;

            case 1:
                fragment = new FollowerFragment();
                fragment.setArguments(bundle);
                break;

            case 2:
                fragment = new FollowingFragment();
                fragment.setArguments(bundle);
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_NAME[position]);
    }

    @Override
    public int getCount() {
        return 3;
    }
}

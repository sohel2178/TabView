package com.example.nlpc06.tabview.Adater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NL PC 06 on 11/11/2017.
 */

public class HospitalAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;

    private List<String> titleList;

    public HospitalAdapter(FragmentManager fm) {
        super(fm);
        fragmentList = new ArrayList<>();
        titleList = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addFragment(Fragment fragment,String title){
        fragmentList.add(fragment);
        titleList.add(title);

        //notifyDataSetChanged();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}

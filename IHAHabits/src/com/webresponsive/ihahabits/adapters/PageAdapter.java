package com.webresponsive.ihahabits.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter
{
    private Fragment[] fragments;
    private String[] fragmentsTitles;

    public PageAdapter(FragmentManager fragmentManager, Fragment[] fragments, String[] fragmentTitles)
    {
        super(fragmentManager);

        this.fragments = fragments;
        fragmentsTitles = fragmentTitles;
    }

    @Override
    public Fragment getItem(int pos)
    {
        return fragments[pos];
    }

    @Override
    public int getCount()
    {
        return fragments.length;
    }

    @Override
    public CharSequence getPageTitle(int pos)
    {
        return fragmentsTitles[pos];
    }
}

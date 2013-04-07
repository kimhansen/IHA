package com.webresponsive.ihahabits;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;

import com.parse.Parse;
import com.webresponsive.ihahabits.adapters.PageAdapter;

public class MainActivity extends FragmentActivity
{
    private PageAdapter pageAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setupParse();
        
        setupLayout();
    }

    private void setupLayout()
    {
        Fragment[] fragments = new Fragment[2];
        HabitsFragment habitsFragment = new HabitsFragment();
        fragments[0] = habitsFragment;

        HabitsHistoryFragment contactsFragment = new HabitsHistoryFragment();
        fragments[1] = contactsFragment;

        String[] fragmentNames = new String[2];
        fragmentNames[0] = HabitsFragment.NAME;
        fragmentNames[1] = HabitsHistoryFragment.NAME;

        pageAdapter = new PageAdapter(getSupportFragmentManager(), fragments, fragmentNames);
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(pageAdapter);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu)
//    {
//        getMenuInflater().inflate(R.menu.activity_main, menu);
//        return true;
//    }

    private void setupParse()
    {
        Parse.initialize(this, "3WcLoKzzwJl8MvMLVNtiw1UtfWxKC5cH91TC27Nh", "mujvlbfcSQAC05CEFJZV4J0DhI3qoDWA1eehe2ey");
    }
}

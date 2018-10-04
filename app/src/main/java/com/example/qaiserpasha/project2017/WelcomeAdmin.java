package com.example.qaiserpasha.project2017;

import android.annotation.SuppressLint;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.qaiserpasha.project2017.Fragments.Admin_Tab1;
import com.example.qaiserpasha.project2017.Fragments.Admin_Tab2;
import com.example.qaiserpasha.project2017.Fragments.Admin_Tab3;
import com.example.qaiserpasha.project2017.Fragments.Collector_Tab2;

import java.util.ArrayList;
import java.util.List;

public class WelcomeAdmin extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    // private SectionsPagerAdapter2 mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    ViewPagerAdapterCustom adpater;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_admin);


        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.containerWellcomeAdmin);
        adpater=new ViewPagerAdapterCustom(getSupportFragmentManager());
        adpater.addFragment(new Admin_Tab1(), "Donor Requests");
        adpater.addFragment(new Admin_Tab2(), "Recipient Requests");
      //  adpater.addFragment(new Admin_Tab3(), "Max Collection ");
        mViewPager.setAdapter(adpater);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabsWellcomeAdmin);

        tabLayout.setupWithViewPager(mViewPager);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_welcome_admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public class ViewPagerAdapterCustom extends FragmentPagerAdapter {


        public ViewPagerAdapterCustom(FragmentManager fm) {
            super(fm);
        }

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();


        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }


    }
}

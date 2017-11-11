package com.example.nlpc06.tabview;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nlpc06.tabview.Adater.HospitalAdapter;
import com.example.nlpc06.tabview.Fragments.CabinFragment;
import com.example.nlpc06.tabview.Fragments.DoctorFragment;
import com.example.nlpc06.tabview.Fragments.PatientFragment;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.view_pager);

        setupViewPager();

        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager() {
        HospitalAdapter adapter = new HospitalAdapter(getSupportFragmentManager());

        adapter.addFragment(new DoctorFragment(),"Doctor");
        adapter.addFragment(new PatientFragment(),"Patient");
        adapter.addFragment(new CabinFragment(),"Cabin");
        
        viewPager.setAdapter(adapter);

    }
}

package com.example.nlpc06.tabview;

import android.Manifest;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.nlpc06.tabview.Adater.HospitalAdapter;
import com.example.nlpc06.tabview.Fragments.CabinFragment;
import com.example.nlpc06.tabview.Fragments.DoctorFragment;
import com.example.nlpc06.tabview.Fragments.PatientFragment;
import com.example.nlpc06.tabview.Fragments.ReleaseFragment;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity {

    private static final int ALL_PERMISSION =3000;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getAllPermission();


    }

    public void init(){
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
        adapter.addFragment(new ReleaseFragment(),"Release");

        viewPager.setAdapter(adapter);

    }

    @AfterPermissionGranted(ALL_PERMISSION)
    private void getAllPermission(){
        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};

        if (EasyPermissions.hasPermissions(this, perms)) {
            // Already have permission, do the thing
            // ...

            init();
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, "All Permission",
                    ALL_PERMISSION, perms);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


}

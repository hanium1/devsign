package com.seeun.devsign;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static SharedPreferences sharedPreference;

    FragmentManager fragmentManager;
    FragmentTransaction transaction;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            fragmentManager = getSupportFragmentManager();
            transaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    transaction.replace(R.id.content, new HomeFragment()).commit();
                    return true;
                case R.id.navigation_history:
                    transaction.replace(R.id.content, new HistoryFragment()).commit();
                    return true;
                case R.id.navigation_information:
                    transaction.replace(R.id.content, new InformationFragment()).commit();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content, new HomeFragment()).commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void Btn1Click(View view) {
        Intent i = new Intent(this, AddDeviceActivity.class);
        i.putExtra("btnnum","1");
        startActivity(i);
    }
    public void Btn2Click(View view) {
        Intent i = new Intent(this, AddDeviceActivity.class);
        i.putExtra("btnnum","2");
        startActivity(i);
    }
    public void Btn3Click(View view) {
        Intent i = new Intent(this, AddDeviceActivity.class);
        i.putExtra("btnnum","3");
        startActivity(i);
    }
    public void Btn4Click(View view) {
        Intent i = new Intent(this, AddDeviceActivity.class);
        i.putExtra("btnnum","4");
        startActivity(i);
    }

}

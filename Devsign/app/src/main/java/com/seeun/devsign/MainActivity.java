package com.seeun.devsign;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutionException;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    static SharedPreferences sharedPreference;
    static DBHelper dbHelper;

    private boolean mConnected = false;
    private BluetoothAdapter mBluetoothAdapter;
    private static final int REQUEST_ENABLE_BT = 1;
    private String mDeviceAddress0;
    HomeFragment homeFrag;
    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    byte[] bytes = null;
    Intent intent;

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

        dbHelper = new DBHelper(this, "DEVICES.db", null, 1);

        /*블루투스 어댑터 초기화*/
        final BluetoothManager bluetoothManager =
                (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();

        /*장치에서 블루투스가 지원되는지 확인*/
        if (mBluetoothAdapter == null) {
            Toast.makeText(this, "error_bluetooth_not_supported", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        homeFrag = new HomeFragment();
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content, new HomeFragment()).commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        homeFrag.imBtn0.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(dbHelper.getAddress(0).isEmpty())
                    intent = new Intent(MainActivity.this, AddDeviceActivity.class);
                else
                    intent = new Intent(MainActivity.this, ModifyDeviceActivity.class);
                intent.putExtra("btnnum","0");
                startActivity(intent);
                return false;
            }
        });
        homeFrag.imBtn1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(dbHelper.getAddress(1).isEmpty())
                    intent = new Intent(MainActivity.this, AddDeviceActivity.class);
                else
                    intent = new Intent(MainActivity.this, ModifyDeviceActivity.class);
                intent.putExtra("btnnum","1");
                startActivity(intent);
                return false;
            }
        });
        homeFrag.imBtn2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(dbHelper.getAddress(2).isEmpty())
                    intent = new Intent(MainActivity.this, AddDeviceActivity.class);
                else
                    intent = new Intent(MainActivity.this, ModifyDeviceActivity.class);
                intent.putExtra("btnnum","2");
                startActivity(intent);
                return false;
            }
        });
        homeFrag.imBtn3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(dbHelper.getAddress(3).isEmpty())
                    intent = new Intent(MainActivity.this, AddDeviceActivity.class);
                else
                    intent = new Intent(MainActivity.this, ModifyDeviceActivity.class);
                intent.putExtra("btnnum","3");
                startActivity(intent);
                return false;
            }
        });
        homeFrag.imBtn4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(dbHelper.getAddress(4).isEmpty())
                    intent = new Intent(MainActivity.this, AddDeviceActivity.class);
                else
                    intent = new Intent(MainActivity.this, ModifyDeviceActivity.class);
                intent.putExtra("btnnum","4");
                startActivity(intent);
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*장치에서 블루투스가 활성화 되었는지 확인.
          블루투스가 비활성화 상태인 경우,
          사용자에게 활성화 할 수 있는 권한을 부여하는 대화상자 표시.*/
        if (!mBluetoothAdapter.isEnabled()) {
            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

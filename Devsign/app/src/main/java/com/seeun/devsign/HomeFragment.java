package com.seeun.devsign;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import static com.seeun.devsign.MainActivity.sharedPreference;


public class HomeFragment extends Fragment {

    View view;
    Boolean reg[] = new Boolean[5];
    static DBHelper dbHelper;
    TextView name0,name1,name2,name3,name4;
    TextView value0,value1,value2,value3,value4;
    ImageButton imBtn0, imBtn1, imBtn2, imBtn3, imBtn4;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        sharedPreference = getActivity().getSharedPreferences("REGISTERED", Context.MODE_MULTI_PROCESS | Context.MODE_WORLD_READABLE);

        dbHelper = new DBHelper(getContext(), "DEVICES.db", null, 1);
        name0=(TextView)view.findViewById(R.id.name0);  value0 = (TextView)view.findViewById(R.id.value0);
        name1=(TextView)view.findViewById(R.id.name1);  value1 = (TextView)view.findViewById(R.id.value1);
        name2=(TextView)view.findViewById(R.id.name2);  value2 = (TextView)view.findViewById(R.id.value2);
        name3=(TextView)view.findViewById(R.id.name3);  value3 = (TextView)view.findViewById(R.id.value3);
        name4=(TextView)view.findViewById(R.id.name4);  value4 = (TextView)view.findViewById(R.id.value4);

        imBtn0 = (ImageButton)view.findViewById(R.id.imageButton0);
        imBtn1 = (ImageButton)view.findViewById(R.id.imageButton1);
        imBtn2 = (ImageButton)view.findViewById(R.id.imageButton2);
        imBtn3 = (ImageButton)view.findViewById(R.id.imageButton3);
        imBtn4 = (ImageButton)view.findViewById(R.id.imageButton4);

        imBtn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name0.getText().equals("    ")){
                    Intent add = new Intent(getActivity(), AddDeviceActivity.class);
                    add.putExtra("btnnum", "0");
                    startActivity(add);
                }else {
                    Intent modi = new Intent(getActivity(), ModifyDeviceActivity.class);
                    modi.putExtra("btnnum", "0");
                    startActivity(modi);
                }
            }
        });
        imBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name1.getText().equals("    ")){
                    Intent add = new Intent(getActivity(), AddDeviceActivity.class);
                    add.putExtra("btnnum", "1");
                    startActivity(add);
                }else {
                    Intent modi = new Intent(getActivity(), ModifyDeviceActivity.class);
                    modi.putExtra("btnnum", "1");
                    startActivity(modi);
                }
            }
        });
        imBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name2.getText().equals("    ")){
                    Intent add = new Intent(getActivity(), AddDeviceActivity.class);
                    add.putExtra("btnnum", "2");
                    startActivity(add);
                }else {
                    Intent modi = new Intent(getActivity(), ModifyDeviceActivity.class);
                    modi.putExtra("btnnum", "2");
                    startActivity(modi);
                }
            }
        });
        imBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name3.getText().equals("    ")){
                    Intent add = new Intent(getActivity(), AddDeviceActivity.class);
                    add.putExtra("btnnum", "3");
                    startActivity(add);
                }else {
                    Intent modi = new Intent(getActivity(), ModifyDeviceActivity.class);
                    modi.putExtra("btnnum", "3");
                    startActivity(modi);
                }
            }
        });
        imBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name4.getText().equals("    ")){
                    Intent add = new Intent(getActivity(), AddDeviceActivity.class);
                    add.putExtra("btnnum", "4");
                    startActivity(add);
                }else {
                    Intent modi = new Intent(getActivity(), ModifyDeviceActivity.class);
                    modi.putExtra("btnnum", "4");
                    startActivity(modi);
                }
            }
        });
        for(int i = 0;i<5;i++)
            reg[i] = sharedPreference.getBoolean("Registered["+i+"]",false);
        try {
            if (reg[0] && name0!=null) {
                name0.setText(dbHelper.getResult(0));
                imgSet(imBtn0,0);
                Log.e("getR(0)",""+dbHelper.getResult(0));
            }else{
                name0.setText("    ");
                imgSet(imBtn0,-1);
            }
            if (reg[1] && name1!=null) {
                imgSet(imBtn1,1);
                name1.setText(dbHelper.getResult(1));
            }
            else{
                name1.setText("    ");
                imgSet(imBtn1,-1);
            }
            if (reg[2] && name2!=null) {
                imgSet(imBtn2,2);
                name2.setText(dbHelper.getResult(2));
            }else{
                name2.setText("    ");
                imgSet(imBtn2,-1);
            }
            if (reg[3] && name3!=null) {
                imgSet(imBtn3,3);
                name3.setText(dbHelper.getResult(3));
            }else{
                name3.setText("    ");
                imgSet(imBtn3,-1);
            }
            if (reg[4] && name4!=null) {
                imgSet(imBtn4,4);
                name4.setText(dbHelper.getResult(4));
            }else{
                name4.setText("    ");
                imgSet(imBtn4,-1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return view;
    }
    public void imgSet(ImageButton imgBtn, int btnnum){
        switch (dbHelper.getImage(btnnum)){
            case 0 :
                imgBtn.setImageResource(R.drawable.dumbbell_100_red_connected);
                break;
            case 1 :
                imgBtn.setImageResource(R.drawable.dumbbell_100_blue);
                break;
            case 2 :
                imgBtn.setImageResource(R.drawable.barbell_red_100);
                break;
            case 3 :
                imgBtn.setImageResource(R.drawable.barbell_blue_100);
                break;
            case 4 :
                imgBtn.setImageResource(R.drawable.squat_pink_100);
                break;
            case 5 :
                imgBtn.setImageResource(R.drawable.squat_yellow_100);
                break;
            default:
                imgBtn.setImageResource(R.drawable.ic_plus);
        }
    }
/*
    private final BroadcastReceiver mGattUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (BluetoothLeService.ACTION_GATT_CONNECTED.equals(action)) {
                mConnected = true;
                updateConnectionState(R.string.connected);
                invalidateOptionsMenu();
            } else if (BluetoothLeService.ACTION_GATT_DISCONNECTED.equals(action)) {
                mConnected = false;
                updateConnectionState(R.string.disconnected);
                invalidateOptionsMenu();
                clearUI();
            } else if (BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED.equals(action)) {
                // Show all the supported services and characteristics on the user interface.
                displayGattServices(mBluetoothLeService.getSupportedGattServices());
            } else if (BluetoothLeService.ACTION_DATA_AVAILABLE.equals(action)) {
                displayData(intent.getStringExtra(BluetoothLeService.EXTRA_DATA));
            }
        }
    };
    */
}

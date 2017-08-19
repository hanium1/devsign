package com.seeun.pop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import static com.seeun.pop.BluetoothLeService.stdata;

public class ReadingActivity extends AppCompatActivity {
    TextView tv_reading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);

      /*  tv_reading = (TextView) findViewById(R.id.ReadingTV);

        Intent intent = this.getIntent();
        //String msg = intent.getStringExtra("msg");
        byte[] dataFromLeService = intent.getByteArrayExtra("dataReading");

        tv_reading.setText("받은 값 : "+dataFromLeService);*/

        tv_reading = (TextView) findViewById(R.id.ReadingTV);

        Intent intentReading = getIntent();
        //String msg = intent.getStringExtra("msg");
        byte[] dataFromLeService = intentReading.getByteArrayExtra("dataReading");

       // tv_reading.setText("받은 값은 "+ dataFromLeService);
        String str = new String(stdata);
        String str2 = new String(str);
        int num = Integer.parseInt(str2);

        //int num = Integer.parseInt(str);
        tv_reading.setText("값"+);


    }
}


/*
package com.seeun.pop;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import static com.seeun.pop.R.id.userheight;
import static com.seeun.pop.R.id.username;


public class MainActivity extends AppCompatActivity {
    EditText userName, userHeight, userWeight;

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (EditText) findViewById(username);
        userHeight = (EditText) findViewById(userheight);
        userWeight = (EditText) findViewById(R.id.userweight);

        sp = getSharedPreferences("profile", MODE_PRIVATE);
        editor = sp.edit();


        editor.putString("name", userName.getText().toString());
        editor.putString("height", userHeight.getText().toString());
        editor.putString("weight", userWeight.getText().toString());
        editor.commit();

        //데이터 가져오기


    }

    @Override
    protected void onPause() {
        super.onPause();

        userName.setText(sp.getString("username", null));
        userHeight.setText(sp.getString("userheight", null));
        userWeight.setText(sp.getString("userweight", null));

    }


}
*/

package com.seeun.pop;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;


public class InfoActivity extends AppCompatActivity {
    EditText userName, userHeight, userWeight;


    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        userName = (EditText) findViewById(R.id.username);
        userHeight = (EditText) findViewById(R.id.userheight);
        userWeight = (EditText) findViewById(R.id.userweight);

        sp = getSharedPreferences("profile", MODE_PRIVATE);

        //데이터 가져오기

    }

    @Override
    protected void onResume() {
        super.onResume();
        sp = getSharedPreferences("profile", MODE_PRIVATE);

        userName.setText(sp.getString("name", null));
        userHeight.setText(sp.getString("height", null));
        userWeight.setText(sp.getString("weight", null));

    }

    @Override
    protected void onPause() {
        super.onPause();

        sp = getSharedPreferences("profile", MODE_PRIVATE);
        editor = sp.edit();

        editor.putString("name", userName.getText().toString());
        editor.putString("height", userHeight.getText().toString());
        editor.putString("weight", userWeight.getText().toString());
        editor.commit();
    }


}


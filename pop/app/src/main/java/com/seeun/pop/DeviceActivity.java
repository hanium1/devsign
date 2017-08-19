package com.seeun.pop;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class DeviceActivity extends AppCompatActivity {

    DatePicker datePicker;
    EditText editTextName, editTextReading;
    EditText editText;
    Button btn;

    //파일 이름을 저장할 변수
    String fileName;

    //데이터베이스 클래스인 Database 생성
    Database DB = new Database(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);

        datePicker = (DatePicker) findViewById(R.id.datePicker);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextReading = (EditText) findViewById(R.id.editTextReading);
        editText = (EditText)findViewById(R.id.editText);
        btn = (Button) findViewById(R.id.btn);

        //캘린더를 이용하여 연월일 가져오기
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

       /* //현재날짜 가지고오기
        //실행했을 때 현재날짜부터 시작하도록 자동 설정
        SQLiteDatabase db = DB.getWritableDatabase();*/

        //int형 날짜를 String형으로 변환하여 파일이름에 저장하기
        fileName = Integer.toString(year) + "년 " + Integer.toString(month + 1) + "월 " + Integer.toString(day) + "일";

        //에디트텍스트에 그 날짜에 해당하는 일기내용 가지고 오기
        //일기내용의 유무에 따라 버튼이 변하도록 하는 것은 readDiary 메소드를 새로 만들어 설정
        String str = readInfo(fileName);
        editText.setText(str);
        btn.setEnabled(true);

        //날짜가 변동되었을 때 새로운 날짜를 fileName에 또 저장을 하고,
        //해당내용을 에디트텍스트에 불러옴
        /*datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                fileName = Integer.toString(year) + "년 " + Integer.toString(monthOfYear) + 1 + "월 " + Integer.toString(dayOfMonth) + "일";
                String str = readInfo(fileName);
                editTextName.setText(str);
                btn.setEnabled(true);
            }
        });*/


        //버튼 이벤트 + 내용 입력 부분(getWritableDatabase())
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //btn.getText().toString().equals("새로저장");
                SQLiteDatabase db = DB.getWritableDatabase();
                String sql = "INSERT INTO info VALUES('" + fileName + "', '" + editTextName.getText().toString() + "', '" + editTextReading.getText().toString() + "');";
                db.execSQL(sql);
                db.close();
                Toast.makeText(DeviceActivity.this, "DB에 저장", Toast.LENGTH_SHORT).show();
                /*else {
                    SQLiteDatabase db = DB.getWritableDatabase();
                    String sql = "update myDiary set content='" + editText.getText().toString() + "' where diaryDate='" + fileName + "';";
                    db.execSQL(sql);
                    db.close();
                    Toast.makeText(Main2Activity.this, "DB가 수정됨", Toast.LENGTH_SHORT).show();
                }*/

            }
        });

    }

    String readInfo(String fName) {
        //일기내용을 담을 수 있는 스트링타입의 변수설정
        String strDiary = null;

        //데이터베이스내용 출력하기(getReadableDatabase())
        SQLiteDatabase db = DB.getReadableDatabase();

        //날짜에 해당하는 모든 내용을 가지고 오라는 명령
        String sql = "SELECT * FROM info WHERE Date='" + fName + "';";

        //검색기능(select)를 사용할 때에는 sql 문을 읽어오는 Cursor 가 필요하며 마지막에 close 해 주어야 함
        Cursor cursor = db.rawQuery(sql, null);

        strDiary = cursor.getString(1);

        cursor.close();
        db.close();
        return strDiary;
    }
}

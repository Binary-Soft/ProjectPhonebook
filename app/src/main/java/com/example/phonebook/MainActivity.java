package com.example.phonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private ImageButton add_f_butt;
    public MyDataBase myDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add_f_butt = (ImageButton) findViewById(R.id.add_flot_button);
        add_f_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
                startActivity(intent);
            }
        });


        myDataBase = new MyDataBase(this);
        SQLiteDatabase sqLiteDatabase = myDataBase.getWritableDatabase();

        Cursor cursor = myDataBase.fetchAllData();

        if(cursor.getCount() == 0){
            ShowData("Error : ", "No Data Found");
        }
        StringBuffer stringBuffer = new StringBuffer();
        while(cursor.moveToNext()){
            stringBuffer.append("ID : "+ cursor.getString(0)+ "\n");
            stringBuffer.append("F_Name : "+ cursor.getString(1)+ "\n");
            stringBuffer.append("L_Name : "+ cursor.getString(2)+ "\n");
            stringBuffer.append("Email : "+ cursor.getString(3)+ "\n");
            stringBuffer.append("Phone : "+ cursor.getString(4)+ "\n\n\n");
        }
        ShowData("Contact Info..", stringBuffer.toString());
    }

    public void ShowData(String title, String contactlist){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(contactlist);
        builder.setCancelable(true);
        builder.show();
    }

}
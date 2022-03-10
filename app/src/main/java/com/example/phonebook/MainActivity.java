package com.example.phonebook;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageButton add_button;
    ImageView empty_imageview;
    TextView no_data;
    MyDataBase myDataBase;
    ArrayList <String> ID, F_name, L_name, Phone_No, Email, Favourite;
    RecyclerView recyclerView;
    ListView listView;

    CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = (ImageButton) findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
                startActivity(intent);
            }
        });


        myDataBase = new MyDataBase(this);
        ID = new ArrayList<>();
        F_name = new ArrayList<>();
        L_name = new ArrayList<>();
        Phone_No = new ArrayList<>();
        Email = new ArrayList<>();
        Favourite  = new ArrayList<>();

        storeContactInArrays();

        customAdapter = new CustomAdapter(MainActivity.this,this, ID, F_name, L_name,
                Phone_No, Email);


        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));





/*
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
        ShowData("Contact Info..", stringBuffer.toString());*/
    }

/*
    public void ShowData(String title, String contactlist){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(contactlist);
        builder.setCancelable(true);
        builder.show();
    }*/


    public void storeContactInArrays(){
        Cursor cursor = myDataBase.fetchAllData();

        if(cursor.getCount() == 0){
          //  empty_imageview.setVisibility(View.VISIBLE);
         //   no_data.setVisibility(View.VISIBLE);
            Toast.makeText(this, "No Data!", Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
                ID.add(cursor.getString(0));
                F_name.add(cursor.getString(1));
                L_name.add(cursor.getString(2));
                Email.add(cursor.getString(3));
                Phone_No.add(cursor.getString(4));
             //   Favourite.add(cursor.getString(5));

            }
         //   empty_imageview.setVisibility(View.GONE);
        //    no_data.setVisibility(View.GONE);
        }
    }






}
package com.example.phonebook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataBase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Phonebook.db";
    private static final String TABLE_NAME = "Contactlist";
    private static final String ID = "_id";
    private static final String F_NAME = "F_Name";
    private static final String L_NAME = "L_Name";
    private static final String EMAIL = "Email";
    private static final int VERSION_NUMBER = 10;
    private static  final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("+ ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " + F_NAME + " VARCHAR(20), "+ L_NAME + " VARCHAR(15), " + EMAIL + "VARCHAR(30);";

// https://www.youtube.com/watch?v=WRrB7WUj4zQ&list=PLgH5QX0i9K3p9xzYLFGdfYliIRBLVDRV5&index=126

    public MyDataBase(Context context){
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        try{
            sqLiteDatabase.execSQL(CREATE_TABLE);
        }
        catch (Exception e){

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){

    }
}

package com.example.phonebook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDataBase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Phonebook.db";
    private static final String TABLE_NAME = "Contactlist";
    private static final String ID = "ID";
    private static final String F_NAME = "F_Name";
    private static final String L_NAME = "L_Name";
    private static final String EMAIL = "Email";
    private static final int VERSION_NUMBER = 50;
    private Context context;
    private static  final String CREATE_TABLE = "create table "+ TABLE_NAME +" ("+ ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+F_NAME+" VARCHAR(20), "+L_NAME+" VARCHAR(15), "+ EMAIL +" VARCHAR(30));";
    private static  final String UPDATE_TABLE = "ALTER TABLE " + TABLE_NAME + " ADD COLUMN Favourite BOOLEAN DEFAULT 'FALSE';";
    String insert = "INSERT INTO Contactlist (F_Name, L_Name, Email) VALUES( Jack, Pritom, jack@gmail.com );";
// https://www.youtube.com/watch?v=WRrB7WUj4zQ&list=PLgH5QX0i9K3p9xzYLFGdfYliIRBLVDRV5&index=126

    public MyDataBase(Context context){
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        try{
            Toast.makeText(context, "On Create is called", Toast.LENGTH_SHORT).show(); // ...................
            sqLiteDatabase.execSQL(CREATE_TABLE);
        }
        catch (Exception e){
            Toast.makeText(context, "Exception : " + e, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){
        try{
            Toast.makeText(context, "On Upgrade is called", Toast.LENGTH_SHORT).show(); //...........
            sqLiteDatabase.execSQL(insert);
        }
        catch (Exception e){
            Toast.makeText(context, "Exception : " + e, Toast.LENGTH_SHORT).show();
        }
    }
}

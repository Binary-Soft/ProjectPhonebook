package com.example.phonebook;

import static android.text.TextUtils.isEmpty;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
    private static final String PHONE_NO = "Phone_No";
    private static final int VERSION_NUMBER = 66;
    private Context context;
    private static  final String CREATE_TABLE = "create table "+ TABLE_NAME +" ("+ ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+F_NAME+" VARCHAR(20), "+L_NAME+" VARCHAR(15), "+ EMAIL +" TEXT DEFAULT '', "+ PHONE_NO +" VARCHAR(20));";
    private static  final String UPDATE_TABLE = "ALTER TABLE " + TABLE_NAME + " ADD COLUMN Favourite BOOLEAN DEFAULT 'FALSE';";
    String ema = "partho@gmail.com";
    String insert = "insert into "+ TABLE_NAME +" ("+ F_NAME +", "+ L_NAME +", "+ EMAIL +", "+ PHONE_NO +") VALUES( 'Partho', 'Debnath', '"+ ema +"', '015555555');";
    String dele = "DROP TABLE Contactlist";
    String fetch = "select * from " + TABLE_NAME +" ORDER BY "+F_NAME+" ASC ;";

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

    public Cursor fetchAllData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(fetch, null);
        return cursor;
    }

    void addcontact(String f_name, String l_name, String phone_number, String email){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        if(isEmpty(f_name) == true){
            Toast.makeText(context, "Please Enter A Name.", Toast.LENGTH_SHORT).show();
        }
        else{
            cv.put(F_NAME, f_name);
            cv.put(L_NAME, l_name);
            cv.put(PHONE_NO, phone_number);
            cv.put(EMAIL, email);

            long result = sqLiteDatabase.insert(TABLE_NAME,null, cv);
            if(result == -1){
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

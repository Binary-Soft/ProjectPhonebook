package com.example.phonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText f_name_input, l_name_input, phone_no_input, email_input ;
    Button update_button, delete_button;

    String id, f_name, l_name, phone_no, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        f_name_input = findViewById(R.id.f_name_input_update);
        l_name_input = findViewById(R.id.l_name_input_update);
        phone_no_input = findViewById(R.id.phone_no_input_update);
        email_input = findViewById(R.id.email_input_update);

        update_button = findViewById(R.id.update_Contact_button);
        delete_button = findViewById(R.id.delete_Contact_button);


        getAndSetIntentData();

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setTitle(f_name + " " + l_name);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                MyDataBase myDataBase = new MyDataBase(UpdateActivity.this);
                f_name = f_name_input.getText().toString().trim();
                l_name = l_name_input.getText().toString().trim();
                phone_no = phone_no_input.getText().toString().trim();
                email = email_input.getText().toString().trim();
                myDataBase.updateData(id, f_name, l_name, phone_no, email);
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("ID") && getIntent().hasExtra("First Name") &&
                getIntent().hasExtra("Last Name") && getIntent().hasExtra("Phone No") && getIntent().hasExtra("Email")){


            id = getIntent().getStringExtra("ID");
            f_name = getIntent().getStringExtra("First Name");
            l_name = getIntent().getStringExtra("Last Name");
            phone_no = getIntent().getStringExtra("Phone No");
            email = getIntent().getStringExtra("Email");


            f_name_input.setText(f_name);
            l_name_input.setText(l_name);
            phone_no_input.setText(phone_no);
            email_input.setText(email);

          //  Log.d("PD", f_name+" "+l_name+" "+phone_no);
        }
        else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + f_name + "  " + l_name + "!");
        builder.setMessage("Are you sure you want to delete " + f_name + "  " + l_name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDataBase myDataBase = new MyDataBase(UpdateActivity.this);
                myDataBase.deleteAContact(id);
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.create().show();
    }
}
package com.example.phonebook;

import static android.text.TextUtils.isEmpty;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddContactActivity extends AppCompatActivity {

    Button add_button;
    EditText f_name_input, l_name_input, phone_no_input, email_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        f_name_input = findViewById(R.id.f_name_input);
        l_name_input = findViewById(R.id.l_name_input);
        phone_no_input = findViewById(R.id.phone_no_input);
        email_input = findViewById(R.id.email_input);


        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDataBase myDataBase = new MyDataBase(AddContactActivity.this);
                myDataBase.addcontact(f_name_input.getText().toString().trim(), l_name_input.getText().toString().trim(),
                        phone_no_input.getText().toString().trim(), email_input.getText().toString().trim());
            }
        });
    }
}
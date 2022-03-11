package com.example.phonebook;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{
    private Context context;
    private Activity activity;
    private ArrayList Contact_ID, F_name, L_name, Phone_No, Email, Favourite;

    CustomAdapter(Activity activity, Context context, ArrayList contact_id, ArrayList F_name, ArrayList L_name,
                  ArrayList Phone_No, ArrayList Email){
        this.activity = activity;
        this.context = context;
        this.Contact_ID = contact_id;
        this.F_name = F_name;
        this.L_name = L_name;
        this.Phone_No = Phone_No;
        this.Email = Email;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.contact_list_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.First_name_txt.setText(String.valueOf(F_name.get(position)));
        holder.Last_name_txt.setText(String.valueOf(L_name.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("ID", String.valueOf(Contact_ID.get(position)));
                intent.putExtra("First Name", String.valueOf(F_name.get(position)));
                intent.putExtra("Last Name", String.valueOf(L_name.get(position)));
                intent.putExtra("Phone No", String.valueOf(Phone_No.get(position)));
                intent.putExtra("Email", String.valueOf(Email.get(position)));

                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return F_name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Contact_id_txt, First_name_txt, Last_name_txt;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            First_name_txt = itemView.findViewById(R.id.First_name_txt);
            Last_name_txt = itemView.findViewById(R.id.Last_name_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);

            Animation animation = AnimationUtils.loadAnimation(context, R.anim.animation);
            mainLayout.setAnimation(animation);
        }
    }
}

package com.ritugo.tikesaya;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.florent37.shapeofview.shapes.CircleView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

public class HomeAct extends AppCompatActivity {

    LinearLayout btn_ticket_pisa, btn_ticket_torri, btn_ticket_monas, btn_ticket_candi, btn_ticket_sphynx, btn_ticket_pagoda;
    ImageView photo_home_user;
    CircleView btn_to_profile;
    TextView user_balance, nama_lengkap, bio;

    DatabaseReference reference;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String usernama_key_new = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getUsernameLocal();

        photo_home_user = findViewById(R.id.photo_home_user);
        btn_to_profile = findViewById(R.id.btn_to_profile);
        user_balance = findViewById(R.id.user_balance);
        nama_lengkap = findViewById(R.id.nama_lengkap);
        bio = findViewById(R.id.bio);
        btn_ticket_pisa = findViewById(R.id.btn_ticket_pisa);
        btn_ticket_torri = findViewById(R.id.btn_ticket_torri);
        btn_ticket_pagoda = findViewById(R.id.btn_ticket_pagoda);
        btn_ticket_candi = findViewById(R.id.btn_ticket_candi);
        btn_ticket_sphynx = findViewById(R.id.btn_ticket_sphynx);
        btn_ticket_monas = findViewById(R.id.btn_ticket_monas);

        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(usernama_key_new);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                nama_lengkap.setText(dataSnapshot.child("nama_lengkap").getValue().toString());
                user_balance.setText("US$ " + dataSnapshot.child("user_balance").getValue().toString());
                bio.setText(dataSnapshot.child("bio").getValue().toString());

                Picasso.with(HomeAct.this).load(dataSnapshot.child("url_photo_profile").getValue().toString())
                        .fit().centerCrop().into(photo_home_user);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btn_ticket_pisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotopisaticket = new Intent(HomeAct.this,TicketDetailAct.class);
                //meletakkan data pada intent
                gotopisaticket.putExtra("jenis_tiket","Pisa");
                startActivity(gotopisaticket);
            }
        });

        btn_ticket_torri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotopisaticket = new Intent(HomeAct.this,TicketDetailAct.class);
                //meletakkan data pada intent
                gotopisaticket.putExtra("jenis_tiket","Torri");
                startActivity(gotopisaticket);
            }
        });

        btn_ticket_pagoda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotopisaticket = new Intent(HomeAct.this,TicketDetailAct.class);
                //meletakkan data pada intent
                gotopisaticket.putExtra("jenis_tiket","Pagoda");
                startActivity(gotopisaticket);
            }
        });

        btn_ticket_candi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotopisaticket = new Intent(HomeAct.this,TicketDetailAct.class);
                //meletakkan data pada intent
                gotopisaticket.putExtra("jenis_tiket","Candi Borobudur");
                startActivity(gotopisaticket);
            }
        });

        btn_ticket_sphynx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotopisaticket = new Intent(HomeAct.this,TicketDetailAct.class);
                //meletakkan data pada intent
                gotopisaticket.putExtra("jenis_tiket","Sphynx");
                startActivity(gotopisaticket);
            }
        });

        btn_ticket_monas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotopisaticket = new Intent(HomeAct.this,TicketDetailAct.class);
                //meletakkan data pada intent
                gotopisaticket.putExtra("jenis_tiket","Monas");
                startActivity(gotopisaticket);
            }
        });

        btn_to_profile = findViewById(R.id.btn_to_profile);

        btn_to_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoprofile = new Intent(HomeAct.this,MyProfileAct.class);
                startActivity(gotoprofile);
            }
        });

    }



    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        usernama_key_new = sharedPreferences.getString(username_key, "");
    }
}

package com.ritugo.tikesaya;

import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterOneAct extends AppCompatActivity {

    Button btn_continue;

    LinearLayout btn_back;

    EditText xusername, xpassword, xemail_address;

    DatabaseReference reference, reference_username;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_one);

        btn_continue = findViewById(R.id.btn_continue);
        btn_back = findViewById(R.id.btn_back);

        xusername = findViewById(R.id.xusername);
        xpassword = findViewById(R.id.xpassword);
        xemail_address = findViewById(R.id.xemail_address);



        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // ubah state menjadi loading
                btn_continue.setEnabled(false);
                btn_continue.setText("Loading...");


                if (xusername.length() != 0 && xpassword.length() != 0 && xemail_address.length() != 0) {

                    // melihat apakah username sudah dipakai di firebase atau belum
                    reference_username = FirebaseDatabase.getInstance().getReference().child("Users").child(xusername.getText().toString());
                    reference_username.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            if (dataSnapshot.exists()){
                                    Toast.makeText(getApplicationContext(), "Username sudah dipakai", Toast.LENGTH_SHORT).show();
                                    btn_continue.setEnabled(true);
                                    btn_continue.setText("CONTINUE");
                            }else {
                                    //menyimpan kpd local storage
                                    SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString(username_key, xusername.getText().toString());
                                    editor.apply();

                                    //simpan kpd database
                                    reference = FirebaseDatabase.getInstance().getReference().child("Users").child(xusername.getText().toString());

                                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            dataSnapshot.getRef().child("username").setValue(xusername.getText().toString());
                                            dataSnapshot.getRef().child("password").setValue(xpassword.getText().toString());
                                            dataSnapshot.getRef().child("email_address").setValue(xemail_address.getText().toString());
                                            dataSnapshot.getRef().child("user_balance").setValue(800);

                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });
                                    //berpindah activity
                                    Intent gotonextregister = new Intent(RegisterOneAct.this, RegisterTwoAct.class);
                                    startActivity(gotonextregister);


                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }else {
                    btn_continue.setEnabled(true);
                    btn_continue.setText("CONTINUE");
                    Toast.makeText(getApplicationContext(), "Lengkapi data diri Anda", Toast.LENGTH_SHORT).show();
                }

            }
        });



        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });
    }
}

//https://pastebin.com/naHzJCcY
//https://pastebin.com/qmmFX8Xk

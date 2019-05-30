package com.example.userform;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registered extends AppCompatActivity {
    EditText e1,e2;
    TextView tv1;
    Button btnl;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth= FirebaseAuth.getInstance();
        e1=findViewById(R.id.username);
        e2=findViewById(R.id.pwd);
        tv1=findViewById(R.id.accnt);
        btnl=findViewById(R.id.login);


        SharedPreferences sharedPreferences=getSharedPreferences("login",MODE_PRIVATE);
        boolean log=sharedPreferences.getBoolean("islogin",false);
        if(log)
        {
            startActivity(new Intent(Registered.this,MainActivity.class));
        }



        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please wait");
        btnl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userlogin();
            }
        });
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Registered.this,Login.class);
                startActivity(intent);
            }
        });

    }
    private void userlogin()
    {
        String u=e1.getText().toString().trim();
        String p=e2.getText().toString().trim();


        SharedPreferences sharedPreferences=getSharedPreferences("login",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("username",u);
        editor.putString("password",p);
        editor.putBoolean("islogin",true);
        editor.commit();


        progressDialog.show();

        if(TextUtils.isEmpty(u))
        {
            e1.setError("Please enter username");
        }
        else if(TextUtils.isEmpty(p)){
            e2.setError("Please enter password");
        }
        else {
            firebaseAuth.signInWithEmailAndPassword(u,p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        startActivity(new Intent(Registered.this,MainActivity.class));
                        Toast.makeText(Registered.this, "Logged in", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(Registered.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}

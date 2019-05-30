package com.example.userform;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Login extends AppCompatActivity {

    EditText e1,e2,e3;
    TextView tv1;
    Button btn;
    FirebaseAuth firebaseAuth;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        e1=findViewById(R.id.mail);
        e2=findViewById(R.id.password);
        e3=findViewById(R.id.confpassword);
        tv1=findViewById(R.id.signin);
        btn=findViewById(R.id.register);

        firebaseAuth=FirebaseAuth.getInstance();

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please wait....");


        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,Registered.class));
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userRegister();

            }
        });
    }

    private void userRegister()
    {

        String user_name=e1.getText().toString().trim();
        String user_password=e2.getText().toString().trim();
        String confirm_password=e3.getText().toString().trim();

        progressDialog.show();

        if(user_password!=confirm_password){
            e3.setError("Passwords do not match");
        }

        if (TextUtils.isEmpty(user_name))
        {
            e1.setError("Required");
        }

        else if (TextUtils.isEmpty(user_password))
        {
            e2.setError("Required");
        }
        else if (TextUtils.isEmpty(confirm_password))
        {
            e3.setError("Required");
        }

        else
        {
            firebaseAuth.createUserWithEmailAndPassword(user_name,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        progressDialog.dismiss();
                        startActivity(new Intent(Login.this,MainActivity.class));
                        Toast.makeText(Login.this, "Registered Successfully", Toast.LENGTH_SHORT).show();

                    }

                    else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(Login.this, "Error Registering", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    }
}

package com.example.userform;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    LinearLayout linearLayout;
    DatabaseReference databaseReference;
    Button button;
    User user;
    EditText e1,e2,e3,e4,e5,e6;

    String s1,s2,s3;

    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener listener;
    FirebaseDatabase firebaseDatabase;

    private String userId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();

        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("users");


        user = new User();

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        firebaseDatabase.getReference("app_title").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

//                getValues();
//                databaseReference.child("User01").setValue(user);

                Toast.makeText(MainActivity.this, "Data Submitted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(MainActivity.this, "" + databaseError, Toast.LENGTH_SHORT).show();

            }
        });


        linearLayout = findViewById(R.id.lv1);
        button = findViewById(R.id.submit);
        e1=findViewById(R.id.nem);
        e2=findViewById(R.id.tall);
        e3=findViewById(R.id.weigh);
        e4=findViewById(R.id.blood);
        e5=findViewById(R.id.allergy);
        e6=findViewById(R.id.disease);


        Spinner spinner = (Spinner) findViewById(R.id.spinn);
        Spinner spinner2 = (Spinner) findViewById(R.id.spint);
        Spinner spinner1 = (Spinner) findViewById(R.id.spining);


        ArrayAdapter<String> myadaapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.bp));
        myadaapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myadaapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position==0)
                {
                    s1="HIGH";
                }
                else
                {
                    s1="LOW";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> myadapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.yn));
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(myadapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0)
                {
                    s2="YES";
                }
                else
                {
                    s2="NO";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> myadapterr = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.yn));
        myadapterr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(myadapterr);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        linearLayout.setVisibility(View.VISIBLE);
                        s3="YES";
                        break;

                    default:
                        s3="NO";
                        break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validateform();

                String name = e1.getText().toString();
                String height = e2.getText().toString();
                String weight = e3.getText().toString();
                String bloodgroup = e4.getText().toString();
                String allergy = e5.getText().toString();
                String disease = e6.getText().toString();
                String BP = s1;
                String allergetic = s3;
                String diabetic = s2;
                // Check for already existed userId
                if (TextUtils.isEmpty(userId)) {
                    createUser(name,height,weight,bloodgroup,allergetic,allergy,diabetic,BP,disease);
                }
            }
        });

    }

    private void createUser(String name, String height, String weight, String bloodgroup, String allergetic, String allergy, String diabetic, String bp, String disease) {
        if (TextUtils.isEmpty(userId)) {
            userId = databaseReference.push().getKey();
        }

        User user = new User(name, height, weight, bloodgroup, bp, diabetic, allergetic, allergy, disease);

        databaseReference.child(userId).setValue(user);

    }


//    @Override
//    public void onStart() {
//        super.onStart();
//        firebaseAuth.addAuthStateListener(listener);
//
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        if(listener!=null){
//            firebaseAuth.removeAuthStateListener(listener);
//        }
//
//    }
//
//    private void getValues()
//    {
//        user.setNem(e1.getText().toString());
//        user.setTall(e2.getText().toString());
//        user.setWeight(e3.getText().toString());
//        user.setBg(e4.getText().toString());
//        user.setBp(s1);
//        user.setDiab(s2);
//        user.setAller(s3);
//
//        user.setAllertype(e5.getText().toString());
//        user.setDisease(e6.getText().toString());
//
//
//    }

    private boolean validateform() {
        boolean result = true;
        if (TextUtils.isEmpty(e1.getText().toString())) {
            e1.setError("Required");
            result = false;
        } else {
            e1.setError(null);
        }
        if (TextUtils.isEmpty(e2.getText().toString())) {
            e2.setError("Required");
            result = false;
        } else {
            e2.setError(null);
        }
        if (TextUtils.isEmpty(e3.getText().toString())) {
            e3.setError("Required");
            result = false;
        } else {
            e3.setError(null);
        }
        if (TextUtils.isEmpty(e4.getText().toString())) {
            e4.setError("Required");
            result = false;
        } else {
            e4.setError(null);
        }
        if (TextUtils.isEmpty(e5.getText().toString())) {
            e5.setError("Required");
            result = false;
        } else {
            e5.setError(null);
        }
        if (TextUtils.isEmpty(e6.getText().toString())) {
            e6.setError("Required");
            result = false;
        } else {
            e6.setError(null);
        }
        return result;
    }

}

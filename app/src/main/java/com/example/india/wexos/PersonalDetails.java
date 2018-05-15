package com.example.india.wexos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import static com.example.india.wexos.R.id.etbroccupation;
import static com.example.india.wexos.R.id.etcontactnumber;
import static com.example.india.wexos.R.id.etcurrentadd;
import static com.example.india.wexos.R.id.etdateofbirth;
import static com.example.india.wexos.R.id.etdateofjoin;
import static com.example.india.wexos.R.id.etfathername;
import static com.example.india.wexos.R.id.etfathername2;
import static com.example.india.wexos.R.id.etfatheroccuation;
import static com.example.india.wexos.R.id.etmothername;
import static com.example.india.wexos.R.id.etmotheroccupation;
import static com.example.india.wexos.R.id.etname;
import static com.example.india.wexos.R.id.etname2;
import static com.example.india.wexos.R.id.etprmntaddress;
import static com.example.india.wexos.R.id.etqualification;
import static com.example.india.wexos.R.id.etsisname;
import static com.example.india.wexos.R.id.etsisoccupation;

public class PersonalDetails extends Activity {
    Button button;
    EditText name,fathername,dateofjoin,dateofbirth,currentadd,permaddress,contactnum,name2,fathername2,fatheroccupation,mothername,motheroccupation,brothername,
             brotheroccupation,sisname,sisoccupation,qualification,university,yearofpass,marks,majorsub;
    String strname,strfathername,strdoj,strdob,strcurrentadad,strpermadd,strcontnum,
           strname2,strfathername2,strfatheroccupation,strmomname,strmomoccupation,
           strbroname, strbroocupation,
           strsisname,strsisoccupation,strqualification,strunversity,stryearofpassing,strmarks,
           strmajorsub;

    SharedPreferences preferences;
    public static final String MyPREFERENCES = "WexosData";

    DatabaseReference databaseReference;

    private DatabaseReference childRef;
    private DatabaseReference listRef;

    String Database_Path = "Wexos_Employee_Details";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);
        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);
        name = (EditText) findViewById(etname);
        fathername = (EditText) findViewById(R.id.etfathername);
        dateofjoin = (EditText) findViewById(R.id.etdateofjoin);
        dateofbirth = (EditText) findViewById(R.id.etdateofbirth);
        currentadd = (EditText) findViewById(R.id.etcurrentadd);
        permaddress = (EditText) findViewById(R.id.etprmntaddress);
        contactnum = (EditText) findViewById(R.id.etcontactnumber);
        name2 = (EditText) findViewById(R.id.etname2);
        fathername2 = (EditText) findViewById(R.id.etfathername2);
        fatheroccupation= (EditText) findViewById(R.id.etfatheroccuation);
        mothername = (EditText) findViewById(R.id.etmothername);
        motheroccupation = (EditText) findViewById(R.id.etmotheroccupation);
        brothername = (EditText) findViewById(R.id.etbroname);
        brotheroccupation = (EditText) findViewById(R.id.etbroccupation);
        sisname = (EditText) findViewById(R.id.etsisname);
        sisoccupation = (EditText) findViewById(R.id.etsisoccupation);
        qualification = (EditText) findViewById(R.id.etqualification);
        university = (EditText) findViewById(R.id.etuniversity);
        yearofpass = (EditText) findViewById(R.id.etpassingyr);
        marks = (EditText) findViewById(R.id.etmarks);
        majorsub = (EditText) findViewById(R.id.etmajrsub);

        button= (Button) findViewById(R.id.submit);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();

            }
        });


    }
    private void registerUser() {

        strname=name.getText().toString();
        strfathername=fathername.getText().toString();
        strdoj=dateofjoin.getText().toString();
        strdob=dateofbirth.getText().toString();
        strcurrentadad=currentadd.getText().toString();
        strpermadd=permaddress.getText().toString();
        strcontnum=contactnum.getText().toString();
        strname2=name2.getText().toString();
        strfathername2=fathername2.getText().toString();
        strfatheroccupation=fatheroccupation.getText().toString();
        strmomname=mothername.getText().toString();
        strmomoccupation=motheroccupation.getText().toString();
        strbroname=brothername.getText().toString();
        strbroocupation=brotheroccupation.getText().toString();
        strsisname=sisname.getText().toString();
        strsisoccupation=sisoccupation.getText().toString();
        strqualification=qualification.getText().toString();
        strunversity=university.getText().toString();
        stryearofpassing=yearofpass.getText().toString();

        strmarks=marks.getText().toString();
        strmajorsub=majorsub.getText().toString();
       // String ImageUploadId = databaseReference.push().toString();


     /* childRef=  databaseReference.child("personal_information");
      listRef=childRef.child("Gururaj");
      listRef.setValue("name");*/

        /*databaseReference.child("gururaj").push();
        databaseReference.child("gururajkumar").push();*/




        preferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("name",strname);
        editor.putString("fathername",strfathername);
        editor.putString("doj",strdoj);
        editor.putString("dob",strdob);
        editor.putString("currentaddress",strcurrentadad);
        editor.putString("permanentaddrs",strpermadd);
        editor.putString("phonenum",strcontnum);
        editor.putString("othernumber",strname2);
        editor.putString("fname",strfathername2);
        editor.putString("foccupation",strfatheroccupation);
        editor.putString("mname",strmomname);
        editor.putString("moccupation",strmomoccupation);
        editor.putString("broname",strbroname);
        editor.putString("broocup",strbroocupation);
        editor.putString("sisname",strsisname);
        editor.putString("sisocp",strsisoccupation);
        editor.putString("qualification",strqualification);
        editor.putString("university",strunversity);
        editor.putString("ypassing",stryearofpassing);
        editor.putString("marks",strmarks);
        editor.putString("majorsub",strmajorsub);
        editor.commit();



        Intent intent=new Intent(PersonalDetails.this,ImageActivity.class);
        startActivity(intent);

       /* lytRegistration.setVisibility(View.GONE);
        lytLogin.setVisibility(View.VISIBLE);*/
    }
}

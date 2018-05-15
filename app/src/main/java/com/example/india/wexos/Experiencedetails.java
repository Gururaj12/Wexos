package com.example.india.wexos;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by India on 16-Feb-18.
 */

public class Experiencedetails extends Activity {



    EditText lang1,lang2,lang3,hobbies,goal,org,periodfrom,periodto,
            lastposition,jobresponsiblity,salary,rleaving,india,abroad,
            staterestriction,passportno,validto,pan,relate,
            training,joiningtime;
    Button b;

    String Database_Path = "Employee_Details";

    private DatabaseReference childReff;
    private DatabaseReference listReff;
    DatabaseReference databaseReference;

    SharedPreferences preferences;
    public static final String MyPREFERENCES = "WexosData";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.experiencedetails);
        lang1= (EditText) findViewById(R.id.lang1);
        lang2= (EditText) findViewById(R.id.lang2);
        lang3= (EditText) findViewById(R.id.lang3);
        hobbies= (EditText) findViewById(R.id.hobbies);
        goal= (EditText) findViewById(R.id.goal);
        org= (EditText) findViewById(R.id.currentorg);
        periodfrom= (EditText) findViewById(R.id.timeperiodfrom);
        periodto= (EditText) findViewById(R.id.timeperiodto);
        lastposition= (EditText) findViewById(R.id.lastpos);
        jobresponsiblity= (EditText) findViewById(R.id.jobres);
        salary= (EditText) findViewById(R.id.sal);
        rleaving= (EditText) findViewById(R.id.leaving);
        india= (EditText) findViewById(R.id.ind);
        abroad= (EditText) findViewById(R.id.anbr);
        staterestriction= (EditText) findViewById(R.id.stateres);
        passportno= (EditText) findViewById(R.id.pass);
        validto= (EditText) findViewById(R.id.valid);
        pan= (EditText) findViewById(R.id.pandetail);
        relate= (EditText) findViewById(R.id.relatedname);
        training= (EditText) findViewById(R.id.train);
        joiningtime= (EditText) findViewById(R.id.timeofjoin);

        preferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        final String a=preferences.getString("name",null);
        Toast.makeText(this, a, Toast.LENGTH_SHORT).show();



        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);

        childReff=  databaseReference.child("Experience_information");
       // final String name=preferences.getString("name",null);

        //childRef.setValue("thanks");


        b= (Button) findViewById(R.id.sub);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           //    registerUser();

               ModelExperience modelExperience=new ModelExperience( lang1.getText().toString(),
                       lang2.getText().toString(),lang3.getText().toString(),
                       hobbies.getText().toString(),goal.getText().toString(),
                       org.getText().toString(),periodfrom.getText().toString(),
                       periodto.getText().toString(),lastposition.getText().toString(),
                       jobresponsiblity.getText().toString(),salary.getText().toString(),
                        rleaving.getText().toString(),india.getText().toString(),
                       abroad.getText().toString(),staterestriction.getText().toString(),
                       passportno.getText().toString(),validto.getText().toString(),pan.getText().
                       toString(),relate.getText().toString(),training.getText().toString(),
                       joiningtime.getText().toString());

               listReff=childReff.child(a);
               listReff.setValue(modelExperience);



               // listRef=childRef.child(name);

              //  listRef.setValue(modelExperience);

                Intent intent=new Intent(Experiencedetails.this,EmergencyDetails.class);
                startActivity(intent);

            }
        });

    }
    private void registerUser() {
       SharedPreferences preferences=getSharedPreferences(MyPREFERENCES,MODE_PRIVATE);

        SharedPreferences.Editor editor=preferences.edit();

        editor.putString("lang1",lang1.getText().toString());
        editor.putString("lang2",lang2.getText().toString());
        editor.putString("lang3",lang3.getText().toString());
        editor.putString("hobbies",hobbies.getText().toString());
        editor.putString("goal",goal.getText().toString());
        editor.putString("org",org.getText().toString());
        editor.putString("periodfrom",periodfrom.getText().toString());
        editor.putString("periodto",periodto.getText().toString());
        editor.putString("lastposition",lastposition.getText().toString());
        editor.putString("jobresp",jobresponsiblity.getText().toString());
        editor.putString("salary",salary.getText().toString());
        editor.putString("relaving",rleaving.getText().toString());
        editor.putString("india",india.getText().toString());
        editor.putString("abroad",abroad.getText().toString());
        editor.putString("staterestrication",staterestriction.getText().toString());
        editor.putString("passportno",passportno.getText().toString());
        editor.putString("validto",validto.getText().toString());
        editor.putString("pan",pan.getText().toString());
        editor.putString("relate",relate.getText().toString());
        editor.putString("training",training.getText().toString());
        editor.putString("joining",joiningtime.getText().toString());
        editor.commit();
        Intent i=new Intent(Experiencedetails.this,EmergencyDetails.class);
        startActivity(i);






       /* lytRegistration.setVisibility(View.GONE);
        lytLogin.setVisibility(View.VISIBLE);*/
    }
}

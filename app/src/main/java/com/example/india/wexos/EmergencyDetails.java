package com.example.india.wexos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class EmergencyDetails extends AppCompatActivity {


    String ConsumerNAme, ConsumerGEnder, DealName, Comment;

    private DatabaseReference childReff;
    private DatabaseReference listReff;
    DatabaseReference databaseReference;






    EditText edtblood,aalergic,bloodpressure,eyeleft,eyeright,majorillness,name,
            occuption,address,contactno,date,place;
    String Database_Path = "Employee_Details";
    String strBlood,strAlergic,strPressure,strLeft,strRight,
            strIllnes,strName,strOccuption,strAddress,strContact,
            strDate,strPlace;
    SharedPreferences preferences;
    public static final String MyPREFERENCES = "WexosData";
  //  public static final String MyPREFERENCES = "WexosData";
    private Button buttonRegister,buttonLogin;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_details);

        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);

        childReff=  databaseReference.child("Emergency_Details");

        edtblood= (EditText) findViewById(R.id.bloodgroup);
        aalergic= (EditText) findViewById(R.id.allergicto);
        bloodpressure= (EditText) findViewById(R.id.bloodpressure);
        eyeleft= (EditText) findViewById(R.id.eyesightleft);
        eyeright= (EditText) findViewById(R.id.eyesightright);
        majorillness= (EditText) findViewById(R.id.anymajorillness);
        name= (EditText) findViewById(R.id.name);
        occuption= (EditText) findViewById(R.id.occupation);

        address= (EditText) findViewById(R.id.address);
        contactno= (EditText) findViewById(R.id.contactno);
        buttonRegister= (Button) findViewById(R.id.submit);

        preferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        final String b=preferences.getString("name",null);
        Toast.makeText(this, b, Toast.LENGTH_SHORT).show();

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // registerUser();

                ModelEmergency modelExperience=new ModelEmergency(edtblood.getText().toString(),aalergic.getText().toString(),bloodpressure.getText().toString(),eyeleft.getText().toString(),eyeright.getText().toString(),majorillness.getText().toString(),
                        name.getText().toString(),occuption.getText().toString(),
                        address.getText().toString(),contactno.getText().toString(),"22 may","bangalore");              listReff=childReff.child("edetails");
              //  listReff.setValue(modelExperience);

                listReff=childReff.child(b);
                listReff.setValue(modelExperience);
                Intent intent=new Intent(EmergencyDetails.this,DocumentPhotos.class);
                startActivity(intent);
            }
        });
    }

    private void registerUser() {
         preferences =getSharedPreferences(MyPREFERENCES,MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
      /*  String value = preferences.getString("name", null);
        String a=preferences.getString("fathername",null);
        Toast.makeText(EmergencyDetails.this, a.toString(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, value.toString(), Toast.LENGTH_SHORT).show();*/

        strBlood=edtblood.getText().toString();
        strAlergic=aalergic.getText().toString();
        strPressure=bloodpressure.getText().toString();
        strLeft=eyeleft.getText().toString();
        strRight=eyeright.getText().toString();
        strIllnes=majorillness.getText().toString();
        strName=name.getText().toString();
        strOccuption=occuption.getText().toString();

        strAddress=address.getText().toString();
        strContact=contactno.getText().toString();
        editor.putString("sblood",strBlood);
        editor.putString("salergic",strAlergic);
        editor.putString("sbpressure",strPressure);
        editor.putString("seleft",strLeft);
        editor.putString("seright",strRight);
        editor.putString("sillnes",strIllnes);
        editor.putString("sname",strName);
        editor.putString("soccuption",strOccuption);
        editor.putString("saddress",strAddress);
        editor.putString("scontact",strContact);
        editor.commit();

      //  Toast.makeText(this, "thankuu", Toast.LENGTH_SHORT).show();
//        strDate=date.getText().toString();
//        strPlace=place.getText().toString();






       /* lytRegistration.setVisibility(View.GONE);
        lytLogin.setVisibility(View.VISIBLE);*/
    }
}

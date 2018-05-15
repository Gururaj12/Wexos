package com.example.india.wexos;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;

public class ImageActivity extends AppCompatActivity {
    // Folder path for Firebase Storage.
    String Storage_Path = "Employee_photo/";

    // Root Database Name for Firebase Database.
    String Database_Path = "Employee_Details";

    private DatabaseReference childRef;
    private DatabaseReference listRef;



    SharedPreferences preferences;
    public static final String MyPREFERENCES = "WexosData";


    // Creating button.
    Button ChooseButton, UploadButton;

    // Creating EditText.
    EditText ImageName ;

    // Creating ImageView.
    ImageView SelectImage;

    // Creating URI.
    Uri FilePathUri;

    // Creating StorageReference and DatabaseReference object.
    StorageReference storageReference;
    DatabaseReference databaseReference;
    String name;

    // Image request code for onActivityResult() .
    int Image_Request_Code = 7;

    ProgressDialog progressDialog ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        preferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);

        ChooseButton = (Button)findViewById(R.id.ButtonChooseImage);
        UploadButton = (Button)findViewById(R.id.ButtonUploadImage);

// Assign ID's to EditText.
        ImageName = (EditText)findViewById(R.id.ImageNameEditText);

// Assign ID'S to image view.
        SelectImage = (ImageView)findViewById(R.id.ShowImageView);

// Assigning Id to ProgressDialog.
        progressDialog = new ProgressDialog(ImageActivity.this);

        UploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UploadImageFileToFirebaseStorage();
                Intent intent1=new Intent(ImageActivity.this,Experiencedetails.class);
                startActivity(intent1);

            }
        });

        ChooseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                // Creating intent.
                Intent intent = new Intent();

                // Setting intent type as image to select image from phone storage.
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Please Select Image"), Image_Request_Code);

            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Image_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {

            FilePathUri = data.getData();

            try {

                // Getting selected image into Bitmap.k
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilePathUri);

                // Setting up bitmap selected image into ImageView.
                SelectImage.setImageBitmap(bitmap);

                // After selecting image change choose button above text.
                ChooseButton.setText("Image Selected");

            }
            catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    // Creating Method to get the selected image file Extension from File Path URI.
    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();

        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        // Returning the file Extension.
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }

    // Creating UploadImageFileToFirebaseStorage method to upload image on storage.
    public void UploadImageFileToFirebaseStorage() {

        // Checking whether FilePathUri Is empty or not.
        if (FilePathUri != null) {

            // Setting progressDialog Title.
            progressDialog.setTitle("Image is Uploading...");

            // Showing progressDialog.
            progressDialog.show();

            // Creating second StorageReference.
            StorageReference storageReference2nd = storageReference.child(Storage_Path + System.currentTimeMillis() + "." + GetFileExtension(FilePathUri));

            // Adding addOnSuccessListener to second StorageReference.
            storageReference2nd.putFile(FilePathUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            // Getting image name from EditText and store into string variable.
                            //String TempImageName = ImageName.getText().toString().trim();

                            String name=preferences.getString("name",null);
                            String Fathername=preferences.getString("fathername",null);
                            String Dateofjoin=preferences.getString("doj",null);
                            String Dateofbirth=preferences.getString("dob",null);
                            String Currentaddress=preferences.getString("currentadress",null);
                            String Permanentaddress=preferences.getString("permanentaddrs",null);
                            String Phonenumber=preferences.getString("phonenum",null);
                            String Othernumber=preferences.getString("othernumber",null);

                            String Fname=preferences.getString("fname",null);
                            String Foccupation=preferences.getString("foccupation",null);
                            String Mothername=preferences.getString("mname",null);
                            String Motheroccuption=preferences.getString("moccupation",null);
                            String Brothername=preferences.getString("broname",null);
                            String Brotheroccupation=preferences.getString("broocup",null);
                            String Sistername=preferences.getString("sisname",null);
                            String Sisteroccupation=preferences.getString("sisocp",null);
                            String Qualification=preferences.getString("qualification",null);
                            String University=preferences.getString("university",null);
                            String Yearofpassing=preferences.getString("ypassing",null);
                            String Marks=preferences.getString("marks",null);
                            String Majorsub=preferences.getString("majorsub",null);


                            // Hiding the progressDialog after done uploading.
                            progressDialog.dismiss();

                            // Showing toast message after done uploading.
                            Toast.makeText(getApplicationContext(), "Image Uploaded Successfully ", Toast.LENGTH_LONG).show();

                            @SuppressWarnings("VisibleForTests")
                            ImageUploadInfo imageUploadInfo = new ImageUploadInfo(name,Fathername,Dateofjoin,
                                    Dateofbirth,Currentaddress,Permanentaddress,Phonenumber,Othernumber,
                                    Fname,Foccupation,Mothername,Motheroccuption,
                                    Brothername,Brotheroccupation,Sistername,Sisteroccupation,
                                    Qualification,University,Yearofpassing, taskSnapshot.getDownloadUrl().toString());

                            // Getting image upload ID.
                        //    String ImageUploadId = databaseReference.push().getKey();

                            childRef=  databaseReference.child("personal_information");
                            listRef=childRef.child(name);
                            listRef.setValue(imageUploadInfo);
                            Intent i=new Intent(ImageActivity.this,Experiencedetails.class);
                            startActivity(i);

                            // Adding image upload id s child element into databaseReference.
                          //  databaseReference.child(ImageUploadId).setValue(imageUploadInfo);
                        }
                    })
                    // If something goes wrong .
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {

                            // Hiding the progressDialog.
                            progressDialog.dismiss();

                            // Showing exception erro message.
                            Toast.makeText(ImageActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })

                    // On progress change upload time.
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                            // Setting progressDialog Title.
                            progressDialog.setTitle("Image is Uploading...");

                        }
                    });
        }
        else {

            Toast.makeText(ImageActivity.this, "Please Select Image or Add Image Name", Toast.LENGTH_LONG).show();

        }
    }


}

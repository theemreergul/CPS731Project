package com.example.cps731project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public static final int NEW_RESTAURANT_ACTIVITY_REQUEST_CODE = 1;
    private RestaurantViewModel mRestaurantViewModel;
    private UserViewModel mUserViewModel;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static String rtID="";
    public static final String rtName_Key="RestaurantName";
    public static final String rtEmail_Key="Email";
    public static final String rtWeb_Key="Website";
    public static final String adPhone_Key="Phone";
    public static final String adStreet_Key="Street";
    public static final String adCity_Key="City";
    public static final String adProvince_Key="Province";
    public static final String adPostalCode_Key="Postal Code";
    public static final String adCountry_Key="Country";
    public static final String adRTID="RestaurantID";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnSettings = findViewById(R.id.btngoToSettings);
        Button btnShowRestaurants = findViewById(R.id.btnListRestaurants);
        Button btnNewAccount = findViewById(R.id.btnNewAccount);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
            Settings.class);
                startActivity(intent);
            }
        });
        btnShowRestaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        ListRestaurantActivity.class);
                startActivity(intent);
            }
        });
        btnNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        NewAccountActivity.class);
                startActivityForResult(intent, NEW_RESTAURANT_ACTIVITY_REQUEST_CODE);
            }
        });
        mRestaurantViewModel = ViewModelProviders.of(this).get(RestaurantViewModel.class);
        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        // Floating action button setup
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddRestaurantActivity.class);
                startActivityForResult(intent, NEW_RESTAURANT_ACTIVITY_REQUEST_CODE);
            }
        });

//        btnAddRestaurant.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextView rtName=findViewById(R.id.edtRestaurantName);
//                TextView rtEmail=findViewById(R.id.edtEmail);
//                TextView rtWeb=findViewById(R.id.edtWebsite);
//                TextView adPhone=findViewById(R.id.edtPhone);
//                TextView adStreet=findViewById(R.id.edtStreet);
//                TextView adCity=findViewById(R.id.edtCity);
//                TextView adProvince=findViewById(R.id.edtProvince);
//                TextView adPostalCode=findViewById(R.id.edtPostalCode);
//                Spinner adCountry=findViewById(R.id.spnCountry);
//
//                Map<String,Object> restaurant=new HashMap<>();
//
//                restaurant.put(rtName_Key,rtName.getText().toString());
//                restaurant.put(rtEmail_Key, rtEmail.getText().toString());
//                restaurant.put(rtWeb_Key, rtWeb.getText().toString());
//                final Map<String,Object> address=new HashMap<>();
//                address.put(adPhone_Key,adPhone.getText().toString());
//                address.put(adStreet_Key,adStreet.getText().toString());
//                address.put(adCity_Key,adCity.getText().toString());
//                address.put(adProvince_Key,adProvince.getText().toString());
//                address.put(adPostalCode_Key,adPostalCode.getText().toString());
//                address.put(adCountry_Key,adCountry.getSelectedItem().toString());
//                db.collection("restaurants").add(restaurant).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        rtID=documentReference.getId();
//                        address.put(adRTID,rtID);
//                        db.collection("AddressDetails").add(address).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                            @Override
//                            public void onSuccess(DocumentReference documentReference) {
//                                displayToast("Restaurant successfully created");
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                displayToast("Restaurant creation failed");
//                            }
//                        });
//
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        displayToast("Restaurant creation failed");
//                    }
//                });
//
//            }
//        });
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_RESTAURANT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            String[] rData=data.getStringArrayExtra(AddRestaurantActivity.EXTRA_REPLY);

            Log.d("rData",rData[0] + rData.length);
            if(rData[0].equals("restaurant")) {
                Log.d("rData",rData[0] + rData.length);
                Restaurant resto = new Restaurant(rData[1], rData[2], rData[3], rData[4], rData[5], "");
                mRestaurantViewModel.insert(resto);

            }
            else if(rData[0].equals("user")){

                Log.d("rData",rData[0] + rData.length);
                User usr = new User(rData[1], rData[2], rData[3], rData[4], rData[5], rData[6],rData[7],"");
                mUserViewModel.insert(usr);
            }

        } else {
            Log.d("result",String.format("%s",resultCode));
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

}
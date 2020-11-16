package com.example.cps731project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddRestaurantActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.example.cps731project.REPLY";
    private EditText mEditName;
    private EditText mStreet;
    private EditText mCity;
    private EditText mProvince;
    private EditText mPostalCode;
    private Spinner mCountry;
    private EditText mPhone;
    private EditText mEmail;
    private EditText mWebsite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);
        mEditName=findViewById(R.id.edtRestaurantName);
        mStreet=findViewById(R.id.edtStreet);
        mCity=findViewById(R.id.edtCity);
        mProvince=findViewById(R.id.edtProvince);
        mPostalCode=findViewById(R.id.edtPostalCode);
        mCountry=findViewById(R.id.spnCountry);
        mPhone=findViewById(R.id.edtPhone);
        mEmail=findViewById(R.id.edtEmail);
        mWebsite=findViewById(R.id.edtWebsite);
        Button btnAddRestaurant =findViewById(R.id.btnAddRestaurant);

         btnAddRestaurant.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Create a new Intent for the reply.
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mEditName.getText())) {
                // No word was entered, set the result accordingly.
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                // Get the new word that the user entered.


                String rName = mEditName.getText().toString();
                String rAddress = mCity.getText().toString();
                String rPhone = mPhone.getText().toString();
                String rEmail = mEmail.getText().toString();
                String rWebsite = mWebsite.getText().toString();
                String[] rData={"restaurant",rName,rAddress,rPhone,rEmail,rWebsite};
                Log.d("rdata",String.format("%s",rData.length));
                replyIntent.putExtra(EXTRA_REPLY, rData);

                // Set the result status to indicate success.
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        }
    });
    }
}
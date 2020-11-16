package com.example.cps731project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class NewAccountActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.example.cps731project.REPLY";
    private EditText uFirstName;
    private EditText uLastName;
    private EditText uUsername;
    private EditText uPhone;
    private EditText uEmail;
    private EditText uPassword;
    private String userType="";
    private UserViewModel mUserViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);
        uFirstName=findViewById(R.id.edtUFirstName);
        uLastName=findViewById(R.id.edtULastName);
        uUsername=findViewById(R.id.edtUserName);
        uPhone=findViewById(R.id.edtPhone);
        uEmail=findViewById(R.id.edtEmail);
        uPassword=findViewById(R.id.edtPassword);
        RadioButton rbWaiter=findViewById(R.id.rbWaiter);
        final RadioButton rbCustomer =findViewById(R.id.rbCustomer);
        Button btnCrtAccount = findViewById(R.id.btnCrtAccount);
        btnCrtAccount.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(uUsername.getText()) || TextUtils.isEmpty(uFirstName.getText()) || TextUtils.isEmpty(uLastName.getText()) ) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {

                    String firstName = uFirstName.getText().toString();
                    String lastName = uLastName.getText().toString();
                    String userName = uUsername.getText().toString();
                    String phone = uPhone.getText().toString();
                    String email = uEmail.getText().toString();

                    String password=uPassword.getText().toString();

                    if(rbCustomer.isChecked())
                    {
                        userType="customer";
                    }
                    else
                    {
                        userType="waiter";
                    }

                    String[] rData={"user",userName,firstName,lastName,password,phone,email,userType};

                    replyIntent.putExtra(EXTRA_REPLY, rData);

                    // Set the result status to indicate success.
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });

    }

}
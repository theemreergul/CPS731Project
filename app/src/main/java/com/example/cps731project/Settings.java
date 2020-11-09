package com.example.cps731project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends AppCompatActivity {
    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.example.android.cps731project";
    private boolean mNotifications;
    private String mDisplayName="John Smith";
    private final String DISPLAY_KEY = "display";
    private final String NOTIFICATIONS_KEY="notifications";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        final TextView mNotificationsView=findViewById(R.id.txtShowNotifications);
        final TextView mDisplayNameInput = findViewById(R.id.edtDisplayName);
        final Switch mNotificationsSwitch=findViewById(R.id.swNotifications);
        Button saveChanges = findViewById(R.id.btnSave);
        mNotifications = mPreferences.getBoolean(NOTIFICATIONS_KEY, true);
        mDisplayName=mPreferences.getString(DISPLAY_KEY,"JohnSmith");
        mNotificationsSwitch.setChecked(mNotifications);
        mDisplayNameInput.setText(String.format("%s", mDisplayName));
        mNotificationsView.setText(String.format("Your notification is set to %b",mNotifications));
        mNotificationsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mNotifications=isChecked;
            }
        });
        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDisplayName= mDisplayNameInput.getText().toString();
                mDisplayNameInput.setText(String.format("%s", mDisplayName));
                mNotificationsView.setText(String.format("%s",mNotifications));
                displayToast("Changes Saved");

            }
        });

    }
    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putString(DISPLAY_KEY, mDisplayName);
        preferencesEditor.putBoolean(NOTIFICATIONS_KEY, mNotifications);
        preferencesEditor.apply();
    }
}
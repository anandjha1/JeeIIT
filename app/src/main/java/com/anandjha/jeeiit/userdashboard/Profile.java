package com.anandjha.jeeiit.userdashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.anandjha.jeeiit.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Profile extends AppCompatActivity {
    private FloatingActionButton fab;
    private TextInputLayout fname,email,mobile,dob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Hook to activity
        fab = findViewById(R.id.fab);
        fname = findViewById(R.id.full_name);
        email = findViewById(R.id.emailid);
        mobile = findViewById(R.id.mobile);
        dob = findViewById(R.id.dob);

        //Disable the fields
       fname.setEnabled(false);
        fname.setFocusable(false);
        email.setEnabled(false);
        email.setFocusable(false);
        mobile.setEnabled(false);
        mobile.setFocusable(false);
        dob.setEnabled(false);
        dob.setFocusable(false);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Very soon you can edit your profile details", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                fab.setImageResource(R.drawable.ic_done);
                //enable the fields
                fname.setEnabled(true);
                fname.setFocusable(true);
                email.setEnabled(true);
                email.setFocusable(true);
                mobile.setEnabled(true);
                mobile.setFocusable(true);
                dob.setEnabled(true);
                dob.setFocusable(true);
            }
        });
    }
}

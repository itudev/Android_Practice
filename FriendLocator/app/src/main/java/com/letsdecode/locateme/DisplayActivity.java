package com.letsdecode.locateme;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

public class DisplayActivity extends Activity {

    int REQUEST_CODE = 1;
    TextView savedSecretCode;
    public Button editButton;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String SECRET_CODE = "secretKey";
    public SharedPreferences sharedpreferences;
    String secretCodeDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        //xml object
        setContentView(R.layout.display_activiity);
        //capturing TextView
        savedSecretCode = (TextView) findViewById(R.id.savedSecretCode);
        editButton = (Button) findViewById(R.id.edit);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        if (sharedpreferences != null) {
            String code = sharedpreferences.getString(SECRET_CODE, "");

            if (code.isEmpty()) {
                Intent intentDisplay = new Intent(this, EditActivity.class);
                startActivityForResult(intentDisplay, REQUEST_CODE);
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        sharedPreferencesFunction();
    }

    private void sharedPreferencesFunction() {
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        if (sharedpreferences.getString(SECRET_CODE, "").isEmpty() == false) {
            secretCodeDisplay = sharedpreferences.getString(SECRET_CODE, "");
            savedSecretCode.setText("saved code " + secretCodeDisplay);
            //editButton clickListener
            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onEditClicked();
                }
            });
        }
    }

    private void onEditClicked() {
        Intent intentDisplay = new Intent(this, EditActivity.class);
        startActivityForResult(intentDisplay, REQUEST_CODE);
    }

    // onActivityResult

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String code = null;
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {

            if (resultCode == RESULT_OK) {
                code = data.getStringExtra("codeValue");
                if (code != null && code.isEmpty() == false) {
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(SECRET_CODE, code);
                    editor.apply();
                }
            }
            final String secretCode = sharedpreferences.getString(SECRET_CODE, "");
            if (secretCode == null || secretCode.isEmpty()) {
                finish();
            }

        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        String codeCode = sharedpreferences.getString(SECRET_CODE, "");
        savedSecretCode.setText("saved code " + codeCode);

    }

}


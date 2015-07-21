package com.letsdecode.locateme;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;


public class EditActivity extends Activity {

    public static String secretCodeValue;
    public EditText secretCodeEdit;
    TextView enterSecretCode;
    public Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);
        enterSecretCode = (TextView) findViewById(R.id.enterSecretCode);
        enterSecretCode.setText("Set your secret code");
        saveButton = (Button) findViewById(R.id.save);
        secretCodeEdit = (EditText) findViewById(R.id.secretCodeEdit);
         /* Set Text Watcher listener */
        secretCodeEdit.addTextChangedListener(secretCodeWatcher);
        ActionBar actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }


    private final TextWatcher secretCodeWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            secretCodeEdit.setVisibility(View.VISIBLE);
        }

        public void afterTextChanged(Editable s) {
            final String secretCode = secretCodeEdit.getText().toString();
            if (secretCode.length() == 4) {
                saveButton.setEnabled(true);
                saveButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        secretCodeValue = secretCodeEdit.getText().toString();
                        Intent codeIntent = new Intent(getApplicationContext(), DisplayActivity.class);
                        codeIntent.putExtra("codeValue", secretCodeValue);
                        setResult(RESULT_OK, codeIntent);
                        finish();
                        
                    }
                });

            } else {
                saveButton.setEnabled(false);
            }

        }


    };


}

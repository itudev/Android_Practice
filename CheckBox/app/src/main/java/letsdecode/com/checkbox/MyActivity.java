package letsdecode.com.checkbox;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;


public class MyActivity extends Activity {
    //checkbox refernce
    CheckBox checkBox1;
    CheckBox checkBox2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        // listener for first check box

        checkBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox1.isChecked() && (!(checkBox2.isChecked()))) {
                    Toast.makeText(MyActivity.this, "you want coffee with sugar?", Toast.LENGTH_SHORT).show();
                } else if (checkBox2.isChecked() && (!(checkBox1.isChecked()))) {
                    Toast.makeText(MyActivity.this, "I know you are diet conscious and i like it :)", Toast.LENGTH_SHORT).show();

                } else if (!(checkBox1.isChecked()) && (!(checkBox2.isChecked()))) {
                    Toast.makeText(MyActivity.this, "Please select the option", Toast.LENGTH_SHORT).show();

                } else if ((checkBox1.isChecked()) && (checkBox2.isChecked())) {
                    Toast.makeText(MyActivity.this, "Please select only one of the given options", Toast.LENGTH_SHORT).show();

                }

            }

        });
// listener for second check box
        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox2.isChecked() && (!(checkBox1.isChecked()))) {
                    Toast.makeText(MyActivity.this, "I know you are diet conscious and i like it :)", Toast.LENGTH_SHORT).show();
                } else if (checkBox1.isChecked() && (!(checkBox2.isChecked()))) {
                    Toast.makeText(MyActivity.this, "you want coffee with sugar?", Toast.LENGTH_SHORT).show();

                } else if (!(checkBox1.isChecked()) && (!(checkBox2.isChecked()))) {
                    Toast.makeText(MyActivity.this, "Please select the option", Toast.LENGTH_SHORT).show();

                } else if ((checkBox1.isChecked()) && (checkBox2.isChecked())) {
                    Toast.makeText(MyActivity.this, "Please select only one of the given options", Toast.LENGTH_SHORT).show();

                }

            }

        });

        if (!(checkBox1.isChecked()) && (!(checkBox2.isChecked()))) {
            Toast.makeText(MyActivity.this, "Please select the option", Toast.LENGTH_SHORT).show();

        }

        if ((checkBox1.isChecked()) && (checkBox2.isChecked())) {
            Toast.makeText(MyActivity.this, "Please select only one of the given options", Toast.LENGTH_SHORT).show();

        }

    }
}

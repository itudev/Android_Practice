package letsdecode.com.validationedittext;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;


public class MyActivity extends Activity implements TextWatcher {
    EditText myText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        myText = (EditText) findViewById(R.id.editText1);
        myText.addTextChangedListener(this);
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        Toast.makeText(this, "before change", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        Toast.makeText(this, "on text change", Toast.LENGTH_SHORT).show();


    }

    @Override
    public void afterTextChanged(Editable editable) {
        try {
            //if number greater than 100 replace it with hundred.
            int number = Integer.parseInt(editable.toString());
            if (number > 100) {
                editable.replace(0, editable.length(), "100");
            }
        } catch (NumberFormatException e) {
        }


    }
}

package letsdecode.com.loginscreenusingjavaobjects;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MyActivity extends Activity {
    RelativeLayout.LayoutParams messageDimension, userNameDimension,
            userNameEditTextDimension, passwordDimension,passwordValueDimension, buttonDimension;
    RelativeLayout main;
    EditText userNameValue, passwordValue;
    TextView message, userName, password;
    Button login;
    int padding = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

        createMessageTextView();
        createUserNameView();
        createUserNameEditTextView();
        createPasswordTextView();
        createPasswordEditTextView();
        createLoginButton();
        // adding the Views to relative layout
        main.addView(message, messageDimension);
        main.addView(userName, userNameDimension);
        main.addView(userNameValue, userNameEditTextDimension);
        main.addView(password, passwordDimension);
        main.addView(passwordValue, passwordValueDimension);
        main.addView(login, buttonDimension);
        setContentView(main);
    }
    /*
    initializing the view objects

     */
    private void init() {
        main = new RelativeLayout(this);
        // setting height and width of Rll
        RelativeLayout.LayoutParams mainDimensions = new RelativeLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        main.setLayoutParams(mainDimensions);
        //main.setBackgroundColor(Color.parseColor("000"));
        userNameValue = new EditText(this);
        passwordValue = new EditText(this);
        message = new TextView(this);// please Login First
        userName = new TextView(this);
        password = new TextView(this);
        login = new Button(this);
        main.setLayoutParams(mainDimensions);
    }

    // textView
    private void createMessageTextView() {
        //Setting height and width of relative layout.
        messageDimension = new RelativeLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        // alignment
        messageDimension.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        message.setText("Please Login First");
        message.setId(R.id.messageId);
        message.setPadding(10, 100, 10, 10);
        message.setLayoutParams(messageDimension);
    }

    // textView
    private void createUserNameView() {
        //Setting height and width of relative layout.
        userNameDimension = new RelativeLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        // alignment
        userNameDimension.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        userNameDimension.addRule(RelativeLayout.BELOW, R.id.messageId);
        userName.setText("User Name");
        userName.setId(R.id.userNameId);
        userName.setPadding(padding, padding, padding, padding);
        userName.setLayoutParams(userNameDimension);
    }


    private void createUserNameEditTextView() {
        //Setting height and width of relative layout.
        userNameEditTextDimension = new RelativeLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        // alignment
        userNameEditTextDimension.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        userNameEditTextDimension.addRule(RelativeLayout.BELOW, R.id.messageId);
        userNameEditTextDimension.addRule(RelativeLayout.RIGHT_OF, R.id.userNameId);
        userNameEditTextDimension.addRule(RelativeLayout.ALIGN_BASELINE, R.id.userNameId);
        userNameValue.setId(R.id.userNameValueId);
        userNameValue.setLayoutParams(userNameEditTextDimension);
    }

    // textView
    private void createPasswordTextView() {
        //Setting height and width of relative layout.
        passwordDimension = new RelativeLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        // alignment
        passwordDimension.addRule(RelativeLayout.BELOW, R.id.userNameValueId);
        passwordDimension.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        passwordDimension.addRule(RelativeLayout.ALIGN_RIGHT, R.id.userNameId);
        password.setGravity(Gravity.RIGHT);
        password.setText("Password");
        password.setId(R.id.passwordId);
        password.setPadding(padding, padding, padding, padding);
        password.setLayoutParams(passwordDimension);
    }

    private void createPasswordEditTextView() {
        //Setting height and width of relative layout.
        passwordValueDimension = new RelativeLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        // alignment
        passwordValueDimension.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        passwordValueDimension.addRule(RelativeLayout.BELOW, R.id.userNameValueId);
        passwordValueDimension.addRule(RelativeLayout.RIGHT_OF, R.id.passwordId);
        passwordValueDimension.addRule(RelativeLayout.ALIGN_BASELINE, R.id.passwordId);

        passwordValue.setId(R.id.passwordValueId);
        passwordValue.setLayoutParams(passwordValueDimension);
    }

    private void createLoginButton() {
        //Setting height and width of relative layout.
        buttonDimension = new RelativeLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        // alignment
        buttonDimension.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        buttonDimension.addRule(RelativeLayout.BELOW, R.id.passwordValueId);
        login.setId(R.id.loginId);
        login.setText("Login");
        login.setLayoutParams(buttonDimension);
    }


}
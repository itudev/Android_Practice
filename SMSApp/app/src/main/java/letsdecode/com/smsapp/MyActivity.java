package letsdecode.com.smsapp;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MyActivity extends Activity {
    Button smsButton;
    EditText phoneEdit, messageEdit;
    TextView enterPhone, enterMessage;
    String SENT = "SMS SENT";
    String DELIVERED = "SMS_DELIVERED";
    PendingIntent sentPI, deliveredPI;
    BroadcastReceiver smsSentReceiver, smsDeliveredReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        smsButton = (Button) findViewById(R.id.smsButton);
        phoneEdit = (EditText) findViewById(R.id.phoneEdit);
        messageEdit = (EditText) findViewById(R.id.messageEdit);
        enterPhone = (TextView) findViewById(R.id.enterPhone);
        enterMessage = (TextView) findViewById(R.id.enterMessage);
        /*context, result code, intent object, flag?

        two pending intent objects, they will be used to send broadcast later
         */
        sentPI = PendingIntent.getBroadcast(this, 0, new Intent(SENT), 0);
        deliveredPI = PendingIntent.getBroadcast(this, 0, new Intent(DELIVERED), 0);


        smsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                sendSMS();

            }
        });


    }


    private void sendSMS() {
        SmsManager sms = SmsManager.getDefault();
        //get text from edittext
        String phoneNumber = phoneEdit.getText().toString();
        if (phoneNumber.isEmpty()) {
            Toast.makeText(this, "please enter number", Toast.LENGTH_SHORT).show();
            return;
        }

        String message = messageEdit.getText().toString();
        if (message.isEmpty()) {

            Toast.makeText(this, "please enter message", Toast.LENGTH_SHORT).show();
            return;
        }
        /*sendTextMessage takes 5 arguments
        destinationAddress
        sc Address
        text
        sentIntent(pending intent to invoke when the message is sent)
        deliveryIntent(Pending intent to invoke when the message has been delivered)
         */


        sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //create the Broadcast receiver when SMS is sent
        smsSentReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS sent", Toast.LENGTH_SHORT).show();

                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(getBaseContext(), "No Service", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(getBaseContext(), "Generic Failure", Toast.LENGTH_SHORT).show();


                }
            }
        };
//create the broadcast receiver when sms is delivered
        smsDeliveredReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS Delivered", Toast.LENGTH_SHORT).show();

                        break;
                    case Activity.RESULT_CANCELED:
                        Toast.makeText(getBaseContext(), "SMS not Delivered", Toast.LENGTH_SHORT).show();

                        break;


                }
            }
        };


        registerReceiver(smsSentReceiver, new IntentFilter(SENT));
        registerReceiver(smsDeliveredReceiver, new IntentFilter(DELIVERED));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(smsSentReceiver);
        unregisterReceiver(smsDeliveredReceiver);
    }

  
}

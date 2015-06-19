package letsdecode.com.framelayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class MyActivity extends Activity {

    ImageView Im1, Im2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Im1 = (ImageView) findViewById(R.id.imageView1);
        Im2 = (ImageView) findViewById(R.id.imageView2);
        Im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //on clicking make the image invisible and other image visible
                Im1.setVisibility(View.GONE);
                Im2.setVisibility(View.VISIBLE);
            }
        });
        Im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //on clicking make the image invisible and other image visible
                Im2.setVisibility(View.GONE);
                Im1.setVisibility(View.VISIBLE);
            }
        });
    }


}

package example.via.barcode;

import android.content.Context;
import android.graphics.Color;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btverify;
    private Button btclean;
    private TextView result;
    private TextView scanmac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btverify = (Button)findViewById(R.id.button);
        btverify.setOnClickListener(btverifymac);
        btclean = (Button)findViewById(R.id.button2);
        btclean.setOnClickListener(btcleanmac);

        result = (TextView)findViewById(R.id.editText);
        scanmac = (TextView)findViewById(R.id.textView2);

    }

    private Button.OnClickListener btverifymac = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {

            String barcode_mac = scanmac.getText().toString();
            String macAddress;
            WifiManager wm = (WifiManager) getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifi = wm.getConnectionInfo();
            if (wifi.getMacAddress() != null) {
                macAddress = wifi.getMacAddress();// MAC地址
                macAddress = macAddress.replace(":","");
            } else {
                macAddress = "null";
                //Log.d("******sid","macAddress = "+macAddress);
            }

            if(barcode_mac.equalsIgnoreCase(macAddress))
            {

                String text = String.format("PASS!!!\n");
                UpdateTextView_Green(text);

            }else{
                //Log.d("******sid","barcode_mac = "+barcode_mac);
                //Log.d("******sid","macAddress = "+macAddress);

                String text = String.format("Failed!!!  MAC address ="+macAddress);

                UpdateTextView_Red(text);
            }


        }
    };

    private void UpdateTextView_Red(String context) {

        result.setText(context + result.getText());
        result.setTextColor(Color.RED);

    }

    private void UpdateTextView_Green(String context) {

        result.setText(context + result.getText());
        result.setTextColor(Color.GREEN);

    }

    private Button.OnClickListener btcleanmac = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {

            result.setText("");
            scanmac.setText("");



        }
    };



}

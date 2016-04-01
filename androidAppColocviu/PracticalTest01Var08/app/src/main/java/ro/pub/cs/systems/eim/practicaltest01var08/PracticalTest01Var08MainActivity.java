package ro.pub.cs.systems.eim.practicaltest01var08;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var08MainActivity extends ActionBarActivity {



    private EditText concatText = null;
    private Button navigate = null;
    private Button topLeftB = null;
    private Button topRighB = null;
    private Button center = null;
    private Button bottomL = null;
    private Button bottomR = null;

    private int nrOfTries = 0;
    private int nrOfSucc = 0;
    private int nrOfFail = 0;
    String addString = "";
    private IntentFilter intentFilter = new IntentFilter();



    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {


        @Override
        public void onClick(View view) {



                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var08Service.class);


                getApplicationContext().startService(intent);



            switch(view.getId()) {
                case R.id.leftT_button:
                    //topLeftB.getText().toString() +
                     addString += "1,";
                     concatText.setText(addString);
                   break;
                case R.id.rightT_button:
                    addString +=  "2,";
                    concatText.setText(addString);
                    break;
                case R.id.centerB:
                    addString += "3,";
                    concatText.setText(addString);
                    break;
                case R.id.bottomL:
                    addString +=  "4,";
                    concatText.setText(addString);
                    break;
                case R.id.bottomR:
                    addString +=  "5,";
                    concatText.setText(addString);
                    break;
                case R.id.textConcat:


                        intent.putExtra("string_to_compute", addString.toString());

                        intent.putExtra("nrOfTries", nrOfTries);
                        intent.putExtra("nrOfSucc", nrOfSucc);
                        intent.putExtra("nrOfFail", nrOfFail);
                        startActivityForResult(intent, 2016);
                        Toast.makeText(getApplicationContext(), "Nav to Secondary: " , Toast.LENGTH_LONG).show();
                    break;
            }
        }

    }

    private MessageBroadcastReceiver messageBroadcastReceiver = null;
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra("broadcast_message");
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            Log.d("RECEIVER", message);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        int result = data.getIntExtra("computed_result", -1);

        Toast.makeText(getApplicationContext(), "Result is: " + result, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), PracticalTest01Var08Service.class);
        intent.putExtra("key_sum", result);
        startService(intent);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var08_main);

        concatText = (EditText)findViewById(R.id.textConcat);

        topLeftB = (Button)findViewById(R.id.leftT_button);
        topLeftB.setOnClickListener(buttonClickListener);
        topRighB = (Button)findViewById(R.id.rightT_button);
        topRighB.setOnClickListener(buttonClickListener);

        center = (Button)findViewById(R.id.centerB);
        center.setOnClickListener(buttonClickListener);

        bottomL = (Button)findViewById(R.id.bottomL);
        bottomL.setOnClickListener(buttonClickListener);
        bottomR = (Button)findViewById(R.id.bottomR);
        bottomR.setOnClickListener(buttonClickListener);


        if (savedInstanceState != null){

            if (savedInstanceState.containsKey("nrOfTries")){
                nrOfTries = savedInstanceState.getInt("nrOfTries");
            } else {
                nrOfTries = 0 ;
            }

            if (savedInstanceState.containsKey("nrOfSucc")){
                nrOfTries = savedInstanceState.getInt("nrOfSucc");
            } else {
                nrOfSucc = 0;
            }

            if (savedInstanceState.containsKey("nrOfFail")){
                nrOfTries = savedInstanceState.getInt("nrOfFail");
            } else {
                nrOfFail = 0;
            }


        } else {
            nrOfTries = 0 ;
            nrOfSucc = 0;
            nrOfFail = 0;
        }

        intentFilter.addAction("broadcast_message_action");

        messageBroadcastReceiver =  new MessageBroadcastReceiver();


    }

    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt("nrOfTries", nrOfTries);
        savedInstanceState.putInt("nrOfSucc", nrOfSucc);
        savedInstanceState.putInt("nrOfFail", nrOfFail);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        if (savedInstanceState.containsKey("nrOfTries")){
            nrOfTries = savedInstanceState.getInt("nrOfTries");
        } else {
            nrOfTries = 0 ;
        }

        if (savedInstanceState.containsKey("nrOfSucc")){
            nrOfTries = savedInstanceState.getInt("nrOfSucc");
        } else {
            nrOfSucc = 0;
        }

        if (savedInstanceState.containsKey("nrOfFail")){
            nrOfTries = savedInstanceState.getInt("nrOfFail");
        } else {
            nrOfFail = 0;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_practical_test01_var08_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

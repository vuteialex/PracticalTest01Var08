package ro.pub.cs.systems.eim.practicaltest01var08;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PracticalTest01Var08Bun extends ActionBarActivity {

    private EditText concatText = null;
    private Button ok = null;
    private Button cancel = null;
    private int nrSucc = 0;
    private int nrFail = 0;

    private ButtonClickListener buttonClickListener = new ButtonClickListener ();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view){
            switch(view.getId()){
                case R.id.okB:
                    setResult(RESULT_OK, null);
                    nrSucc++;
                    break;
                case R.id.okCC:
                    setResult(RESULT_CANCELED,null);
                    nrFail++;
                    break;

            }
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var08_bun);

        concatText = (EditText)findViewById(R.id.textConcat2);



        Intent intent = getIntent();
        if (intent != null) {
            String toCompute = intent.getStringExtra("string_to_compute");
            concatText.setText(String.valueOf(toCompute));
            intent.putExtra("computed_result", toCompute);

            String tries = intent.getStringExtra("nrOfTries");
            concatText.setText(String.valueOf(tries));
            intent.putExtra("computed_result", tries);

            String succ = intent.getStringExtra("nrOfSucc");
            concatText.setText(String.valueOf(succ));
            intent.putExtra("computed_result", succ);

            String fail = intent.getStringExtra("nrOfFail");
            concatText.setText(String.valueOf(fail));
            intent.putExtra("computed_result", fail);
            setResult(Activity.RESULT_OK, intent);

            finish();
        }
    }
}

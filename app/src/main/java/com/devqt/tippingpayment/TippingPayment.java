package com.devqt.tippingpayment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class TippingPayment extends AppCompatActivity {


    private static final String AMOUNT = "AMOUNT";
    private static final String CUSTOM_PERCENT = "CUSTOM_PERCENT";

    private double currentAmount;
    private int currentCustomPercent;
    private EditText tip10;
    private EditText total10;
    private EditText tip15;
    private EditText total15;
    private EditText amountText;
    private EditText tip20;
    private EditText total20;
    private TextView customTip;
    private TextView tipCustomEdit;
    private EditText totalCustomEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipping_payment);


        if (savedInstanceState == null){
            currentAmount = 0.0;
            currentCustomPercent = 5;
        }
        else {
            currentAmount = savedInstanceState.getDouble(AMOUNT);
            currentCustomPercent = savedInstanceState.getInt(CUSTOM_PERCENT);
        }

        tip10 = (EditText) findViewById(R.id.tip10);
        total10 = (EditText) findViewById(R.id.total10);
        tip15 = (EditText) findViewById(R.id.tip15);
        total15 = (EditText) findViewById(R.id.total15);
        tip20 = (EditText) findViewById(R.id.tip20);
        total20 = (EditText) findViewById(R.id.total20);

        customTip = (TextView) findViewById(R.id.custom_tip);

        tipCustomEdit = (EditText) findViewById(R.id.tip_custom_edit);
        totalCustomEdit = (EditText) findViewById(R.id.total_custom_edit);

        amountText = (EditText) findViewById(R.id.amount_text);

        amountText.addTextChangedListener(amountTextWatcher);

        SeekBar customSeekBar = (SeekBar) findViewById(R.id.custom_seekbar);
        customSeekBar.setOnSeekBarChangeListener(customSeekBarListener);
    }

    private void StandartParameters(){

        double tenPercentTip = currentAmount * .1;
        double tenPercentTotal = currentAmount + tenPercentTip;

        tip10.setText(String.format("%.02f", tenPercentTip));
        total10.setText(String.format("%.02f", tenPercentTip));


        double fifteenPercentTip = currentAmount * .15;
        double fifteenPercentTotal = currentAmount + fifteenPercentTip;

        tip15.setText(String.format("%.02f", fifteenPercentTip));
        total15.setText(String.format("%.02f", fifteenPercentTotal));


        double twentyPercentTip = currentAmount * .20;
        double twentyPercentTotal = currentAmount + twentyPercentTip;


        tip20.setText(String.format("%.02f", twentyPercentTip));
        total20.setText(String.format("%.02f", twentyPercentTotal));
    }
    private void InstateCustom(){

        customTip.setText(currentCustomPercent + "%");


        double customTipAmount = currentAmount * currentCustomPercent * .01;


        double customTotalAmount = currentAmount + customTipAmount;
        tipCustomEdit.setText(String.format("%.02f", customTipAmount));
        totalCustomEdit.setText(String.format("%.02f", customTotalAmount));

    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        outState.putDouble(AMOUNT, currentAmount);
        outState.putInt(CUSTOM_PERCENT, currentCustomPercent);
    }

    private OnSeekBarChangeListener customSeekBarListener = new OnSeekBarChangeListener(){


        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){

            currentCustomPercent = seekBar.getProgress();
            InstateCustom();
        }

        @Override public void onStartTrackingTouch(SeekBar seekBar){

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar){

        }
    };


    private TextWatcher amountTextWatcher = new TextWatcher()
    {

        @Override
        public void afterTextChanged(Editable s) {


        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            try{
                currentAmount = Double.parseDouble(s.toString());
            }
            catch(NumberFormatException e){
                currentAmount = 0.0;
            }

            StandartParameters();
            InstateCustom();
        }

    };
}

package com.example.duong.homework1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.DecimalFormat;

import static java.lang.Double.valueOf;

public class MainActivity extends AppCompatActivity {

    TextView tip10;
    TextView tip15;
    TextView tip20;
    TextView total10;
    TextView total15;
    TextView total20;
    TextView customTip;
    TextView customPercent;
    TextView customTotal;
    SeekBar customSeekBar;
    EditText billTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set View ID's
        tip10 = findViewById(R.id.tip10);
        tip15 = findViewById(R.id.tip15);
        tip20 = findViewById(R.id.tip20);
        total10 = findViewById(R.id.total10);
        total15 = findViewById(R.id.total15);
        total20 = findViewById(R.id.total20);
        customTip = findViewById(R.id.customTIp);
        customPercent = findViewById(R.id.customPercent);
        customTotal = findViewById(R.id.customTotal);
        customSeekBar = findViewById(R.id.customSeekBar);
        billTotal = findViewById(R.id.billTotal);


        //Listen for changes to the edittext (bill total)
        billTotal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                update();
            }
        });

        //Seekbar Listener
        customSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                update();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void update()
    {
        DecimalFormat df2 = new DecimalFormat("0.00");
        double total;

        if(TextUtils.isEmpty(billTotal.getText().toString()))
        {
            total = 0;
        }
        else
        {
            total = valueOf(billTotal.getText().toString());
        }

        //10%
        double tenTip = total * .1;
        String tenTipString = "$" + df2.format(tenTip);
        double tenTotal = total + tenTip;
        String tenTotalString = "$" + df2.format(tenTotal);
        tip10.setText(tenTipString);
        total10.setText(tenTotalString);

        //15%
        double fifteenTip = total * .15;
        String fifteenTipString = "$" + df2.format(fifteenTip);
        double fifteenTotal = total + fifteenTip;
        String fifteenTotalString = "$" + df2.format(fifteenTotal);
        tip15.setText(fifteenTipString);
        total15.setText(fifteenTotalString);

        //20%
        double twentyTip = total * .2;
        String twentyTipString = "$" + df2.format(twentyTip);
        double twentyTotal = total + twentyTip;
        String twentyTotalString = "$" + df2.format(twentyTotal);
        tip20.setText(twentyTipString);
        total20.setText(twentyTotalString);

        //Custom
        double seekProgress = customSeekBar.getProgress();
        double customTipAmount = total * (seekProgress/100);
        String customTipString = "$" + df2.format(customTipAmount);
        double customTotalAmount = total + customTipAmount;
        String customTotalString = "$" + df2.format(customTotalAmount);
        String customPercentString = customSeekBar.getProgress() + "%";
        customPercent.setText(customPercentString);
        customTotal.setText(customTotalString);
        customTip.setText(customTipString);

    }
}

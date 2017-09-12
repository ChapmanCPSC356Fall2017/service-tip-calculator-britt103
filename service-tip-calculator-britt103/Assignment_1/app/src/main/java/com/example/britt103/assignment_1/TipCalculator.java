package com.example.britt103.assignment_1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static android.support.v7.widget.AppCompatDrawableManager.get;

public class TipCalculator extends AppCompatActivity {
    private EditText BillValue;
    private SeekBar tipScore;
    private Button calculateTip;
    private TextView servicescore;
    private TextView TotalBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);

        this.BillValue = (EditText) findViewById(R.id.bill_value);
        this.tipScore = (SeekBar) findViewById(R.id.sb_score);
        this.calculateTip = (Button) findViewById(R.id.btn_go);
        this.servicescore = (TextView) findViewById(R.id.txt_score);
        this.TotalBill = (TextView) findViewById(R.id.txt_total);

        /*sets the slider to  go from 1 to 10 and display above*/
        tipScore.setProgress(0);
        tipScore.incrementProgressBy(1);
        tipScore.setMax(9);


        tipScore.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar tipScore, int progress, boolean fromUser) {
                //displays value of seekbar tipscore between 1 and 10 for later use
                progress = tipScore.getProgress();
                progress = progress + 1;
                servicescore.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        this.calculateTip.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (BillValue.getText().toString().equals("") || BillValue.getText().toString().isEmpty()) {
                    Toast.makeText(TipCalculator.this, "Enter your bill amount", Toast.LENGTH_LONG).show();
                    return;
                }
                double billInput = Double.parseDouble(BillValue.getText().toString());
                double tipPercent;
                double billWithTip;
                int serviceScore = tipScore.getProgress();
                if (serviceScore <= 2) {
                    tipPercent = 0.10;
                    billWithTip = (billInput + (billInput * tipPercent));
                    TotalBill.setText("$" + removeTrailingZero(String.valueOf(String.format("%.2f",billWithTip))));
                }
                if (serviceScore >= 3 && serviceScore <= 4) {
                    tipPercent = 0.13;
                    billWithTip = (billInput + (billInput * tipPercent));
                    TotalBill.setText("$" + removeTrailingZero(String.valueOf(String.format("%.2f",billWithTip))));
                }
                if (serviceScore >= 5 && serviceScore <= 6) {
                    tipPercent = 0.15;
                    billWithTip = (billInput + (billInput * tipPercent));
                    TotalBill.setText("$" + removeTrailingZero(String.valueOf(String.format("%.2f",billWithTip))));
                }
                if (serviceScore >= 7 && serviceScore <= 8) {
                    tipPercent = 0.20;
                    billWithTip = (billInput + (billInput * tipPercent));
                    TotalBill.setText("$" + removeTrailingZero(String.valueOf(String.format("%.2f",billWithTip))));
                }
                if (serviceScore == 9) {
                    tipPercent = 0.25;
                    billWithTip = (billInput + (billInput * tipPercent));
                    TotalBill.setText("$" + removeTrailingZero(String.valueOf(String.format("%.2f",billWithTip))));
                }
            }
        });
    }
    public String removeTrailingZero(String Input) {
        if (!Input.contains(".")) {
            return Input;
        }
        int dotPosition = Input.indexOf(".");
        String newValue = Input.substring(dotPosition, Input.length());
        if (newValue.startsWith(".0")) {
            return Input.substring(0, dotPosition);
        }
        return Input;
    }
}


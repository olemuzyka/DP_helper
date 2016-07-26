package com.gmail.olemuzyka.dphelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText vInputHight;
    EditText vInputWight;
    EditText vInputInhces;
    Button vCalcButton;
    TextView vErrorHigh;
    TextView vErrorWidth;
    TextView vErrorInches;
    TextView vDpi;
    TextView vTarget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vInputHight = (EditText) findViewById(R.id.hight);
        vInputWight = (EditText) findViewById(R.id.wight);
        vInputInhces = (EditText) findViewById(R.id.inch);
        vErrorHigh = (TextView) findViewById(R.id.error_high);
        vErrorWidth = (TextView) findViewById(R.id.error_width);
        vErrorInches = (TextView) findViewById(R.id.error_inches);
        vCalcButton = (Button) findViewById(R.id.calc_button);
        vDpi = (TextView) findViewById(R.id.resultsDp);
        vTarget = (TextView) findViewById(R.id.resultsX);
        vCalcButton.setOnClickListener(clkCalc);
    }

        //click on button "Calculate"
        final View.OnClickListener clkCalc = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean highIs = false;
                boolean widthIs = false;
                boolean inchesIs = false;
                boolean success = false;
                if (TextUtils.isEmpty(vInputHight.getText().toString())) {
                    vErrorHigh.setVisibility(View.VISIBLE);
                    vErrorHigh.setText(R.string.fill_text);
                } else {
                    vErrorHigh.setVisibility(View.INVISIBLE);
                    highIs = true;
                }
                if (TextUtils.isEmpty(vInputWight.getText().toString())) {
                    vErrorWidth.setVisibility(View.VISIBLE);
                    vErrorWidth.setText(R.string.fill_text);
                } else {
                    vErrorWidth.setVisibility(View.INVISIBLE);
                    widthIs = true;
                }
                if (TextUtils.isEmpty(vInputInhces.getText().toString())) {
                    vErrorInches.setVisibility(View.VISIBLE);
                    vErrorInches.setText(R.string.fill_text);
                } else {
                    vErrorInches.setVisibility(View.INVISIBLE);
                    inchesIs = true;
                }
                if (highIs && widthIs && inchesIs) {
                    int dpi = Utils.calculate(getHeight(), getWidth(), gerInches());
                    vDpi.setText(dpi + getString(R.string.dpi));
                    vTarget.setText(Utils.setTarget(dpi));
                    success = true;
                }
                if (success) {
                    vCalcButton.setText(R.string.button_clear);
                    vCalcButton.setOnClickListener(clearListener);
                }

            }
        };

        //Click on button "Clear"
        View.OnClickListener clearListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
                vCalcButton.setText(R.string.calculate);
                vCalcButton.setOnClickListener(clkCalc);
            }
        };



    //Method makes all fields empty
    public void clear() {
        vInputHight.setText("");
        vInputWight.setText("");
        vInputInhces.setText("");
        vDpi.setText("");
        vTarget.setText("");
    }

    public double getHeight() {
        String h = vInputHight.getText().toString();
        double height = Double.parseDouble(h);
        return height;
    }

    public double getWidth() {
        String w = vInputWight.getText().toString();
        double width = Double.parseDouble(w);
        return width;
    }

    public double gerInches() {
        String i = vInputInhces.getText().toString();
        double inches = Double.parseDouble(i);
        return inches;
    }
}

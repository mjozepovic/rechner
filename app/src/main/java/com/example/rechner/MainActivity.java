package com.example.rechner;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextNumberDecimalWertEins = (EditText) findViewById(R.id.editTextNumberDecimalWertEins);
        EditText editTextNumberDecimalWertZwei = (EditText) findViewById(R.id.editTextNumberDecimalWertZwei);
        RadioGroup operation_radio_group = (RadioGroup) findViewById(R.id.operation_radio_group);
        Button buttonBerechnen = (Button) findViewById(R.id.buttonBerechnen);
        TextView textViewErgebnis = (TextView) findViewById(R.id.textViewErgebnis);
        textViewErgebnis.setText("");

        buttonBerechnen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedOperationId = operation_radio_group.getCheckedRadioButtonId();
                RadioButton selectedOperation = (RadioButton) findViewById(selectedOperationId);

                String operation = selectedOperation.getText().toString();
                double value1 = Double.parseDouble(editTextNumberDecimalWertEins.getText().toString());
                double value2 = Double.parseDouble(editTextNumberDecimalWertZwei.getText().toString());
                double result;
                switch(operation) {
                    case "+":
                        result = value1 + value2;
                        break;
                    case "-":
                        result = value1 - value2;
                        break;
                    case "*":
                        result = value1 * value2;
                        break;
                    case "/":
                        result = value1 / value2;
                        break;
                    default:
                        result = 0;
                }
                if(result < 0) {
                    textViewErgebnis.setTextColor(Color.parseColor("#FF0000"));
                    textViewErgebnis.setText(String.valueOf(result));
                } else {
                    textViewErgebnis.setTextColor(Color.parseColor("#000000"));
                    textViewErgebnis.setText(String.valueOf(result));
                }

            }
        });

        textViewErgebnis.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                textViewErgebnis.setText("");
                return false;
            }
        });
    }
}
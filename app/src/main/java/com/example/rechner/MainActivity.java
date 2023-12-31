package com.example.rechner;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("com.example.rechner", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        EditText editTextNumberDecimalWertEins = (EditText) findViewById(R.id.editTextNumberDecimalWertEins);
        EditText editTextNumberDecimalWertZwei = (EditText) findViewById(R.id.editTextNumberDecimalWertZwei);
        RadioGroup operation_radio_group = (RadioGroup) findViewById(R.id.operation_radio_group);
        Button buttonBerechnen = (Button) findViewById(R.id.buttonBerechnen);
        Button buttonMS = (Button) findViewById(R.id.buttonMS);
        Button buttonMR = (Button) findViewById(R.id.buttonMR);
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
                    textViewErgebnis.setTextColor(getResources().getColor(R.color.red));
                    textViewErgebnis.setText(String.valueOf(result));
                } else {
                    textViewErgebnis.setTextColor(getResources().getColor(R.color.black));
                    textViewErgebnis.setText(String.valueOf(result));
                }

            }
        });

        buttonMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedOperationId = operation_radio_group.getCheckedRadioButtonId();
                RadioButton selectedOperation = (RadioButton) findViewById(selectedOperationId);
                String operation = selectedOperation.getText().toString();
                editor.putString("operation", operation);
                editor.apply();
                Toast.makeText(MainActivity.this, getResources().getString(R.string.saved), Toast.LENGTH_SHORT).show();
            }
        });

        buttonMR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String operation = sharedPreferences.getString("operation", "");
                if (!operation.equals("")) {
                    for (int i = 0; i < operation_radio_group.getChildCount(); i++) {
                        RadioButton radioButton = (RadioButton) operation_radio_group.getChildAt(i);
                        if (radioButton.getText().toString().equals(operation)) {
                            radioButton.setChecked(true);
                            break;
                        }
                    }
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

    @Override
    protected void onResume() {
        super.onResume();
        Button berechnenButton = findViewById(R.id.buttonBerechnen);
        berechnenButton.setBackgroundColor(getResources().getColor(R.color.green));
    }
}

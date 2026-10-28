package com.bmical;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText etHeight = findViewById(R.id.etHeight);
        EditText etWeight = findViewById(R.id.etWeight);
        RadioGroup rgGender = findViewById(R.id.rgGender);
        CheckBox cbSave = findViewById(R.id.cbSave);
        Button btnCalc = findViewById(R.id.btnCalc);
        TextView tvResult = findViewById(R.id.tvResult);

        btnCalc.setOnClickListener(v -> {
            String heightText = etHeight.getText().toString().trim();
            String weightText = etWeight.getText().toString().trim();

            if (heightText.isEmpty()) {
                etHeight.setError("Podaj wzrost!");
                return;
            }
            if (weightText.isEmpty()) {
                etWeight.setError("Podaj wagę!");
                return;
            }

            double height;
            double weight;
            try {
                height = Double.parseDouble(heightText);
                weight = Double.parseDouble(weightText);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Wpisz poprawne liczby", Toast.LENGTH_SHORT).show();
                return;
            }

            if (height <= 0 || weight <= 0) {
                Toast.makeText(this, "Wartości muszą być większe od zera", Toast.LENGTH_SHORT).show();
                return;
            }

            double heightM = height / 100;
            double bmi = weight / (heightM * heightM);

            String interpretation;
            if (bmi < 18.5) {
                interpretation = "Niedowaga";
            } else if (bmi < 25) {
                interpretation = "Waga prawidłowa";
            } else if (bmi < 30) {
                interpretation = "Nadwaga";
            } else {
                interpretation = "Otyłość";
            }

            String gender = rgGender.getCheckedRadioButtonId() == R.id.rbFemale ? "Kobieta" : "Mężczyzna";

            tvResult.setText(gender + "\nBMI: " + String.format("%.1f", bmi) + "\n" + interpretation);

            if (cbSave.isChecked()) {
                Toast.makeText(this, "Dane zapisane", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
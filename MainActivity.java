package com.example.srtlabs3x;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private String[] learningRate = {"Learning rate", "0.001", "0.01", "0.05", "0.1", "0.2", "0.3"};
    private String[] timeDead = {"Deadline", "0.5", "1", "2", "5"};
    private String[] iteration = {"Iterations", "100", "200", "500", "1000"};

    TextView pointA;
    TextView pointB;
    TextView pointC;
    TextView pointD;
    TextView w1;
    TextView w2;
    TextView errorLabel;

    Spinner rateSpinner;
    Spinner timeSpinner;
    Spinner iterSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pointA = findViewById(R.id.numberTypedIn);
        pointB = findViewById(R.id.numberTypedIn2);
        pointC = findViewById(R.id.numberTypedIn3);
        pointD = findViewById(R.id.numberTypedIn4);
        w1 = findViewById(R.id.w1);
        w2 = findViewById(R.id.w2);
        errorLabel = findViewById(R.id.textError);

        errorLabel.setTextColor(Color.RED);
        w1.setTextColor(Color.GREEN);
        w2.setTextColor(Color.GREEN);

        rateSpinner = findViewById(R.id.spinner3);
        timeSpinner = findViewById(R.id.spinner2);
        iterSpinner = findViewById(R.id.spinner4);
        rateSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, learningRate){
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLUE);
                }
                return view;
            }
        });
        timeSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, timeDead){
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLUE);
                }
                return view;
            }
        });
        iterSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, iteration){
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLUE);
                }
                return view;
            }
        });

    }

    @SuppressLint("SetTextI18n")
    public void executeLab(View v) {
        if (pointA.getText().toString().isEmpty() || pointB.getText().toString().isEmpty() ||
                pointC.getText().toString().isEmpty() || pointD.getText().toString().isEmpty()) {
            errorLabel.setText("Enter all points, please!");
            return;
        }
        if (rateSpinner.getSelectedItem().toString().equals("Learning rate") || timeSpinner.getSelectedItem().toString().equals("Deadline") ||
                iterSpinner.getSelectedItem().toString().equals("Iterations")) {
            errorLabel.setText("Choose all args (rate, time & iters)!");
            return;
        }
        double getRate = Double.parseDouble(rateSpinner.getSelectedItem().toString());
        double getTime = Double.parseDouble(timeSpinner.getSelectedItem().toString());
        int getIter = Integer.parseInt(iterSpinner.getSelectedItem().toString());
        String[][] points = {{pointA.getText().toString().split(",")[0], pointA.getText().toString().split(",")[1]},
                             {pointB.getText().toString().split(",")[0], pointB.getText().toString().split(",")[1]},
                             {pointC.getText().toString().split(",")[0], pointC.getText().toString().split(",")[1]},
                             {pointD.getText().toString().split(",")[0], pointD.getText().toString().split(",")[1]}};


        PerceptronModel perceptronModel = new PerceptronModel(getRate, getTime, getIter, points);
        w1.setText(Double.toString(perceptronModel.getWeights()[0]));
        w2.setText(Double.toString(perceptronModel.getWeights()[1]));
        errorLabel.setText("");
    }

    public void cleanForms(View v) {
        errorLabel.setText("");
        w1.setText("");
        w2.setText("");
    }
}
package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {

    private Button One, Two, Three, Four, Five, Six, Seven, Eight, Nine, Zero;
    private Button Plus, Minus, Division, Multiply, Result, Clear, Sqrt, Square, Percent;
    private TextView Formula, EndResult;
    private double valueFirst = Double.NaN;
    private double valueSecond;
    private char Action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        setupView();

        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                Formula.setText(Formula.getText().toString() + button.getText().toString());
            }
        };

        One.setOnClickListener(numberClickListener);
        Two.setOnClickListener(numberClickListener);
        Three.setOnClickListener(numberClickListener);
        Four.setOnClickListener(numberClickListener);
        Five.setOnClickListener(numberClickListener);
        Six.setOnClickListener(numberClickListener);
        Seven.setOnClickListener(numberClickListener);
        Eight.setOnClickListener(numberClickListener);
        Nine.setOnClickListener(numberClickListener);
        Zero.setOnClickListener(numberClickListener);

        View.OnClickListener actionClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                calculate();
                if (!Double.isNaN(valueFirst)) {
                    Action = button.getText().charAt(0);
                    Formula.setText(String.valueOf(valueFirst) + Action);
                } else {
                    return;
                }
                EndResult.setText(null);

            }
        };

        View.OnClickListener actionClickListener2 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                Action = button.getText().charAt(0);
                Formula.setText(String.valueOf(Action));
                EndResult.setText(null);
                valueFirst = Double.NaN;
            }
        };
        Plus.setOnClickListener(actionClickListener);
        Minus.setOnClickListener(actionClickListener);
        Multiply.setOnClickListener(actionClickListener);
        Division.setOnClickListener(actionClickListener);
        Sqrt.setOnClickListener(actionClickListener2);
        Square.setOnClickListener(actionClickListener);
        Percent.setOnClickListener(actionClickListener2);
        //
        Result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
                Action = '=';
                EndResult.setText(String.valueOf(valueFirst));
                Formula.setText(null);
            }
        });
        Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EndResult.setText("0");
                valueFirst = Double.NaN;
                Formula.setText(null);
            }
        });
    }
    private void setupView(){
        One = (Button) findViewById(R.id.One);
        Two = (Button) findViewById(R.id.Two);
        Three = (Button) findViewById(R.id.Three);
        Four = (Button) findViewById(R.id.Four);
        Five = (Button) findViewById(R.id.Five);
        Six = (Button) findViewById(R.id.Six);
        Seven = (Button) findViewById(R.id.Seven);
        Eight = (Button) findViewById(R.id.Eight);
        Nine = (Button) findViewById(R.id.Nine);
        Zero = (Button) findViewById(R.id.Zero);
        //
        Plus = (Button) findViewById(R.id.Plus);
        Minus = (Button) findViewById(R.id.Minus);
        Division = (Button) findViewById(R.id.Division);
        Multiply = (Button) findViewById(R.id.Multiply);
        Sqrt = (Button) findViewById(R.id.Sqrt);
        Square = (Button) findViewById(R.id.Square);
        Percent = (Button) findViewById(R.id.Percent);
        //
        Result = (Button) findViewById(R.id.Result);
        Clear = (Button) findViewById(R.id.Clear);
        //
        Formula = (TextView) findViewById(R.id.Formula);
        EndResult = (TextView) findViewById(R.id.EndResult);
    }

    private void calculate() {
        String textFormula = Formula.getText().toString();
        int indexAction = textFormula.indexOf(Action);
        if (!Double.isNaN(valueFirst) || (Action == '√') || (Action == '%')) {
            try {
                if (indexAction != -1) {
                    String numberValue = textFormula.substring(indexAction + 1);
                    valueSecond = Double.parseDouble(numberValue);
                    switch (Action) {
                        case '+':
                            valueFirst += valueSecond;
                            break;
                        case '-':
                            valueFirst -= valueSecond;
                            break;
                        case '*':
                            valueFirst *= valueSecond;
                            break;
                        case '/':
                            if (valueSecond == 0) {
                                valueFirst = 0.0;
                            } else {
                                valueFirst /= valueSecond;
                            }
                            break;
                        case '√':
                            valueFirst = Math.sqrt(valueSecond);
                            break;
                        case '^':
                            valueFirst = Math.pow(valueFirst, valueSecond);
                            break;
                        case '%':
                            valueFirst = valueSecond / 100;
                            break;
                        case '=':
                            valueFirst = valueSecond;
                            break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                valueFirst = Double.parseDouble(Formula.getText().toString());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        EndResult.setText(String.valueOf(valueFirst));
        Formula.setText("");
    }
}
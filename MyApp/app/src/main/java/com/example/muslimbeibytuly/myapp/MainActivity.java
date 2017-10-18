package com.example.muslimbeibytuly.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Calculator calculator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.editText);
        calculator = new Calculator();
    }

    public void numberClicked(View view){
        Button button = (Button)view;
        editText.setText(editText.getText().append(button.getText()));
    }

    public void operationClicked(View view){
        char operation = ((Button)view).getText().charAt(0);
        switch (operation){
            case 'C':
                calculator.clear();
                editText.setText("");
                break;
            case '=':
                calculator.secondOperand = Float.parseFloat(editText.getText().toString());
                editText.setText(String.valueOf(calculator.calculate()));
                break;
            case '+':case '-':case '/':case '*':
                if(editText.getText().toString().equals("")){
                    calculator.firstOperand = Float.parseFloat(editText.getText().toString());
                    calculator.operation = operation;
                    editText.setText("");
                }
                break;
            default:
                break;
        }
    }
}
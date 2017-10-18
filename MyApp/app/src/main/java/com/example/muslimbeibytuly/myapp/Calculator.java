package com.example.muslimbeibytuly.myapp;

/**
 * Created by muslimbeibytuly on 8/28/17.
 */

class Calculator {
    float firstOperand, secondOperand;
    char operation;

    Calculator(){
        firstOperand = 0;
        secondOperand = 0;
        operation = ' ';
    }

    void clear(){
        firstOperand = 0;
        secondOperand = 0;
        operation = ' ';
    }

    float calculate(){
        switch (operation){
            case '+':
                return firstOperand + secondOperand;
            case '-':
                return firstOperand - secondOperand;
            case '/':
                return firstOperand / secondOperand;
            case '*':
                return firstOperand * secondOperand;
        }
        return 0;
    }
}

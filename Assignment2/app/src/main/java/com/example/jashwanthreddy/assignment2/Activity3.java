package com.example.jashwanthreddy.assignment2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.util.Log;

import java.util.regex.Pattern;


public class Activity3 extends AppCompatActivity {
    private TextView _screen;
    private String display = "";
    private String currentOperator = "";
    private String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        _screen = (TextView)findViewById(R.id.textView);
        _screen.setText(display);
    }

    private void updateScreen(){
        _screen.setText(display);
    }
    private void clear()
    {
        display = "";
        currentOperator = "";
        result = "";
    }
    public void onClickNumber(View v)
    {
        if (result != "")
        {
            clear();
            updateScreen();
        }
        Button b = (Button)v;
        display += b.getText();
        updateScreen();
    }
    private boolean isOperator(char op)
    {
        switch (op)
        {
            case '+':
                return true;
            case '-':
                return true;
            case '*':
                return true;
            case '/':
                return true;
            case '%':
                return true;
            default:
                return false;
        }
    }
    public void onClickOperator(View v)
    {
        if (display == "")return;
        Button b = (Button)v;
        if (result != "")
        {
            String _display = result;
            clear();
            display = _display;
        }
        if (currentOperator != "") {
            if (isOperator(display.charAt(display.length() - 1))) {
                display = display.replace(display.charAt(display.length() - 1), b.getText().charAt(0));
                updateScreen();
                return;
            } else {
                getResult();
                display = result;
                result = "";
            }
            currentOperator = b.getText().toString();
        }
        display += b.getText();
        currentOperator = b.getText().toString();
        updateScreen();
    }
    public void onClickEqualButton(View v)
    {
        if (display == "")
        {
            Log.d("Calc", "EMpty display and return");
            return;
        }
        if (!getResult()) {
            Log.d("Calc", "Failed to get result for equal to ");
            return;
        }
        _screen.setText(display + "\n" + String.valueOf(result));
    }
    private double operate(String a, String b, String op)
    {
        switch(op)
        {
            case "+": return Double.valueOf(a) + Double.valueOf(b);
            case "-": return Double.valueOf(a) - Double.valueOf(b);
            case "*": return Double.valueOf(a) * Double.valueOf(b);
            case "/":
                try
                {
                    return Double.valueOf(a) / Double.valueOf(b);
                }
                catch (Exception e) {
                    Log.d("Calc", e.getMessage());
                }
            case "%": return Double.valueOf(Integer.valueOf(a) % Integer.valueOf(b));
            default: return -1;
        }
    }
    private boolean getResult()
    {
        if (currentOperator == "")
        {
            Log.d("Calc", "Empty Current Operator");
            return false;
        }
        String[] operation = display.split(Pattern.quote(currentOperator));
        if (operation.length < 2)
        {
            Log.d("Calc", "Two operands are absent to perform operation");
            return false;
        }
        result = String.valueOf(operate(operation[0], operation[1], currentOperator));
        return true;
    }

}

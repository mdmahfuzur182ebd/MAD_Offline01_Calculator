package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import org.mariuszgromada.math.mxparser.*;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    private EditText display;
    Switch switch_id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switch_id = findViewById(R.id.switch_id);

        switch_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (switch_id.isChecked())
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });


        display= findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              if (getString(R.string.display).equals(display.getText().toString())){
                  display.setText("");
              }
            }
        });
    }

    private void updateText(String strToAdd){
        String oldSet = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldSet.substring(0, cursorPos);
        String rightStr = oldSet.substring(cursorPos);

        if(getString(R.string.display).equals(display.getText().toString())){
            display.setText(strToAdd);
            display.setSelection(cursorPos + 1);
        }
        else {
            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
            display.setSelection(cursorPos + 1);
        }
    }

    public  void zeroBtn(View view){
       updateText("0");
    }

    public  void oneBtn(View view){
        updateText("1");
    }

    public  void twoBtn(View view){
        updateText("2");
    }

    public  void threeBtn(View view){
        updateText("3");
    }

    public  void fourBtn(View view){
        updateText("4");
    }

    public  void fiveBtn(View view){
        updateText("5");
    }

    public  void sixBtn(View view){
        updateText("6");
    }

    public  void sevenBtn(View view){
        updateText("7");
    }

    public  void eightBtn(View view){
        updateText("8");
    }


    public  void nineBtn(View view){
        updateText("9");
    }

    public  void multiplyBtn(View view){
        updateText("x");
    }

    public  void divideBtn(View view){
        updateText("/");
    }

    public  void AddBtn(View view){
        updateText("+");
    }

    public  void subtractBtn(View view){
        updateText("-");
    }

    public  void exponent(View view){
        updateText("^");
    }

    public  void modulo(View view){
        updateText("%");
    }

    public  void pointBtn(View view){
        updateText(".");
    }

    public  void equalBtn(View view){
        String userExp = display.getText().toString();

        userExp = userExp.replaceAll("x","*");

        Expression exp = new Expression(userExp);
        String result = String.valueOf(exp.calculate());

        display.setText(result);
        display.setSelection(result.length());

    }

    public  void clear(View view){
       display.setText("");
    }


    public  void parentheses(View view){
       int cursorPos = display.getSelectionStart();
       int openPar = 0;
       int closedPar = 0;
       int textLen = display.getText().length();

       for (int i=0; i < cursorPos; i++) {
           if (display.getText().toString().substring(i, i+1).equals( "(" )) {
               openPar +=1;
           }
           if (display.getText() .toString().substring(i, i+1).equals( ")" )) {
               closedPar = closedPar + 1;
           }
       }
       if(openPar == closedPar || display.getText().toString().substring(textLen-1, textLen).equals("(")){
          updateText("(");
        }

        else if(closedPar < openPar && !display.getText().toString().substring(textLen-1, textLen).equals("(")){
            updateText(")");
        }
        display.setSelection(cursorPos+1);
    }


    public  void backspaceBtn(View view){
          int cursorPos = display.getSelectionStart();
          int textLen = display.getText().length();

          if(cursorPos != 0 && textLen !=0){
              SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
              selection.replace(cursorPos-1, cursorPos, "");
              display.setText(selection);
              display.setSelection(cursorPos-1);
          }
    }


}
package com.example.akash.calculatorv2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.drawable.DrawableWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button one;
    Button two;
    Button three;
    Button four;
    Button five;
    Button six;
    Button seven;
    Button eight;
    Button nine;
    Button zero;
    Button plus;
    Button minus;
    Button multiply;
    Button divide;
    Button equal;
    Button Clear;
    TextView display;
    String operation ="";
    ArrayList<Double> numbers = new ArrayList<Double>();
    ArrayList<String> operators = new ArrayList<String>();
    ArrayList<String> tokens = new ArrayList<String>();
    ArrayList<Integer> removableOperators = new ArrayList<Integer>();
    Boolean answer = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        one = (Button)findViewById(R.id.Button1);
        two = (Button)findViewById(R.id.Button2);
        three = (Button)findViewById(R.id.Button3);
        four = (Button)findViewById(R.id.Button4);
        five = (Button)findViewById(R.id.Button5);
        six = (Button)findViewById(R.id.Button6);
        seven = (Button)findViewById(R.id.Button7);
        eight = (Button)findViewById(R.id.Button8);
        nine = (Button)findViewById(R.id.Button9);
        zero = (Button)findViewById(R.id.Button0);
        plus = (Button)findViewById(R.id.ButtonAdd);
        minus = (Button)findViewById(R.id.ButtonSubtract);
        multiply = (Button)findViewById(R.id.ButtonMultiply);
        divide = (Button)findViewById(R.id.ButtonDivide);
        equal = (Button)findViewById(R.id.ButtonEqual);
        Clear = (Button)findViewById(R.id.ButtonCE);
        display = (TextView)findViewById(R.id.Display);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        zero.setOnClickListener(this);
        plus.setOnClickListener(this);
        multiply.setOnClickListener(this);
        minus.setOnClickListener(this);
        divide.setOnClickListener(this);
        Clear.setOnClickListener(this);
        equal.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Button0:
                if (answer){
                    operation = "0";
                answer = false;
                }
                else operation+="0";
                break;
            case R.id.Button1:
                if (answer){
                operation = "1";
                answer = false;
            }
            else operation+="1";
                break;
            case R.id.Button2:
                if (answer){
                    operation = "2";
                    answer = false;
                }
                else operation+="2";
                break;
            case R.id.Button3:
                if (answer){
                    operation = "3";
                    answer = false;
                }
                else operation+="3";
                break;
            case R.id.Button4:
                if (answer){
                    operation = "4";
                    answer = false;
                }
                else operation+="4";
                break;
            case R.id.Button5:
                if (answer){
                    operation = "5";
                    answer = false;
                }
                else operation+="5";
                break;
            case R.id.Button6:
                if (answer){
                    operation = "6";
                    answer = false;
                }
                else operation+="6";
                break;
            case R.id.Button7:
                if (answer){
                    operation = "7";
                    answer = false;
                }
                else operation+="7";
                break;
            case R.id.Button8:
                if (answer){
                    operation = "8";
                    answer = false;
                }
                else operation+="8";
                break;
            case R.id.Button9:
                if (answer){
                    operation = "9";
                    answer = false;
                }
                else operation+="9";
                break;
            case R.id.ButtonEqual:
                StringTokenizer tokenizer = new StringTokenizer(operation, "+"+"-"+"*"+"/" ,true);
                    while(tokenizer.hasMoreTokens()){
                        String current = tokenizer.nextToken();
                        tokens.add(current);
                    if(current.equals("+")|| current .equals("-") || current.equals("*")|| current.equals("/")) {
                       if (tokens.get(0).equals("+") || tokens.get(0).equals("-") || tokens.get(0).equals("*") || tokens.get(0).equals("/")){
                            operation = "Error";
                       }
                       else if(tokens.get(tokens.size()-2).equals("+")|| tokens.get(tokens.size()-2).equals("-") || tokens.get(tokens.size()-2).equals("*")|| tokens.get(tokens.size()-2).equals("/")){
                            operation = "Error";
                       }
                        else operators.add(current);
                    }
                    else {
                        numbers.add(Double.parseDouble(current));
                    }
                }

                if (operation.equals("Error") || (numbers.size()==operators.size())) {
                    display.setText("Error");
                }
                else {
                    for(int x = 0; x<operators.size(); x++){
                        if(operators.get(x).equals("*")) {
                            double temp = numbers.get(x);
                            numbers.set(x+1,temp * numbers.get(x + 1));
                            removableOperators.add(x);
                        }
                        else if(operators.get(x).equals("/")) {
                            double temp = numbers.get(x);
                            double ans = (temp / numbers.get(x + 1));
                            numbers.set(x+1, ans);
                            removableOperators.add(x);
                        }
                    }
                    for(int x = removableOperators.size()-1; x>=0; x--){
                        int temp = removableOperators.get(x);
                        operators.remove(temp);
                        numbers.remove(temp);
                    }

                    for(int x = 0; x<operators.size(); x++){
                        if(operators.get(x).equals( "+")) {
                            double temp = numbers.get(x);
                            numbers.set(x+1,temp + numbers.get(x + 1));
                        }
                        else if(operators.get(x).equals("-")) {
                            double temp = numbers.get(x);
                            numbers.set(x+1,temp - numbers.get(x + 1));
                        }
                    }

                    if(Math.round(numbers.get(numbers.size()-1)) == numbers.get(numbers.size()-1)){
                        int num = ((int)Math.round(numbers.get(numbers.size()-1)));
                        operation = Integer.toString(num);
                    }
                    else operation = numbers.get(numbers.size() - 1).toString();
                    answer = true;
                }
                numbers = new ArrayList<Double>();
                operators = new ArrayList<String>();
                tokens = new ArrayList<String>();
                removableOperators = new ArrayList<Integer>();
                break;
            case R.id.ButtonAdd:operation+="+";
                answer = false;
                break;
            case R.id.ButtonSubtract:operation+="-";
                answer = false;
                break;
            case R.id.ButtonMultiply:operation+="*";
                answer = false;
                break;
            case R.id.ButtonDivide:operation+="/";
                answer = false;
                break;
            case R.id.ButtonCE: operation = "0";
                answer = true;
                break;
        }
        if (operation.equals("Error")){
            display.setText("Error");
            operation = "";
        }
        else display.setText(operation);
    }
}
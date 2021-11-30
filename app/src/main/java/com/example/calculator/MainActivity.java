package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private String exp = "";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txt = findViewById(R.id.display);
        txt.setMovementMethod(new ScrollingMovementMethod());
    }

    public void buttonResp(View view)
    {
        TextView disp = findViewById(R.id.display);


        switch(view.getId())
        {
            case R.id.bClr:
                exp = "";
                break;
            case R.id.bSqrt:
                exp += "";
                break;
            case R.id.bDel:
                if(exp.length()>=1)
                    exp = exp.substring(0, exp.length()-1);
                break;
            case R.id.bEquals:
                exp = String.valueOf(evualateExpr());
                break;
            default:
                Button b = (Button)view;
                exp+=b.getText().toString();
        }
        disp.setText(exp);
    }
    private double evualateExpr()
    {
        double a=0.0, b=0.0, res = 0.0;
        int i, n;
        char op, ch=' ';
        boolean flag = false;

        n =0;
        for( i = 0 ; i < exp.length() ; i++)
        {
            ch = exp.charAt(i);
            if(Character.isDigit(ch))
            {
                if(flag)
                    a = a + Math.pow(10,n++)*(ch - '0');
                else
                    a = a * 10 + (ch - '0');
            }
            else if(ch == '.')
                flag = true;
            else
                break;
        }
        n = 0;
        flag = false;
        op = ch;
        for( i = i+1 ; i < exp.length() ; i++)
        {
            ch = exp.charAt(i);
            if(Character.isDigit(ch))
            {
                if(flag)
                    b = b + Math.pow(10,n++)*(ch - '0');
                else
                    b = b * 10 + (ch - '0');
            }
            else if(ch == '.')
                flag = true;
            else
                break;
        }
        switch(op)
        {
            case '+':
                res = a+b;
                break;
            case '-':
                res = a-b;
                break;
            case '/':
                res = a/b;
                break;
            case '*':
                res = a*b;
                break;
            case '^':
                res = Math.pow(a,b);
                break;
            default:
                res = -1.0;
        }
        return res;
    }
}
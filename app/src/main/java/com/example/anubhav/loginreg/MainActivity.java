package com.example.anubhav.loginreg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name, address, username, password;
    Button lgnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=(EditText)findViewById(R.id.editText);
        address=(EditText)findViewById(R.id.editText2);
        username=(EditText)findViewById(R.id.editText3);
        password=(EditText)findViewById(R.id.editText4);
        lgnButton = (Button) findViewById(R.id.button);
        lgnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nm = name.getText().toString();
                String addrs = address.getText().toString();
                String unm = username.getText().toString();
                String pwd = password.getText().toString();
                if (nm.isEmpty()|| addrs.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please check the required fields", Toast.LENGTH_LONG).show();
                }else {
                    Registration registration = new Registration(MainActivity.this);
                    registration.execute(nm, addrs, unm, pwd);
                }
            }
        });
    }
}

package com.example.login1;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonreg;
    private EditText editTextemail;
    private EditText editTextpass;
    private TextView text1;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        buttonreg = (Button) findViewById(R.id.button);
        editTextemail = (EditText) findViewById(R.id.editText3);
        editTextpass = (EditText) findViewById(R.id.editText4);
        text1 = (TextView) findViewById(R.id.textView7);
        buttonreg.setOnClickListener(this);
        text1.setOnClickListener(this);

    }

    private void registers() {
        String email = editTextemail.getText().toString().trim();
        String password = editTextpass.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "you need to enter the email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {

            Toast.makeText(this, "you need to enter the password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("you are getting registered");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"registered ", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"not registered ", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onClick(View view){
        if(view == buttonreg){
            registers();
        }
        if(view == text1){
            //no thing
        }
    }




}

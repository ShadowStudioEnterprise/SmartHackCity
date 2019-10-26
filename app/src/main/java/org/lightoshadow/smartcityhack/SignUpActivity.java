package org.lightoshadow.smartcityhack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private String TAG="";
    private String email;
    private String password;
    private String passwordC;
    private String name;
    private String surname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        EditText editTextName=findViewById(R.id.nameSignInET);
        name = editTextName.getText().toString();
        EditText editTextSurname=findViewById(R.id.surnameSignInET);
        surname = editTextSurname.getText().toString();
        EditText editTextEmail=findViewById(R.id.emailET);
        email = editTextEmail.getText().toString();
        EditText editTextPassword=findViewById(R.id.passwordET);
        EditText editTextConfirm=findViewById(R.id.paswordConfirmET);

        password = editTextPassword.getText().toString();
        passwordC = editTextConfirm.getText().toString();
        Button button=findViewById(R.id.buttonConfirmSignUp);
        button.setOnClickListener(this);

    }
    private  void goMain(){
        Intent intent=new Intent(getApplicationContext(), LogInActivity.class);
        getApplicationContext().startActivity(intent);
    }
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.buttonConfirmSignUp) {
            if (password.length() == 8 && password.compareTo(passwordC) == 0 && !name.isEmpty() ) {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    goMain();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                }
                            }
                        });
            }
        }
    }
}

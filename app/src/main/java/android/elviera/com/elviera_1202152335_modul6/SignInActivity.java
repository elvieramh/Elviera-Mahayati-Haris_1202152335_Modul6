package android.elviera.com.elviera_1202152335_modul6;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    private EditText txtUsername, txtPassword;
    private Button btnSignin;
    private Button btnSignup;
    private ProgressDialog loading;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //mengambil resource dari layout activity sign in
        txtUsername=(EditText)findViewById(R.id.txtLoginEmail);
        txtPassword=(EditText)findViewById(R.id.txtLoginPIN);
        btnSignin=(Button)findViewById(R.id.btnSignin);
        btnSignup=(Button)findViewById(R.id.btnSignup);

        loading=(ProgressDialog)new ProgressDialog(this);

        //txtUsername.setText("elvieraharris97@gmail.com");
        //txtPassword.setText("vira");

        //membantu login
        auth=FirebaseAuth.getInstance();

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    //mengubah nilai
    public void login(){
        String user = txtUsername.getText().toString();
        String pin = txtPassword.getText().toString();
        Log.d("CREDENTIAL::LOGIN","u:"+user+", p:"+pin);
        if(TextUtils.isEmpty(user)){txtUsername.setError("Required"); return;}
        if(TextUtils.isEmpty(user)){txtPassword.setError("Required"); return;}

        //Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show();
        loading.setMessage("Checking User ...");
        loading.show();
        auth.signInWithEmailAndPassword(user, pin)
                .addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            loading.dismiss();
                            Toast.makeText(SignInActivity.this, "Berhasil Login", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(SignInActivity.this,MainActivity.class));
                        }else{
                            loading.dismiss();
                            Toast.makeText(SignInActivity.this, "Username / Password Salah", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
//mengubah niali
    public void register(){
        String user = txtUsername.getText().toString();
        String pin = txtPassword.getText().toString();
        Log.d("CREDENTIAL::REGISTER","u:"+user+", p:"+pin);
        if(TextUtils.isEmpty(user)){txtUsername.setError("Required");}
        if(TextUtils.isEmpty(user)){txtPassword.setError("Required");}

        //Toast.makeText(this, "Register", Toast.LENGTH_SHORT).show();
        loading.setMessage("Wait a sec ...");
        loading.show();
        auth.createUserWithEmailAndPassword(user, pin).addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    loading.dismiss();
                    Toast.makeText(SignInActivity.this, "Berhasil Register", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(SignInActivity.this,SignInActivity.class));
                }else{
                    loading.dismiss();
                    Toast.makeText(SignInActivity.this, "Gagal Register", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

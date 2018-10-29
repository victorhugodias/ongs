package com.example.victordias.ong.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.victordias.ong.DAO.ConfiguracaoFirebase;
import com.example.victordias.ong.Entity.Usuarios;
import com.example.victordias.ong.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etSenha;
    private TextView tvCadastro,tvRecuperarSenha;
    private Button btLogin;
    private FirebaseAuth autenticacao;
    private Usuarios usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        tvCadastro = (TextView) findViewById(R.id.tvLogin);
        tvRecuperarSenha = (TextView) findViewById(R.id.tvRecuperarSenha);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etSenha = (EditText) findViewById(R.id.etSenha);
        btLogin = (Button) findViewById(R.id.btLogin);

        tvCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(i);
            }
        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etEmail.getText().toString().equals("") && !etSenha.getText().toString().equals("")){
                    usuarios = new Usuarios();
                    usuarios.setEmail(etEmail.getText().toString());
                    usuarios.setSenha(etSenha.getText().toString());
                    validarLogin();
                }else{
                    Toast.makeText(LoginActivity.this, "Preencha os campos de Email e Senha.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void validarLogin(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(usuarios.getEmail(), usuarios.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    telaPerfil();
                    Toast.makeText(LoginActivity.this, "Login Efetuado com Sucesso.", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(LoginActivity.this, "Usuario ou Senha inválidos.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void telaPerfil(){
        Intent i = new Intent(LoginActivity.this, PerfilActivity.class);
        startActivity(i);
        finish();
    }


}

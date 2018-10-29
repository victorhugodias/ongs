package com.example.victordias.ong.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.victordias.ong.DAO.ConfiguracaoFirebase;
import com.example.victordias.ong.Entity.Usuarios;
import com.example.victordias.ong.R;
import com.example.victordias.ong.helper.Base64Custom;
import com.example.victordias.ong.helper.Preferencias;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class CadastroActivity extends AppCompatActivity {

    private EditText etRazaoSocial;
    private EditText etNomeFantasia;
    private EditText etCnpj;
    private EditText etRepresentanteLegal;
    private EditText etTelefone;
    private EditText etEndereco;
    private EditText etMunicipio;
    private EditText etBairro;
    private EditText etUf;
    private EditText etCep;
    private EditText etEmail;
    private EditText etSenha;
    private EditText etConfirmarSenha;
    private Button btCadastrar;
    private Button btLimpar;
    private Usuarios usuarios;
    private FirebaseAuth autenticacao;
    private DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_cadastro);


        etRazaoSocial = (EditText) findViewById(R.id.etRazaoSocial);
        etNomeFantasia = (EditText) findViewById(R.id.etNomeFantasia);
        etCnpj = (EditText) findViewById(R.id.etCnpj);
        etRepresentanteLegal = (EditText) findViewById(R.id.etRepresentanteLegal);
        etTelefone = (EditText) findViewById(R.id.etTelefone);
        etEndereco = (EditText) findViewById(R.id.etEndereco);
        etMunicipio = (EditText) findViewById(R.id.etMunicipio);
        etBairro = (EditText) findViewById(R.id.etBairro);
        etUf = (EditText) findViewById(R.id.etUf);
        etCep = (EditText) findViewById(R.id.etCep);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etSenha = (EditText) findViewById(R.id.etSenha);
        etConfirmarSenha = (EditText) findViewById(R.id.etConfirmarSenha);
        btCadastrar = (Button) findViewById(R.id.btCadastrar);
        btLimpar = (Button) findViewById(R.id.btLimpar);



        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etSenha.getText().toString().equals(etConfirmarSenha.getText().toString())){
                    usuarios = new Usuarios();
                    usuarios.setRazaoSocial(etRazaoSocial.getText().toString());
                    usuarios.setNomeFantasia(etNomeFantasia.getText().toString());
                    usuarios.setCnpj(etCnpj.getText().toString());
                    usuarios.setTelefone(etTelefone.getText().toString());
                    usuarios.setEndereco(etEndereco.getText().toString());
                    usuarios.setBairro(etBairro.getText().toString());
                    usuarios.setMunicipio(etMunicipio.getText().toString());
                    usuarios.setUf(etUf.getText().toString());
                    usuarios.setCep(etCep.getText().toString());
                    usuarios.setEmail(etEmail.getText().toString());
                    usuarios.setSenha(etSenha.getText().toString());
                    usuarios.setRepresentanteLegal(etRepresentanteLegal.getText().toString());
                    cadastrarUsuario();
                }else{
                    Toast.makeText(CadastroActivity.this, "As Senhas não são correspondentes.", Toast.LENGTH_LONG).show();
                }


            }
        });
    }//fim onCreate

    private void cadastrarUsuario(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuarios.getEmail(),
                usuarios.getSenha()
        ).addOnCompleteListener(CadastroActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Toast.makeText(CadastroActivity.this, "Usuarios Cadastrado com Sucesso.", Toast.LENGTH_LONG).show();
                    String identificadorUsuario = Base64Custom.codificarBase64(usuarios.getEmail());
                    FirebaseUser usuariosFirebase = task.getResult().getUser();
                    usuarios.setId(identificadorUsuario);
                    usuarios.salvar();

                    Preferencias preferencias = new Preferencias(CadastroActivity.this);
                    preferencias.salvarUsuarioPreferencias(identificadorUsuario, usuarios.getEmail());
                    abrirLoginUsuario();
                }else{
                    String erroExcecao = "";
                    try{
                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e){
                        erroExcecao = "Digite uma senha mais forte, contendo no minimo 8 caracteres entre letras e números.";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        erroExcecao = "O e-mail digitado é inválido, digite um nome e-mail.";
                    }catch (FirebaseAuthUserCollisionException e){
                        erroExcecao = "Esse e-mail já está cadastrado no sistema.";
                    }catch (Exception e){
                        erroExcecao = "Erro ao efetuar o cadastro.";
                        e.printStackTrace();
                    }
                    Toast.makeText(CadastroActivity.this, "Erro: " + erroExcecao, Toast.LENGTH_LONG).show();
                }
            }
        });
    }//fim cadastrar usuario

    public void abrirLoginUsuario(){
        Intent i = new Intent(CadastroActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }

}

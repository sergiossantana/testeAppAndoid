package com.example.sergi.aps1;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import DAO.Pessoa.PessoaOperations;
import Model.Pessoa;

public class Cadastro extends AppCompatActivity  {

    private static final String EXTRA_ADD_UPDATE = "com.example.sergi.aps1.MainActivety";
    private EditText edtNome;
    private EditText edtSobrenome;
    private EditText edtEmail;
    private RadioGroup radioGroup;
    private EditText edtDataNascimento;
    private RadioButton rdSexoM, rdSexoF;
    private EditText edtSenha;
    private EditText edtCfSenha;
    private Button save;
    private PessoaOperations pessoaOperacao;
    private String mode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        final Pessoa novaPessoa = new Pessoa();

        edtNome = (EditText) findViewById(R.id.txtNome);
        edtSobrenome = (EditText) findViewById(R.id.txtSobrenome);
        edtEmail = (EditText) findViewById(R.id.txtEmail);
        edtDataNascimento = (EditText) findViewById(R.id.txtDataNascimento);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroupSexo);
        edtSenha = (EditText) findViewById(R.id.txtSenha);
        edtCfSenha = (EditText) findViewById(R.id.txtConfSenha);
        rdSexoM = (RadioButton) findViewById(R.id.rdSexoM);
        rdSexoF = (RadioButton) findViewById(R.id.rdSexoF);
        save = (Button) findViewById(R.id.buttonSavePessoa);
        pessoaOperacao = new PessoaOperations(this);
        pessoaOperacao.open();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if (checkedId == R.id.rdSexoM) {
                    novaPessoa.setSexo("Masculino");

                } else if (checkedId == R.id.rdSexoF) {
                    novaPessoa.setSexo("Feminino");

                }
            }

        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String senha, senha1;

                    if (edtCfSenha.getText().toString().equals(edtSenha.getText().toString())) {
                        novaPessoa.setNome(edtNome.getText().toString());
                        novaPessoa.setSobrenome(edtSobrenome.getText().toString());
                        novaPessoa.setEmail(edtEmail.getText().toString());
                        novaPessoa.setDataNascimento(edtDataNascimento.getText().toString());

                        novaPessoa.setSenha(edtSenha.getText().toString());
                        pessoaOperacao.addPessoa(novaPessoa);

                        //employeeData.addEmployee(newEmployee);
                        Toast t = Toast.makeText(Cadastro.this, novaPessoa.getNome() + "Foi Salvo(a) com SUCESSO! !", Toast.LENGTH_SHORT);
                        t.show();
                        Intent i = new Intent(Cadastro.this, MainActivity.class);
                        startActivity(i);

                    } else {
                        Toast t = Toast.makeText(Cadastro.this, "As senha não são iguais!", Toast.LENGTH_SHORT);
                        t.show();
                    }


                } catch (Exception E) {
                    Toast t = Toast.makeText(Cadastro.this, "Erro ao Inserir no Banco", Toast.LENGTH_SHORT);
                    t.show();
                }


            }
        });
    }




        public String formatDate(Date date) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String hireDate = sdf.format(date);
            return hireDate;
        }


    }



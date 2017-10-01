package com.example.sergi.aps1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import DAO.Pessoa.PessoaRepositorio;
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
    private PessoaRepositorio pessoaOperacao;
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
        pessoaOperacao = new PessoaRepositorio(this);

        //Verifica se recebeu algum objeto (editar)
        Intent it = getIntent();
        Pessoa pessoa = it.getParcelableExtra("pessoa");
        if(pessoa !=null){
            edtNome.setText(pessoa.getNome());
            edtSobrenome.setText(pessoa.getSobrenome());
            edtEmail.setText(pessoa.getEmail());
            edtDataNascimento.setText(pessoa.getDataNascimento());
            if(pessoa.getSexo().equals("Masculino")){
                rdSexoM.setChecked(true);
            }else{
                rdSexoF.setChecked(true);
            }
            edtSenha.setText(novaPessoa.getSenha());
            novaPessoa.setPessoaId(pessoa.getPessoaId());
        }


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
                    if(edtSenha.getText().length() < 3){
                        Toast t = Toast.makeText(Cadastro.this, "Senha deve ter aos menos 4 digitos", Toast.LENGTH_SHORT);
                        t.show();
                    }else if (edtCfSenha.getText().toString().equals(edtSenha.getText().toString())) {
                        novaPessoa.setNome(edtNome.getText().toString());
                        novaPessoa.setSobrenome(edtSobrenome.getText().toString());
                        novaPessoa.setEmail(edtEmail.getText().toString());
                        novaPessoa.setDataNascimento(edtDataNascimento.getText().toString());

                        novaPessoa.setSenha(edtSenha.getText().toString());
                        pessoaOperacao.salvar(novaPessoa);

                        //employeeData.addEmployee(newEmployee);
                        Toast t = Toast.makeText(Cadastro.this, novaPessoa.getPessoaId() + " - " +
                                novaPessoa.getNome() + " Foi Salvo(a) com SUCESSO! !", Toast.LENGTH_SHORT);
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



    }



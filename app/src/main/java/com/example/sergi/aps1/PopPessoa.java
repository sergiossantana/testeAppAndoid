package com.example.sergi.aps1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import DAO.Pessoa.PessoaRepositorio;
import Model.Pessoa;

/**
 * Created by sergi on 01/10/2017.
 */

public class PopPessoa extends Activity {
    private PessoaRepositorio pessoaOperacao;
    private Pessoa pessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poppessoa);

        pessoaOperacao = new PessoaRepositorio(this);

        //Tamanho do pop up
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width*.8), (int) (height*.6));

        //Instanciar objetos do layout
        Button bFechar = (Button)findViewById(R.id.bPopFechar);
        Button bDeletar = (Button)findViewById(R.id.bPopDeletar);
        Button bAlterar = (Button)findViewById(R.id.bPopAlterar);

        TextView tNome = (TextView)findViewById(R.id.tvPopNome);
        TextView tEmail = (TextView)findViewById(R.id.tvPopEmail);
        TextView tDataNascimento = (TextView)findViewById(R.id.tvPopDataNascimento);
        TextView tSexo = (TextView)findViewById(R.id.tvPopSexo);

        Intent it = getIntent();
        //Recebe objeto pessoa
        pessoa = it.getParcelableExtra("pessoa");
        String t = pessoa.getNome() + " " + pessoa.getSobrenome();
        tNome.setText(t);
        tEmail.setText(pessoa.getEmail());
        tDataNascimento.setText(pessoa.getDataNascimento());
        tSexo.setText(pessoa.getSexo());

        //Botão Fechar
        bFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Botão Deletar
        bDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int exc = pessoaOperacao.excluir(pessoa);
                if(exc > 0){
                    Toast t = Toast.makeText(PopPessoa.super.getApplicationContext(),
                            pessoa.getNome() + " foi excluida!", Toast.LENGTH_SHORT);
                    t.show();
                }
                finish();
            }
        });

        //Botão Alterar
        bAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PopPessoa.super.getApplication(), Cadastro.class);
                intent.putExtra("pessoa", pessoa);
                startActivity(intent);
                finish();
            }
        });


    }
}

package com.example.sergi.aps1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonAdd = (Button)findViewById(R.id.buttonAddPessoa);
        buttonAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonAddPessoa:
                startActivity(new Intent(this, Cadastro.class));
                break;
            case R.id.buttonViewPessoa:
                startActivity(new Intent(this, ListaPessoa.class));
                break;
            default:
                Toast.makeText(this, "botão errado", Toast.LENGTH_LONG).show();
        }
    }
}

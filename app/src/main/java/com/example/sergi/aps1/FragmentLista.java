package com.example.sergi.aps1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;
import DAO.Pessoa.PessoaRepositorio;
import Model.Pessoa;


public class FragmentLista extends ListFragment
        implements AdapterView.OnItemLongClickListener{
    ListView mListView;
    //ArrayAdapter<String> adapter;
    //String[] data = {"Sergio", "Alexandra", "Eduarda", "Paula"};
    List<Pessoa> mPessoas;
    ArrayAdapter<Pessoa> mAdapter;
    PessoaRepositorio mRepositorio;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mRepositorio = new PessoaRepositorio(getActivity());
        mListView = getListView();
        Buscar();
    }

    @Override
    public void onResume(){
        super.onResume();
        mRepositorio = new PessoaRepositorio(getActivity());
        mListView = getListView();
        Buscar();
    }
    public void Buscar(){
        mPessoas = mRepositorio.buscarPessoa(null);
       //mListView.setOnItemLongClickListener(this);
        mAdapter = new ArrayAdapter<Pessoa>(
                getActivity(),
                android.R.layout.simple_list_item_activated_1,
                mPessoas);
        setListAdapter(mAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Pessoa novaPessoa = (Pessoa) l.getItemAtPosition(position);
        Intent intent = new Intent(FragmentLista.super.getActivity(), PopPessoa.class);
        intent.putExtra("pessoa", novaPessoa);
        startActivity(intent);


        /*Pessoa novaPessoa = (Pessoa) l.getItemAtPosition(position);
        String p = novaPessoa.getPessoaId() + " - " + novaPessoa.getNome() + " " +
                novaPessoa.getSobrenome() + " - " + novaPessoa.getDataNascimento() + " " +
                novaPessoa.getSexo() + " " + novaPessoa.getEmail();
        Toast t = Toast.makeText(FragmentLista.super.getActivity(), p, Toast.LENGTH_SHORT);
        t.show();*/
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        return false;
    }
}

package com.example.sergi.aps1;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.List;
import DAO.Pessoa.PessoaRepositorio;
import Model.Pessoa;


public class FragmentLista extends ListFragment {
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

    public void Buscar(){
        mPessoas = mRepositorio.buscarPessoa(null);
       //mListView.setOnItemLongClickListener(this);
        mAdapter = new ArrayAdapter<Pessoa>(
                getActivity(),
                android.R.layout.simple_list_item_activated_1,
                mPessoas);
        setListAdapter(mAdapter);
    }
}

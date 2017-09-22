package com.example.sergi.aps1;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import DAO.Pessoa.PessoaRepositorio;
import Model.Pessoa;

/**
 * Created by sergi on 22/09/2017.
 */

public class PessoaListFragment extends ListFragment implements ActionMode.Callback, AdapterView.OnItemLongClickListener {
    ListView mListView;
    List<Pessoa> mPessoas;
    ArrayAdapter<Pessoa> mAdapter;
    PessoaRepositorio mRepositorio;

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceSatate) {
        super.onActivityCreated(saveInstanceSatate);
        mRepositorio = new PessoaRepositorio(getActivity());
        mListView = getListView();
        limparBusca();
    }

    public void buscar(String s){
        if(s == null || s.trim().equals("")){
            limparBusca();
            return;
        }
        List<Pessoa> pessoasEncontradas = mRepositorio.buscarPessoa("%" + s + "%");
        mListView.setOnItemLongClickListener(null);
        mAdapter = new ArrayAdapter<Pessoa>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                pessoasEncontradas);
        setListAdapter(mAdapter);
    }

    public void limparBusca(){
        mPessoas = mRepositorio.buscarPessoa(null);
        mListView.setOnItemLongClickListener(this);
        mAdapter = new ArrayAdapter<Pessoa>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                mPessoas);
    }


    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        return false;
    }
}

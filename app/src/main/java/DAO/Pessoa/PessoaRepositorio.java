package DAO.Pessoa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import Model.Pessoa;

/**
 * Created by sergi on 21/09/2017.
 */

public class PessoaRepositorio {
    private PessoaSQLHelper helper;

    public PessoaRepositorio(Context ctx){
        helper = new PessoaSQLHelper(ctx);
    }

    //Insere dados no banco
    private long inserir(Pessoa pessoa){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PessoaSQLHelper.COLUMN_NOME, pessoa.getNome());
        cv.put(PessoaSQLHelper.COLUMN_SOBRENOME, pessoa.getSobrenome());
        cv.put(PessoaSQLHelper.COLUMN_EMAIL, pessoa.getEmail());
        cv.put(PessoaSQLHelper.COLUMN_DATA_NASCIMENTO, pessoa.getDataNascimento());
        cv.put(PessoaSQLHelper.COLUMN_SEXO, pessoa.getSexo());
        cv.put(PessoaSQLHelper.COLUMN_SENHA, pessoa.getSenha());

        long id = db.insert(PessoaSQLHelper.TABLE_PESSOA, null, cv);
        if(id != -1){
            pessoa.setPessoaId(id);
        }
        db.close();
        return id;
    }

    //Upadate de dados no banco
    private int atualizar(Pessoa pessoa){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(PessoaSQLHelper.COLUMN_ID, pessoa.getPessoaId());
        cv.put(PessoaSQLHelper.COLUMN_NOME, pessoa.getNome());
        cv.put(PessoaSQLHelper.COLUMN_SOBRENOME, pessoa.getSobrenome());
        cv.put(PessoaSQLHelper.COLUMN_EMAIL, pessoa.getEmail());
        cv.put(PessoaSQLHelper.COLUMN_DATA_NASCIMENTO, pessoa.getDataNascimento());
        cv.put(PessoaSQLHelper.COLUMN_SEXO, pessoa.getSexo());
        cv.put(PessoaSQLHelper.COLUMN_SENHA, pessoa.getSenha());

        int linhasAfetadas = db.update(PessoaSQLHelper.TABLE_PESSOA, cv,
                PessoaSQLHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(pessoa.getPessoaId())});
        db.close();
        return linhasAfetadas;
    }

    //Define se insert ou update no banco
    public void salvar(Pessoa pessoa){
        if(pessoa.getPessoaId() == 0){
            inserir(pessoa);
        }else{
            atualizar(pessoa);
        }
    }

    //Exclui dados do banco
    public int excluir(Pessoa pessoa){
        SQLiteDatabase db = helper.getWritableDatabase();
        int linhasAfetadas = db.delete(PessoaSQLHelper.TABLE_PESSOA,
                PessoaSQLHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(pessoa.getPessoaId())});
        db.close();
        return linhasAfetadas;
    }

    //Efetua busca por nome se não informado parametro retorna todo o banco
    public List<Pessoa> buscarPessoa(String filtro){
        SQLiteDatabase db = helper.getReadableDatabase();

        String sql = "SELECT * FROM " + PessoaSQLHelper.TABLE_PESSOA;
        String[] argumentos = null;
        if(filtro != null){
            sql += " WHERE " + PessoaSQLHelper.COLUMN_NOME + " LIKE ?";
            argumentos = new String[]{filtro};
        }
        sql += " ORDER BY " + PessoaSQLHelper.COLUMN_NOME;
        Cursor cursor = db.rawQuery(sql, argumentos);
        List<Pessoa> pessoas = new ArrayList<Pessoa>();
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(PessoaSQLHelper.COLUMN_ID));
            String nome = cursor.getString(cursor.getColumnIndex(PessoaSQLHelper.COLUMN_NOME));
            String sobrenome = cursor.getString(cursor.getColumnIndex(PessoaSQLHelper.COLUMN_SOBRENOME));
            String email = cursor.getString(cursor.getColumnIndex(PessoaSQLHelper.COLUMN_EMAIL));
            String dataNacimento = cursor.getString(cursor.getColumnIndex(PessoaSQLHelper.COLUMN_DATA_NASCIMENTO));
            String sexo = cursor.getString(cursor.getColumnIndex(PessoaSQLHelper.COLUMN_SEXO));
            String senha = cursor.getString(cursor.getColumnIndex(PessoaSQLHelper.COLUMN_SENHA));
            Pessoa pessoa = new Pessoa(id, nome, sobrenome, email, dataNacimento, sexo, senha);
            pessoas.add(pessoa);
        }
        cursor.close();
        db.close();
        return pessoas;
    }
}

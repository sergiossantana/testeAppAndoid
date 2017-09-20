
package DAO.Pessoa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import Model.Pessoa;

public class PessoaOperations {
   public static final String LOGTAG = "P_MNGMNT_SYS";

   SQLiteOpenHelper dbhandler;
   SQLiteDatabase database;

   private static final String[] allColumns = {
           PessoasDBHandler.COLUMN_ID,
           PessoasDBHandler.COLUMN_NOME,
           PessoasDBHandler.COLUMN_SOBRENOME,
           PessoasDBHandler.COLUMN_EMAIL,
           PessoasDBHandler.COLUMN_DATA_NASCIMENTO,
           PessoasDBHandler.COLUMN_SEXO,
           PessoasDBHandler.COLUMN_SENHA


   };

   public PessoaOperations(Context context) {
       dbhandler = new PessoasDBHandler(context);
   }

   public void open() {
       Log.i(LOGTAG, "Database Opened");
       database = dbhandler.getWritableDatabase();


   }

   public void close() {
       Log.i(LOGTAG, "Database Closed");
       dbhandler.close();

   }

   public Pessoa addPessoa(Pessoa Pessoa) {
       ContentValues values = new ContentValues();
       values.put(PessoasDBHandler.COLUMN_NOME, Pessoa.getNome());
       values.put(PessoasDBHandler.COLUMN_SOBRENOME, Pessoa.getSobrenome());
       values.put(PessoasDBHandler.COLUMN_EMAIL, Pessoa.getEmail());
       values.put(PessoasDBHandler.COLUMN_DATA_NASCIMENTO, Pessoa.getDataNascimento());
       values.put(PessoasDBHandler.COLUMN_SEXO, Pessoa.getSexo());
       values.put(PessoasDBHandler.COLUMN_SENHA, Pessoa.getSenha());
       long insertid = database.insert(PessoasDBHandler.TABLE_PESSOAS, null, values);
       Pessoa.setPessoaId(insertid);
       return Pessoa;

   }

   // Getting single Pessoa
   public Pessoa getPessoa(long id) {

       Cursor cursor = database.query(PessoasDBHandler.TABLE_PESSOAS, allColumns, PessoasDBHandler.COLUMN_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
       if (cursor != null)
           cursor.moveToFirst();

       Pessoa e = new Pessoa(Long.parseLong(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
       // return Pessoa
       return e;
   }

   public List<Pessoa> getAllPessoas() {

       Cursor cursor = database.query(PessoasDBHandler.TABLE_PESSOAS, allColumns, null, null, null, null, null);

       List<Pessoa> Pessoas = new ArrayList<>();
       if (cursor.getCount() > 0) {
           while (cursor.moveToNext()) {
               Pessoa Pessoa = new Pessoa();
               Pessoa.setPessoaId(cursor.getLong(cursor.getColumnIndex(PessoasDBHandler.COLUMN_ID)));
               Pessoa.setNome(cursor.getString(cursor.getColumnIndex(PessoasDBHandler.COLUMN_NOME)));
               Pessoa.setSobrenome(cursor.getString(cursor.getColumnIndex(PessoasDBHandler.COLUMN_SOBRENOME)));
               Pessoa.setEmail(cursor.getString(cursor.getColumnIndex(PessoasDBHandler.COLUMN_EMAIL)));
               Pessoa.setDataNascimento(cursor.getString(cursor.getColumnIndex(PessoasDBHandler.COLUMN_DATA_NASCIMENTO)));
               Pessoa.setSexo(cursor.getString(cursor.getColumnIndex(PessoasDBHandler.COLUMN_SEXO)));
               Pessoa.setSenha(cursor.getString(cursor.getColumnIndex(PessoasDBHandler.COLUMN_SENHA)));
               Pessoas.add(Pessoa);
           }
       }
       // return All Pessoas
       return Pessoas;
   }
}

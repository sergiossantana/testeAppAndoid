package DAO.Pessoa;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sergi on 21/09/2017.
 */

public class PessoaSQLHelper extends SQLiteOpenHelper {
    //Variaveis com dados do banco
    private static final String DATABASE_NAME = "pessoaDb";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_PESSOA = "pessoa";
    public static final String COLUMN_ID = "pessoaId";
    public static final String COLUMN_NOME = "name";
    public static final String COLUMN_SOBRENOME= "sobrenome";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_DATA_NASCIMENTO= "dataNascimento";
    public static final String COLUMN_SEXO= "sexo";
    public static final String COLUMN_SENHA= "senha";

    public PessoaSQLHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    //Cria o banco de dados
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL(
                "CREATE TABLE " + TABLE_PESSOA + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NOME + " TEXT NOT NULL, " +
                        COLUMN_SOBRENOME + " TEXT NOT NULL, " +
                        COLUMN_EMAIL + " TEXT, " +
                        COLUMN_DATA_NASCIMENTO + " TEXT, " +
                        COLUMN_SEXO + " TEXT, " +
                        COLUMN_SENHA + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int NewVersion){
        //próxima versões
    }

}

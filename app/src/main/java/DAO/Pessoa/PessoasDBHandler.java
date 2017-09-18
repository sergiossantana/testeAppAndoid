package DAO.Pessoa;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class PessoasDBHandler extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "pessoas.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_PESSOAS = "pessoas";
    public static final String COLUMN_ID = "pessoaId";
    public static final String COLUMN_NOME = "firstname";
    public static final String COLUMN_SOBRENOME= "sobrenome";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_DATA_NASCIMENTO= "dataNascimento";
    public static final String COLUMN_SEXO= "sexo";
    public static final String COLUMN_SENHA= "senha";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_PESSOAS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NOME + " TEXT, " +
                    COLUMN_SOBRENOME + " TEXT, " +
                    COLUMN_EMAIL+ " TEXT, " +
                    COLUMN_DATA_NASCIMENTO + " NUMERIC, " +
                    COLUMN_SEXO + " TEXT " +
                    COLUMN_SENHA + " TEXT " +
                    ")";


    public PessoasDBHandler(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PESSOAS);
        db.execSQL(TABLE_CREATE);
    }
}
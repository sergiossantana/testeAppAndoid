package Model;


import android.os.Parcel;
import android.os.Parcelable;


public class Pessoa implements Parcelable {

    private long pessoaId;
    private String nome;
    private String sobrenome;
    private String email;
    private String dataNascimento;
    private String sexo;
    private String senha;

    public Pessoa(){};


    public Pessoa(long pessoaId, String nome, String sobrenome, String email, String dataNascimento,
                  String sexo, String senha){
        this.pessoaId = pessoaId;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.senha = senha;

    }


    protected Pessoa(Parcel in) {
        pessoaId = in.readLong();
        nome = in.readString();
        sobrenome = in.readString();
        email = in.readString();
        dataNascimento = in.readString();
        sexo = in.readString();
        senha = in.readString();
    }

    public static final Creator<Pessoa> CREATOR = new Creator<Pessoa>() {
        @Override
        public Pessoa createFromParcel(Parcel in) {
            return new Pessoa(in);
        }

        @Override
        public Pessoa[] newArray(int size) {
            return new Pessoa[size];
        }
    };

    public long getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(long pessoaId) {
        this.pessoaId = pessoaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String email) {
        this.senha = senha;
    }

    @Override
    public String toString(){
        return nome;

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(pessoaId);
        parcel.writeString(nome);
        parcel.writeString(sobrenome);
        parcel.writeString(email);
        parcel.writeString(dataNascimento);
        parcel.writeString(sexo);
        parcel.writeString(senha);
    }
}
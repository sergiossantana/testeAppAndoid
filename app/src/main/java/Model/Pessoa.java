package Model;



public class Pessoa {

    private long pessoaId;
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    private String sexo;
    private String dataNascimento;


    public Pessoa(long pessoaId, String nome, String sobrenome, String sexo, String dataNascimento, String email, String senha){
        this.pessoaId = pessoaId;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.senha = senha;

    }

    public Pessoa(){

    }

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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }

    public void setSenha(String email) {
        this.senha = senha;
    }

    public String toString(){
        return "Codigo: "+getPessoaId()+ "\n" +
                "Nome: "+getNome() + " " + getSobrenome() + "\n" +
                "Data de Nascimento: "+getDataNascimento() + "\n" +
                "Email: "+getEmail();


    }
}
package ufscar.projeto.com.br.melletgolf.modelo;

import java.io.Serializable;
import java.util.Date;

public class DadosJogador implements Serializable {

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNomeCompleto() {
        return NomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        NomeCompleto = nomeCompleto;
    }

    public String getCod() {
        return Cod;
    }

    public void setCod(String cod) {
        Cod = cod;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    private Date dataNascimento;
    private String NomeCompleto;
    private String Cod;
    private String foto;
}

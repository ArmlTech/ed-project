package model.dto;

public class Professor {
    String cpf;
    String nome;
    Float qtdPontos;
    Integer idAreaConhecimento;

    public Professor(String cpf, String nome, Float qtdPontos, Integer idAreaConhecimento) {
        this.cpf = cpf;
        this.nome = nome;
        this.qtdPontos = qtdPontos;
        this.idAreaConhecimento = idAreaConhecimento;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public Float getQtdPontos() {
        return qtdPontos;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setQtdPontos(Float qtdPontos) {
        this.qtdPontos = qtdPontos;
    }

    public Integer getAreaID() {
        return idAreaConhecimento;
    }

    public void setAreaID(Integer idAreaConhecimento) {
        this.idAreaConhecimento = idAreaConhecimento;
    }

	@Override
	public String toString() {
		return "Professor [cpf=" + cpf + ", nome=" + nome + ", qtdPontos=" + qtdPontos + ", areaID=" + idAreaConhecimento + "]";
	}
}

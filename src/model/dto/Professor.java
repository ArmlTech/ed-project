package model.dto;

public class Professor {
    String cpf;
    String nome;
    Float qtdPontos;
    int areaID;

    public Professor(String cpf, String nome, Float qtdPontos, int areaID) {
        this.cpf = cpf;
        this.nome = nome;
        this.qtdPontos = qtdPontos;
        this.areaID = areaID;
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

    public int getAreaID() {
        return areaID;
    }

    public void setAreaID(int areaID) {
        this.areaID = areaID;
    }

    public String toCSV() {
        return cpf + ';' + nome + ';' + qtdPontos + ";" + areaID + "\n";
    }

	@Override
	public String toString() {
		return "Professor [cpf=" + cpf + ", nome=" + nome + ", qtdPontos=" + qtdPontos + ", areaID=" + areaID + "]";
	}
}

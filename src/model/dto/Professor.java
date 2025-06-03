package model.dto;

public class Professor implements IGenericEntity {
    private Integer id;
    private String cpf;
    private String nome;
    private Float qtdPontos;
    private Integer idAreaConhecimento;

    public Professor(Integer id, String cpf, String nome, Float qtdPontos, Integer idAreaConhecimento) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.qtdPontos = qtdPontos;
        this.idAreaConhecimento = idAreaConhecimento;
    }

    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
        return nome;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Professor)) return false;
        Professor other = (Professor) obj;
        return this.id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

	
}

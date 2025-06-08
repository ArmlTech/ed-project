package model.dto;

public class InscritoDisplay implements IGenericEntity {
    private Integer id;
    private String cpf;
    private String nome;
    private Float qtdPontos;
    private String area;
    private String disciplina;
    private Integer idProcesso;

    public InscritoDisplay(Integer id, String cpf, String nome, Float qtdPontos, String area, String disciplina, Integer idProcesso) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.qtdPontos = qtdPontos;
        this.area = area;
        this.disciplina = disciplina;
        this.idProcesso = idProcesso;
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

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getQtdPontos() {
        return qtdPontos;
    }

    public void setQtdPontos(Float qtdPontos) {
        this.qtdPontos = qtdPontos;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public Integer getIdProcesso() {
        return idProcesso;
    }

    public void setIdProcesso(Integer idProcesso) {
        this.idProcesso = idProcesso;
    }
	
}

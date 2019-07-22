package br.com.fiap.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_escola")
public class Escola implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idEscola;
	private String nomeEscola;
	private String enderecoEscola;
	private Set<Curso> cursos = new HashSet<>();

	public Escola() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_escola")
	public int getIdEscola() {
		return idEscola;
	}

	public void setIdEscola(int idEscola) {
		this.idEscola = idEscola;
	}

	@Column(name = "nome_escola", nullable = false, length = 255)
	public String getNomeEscola() {
		return nomeEscola;
	}

	public void setNomeEscola(String nomeEscola) {
		this.nomeEscola = nomeEscola;
	}

	@Column(name = "endereco_escola", nullable = false, length = 255)
	public String getEnderecoEscola() {
		return enderecoEscola;
	}

	public void setEnderecoEscola(String enderecoEscola) {
		this.enderecoEscola = enderecoEscola;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "escola")
	public Set<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(Set<Curso> cursos) {
		this.cursos = cursos;
	}

}

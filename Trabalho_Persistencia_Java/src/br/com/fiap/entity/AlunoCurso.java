package br.com.fiap.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_aluno_curso")
public class AlunoCurso implements Serializable {

	private static final long serialVersionUID = 1L;

	private AlunoCursoPK alunoCursoPk;
	private int nota;
	private Aluno aluno;
	private Curso curso;

	public AlunoCurso() {
		super();
	}

	@EmbeddedId
	public AlunoCursoPK getAlunoCursoPk() {
		return alunoCursoPk;
	}

	public void setAlunoCursoPk(AlunoCursoPK alunoCursoPk) {
		this.alunoCursoPk = alunoCursoPk;
	}

	@Column(name = "nota")
	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_id_aluno", insertable = false, updatable = false)
	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_id_curso", insertable = false, updatable = false)
	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

}

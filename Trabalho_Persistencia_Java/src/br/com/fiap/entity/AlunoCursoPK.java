package br.com.fiap.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AlunoCursoPK implements Serializable {

	private static final long serialVersionUID = 1L;

	private int fkIdAluno;
	private int fkIdCurso;

	public AlunoCursoPK() {
		super();
	}

	@Column(name = "fk_id_aluno")
	public int getFkIdAluno() {
		return fkIdAluno;
	}

	public void setFkIdAluno(int fkIdAluno) {
		this.fkIdAluno = fkIdAluno;
	}

	@Column(name = "fk_id_curso")
	public int getFkIdCurso() {
		return fkIdCurso;
	}

	public void setFkIdCurso(int fkIdCurso) {
		this.fkIdCurso = fkIdCurso;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof AlunoCursoPK) {
			AlunoCursoPK pk = (AlunoCursoPK) o;
			if (this.getFkIdAluno() != pk.getFkIdAluno()) {
				return false;
			}
			if (this.getFkIdCurso() != pk.getFkIdCurso()) {
				return false;
			}
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return this.getFkIdCurso() + this.getFkIdAluno();
	}

}

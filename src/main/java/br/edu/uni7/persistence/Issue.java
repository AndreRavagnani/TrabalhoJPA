package br.edu.uni7.persistence;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_ISSUES")
@DiscriminatorValue("IS")
@PrimaryKeyJoinColumn(name = "FK_ITEM_AVAL")
public class Issue extends ItemAvaliacao {

	@Column(name = "NU_QTDE_VOTOS")
	private Integer quantidadeDeVotos;

	public Integer getQuantidadeDeVotos() {
		return quantidadeDeVotos;
	}

	@PrePersist
	public void verificaVotos() throws Exception {

		if (this.quantidadeDeVotos != 0)
			throw new Exception("Item deverá está aberto");

	}

	public void setQuantidadeDeVotos(Integer quantidadeDeVotos) {
		this.quantidadeDeVotos = quantidadeDeVotos;
	}
}

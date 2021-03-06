package br.edu.uni7.persistence;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TBL_AVALIACOES")
public class Avaliacao {

	@Id
	@Column(name = "PK_AVAL")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "DT_DATA_AVALIACAO")
	private Date data;

	@NotNull
	@OneToOne
	@JoinColumn(name = "FK_PRO")
	private Produto produto;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_USU")
	private Usuario autor;

	@Size(min = 1)
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_ITEM_AVAL")
	private List<ItemAvaliacao> itensAvaliacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	public List<ItemAvaliacao> getItensAvaliacao() {
		return itensAvaliacao;
	}

	public void setItensAvaliacao(List<ItemAvaliacao> itensAvaliacao) {
		this.itensAvaliacao = itensAvaliacao;
	}

	@PrePersist
	@PreUpdate
	public void verificaData() throws Exception {
		if (this.data == null) {
			throw new Exception("Data Nao Preenchida");
		}
	}

	@PrePersist
	public void verificaProduto() throws Exception {
		for (ItemAvaliacao itemAvaliacao : itensAvaliacao) {
			if (itemAvaliacao.getStatus() != Status.ABERTO)
				throw new Exception("Item dever� est� aberto");
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Avaliacao other = (Avaliacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

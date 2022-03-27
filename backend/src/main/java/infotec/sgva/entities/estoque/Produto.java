package infotec.sgva.entities.estoque;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import infotec.sgva.enums.CategoriaProduto;
import infotec.sgva.enums.ProdutoStatus;

@Entity
@Table(name = "tb_produtos", schema = "estoque")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String codigo;
	private String descricao;
	private Integer qnt;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'z'", timezone = "GMT")
	private Date dataEntrada;
	
	@Enumerated(value = EnumType.STRING)
	private CategoriaProduto categoria;
	
	@Enumerated(value = EnumType.STRING)
	private ProdutoStatus status;
	
	@ManyToMany
	@JoinTable(name = "tb_produtos_fornecedores", schema = "estoque" , joinColumns = @JoinColumn(name = "produtos_id"),
										 inverseJoinColumns = @JoinColumn(name = "fornecedores_id"))								
	Set<Fornecedores> fornecedores = new HashSet<>();
	
	public Produto() {
		
	}
	
	
	public Produto(Long id, String codigo, String descricao, Integer qnt, Date dataEntrada, CategoriaProduto categoria,
			ProdutoStatus status, Set<Fornecedores> fornecedores) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.descricao = descricao;
		this.qnt = qnt;
		this.dataEntrada = dataEntrada;
		this.categoria = categoria;
		this.status = status;
		this.fornecedores = fornecedores;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public Integer getQnt() {
		return qnt;
	}


	public void setQnt(Integer qnt) {
		this.qnt = qnt;
	}


	public Date getDataEntrada() {
		return dataEntrada;
	}


	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}


	public CategoriaProduto getCategoria() {
		return categoria;
	}


	public void setCategoria(CategoriaProduto categoria) {
		this.categoria = categoria;
	}


	public ProdutoStatus getStatus() {
		return status;
	}


	public void setStatus(ProdutoStatus status) {
		this.status = status;
	}


	public Set<Fornecedores> getFornecedores() {
		return fornecedores;
	}
	
	
	

}

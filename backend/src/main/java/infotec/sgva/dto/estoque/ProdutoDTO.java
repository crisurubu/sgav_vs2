package infotec.sgva.dto.estoque;

import java.text.SimpleDateFormat;

import infotec.sgva.entities.estoque.Produto;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProdutoDTO {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	private Long id;
	private String codigo;
	private String descricao;
	private Integer qnt;
	private String dataEntrada;
	private String categoria;
	private String status;
	private FornecedoresDTO fornecedor;
	
	
	//private List<FornecedoresDTO> fornecedores = new ArrayList<>();

	public ProdutoDTO(Long id, String codigo, String descricao, Integer qnt, String dataEntrada, String categoria,
		String status, FornecedoresDTO fornecedor) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.descricao = descricao;
		this.qnt = qnt;
		this.dataEntrada = dataEntrada;
		this.categoria = categoria;
		this.status = status;
		this.fornecedor = fornecedor;
		
	}
	
	public ProdutoDTO(Produto produto) {
		id = produto.getId();		
		codigo = produto.getCodigo();
		descricao = produto.getDescricao();
		qnt = produto.getQnt();
		dataEntrada = sdf.format(produto.getDataEntrada());
		categoria = produto.getCategoria().name();
		status = produto.getStatus().name();
		
		if(produto.getFornecedor() != null) {
			fornecedor = FornecedoresDTO.convert(produto.getFornecedor());		
		}
		
		
		//fornecedores = produto.getFornecedores().stream().map(x -> new FornecedoresDTO(x)).collect(Collectors.toList());
		
	}
	
	public static ProdutoDTO convert(Produto produto) {
		ProdutoDTO newProduto = new ProdutoDTO();
		newProduto.setId(produto.getId());	
		newProduto.setCodigo(produto.getCodigo());
		newProduto.setDescricao(produto.getDescricao());
		newProduto.setQnt(produto.getQnt());		
		newProduto.setCategoria(produto.getCategoria().name());
		newProduto.setStatus(produto.getStatus().name());
		
		if(produto.getFornecedor() != null) {
			newProduto.setFornecedor(FornecedoresDTO.convert(produto.getFornecedor()));
			
		}
		return newProduto;
		
		
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

	public String getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public FornecedoresDTO getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(FornecedoresDTO fornecedor) {
		this.fornecedor = fornecedor;
	}

	

	
	
	
	

}

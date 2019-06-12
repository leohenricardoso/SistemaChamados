package br.com.sistemachamados.domain;

public class Vendedor {
	private Long idVendedor;
	private String nomeVendedor;
	
	public Long getIdVendedor() {
		return idVendedor;
	}
	
	public void setIdVendedor(Long idVendedor) {
		this.idVendedor = idVendedor;
	}
	
	public String getNomeVendedor() {
		return nomeVendedor;
	}
	
	public void setNomeVendedor(String nomeVendedor) {
		this.nomeVendedor = nomeVendedor;
	}
	
	@Override
	public String toString() {
		String saida;
		saida = "ID: " + getIdVendedor() + " - " + "Nome: " + getNomeVendedor();
		return saida;
	}
}

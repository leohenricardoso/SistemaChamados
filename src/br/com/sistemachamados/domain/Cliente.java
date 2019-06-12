package br.com.sistemachamados.domain;

public class Cliente {
	private Long idCliente;
	private String nomeCliente;
	private String telefoneCliente;
	private String emailCliente;
	
	public Long getIdCliente() {
		return idCliente;
	}
	
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	
	public String getNomeCliente() {
		return nomeCliente;
	}
	
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	
	public String getTelefoneCliente() {
		return telefoneCliente;
	}
	
	public void setTelefoneCliente(String telefoneCliente) {
		this.telefoneCliente = telefoneCliente;
	}
	
	public String getEmailCliente() {
		return emailCliente;
	}
	
	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}
	
	@Override
	public String toString() {		
		String saida = idCliente + " - " + nomeCliente + " - " + telefoneCliente + " - " + emailCliente;
		return saida;
	}
	
}

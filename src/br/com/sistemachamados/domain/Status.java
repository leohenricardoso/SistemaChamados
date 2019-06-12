package br.com.sistemachamados.domain;

public class Status {
	private Long idStatus;
	private String descricaoStatus;
	
	public Long getIdStatus() {
		return idStatus;
	}
	
	public void setIdStatus(Long idStatus) {
		this.idStatus = idStatus;
	}
	
	public String getDescricaoStatus() {
		return descricaoStatus;
	}
	
	public void setDescricaoStatus(String descricaoStatus) {
		this.descricaoStatus = descricaoStatus;
	}
	
	@Override
	public String toString() {
		String saida;
		saida = "ID: " + getIdStatus() + " - " + getDescricaoStatus();
		return saida;
	}
	
}

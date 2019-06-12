package br.com.sistemachamados.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

//import java.util.Date;

public class Chamado {
	private Long idChamado;
	private Date dataAtual;
	private Date dataRetorno;
	private String descricaoChamado;
	private String observacaoChamado;
	private Cliente cliente = new Cliente();
	private Vendedor vendedor = new Vendedor();
	private Status status = new Status();

	public Long getIdChamado() {
		return idChamado;
	}

	public void setIdChamado(Long idChamado) {
		this.idChamado = idChamado;
	}

	public String getDescricaoChamado() {
		return descricaoChamado;
	}

	public void setDescricaoChamado(String descricaoChamado) {
		this.descricaoChamado = descricaoChamado;
	}

	public String getObservacaoChamado() {
		return observacaoChamado;
	}

	public void setObservacaoChamado(String observacaoChamado) {
		this.observacaoChamado = observacaoChamado;
	}

	public Date getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(Date string) {
		this.dataAtual = string;
	}

	public Date getDataRetorno() {
		return dataRetorno;
	}

	public void setDataRetorno(Date dataRetorno) {
		this.dataRetorno = dataRetorno;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getDataRetornoFormatada() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
		return sdf.format(this.getDataRetorno());
	}

	/*
	 * @Override public String toString() { //ConversaoData convert = new
	 * ConversaoData(); String saida; saida = idChamado + " - " +
	 * cliente.getNomeCliente() + " - " + descricaoChamado + " - " +
	 * observacaoChamado + " - " + dataRetorno + " - " + status.getDescricaoStatus()
	 * + " - " + vendedor.getNomeVendedor() + " - "; return saida; }
	 */
	
}

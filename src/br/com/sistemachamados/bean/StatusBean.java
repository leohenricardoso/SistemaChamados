package br.com.sistemachamados.bean;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sistemachamados.dao.StatusDAO;
import br.com.sistemachamados.domain.Status;
import br.com.sistemachamados.util.JSFUtil;

@ManagedBean(name = "StatusBean")
@ViewScoped
public class StatusBean {

	private Status status;
	private ArrayList<Status> listaStatus;
	private ArrayList<Status> statusFiltrados;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public ArrayList<Status> getListaStatus() {
		return listaStatus;
	}

	public void setListaStatus(ArrayList<Status> listaStatus) {
		this.listaStatus = listaStatus;
	}

	public ArrayList<Status> getStatusFiltrados() {
		return statusFiltrados;
	}

	public void setStatusFiltrados(ArrayList<Status> statusFiltrados) {
		this.statusFiltrados = statusFiltrados;
	}

	@PostConstruct
	public void carregarListagem() {
		try {
			StatusDAO dao = new StatusDAO();
			listaStatus = dao.listarStatus();
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao carregar lista de status");

		}

	}

	public void prepararNovo() {
		try {
			status = new Status();
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void novo() {
		try {
			StatusDAO dao = new StatusDAO();
			dao.salvarNovoStatus(status);
			listaStatus = dao.listarStatus();
			JSFUtil.adicionarMensagemSucesso("Status adicionado com sucesso!!!");
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao adicionar status");
		}
	}

	public void excluir() {
		try {
			StatusDAO dao = new StatusDAO();
			dao.excluirStatus(status);
			listaStatus = dao.listarStatus();
			JSFUtil.adicionarMensagemSucesso("Status excluido com sucesso!!!");
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("Erro ao excluir status");
			e.printStackTrace();
		}
	}

	public void editar() {
		try {
			StatusDAO dao = new StatusDAO();
			dao.alterarStatus(status);
			listaStatus = dao.listarStatus();
			JSFUtil.adicionarMensagemSucesso("Status editado com sucesso!!!");
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("Erro ao editar status");
			e.printStackTrace();
		}
	}

}

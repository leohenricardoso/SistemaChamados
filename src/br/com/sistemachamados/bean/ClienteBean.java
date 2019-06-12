package br.com.sistemachamados.bean;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sistemachamados.dao.ClienteDAO;
import br.com.sistemachamados.domain.Cliente;
import br.com.sistemachamados.util.JSFUtil;

@ManagedBean(name = "ClienteBean")
@ViewScoped
public class ClienteBean {
	private Cliente cliente;
	private ArrayList<Cliente> clientes;
	private ArrayList<Cliente> clientesFiltrados;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}

	public ArrayList<Cliente> getClientesFiltrados() {
		return clientesFiltrados;
	}

	public void setClientesFiltrados(ArrayList<Cliente> clientesFiltrados) {
		this.clientesFiltrados = clientesFiltrados;
	}

	@PostConstruct
	public void carregarListagem() {
		try {
			ClienteDAO dao = new ClienteDAO();
			clientes = dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao carregar lista de clientes");
		}

	}

	public void prepararNovo() {
		try {
			cliente = new Cliente();
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void novo() {
		try {
			ClienteDAO dao = new ClienteDAO();
			dao.salvarCliente(cliente);
			clientes = dao.listar();
			JSFUtil.adicionarMensagemSucesso("Cliente adicionado com sucesso!!!");
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao adicionar cliente");
		}
	}

	public void excluir() {
		try {
			ClienteDAO dao = new ClienteDAO();
			dao.excluirCLiente(cliente);
			clientes = dao.listar();
			JSFUtil.adicionarMensagemSucesso("Cliente excluido com sucesso!!!");
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao excluir cliente");
		}
	}

	public void editar() {
		try {
			ClienteDAO dao = new ClienteDAO();
			dao.editarCliente(cliente);
			clientes = dao.listar();
			JSFUtil.adicionarMensagemSucesso("Cliente editado com sucesso!!!");
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao editar cliente");
		}
	}

}

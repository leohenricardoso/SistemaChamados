package br.com.sistemachamados.bean;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sistemachamados.dao.ChamadoDAO;
import br.com.sistemachamados.dao.ClienteDAO;
import br.com.sistemachamados.dao.StatusDAO;
import br.com.sistemachamados.dao.VendedorDAO;
import br.com.sistemachamados.domain.Chamado;
import br.com.sistemachamados.domain.Cliente;
import br.com.sistemachamados.domain.Status;
import br.com.sistemachamados.domain.Vendedor;
import br.com.sistemachamados.util.JSFUtil;

@ManagedBean(name = "ChamadoBean")
@ViewScoped
public class ChamadoBean {
	private Chamado chamado;
	private ArrayList<Chamado> chamados;
	private ArrayList<Chamado> chamadosFiltrados;
	private ArrayList<Vendedor> comboVendedores;
	private ArrayList<Cliente> comoCliente;
	private ArrayList<Status> comboStatus;	
	
	public ArrayList<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(ArrayList<Chamado> chamados) {
		this.chamados = chamados;
	}

	public ArrayList<Chamado> getChamadosFiltrados() {
		return chamadosFiltrados;
	}

	public void setChamadosFiltrados(ArrayList<Chamado> chamadosFiltrados) {
		this.chamadosFiltrados = chamadosFiltrados;
	}

	public Chamado getChamado() {
		return chamado;
	}

	public void setChamado(Chamado chamado) {
		this.chamado = chamado;
	}

	public ArrayList<Vendedor> getComboVendedores() {
		return comboVendedores;
	}

	public void setComboVendedores(ArrayList<Vendedor> comboVendedores) {
		this.comboVendedores = comboVendedores;
	}

	public ArrayList<Cliente> getComoCliente() {
		return comoCliente;
	}

	public void setComoCliente(ArrayList<Cliente> comoCliente) {
		this.comoCliente = comoCliente;
	}

	public ArrayList<Status> getComboStatus() {
		return comboStatus;
	}

	public void setComboStatus(ArrayList<Status> comboStatus) {
		this.comboStatus = comboStatus;
	}

	public void carregarListagem() {
		try {
			ChamadoDAO dao = new ChamadoDAO();
			chamados = dao.listarChamado();
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao carregar lista de chamados");
		}
	}

	public void prepararNovo() {
		try {
			chamado = new Chamado();
			ClienteDAO clienteDao = new ClienteDAO();
			comoCliente = clienteDao.listar();

			VendedorDAO vendedorDao = new VendedorDAO();
			comboVendedores = vendedorDao.listarVendedor();

			StatusDAO statusDao = new StatusDAO();
			comboStatus = statusDao.listarStatus();

		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void novo() {
		try {
			ChamadoDAO dao = new ChamadoDAO();
			dao.salvarChamado(chamado);
			chamados = dao.listarChamado();
			JSFUtil.adicionarMensagemSucesso("Chamado adicionado com sucesso!!!");
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao adicionar chamado");
		}
	}

	public void excluir() {
		try {
			ChamadoDAO dao = new ChamadoDAO();
			dao.excluirChamado(chamado);
			chamados = dao.listarChamado();
			JSFUtil.adicionarMensagemSucesso("Chamado excluido com sucesso!!!");
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("Erro ao excluir chamado");
			e.printStackTrace();
		}
	}

	public void prepararEditar() {
		try {
			ClienteDAO clienteDao = new ClienteDAO();
			comoCliente = clienteDao.listar();

			VendedorDAO vendedorDao = new VendedorDAO();
			comboVendedores = vendedorDao.listarVendedor();

			StatusDAO statusDao = new StatusDAO();
			comboStatus = statusDao.listarStatus();
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void editar() {
		try {
			ChamadoDAO dao = new ChamadoDAO();
			dao.editarChamado(chamado);
			chamados = dao.listarChamado();
			JSFUtil.adicionarMensagemSucesso("Chamado editado com sucesso!!!");
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("Erro ao editar chamado");
			e.printStackTrace();
		}
	}
}

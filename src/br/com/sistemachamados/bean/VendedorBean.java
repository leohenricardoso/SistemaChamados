package br.com.sistemachamados.bean;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sistemachamados.dao.VendedorDAO;
import br.com.sistemachamados.domain.Vendedor;
import br.com.sistemachamados.util.JSFUtil;

@ManagedBean(name = "VendedorBean")
@ViewScoped
public class VendedorBean {
	private Vendedor vendedor;
	private ArrayList<Vendedor> vendedores;
	private ArrayList<Vendedor> vendedoresFiltrados;	

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public ArrayList<Vendedor> getVendedores() {
		return vendedores;
	}

	public void setVendedores(ArrayList<Vendedor> vendedores) {
		this.vendedores = vendedores;
	}

	public ArrayList<Vendedor> getVendedoresFiltrados() {
		return vendedoresFiltrados;
	}

	public void setVendedoresFiltrados(ArrayList<Vendedor> vendedoresFiltrados) {
		this.vendedoresFiltrados = vendedoresFiltrados;
	}

	@PostConstruct
	public void carregarListagem() {
		try {
			VendedorDAO dao = new VendedorDAO();
			vendedores = dao.listarVendedor();
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	public void prepararNovo() {
		try {
			vendedor = new Vendedor();
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void novo() {
		try {
			VendedorDAO dao = new VendedorDAO();
			dao.salvarVendedor(vendedor);
			vendedores = dao.listarVendedor();
			JSFUtil.adicionarMensagemSucesso("Vendedor adicionado com sucesso!!!");
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao adicionar vendedor");
		}
	}

	public void excluir() {
		try {
			VendedorDAO dao = new VendedorDAO();
			dao.excluirVendedor(vendedor);
			vendedores = dao.listarVendedor();
			JSFUtil.adicionarMensagemSucesso("Vendedor excluido com sucesso!!!");
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("Erro ao excluir vendedor");
			e.printStackTrace();
		}
	}

	public void editar() {
		try {
			VendedorDAO dao = new VendedorDAO();
			dao.editarVendedor(vendedor);
			vendedores = dao.listarVendedor();
			JSFUtil.adicionarMensagemSucesso("Vendedor editado com sucesso!!!");
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("Erro ao editar vendedor");
			e.printStackTrace();
		}
	}
}

package br.com.sistemachamados.test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import br.com.sistemachamados.dao.VendedorDAO;
import br.com.sistemachamados.domain.Vendedor;

public class VendedorDAOTest {

	
	public void salvarVendedor() throws SQLException {
		Vendedor v = new Vendedor();

		v.setIdVendedor(2L);
		v.setNomeVendedor("Gabriel Faria");

		VendedorDAO dao = new VendedorDAO();

		try {
			dao.salvarVendedor(v);
			System.out.println("Deu certo!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public void excluirVendedor() throws SQLException {
		Vendedor v = new Vendedor();
		v.setIdVendedor(2L);

		VendedorDAO dao = new VendedorDAO();
		try {
			dao.excluirVendedor(v);
			System.out.println("Excluido");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void listarVendedor() throws SQLException {

		VendedorDAO dao = new VendedorDAO();

		ArrayList<Vendedor> lista = dao.listarVendedor();

		for (Vendedor vendedor : lista) {
			System.out.println(vendedor);
		}

	}

	
	public void buscarPorID() throws SQLException {
		Vendedor v = new Vendedor();
		v.setIdVendedor(1L);

		VendedorDAO dao = new VendedorDAO();
		try {
			System.out.println("Resultado " + dao.buscarPorID(v));
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	@Test
	public void buscarPorNome() throws SQLException {
		Vendedor v = new Vendedor();
		v.setNomeVendedor("din");

		VendedorDAO dao = new VendedorDAO();

		try {
			ArrayList<Vendedor> lista = dao.buscarPorNome(v);
			for (Vendedor vendedor : lista) {
				System.out.println("Resultado: " + vendedor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}

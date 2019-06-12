package br.com.sistemachamados.test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import br.com.sistemachamados.dao.StatusDAO;
import br.com.sistemachamados.domain.Status;

public class StatusDAOTest {

	public void salvar() {
		try {
			Status s = new Status();
			s.setIdStatus(3L);
			s.setDescricaoStatus("Teste");
			StatusDAO dao = new StatusDAO();
			dao.salvarNovoStatus(s);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void excluir() {
		try {
			Status s = new Status();
			s.setIdStatus(3L);
			StatusDAO dao = new StatusDAO();
			dao.excluirStatus(s);
			;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void editar() {
		try {
			Status s = new Status();
			s.setIdStatus(1L);
			s.setDescricaoStatus("Em andamento");

			StatusDAO dao = new StatusDAO();
			dao.alterarStatus(s);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public void listar() {
		StatusDAO dao = new StatusDAO();
		try {
			ArrayList<Status> lista = dao.listarStatus();
			for (Status s : lista) {
				System.out.println(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void buscarID() {
		try {
			Status s = new Status();
			s.setIdStatus(1L);
			
			StatusDAO dao = new StatusDAO();
			
			ArrayList<Status> lista = dao.buscarPorIDStatus(s);
			for(Status status : lista) {
				System.out.println(status);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void buscarDescricao() {
		try {
			Status s = new Status();
			s.setDescricaoStatus("and");
			
			StatusDAO dao = new StatusDAO();
			
			ArrayList<Status> lista = dao.buscarPorDescricaoStatus(s);
			for(Status status : lista) {
				System.out.println(status);
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package br.com.sistemachamados.test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import br.com.sistemachamados.dao.ChamadoDAO;
import br.com.sistemachamados.domain.Chamado;
import br.com.sistemachamados.domain.Cliente;
import br.com.sistemachamados.domain.Status;
import br.com.sistemachamados.domain.Vendedor;

public class ChamadoDAOTest {
	
	
	public void salvar() throws SQLException{
		Chamado c = new Chamado();
		Cliente c1 = new Cliente();
		Vendedor v = new Vendedor();
		Status s = new Status();
		
		c.setDescricaoChamado("Teste Supremo");
		c.setObservacaoChamado("Vou plalalaessor");			
		//c.setDataRetorno();		
		//c.setHoraRetorno();
		c1.setIdCliente(1L);
		v.setIdVendedor(1L);
		s.setIdStatus(1L);
		c.setCliente(c1);
		c.setVendedor(v);
		c.setStatus(s);
		
		ChamadoDAO dao = new ChamadoDAO();
		dao.salvarChamado(c);
	}
	
	
	public void excluir() throws SQLException{
		Chamado c = new Chamado();
		c.setIdChamado(8L);
		
		ChamadoDAO dao = new ChamadoDAO();
		dao.excluirChamado(c);
	}
	
	
	public void editar() throws SQLException{
		Chamado c = new Chamado();
		c.setIdChamado(7L);
		c.setDescricaoChamado("Teste editar");
		c.setObservacaoChamado("Teste observação");
		//c.setDataRetorno("2019-06-17");
		//c.setHoraRetorno("22:00:00");
		Status s = new Status();
		s.setIdStatus(1L);
		c.setStatus(s);
		
		Vendedor v = new Vendedor();
		v.setIdVendedor(1L);
		c.setVendedor(v);
		
		ChamadoDAO dao = new ChamadoDAO();
		dao.editarChamado(c);
	}
	
	
	public void listar() throws SQLException{
		ChamadoDAO dao = new ChamadoDAO();
		try {
			ArrayList<Chamado> lista = dao.listarChamado();
			
			for(Chamado c : lista) {
				System.out.println(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}		
	
	public void buscarID() {
		Chamado c = new Chamado();		
		c.setIdChamado(4L);	
		
		ChamadoDAO dao = new ChamadoDAO();
		
		try {		
			ArrayList<Chamado> lista = dao.buscarPorIDChamado(c);			
			for(Chamado chamado : lista) {				
				System.out.println(chamado);
			}
		} catch (Exception e) {
			System.out.println("Erro ao buscar " + e);
		}
	}
	
	
	public void buscarNomeCliente() {
		Chamado c = new Chamado();
		Cliente cl = new Cliente();
		cl.setNomeCliente("L");
		c.setCliente(cl);
		
		ChamadoDAO dao = new ChamadoDAO();
		
		try {			
			ArrayList<Chamado> lista = dao.buscarPorNomeClienteChamado(c);			
			for(Chamado chamado : lista) {				
				System.out.println(chamado);
			}
		} catch (Exception e) {
			System.out.println("Erro ao buscar " + e);
		}
	}
	
	
	public void buscarNomeVendedor() {
		Chamado c = new Chamado();
		Vendedor v = new Vendedor();
		v.setNomeVendedor("P");
		
		c.setVendedor(v);
		
		ChamadoDAO dao = new ChamadoDAO();
		
		try {			
			ArrayList<Chamado> lista = dao.buscarPorNomeVendedorChamado(c);			
			for(Chamado chamado : lista) {				
				System.out.println(chamado);
			}
		} catch (Exception e) {
			System.out.println("Erro ao buscar " + e);
		}
	}
	
	
	public void buscarIDVendedor() {
		Chamado c = new Chamado();	
		Vendedor v = new Vendedor();
		v.setIdVendedor(1L);
		c.setVendedor(v);;	
		
		ChamadoDAO dao = new ChamadoDAO();
		
		try {		
			ArrayList<Chamado> lista = dao.buscarPorIDVendedorChamado(c);			
			for(Chamado chamado : lista) {				
				System.out.println(chamado);
			}
		} catch (Exception e) {
			System.out.println("Erro ao buscar " + e);
		}
	}
	
	@Test
	public void buscarIDCliente() {
		Chamado c = new Chamado();	
		Cliente cl = new Cliente();
		cl.setIdCliente(1L);
		c.setCliente(cl);;	
		
		ChamadoDAO dao = new ChamadoDAO();
		
		try {		
			ArrayList<Chamado> lista = dao.buscarPorIDClienteChamado(c);			
			for(Chamado chamado : lista) {				
				System.out.println(chamado);
			}
		} catch (Exception e) {
			System.out.println("Erro ao buscar " + e);
		}
	}
	
	
	/*public void buscarDataChamado() {
		ConversaoData conv = new ConversaoData();		
		Chamado c = new Chamado();		
		//c.setDataAtual(conv.converteParaUSA("15/05/2019")); 		//converteParaUSA => Criado para passar para o formato do banco antes de fazer a pesquisa no banco
		
		ChamadoDAO dao = new ChamadoDAO();		
		try {				
			ArrayList<Chamado> lista = dao.buscarPorDataChamado(c);				
			for(Chamado chamado : lista) {				
				System.out.println(chamado);				
			}
			System.out.println("terminei o try");
		} catch (Exception e) {
			System.out.println("Erro ao buscar " + e);
		}
	}*/
}

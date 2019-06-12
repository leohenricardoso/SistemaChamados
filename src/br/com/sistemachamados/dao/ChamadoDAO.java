package br.com.sistemachamados.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.sistemachamados.domain.Chamado;
import br.com.sistemachamados.domain.Cliente;
import br.com.sistemachamados.domain.Status;
import br.com.sistemachamados.domain.Vendedor;
import br.com.sistemachamados.factory.ConexaoFactory;

public class ChamadoDAO {		
	public void salvarChamado(Chamado c) throws SQLException{
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into chamado ");
		sql.append("(descricao_chamado, observacao, id_status, id_cliente, id_vendedor, data_chamado, data_retorno) ");
		sql.append("values (?, ?, ?, ?, ?, ?, ?) ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1, c.getDescricaoChamado());
		comando.setString(2, c.getObservacaoChamado());
		comando.setLong(3, c.getStatus().getIdStatus());
		comando.setLong(4, c.getCliente().getIdCliente());
		comando.setLong(5, c.getVendedor().getIdVendedor());		
		comando.setTimestamp(6, new java.sql.Timestamp(new java.util.Date().getTime()));
		comando.setTimestamp(7, new java.sql.Timestamp(c.getDataRetorno().getTime()));
		
		
		comando.executeUpdate();
	}
	
	public void excluirChamado(Chamado c) throws SQLException{
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from chamado ");
		sql.append("where id_chamado = ? ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setLong(1, c.getIdChamado());
		
		comando.executeUpdate();
		
	}
	
	public void editarChamado(Chamado c) throws SQLException{
		StringBuilder sql = new StringBuilder();
		
		sql.append("update chamado ");
		sql.append("set descricao_chamado = ?, observacao = ?, id_status = ?, id_vendedor = ?, ");
		sql.append("data_retorno = ? ");
		sql.append("where id_chamado = ? ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1, c.getDescricaoChamado());
		comando.setString(2, c.getObservacaoChamado());
		comando.setLong(3, c.getStatus().getIdStatus());		
		comando.setLong(4, c.getVendedor().getIdVendedor());	
		comando.setTimestamp(5, new java.sql.Timestamp(c.getDataRetorno().getTime()));			
		comando.setLong(6, c.getIdChamado());
		
		comando.executeUpdate();
	}
	
	public ArrayList<Chamado> listarChamado() throws SQLException{
		StringBuilder sql = new StringBuilder();		
		
		sql.append("SELECT c.id_chamado, cl.nome_cliente, c.descricao_chamado, ");
		sql.append("c.data_retorno, c.data_chamado, c.data_retorno, ");
		sql.append("c.observacao, s.descricao_status, v.nome_vendedor ");
		sql.append("FROM chamado c ");	
		sql.append("inner join cliente cl on cl.id_cliente = c.id_cliente ");
		sql.append("inner join vendedor v on v.id_vendedor = c.id_vendedor ");
		sql.append("inner join status s on s.id_status = c.id_status ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Chamado> chamados = new ArrayList<Chamado>();
		
		while(resultado.next()) {
			Chamado c = new Chamado();
			Vendedor v = new Vendedor();
			Cliente cl = new Cliente();
			Status s = new Status();
			
			c.setIdChamado(resultado.getLong("c.id_chamado"));
			cl.setNomeCliente(resultado.getString("cl.nome_cliente"));
			c.setDescricaoChamado(resultado.getString("c.descricao_chamado"));
			c.setDataRetorno(resultado.getTimestamp("c.data_retorno"));			
			c.setDataAtual(resultado.getTimestamp("c.data_chamado"));				
			c.setObservacaoChamado(resultado.getString("c.observacao"));
			s.setDescricaoStatus(resultado.getString("s.descricao_status"));
			v.setNomeVendedor(resultado.getString("v.nome_vendedor"));
			
			c.setCliente(cl);
			c.setVendedor(v);
			c.setStatus(s);
			
			chamados.add(c);
		}
		return chamados;
	}
	
	public ArrayList<Chamado> buscarPorIDChamado(Chamado c) throws SQLException{
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT c.id_chamado, cl.nome_cliente, c.descricao_chamado, ");
		sql.append("c.data_retorno, c.data_chamado, c.data_retorno, ");
		sql.append("c.observacao, s.descricao_status, v.nome_vendedor ");
		sql.append("FROM chamado c, cliente cl, vendedor v, status s ");	
		sql.append("where c.id_chamado = ? ");
		sql.append("order by cl.nome_cliente ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setLong(1, c.getIdChamado());
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Chamado> lista = new ArrayList<Chamado>();
		
		while (resultado.next()) {
			Chamado ch = new Chamado();
			Vendedor v = new Vendedor();
			Cliente cl = new Cliente();
			Status s = new Status();
			
			ch.setIdChamado(resultado.getLong("c.id_chamado"));
			cl.setNomeCliente(resultado.getString("cl.nome_cliente"));
			ch.setDescricaoChamado(resultado.getString("c.descricao_chamado"));
			ch.setDataRetorno(resultado.getDate("c.data_retorno"));
			//ch.setHoraRetorno(resultado.getTime("c.hora_retorno"));
			ch.setDataAtual(resultado.getDate("c.data_chamado"));	
			//ch.setHoraAtual(resultado.getTime("c.hora_chamado"));
			ch.setObservacaoChamado(resultado.getString("c.observacao"));
			s.setDescricaoStatus(resultado.getString("s.descricao_status"));
			v.setNomeVendedor(resultado.getString("v.nome_vendedor"));
			
			ch.setCliente(cl);
			ch.setVendedor(v);
			ch.setStatus(s);
			
			lista.add(ch);				
		}
		return lista;
	}
	
	public ArrayList<Chamado> buscarPorNomeClienteChamado(Chamado c) throws SQLException{
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT c.id_chamado, cl.nome_cliente, c.descricao_chamado, ");
		sql.append("c.data_retorno, c.data_chamado, c.data_retorno, ");
		sql.append("c.observacao, s.descricao_status, v.nome_vendedor ");
		sql.append("FROM chamado c, cliente cl, vendedor v, status s ");	
		sql.append("where cl.nome_cliente like ? ");
		sql.append("order by cl.nome_cliente ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1, "%" + c.getCliente().getNomeCliente() + "%");
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Chamado> lista = new ArrayList<Chamado>();
		
		while (resultado.next()) {
			Chamado ch = new Chamado();
			Vendedor v = new Vendedor();
			Cliente cl = new Cliente();
			Status s = new Status();
			
			ch.setIdChamado(resultado.getLong("c.id_chamado"));
			cl.setNomeCliente(resultado.getString("cl.nome_cliente"));
			ch.setDescricaoChamado(resultado.getString("c.descricao_chamado"));
			ch.setDataRetorno(resultado.getDate("c.data_retorno"));
			//ch.setHoraRetorno(resultado.getTime("c.hora_retorno"));
			ch.setDataAtual(resultado.getDate("c.data_chamado"));	
			//ch.setHoraAtual(resultado.getTime("c.hora_chamado"));
			ch.setObservacaoChamado(resultado.getString("c.observacao"));
			s.setDescricaoStatus(resultado.getString("s.descricao_status"));
			v.setNomeVendedor(resultado.getString("v.nome_vendedor"));
			
			ch.setCliente(cl);
			ch.setVendedor(v);
			ch.setStatus(s);
			
			lista.add(ch);				
		}
		return lista;
	}
	
	public ArrayList<Chamado> buscarPorNomeVendedorChamado(Chamado c) throws SQLException{
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT c.id_chamado, cl.nome_cliente, c.descricao_chamado, ");
		sql.append("c.data_retorno, c.data_chamado, c.data_retorno, ");
		sql.append("c.observacao, s.descricao_status, v.nome_vendedor ");
		sql.append("FROM chamado c, cliente cl, vendedor v, status s ");	
		sql.append("where v.nome_vendedor like ? ");
		sql.append("order by cl.nome_cliente ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1, "%" + c.getVendedor().getNomeVendedor() + "%");
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Chamado> lista = new ArrayList<Chamado>();
		
		while (resultado.next()) {
			Chamado ch = new Chamado();
			Vendedor v = new Vendedor();
			Cliente cl = new Cliente();
			Status s = new Status();
			
			ch.setIdChamado(resultado.getLong("c.id_chamado"));
			cl.setNomeCliente(resultado.getString("cl.nome_cliente"));
			ch.setDescricaoChamado(resultado.getString("c.descricao_chamado"));
			ch.setDataRetorno(resultado.getDate("c.data_retorno"));
			//ch.setHoraRetorno(resultado.getTime("c.hora_retorno"));
			ch.setDataAtual(resultado.getDate("c.data_chamado"));	
			//ch.setHoraAtual(resultado.getTime("c.hora_chamado"));
			ch.setObservacaoChamado(resultado.getString("c.observacao"));
			s.setDescricaoStatus(resultado.getString("s.descricao_status"));
			v.setNomeVendedor(resultado.getString("v.nome_vendedor"));
			
			ch.setCliente(cl);
			ch.setVendedor(v);
			ch.setStatus(s);
			
			lista.add(ch);				
		}
		return lista;
	}
	
	public ArrayList<Chamado> buscarPorIDVendedorChamado(Chamado c) throws SQLException{
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT c.id_chamado, cl.nome_cliente, c.descricao_chamado, ");
		sql.append("c.data_retorno, c.data_chamado, c.data_retorno, ");
		sql.append("c.observacao, s.descricao_status, v.nome_vendedor ");
		sql.append("FROM chamado c, cliente cl, vendedor v, status s ");	
		sql.append("where v.id_vendedor = ? ");
		sql.append("order by cl.nome_cliente ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setLong(1, c.getVendedor().getIdVendedor());
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Chamado> lista = new ArrayList<Chamado>();
		
		while (resultado.next()) {
			Chamado ch = new Chamado();
			Vendedor v = new Vendedor();
			Cliente cl = new Cliente();
			Status s = new Status();
			
			ch.setIdChamado(resultado.getLong("c.id_chamado"));
			cl.setNomeCliente(resultado.getString("cl.nome_cliente"));
			ch.setDescricaoChamado(resultado.getString("c.descricao_chamado"));
			ch.setDataRetorno(resultado.getDate("c.data_retorno"));
			//ch.setHoraRetorno(resultado.getTime("c.hora_retorno"));
			ch.setDataAtual(resultado.getDate("c.data_chamado"));	
			//ch.setHoraAtual(resultado.getTime("c.hora_chamado"));
			ch.setObservacaoChamado(resultado.getString("c.observacao"));
			s.setDescricaoStatus(resultado.getString("s.descricao_status"));
			v.setNomeVendedor(resultado.getString("v.nome_vendedor"));
			
			ch.setCliente(cl);
			ch.setVendedor(v);
			ch.setStatus(s);
			
			lista.add(ch);				
		}
		return lista;
	}
	
	public ArrayList<Chamado> buscarPorIDClienteChamado(Chamado c) throws SQLException{
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT c.id_chamado, cl.nome_cliente, c.descricao_chamado, ");
		sql.append("c.data_retorno, c.data_chamado, c.data_retorno, ");
		sql.append("c.observacao, s.descricao_status, v.nome_vendedor ");
		sql.append("FROM chamado c, cliente cl, vendedor v, status s ");	
		sql.append("where cl.id_cliente = ? ");
		sql.append("order by cl.nome_cliente ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setLong(1, c.getCliente().getIdCliente());
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Chamado> lista = new ArrayList<Chamado>();
		
		while (resultado.next()) {
			Chamado ch = new Chamado();
			Vendedor v = new Vendedor();
			Cliente cl = new Cliente();
			Status s = new Status();
			
			ch.setIdChamado(resultado.getLong("c.id_chamado"));
			cl.setNomeCliente(resultado.getString("cl.nome_cliente"));
			ch.setDescricaoChamado(resultado.getString("c.descricao_chamado"));
			ch.setDataRetorno(resultado.getDate("c.data_retorno"));
			//ch.setHoraRetorno(resultado.getTime("c.hora_retorno"));
			ch.setDataAtual(resultado.getDate("c.data_chamado"));	
			//ch.setHoraAtual(resultado.getTime("c.hora_chamado"));
			ch.setObservacaoChamado(resultado.getString("c.observacao"));
			s.setDescricaoStatus(resultado.getString("s.descricao_status"));
			v.setNomeVendedor(resultado.getString("v.nome_vendedor"));
			
			ch.setCliente(cl);
			ch.setVendedor(v);
			ch.setStatus(s);
			
			lista.add(ch);				
		}
		return lista;
	}
	
	/*public ArrayList<Chamado> buscarPorDataChamado(Chamado c) throws SQLException{
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT c.id_chamado, cl.nome_cliente, c.descricao_chamado, ");
		sql.append("c.data_retorno, c.data_chamado ,c.hora_chamado, c.data_retorno, c.hora_retorno, ");
		sql.append("c.observacao, s.descricao_status, v.nome_vendedor ");
		sql.append("FROM chamado c, cliente cl, vendedor v, status s ");	
		sql.append("where c.data_chamado = ? ");
		sql.append("order by cl.nome_cliente ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setDate(1, c.getDataAtual());
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Chamado> lista = new ArrayList<Chamado>();
		
		while (resultado.next()) {
			Chamado ch = new Chamado();
			Vendedor v = new Vendedor();
			Cliente cl = new Cliente();
			Status s = new Status();
			
			ch.setIdChamado(resultado.getLong("c.id_chamado"));
			cl.setNomeCliente(resultado.getString("cl.nome_cliente"));
			ch.setDescricaoChamado(resultado.getString("c.descricao_chamado"));
			ch.setDataRetorno(resultado.getDate("c.data_retorno"));
			ch.setHoraRetorno(resultado.getTime("c.hora_retorno"));
			ch.setDataAtual(resultado.getDate("c.data_chamado"));	
			ch.setHoraAtual(resultado.getTime("c.hora_chamado"));
			ch.setObservacaoChamado(resultado.getString("c.observacao"));
			s.setDescricaoStatus(resultado.getString("s.descricao_status"));
			v.setNomeVendedor(resultado.getString("v.nome_vendedor"));
			
			ch.setCliente(cl);
			ch.setVendedor(v);
			ch.setStatus(s);
			
			lista.add(ch);				
		}
		return lista;
	}*/
}

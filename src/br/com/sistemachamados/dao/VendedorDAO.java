package br.com.sistemachamados.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.sistemachamados.domain.Vendedor;
import br.com.sistemachamados.factory.ConexaoFactory;

public class VendedorDAO {
	public void salvarVendedor(Vendedor v) throws SQLException {
		StringBuilder sql = new StringBuilder();

		sql.append("insert into vendedor ");
		sql.append("(id_vendedor, nome_vendedor) ");
		sql.append("values(?, ?) ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());		

		comando.setLong(1, v.getIdVendedor());
		comando.setString(2, v.getNomeVendedor());

		comando.executeUpdate();

	}

	public void editarVendedor(Vendedor v) throws SQLException {
		StringBuilder sql = new StringBuilder();

		sql.append("update vendedor ");
		sql.append("set id_vendedor = ?, nome_vendedor = ? ");
		sql.append("where (id_vendedor = ?) ");		
		
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setLong(1, v.getIdVendedor());
		comando.setString(2, v.getNomeVendedor());
		comando.setLong(3, v.getIdVendedor());

		comando.executeUpdate();

	}

	public void excluirVendedor(Vendedor v) throws SQLException {
		StringBuilder sql = new StringBuilder();

		sql.append("delete from vendedor ");
		sql.append("where id_vendedor = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setLong(1, v.getIdVendedor());

		comando.executeUpdate();

	}

	public ArrayList<Vendedor> listarVendedor() throws SQLException {
		StringBuilder sql = new StringBuilder();

		sql.append("select id_vendedor, nome_vendedor ");
		sql.append("from vendedor ");
		sql.append("order by id_vendedor asc ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<Vendedor> lista = new ArrayList<Vendedor>();

		while (resultado.next()) {
			Vendedor v = new Vendedor();

			v.setIdVendedor(resultado.getLong("id_vendedor"));
			v.setNomeVendedor(resultado.getString("nome_vendedor"));
			lista.add(v);
		}
		return lista;
	}

	public Vendedor buscarPorID(Vendedor v) throws SQLException {

		StringBuilder sql = new StringBuilder();

		sql.append("select id_vendedor, nome_vendedor ");
		sql.append("from vendedor ");
		sql.append("where id_vendedor = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setLong(1, v.getIdVendedor());

		ResultSet resultado = comando.executeQuery();

		Vendedor retorno = null;

		if (resultado.next()) {
			retorno = new Vendedor();
			retorno.setIdVendedor(resultado.getLong("id_vendedor"));
			retorno.setNomeVendedor(resultado.getString("nome_vendedor"));

		}

		return retorno;
	}
	public ArrayList<Vendedor> buscarPorNome(Vendedor v) throws SQLException {

		StringBuilder sql = new StringBuilder();

		sql.append("select id_vendedor, nome_vendedor ");
		sql.append("from vendedor ");
		sql.append("where nome_vendedor like ? ");
		sql.append("order by nome_vendedor asc");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, "%" + v.getNomeVendedor() + "%");

		ResultSet resultado = comando.executeQuery();

		ArrayList<Vendedor> lista = new ArrayList<Vendedor>();

		while (resultado.next()) {
			Vendedor item = new Vendedor();

			item.setIdVendedor(resultado.getLong("id_vendedor"));
			item.setNomeVendedor(resultado.getString("nome_vendedor"));
		
			lista.add(item);
		}
		return lista;
	}
}

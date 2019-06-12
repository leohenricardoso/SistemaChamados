package br.com.sistemachamados.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.sistemachamados.domain.Status;
import br.com.sistemachamados.factory.ConexaoFactory;

public class StatusDAO {

	public void salvarNovoStatus(Status s) throws SQLException {
		StringBuilder sql = new StringBuilder();

		sql.append("insert into status ");
		sql.append("(id_status, descricao_status) ");
		sql.append("values (?, ?) ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setLong(1, s.getIdStatus());
		comando.setString(2, s.getDescricaoStatus());

		comando.executeUpdate();
	}

	public void excluirStatus(Status s) throws SQLException {
		StringBuilder sql = new StringBuilder();

		sql.append("delete from status ");
		sql.append("where id_status = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setLong(1, s.getIdStatus());

		comando.executeUpdate();

	}

	public void alterarStatus(Status s) throws SQLException {
		StringBuilder sql = new StringBuilder();

		sql.append("update status ");
		sql.append("set id_status = ?, ");
		sql.append("descricao_status = ? ");
		sql.append("where id_status = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setLong(1, s.getIdStatus());
		comando.setString(2, s.getDescricaoStatus());
		comando.setLong(3, s.getIdStatus());
		
		comando.executeUpdate();
	}

	public ArrayList<Status> listarStatus() throws SQLException {

		StringBuilder sql = new StringBuilder();

		sql.append("select s.id_status, s.descricao_status ");
		sql.append("from status s");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<Status> listaDeStatusChamado = new ArrayList<Status>();

		while (resultado.next() == true) {
			Status s = new Status();

			s.setIdStatus(resultado.getLong("id_status"));
			s.setDescricaoStatus(resultado.getString("s.descricao_status"));

			listaDeStatusChamado.add(s);
		}
		return listaDeStatusChamado;
	}

	// aqui embaixo copiei colei e editei

	public ArrayList<Status> buscarPorIDStatus(Status s) throws SQLException {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT s.id_status, s.descricao_status ");
		sql.append("FROM status s ");
		sql.append("where s.id_status = ? ");		

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setLong(1, s.getIdStatus());

		ResultSet resultado = comando.executeQuery();

		ArrayList<Status> lista = new ArrayList<Status>();

		while (resultado.next()) {
			Status status = new Status();

			status.setIdStatus(resultado.getLong("s.id_status"));
			status.setDescricaoStatus(resultado.getString("s.descricao_status"));

			lista.add(status);
		}
		return lista;
	}

	public ArrayList<Status> buscarPorDescricaoStatus(Status s) throws SQLException {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT s.id_status, s.descricao_status ");
		sql.append("FROM status s ");
		sql.append("where s.descricao_status like ? ");
		sql.append("order by s.descricao_status asc ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, "%" + s.getDescricaoStatus() + "%");

		ResultSet resultado = comando.executeQuery();

		ArrayList<Status> lista = new ArrayList<Status>();

		while (resultado.next()) {
			Status st = new Status();
			st.setIdStatus(resultado.getLong("s.id_status"));
			st.setDescricaoStatus(resultado.getString("s.descricao_status"));

			lista.add(st);
		}
		return lista;
	}

}

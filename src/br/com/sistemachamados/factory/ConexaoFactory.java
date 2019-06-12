package br.com.sistemachamados.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {
	private static final String USUARIO = "root";
	private static final String SENHA = "root";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/chamados";

	public static Connection conectar() throws SQLException {
		DriverManager.registerDriver(new com.mysql.jdbc.Driver()); // Caso de
		// problema de conexao
		Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
		return conexao;
	}

	/*public static void main(String[] args) {
		try {
			Connection conexao = ConexaoFactory.conectar();
			System.out.println("Conexão realizada com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}*/
}

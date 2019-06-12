package br.com.sistemachamados.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.sistemachamados.domain.Cliente;
import br.com.sistemachamados.factory.ConexaoFactory;

public class ClienteDAO {
	public void salvarCliente(Cliente c) throws SQLException{
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into cliente ");
		sql.append("(nome_cliente, telefone_cliente, email_cliente) ");
		sql.append("values (?, ?, ?) ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1, c.getNomeCliente());
		comando.setString(2, c.getTelefoneCliente());
		comando.setString(3, c.getEmailCliente());
		
		comando.executeUpdate();
	}
	
	public void editarCliente(Cliente c) throws SQLException{
		StringBuilder sql = new StringBuilder();
		
		sql.append("update cliente ");
		sql.append("set nome_cliente = ?, telefone_cliente = ?, email_cliente = ? ");
		sql.append("where id_cliente = ? ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1, c.getNomeCliente());
		comando.setString(2, c.getTelefoneCliente());
		comando.setString(3, c.getEmailCliente());
		comando.setLong(4, c.getIdCliente());
		
		comando.executeUpdate();
	}
	
	public void excluirCLiente(Cliente c) throws SQLException{
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from cliente ");
		sql.append("where id_cliente = ? ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setLong(1, c.getIdCliente());
		
		comando.executeUpdate();
	}
	
	public ArrayList<Cliente> listar() throws SQLException {
        StringBuilder sql = new StringBuilder();

        sql.append("select id_cliente, nome_cliente, telefone_cliente, email_cliente ");
        sql.append("from Cliente ");
        sql.append("order by nome_cliente asc ");

        Connection conexao = ConexaoFactory.conectar();

        PreparedStatement comando = conexao.prepareStatement(sql.toString());

        ResultSet resultado = comando.executeQuery();

        ArrayList<Cliente> lista = new ArrayList<Cliente>();

        while(resultado.next()) {
            Cliente c = new Cliente();
            
            c.setIdCliente(resultado.getLong("id_cliente"));
            c.setNomeCliente(resultado.getString("nome_cliente"));
            c.setTelefoneCliente(resultado.getString("telefone_cliente"));
            c.setEmailCliente(resultado.getString("email_cliente"));
            lista.add(c);
        }
        return lista;
	}
	
	public Cliente buscarPorID(Cliente c) throws SQLException{
		StringBuilder sql = new StringBuilder();
		
		sql.append("select id_cliente, nome_cliente, telefone_cliente, email_cliente ");
		sql.append("from cliente ");	
		sql.append("where id_cliente = ? ");
		
		Connection conexao = ConexaoFactory.conectar();	
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setLong(1, c.getIdCliente());
		
		ResultSet resultado = comando.executeQuery();
		
		Cliente retorno = null;
		
		if(resultado.next()) {
			retorno = new Cliente();
			retorno.setIdCliente(resultado.getLong("id_cliente"));
			retorno.setNomeCliente(resultado.getString("nome_cliente"));
			retorno.setTelefoneCliente(resultado.getString("telefone_cliente"));
			retorno.setEmailCliente(resultado.getString("email_cliente"));
		}
		return retorno;
	}
	
	public ArrayList<Cliente> buscarPorNome(Cliente c) throws SQLException{
		StringBuilder sql = new StringBuilder();
		
		sql.append("select id_cliente, nome_cliente, telefone_cliente, email_cliente ");
		sql.append("from cliente ");	
		sql.append("where nome_cliente like ? ");
		sql.append("order by nome_cliente asc ");
		
		Connection conexao = ConexaoFactory.conectar();	
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1, "%" + c.getNomeCliente() + "%");
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		
		while(resultado.next()) {
			Cliente item = new Cliente();
			
			item.setIdCliente(resultado.getLong("id_cliente"));
			item.setNomeCliente(resultado.getString("nome_cliente"));
			item.setTelefoneCliente(resultado.getString("telefone_cliente"));
			item.setEmailCliente(resultado.getString("email_cliente"));
			
			lista.add(item);
		}
		return lista;
	}
	
	//SALVAR
	/*public static void main(String[] args) {
		Cliente c = new Cliente();
		
		c.setNomeCliente("Gabriel Faria te amo");
		c.setTelefoneCliente("66669999");
		c.setEmailCliente("raposinha sexy");
		
		ClienteDAO dao = new ClienteDAO();
		try {
			dao.salvarCliente(c);
			System.out.println("Deu certo!");
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}*/
	
	//EDITAR
	/*public static void main(String[] args) {
		Cliente c = new Cliente();
		
		c.setNomeCliente("Leonardo Henrique");
		c.setEmailCliente("leohenricardoso@gmail.com");
		c.setTelefoneCliente("991821340");
		c.setIdCliente(1L);
		
		ClienteDAO dao = new ClienteDAO();
		try {
			dao.editarCliente(c);
			System.out.println("Editado!!");
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}*/
	
	//EXCLUIR
	/*public static void main(String[] args) {
		Cliente c = new Cliente();
		c.setIdCliente(2L);
		
		ClienteDAO dao = new ClienteDAO();
		try {
			dao.excluirCLiente(c);
			System.out.println("Excluido!");
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}*/
	
	//LISTAR
	/*public static void main(String[] args) {
		ClienteDAO dao = new ClienteDAO();
		
		try {
			ArrayList<Cliente> lista = dao.listar();
			
			for(Cliente cliente : lista) {
				System.out.println("Resultado: " + cliente);
			}
		} catch (SQLException e) {			
			e.printStackTrace();			
		}
	}*/
	
	//BUSCAR POR ID CLIENTE
	/*public static void main(String[] args) {
		Cliente c = new Cliente();
		c.setIdCliente(1L);
		
		ClienteDAO dao = new ClienteDAO();
		try {
			//Cliente c1 = dao.buscarPorID(c);
			System.out.println("Resultado: " +  dao.buscarPorID(c));
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}*/
	
	//BUSCAR POR NOME
	/*public static void main(String[] args) {
		Cliente c = new Cliente();
		c.setNomeCliente("Leo");
		
		ClienteDAO dao = new ClienteDAO();
		
		try {
			ArrayList<Cliente> lista = dao.buscarPorNome(c);
			for(Cliente cliente : lista) {
				System.out.println("Resultado: " + cliente);
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
	}*/	
}

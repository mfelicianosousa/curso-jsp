package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnectionBanco;
import model.Login;

public class DAOLoginRepository {
	
	private Connection connection ;
	
	public DAOLoginRepository() {
		
		connection = SingleConnectionBanco.getConnection() ;
	}
	
	public boolean validarAutenticacao(Login login) throws Exception {
		
		String sql = "SELECT is_ativo FROM usuario WHERE login = ? AND senha = ?";
		
		PreparedStatement statement = connection.prepareStatement(sql) ;
		statement.setString(1, login.getLogin()) ;
		statement.setString(2, login.getSenha()) ;
			
		
		ResultSet resultSet = statement.executeQuery();
		if ( resultSet.next() ) {
		
			return true ; /* autenticado */
		}
		return false ; /* não autenticado */
		
	}

}

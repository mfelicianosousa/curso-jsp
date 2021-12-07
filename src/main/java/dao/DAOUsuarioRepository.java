package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnectionBanco;
import entidades.Usuario;

public class DAOUsuarioRepository {

	private Connection connection ;
	
	public DAOUsuarioRepository(){
		
		connection = SingleConnectionBanco.getConnection() ;
	}
	
	public Usuario gravarUsuario( Usuario usuario, Long usuarioLogado ) throws Exception {
		
		if (usuario.isNovo() ) {
			/* Grava novo registro */
			String sql = "INSERT INTO usuario (nome, login, senha, email, is_administrador, is_ativo, usuario_id ) VALUES (?, ?, ?, ?, ?, ?, ?) ";
			   
			PreparedStatement preparedSql = connection.prepareStatement(sql) ;
			preparedSql.setString(1, usuario.getNome() );
			preparedSql.setString(2, usuario.getLogin() );
			preparedSql.setString(3, usuario.getSenha() );
			preparedSql.setString(4, usuario.getEmail() );
			preparedSql.setBoolean(5, usuario.getIsAdministrador());
			preparedSql.setBoolean(6, usuario.getIsAtivo());
			preparedSql.setLong(7, usuarioLogado);
			
	        preparedSql.execute() ;
			connection.commit();
				
			
		} else {
			/* Atualiza registro */
			String sql = "UPDATE usuario SET nome = ?, login = ?, senha = ?, email = ?, is_administrador = ?, is_ativo = ? WHERE id = ? ";
			   
			PreparedStatement preparedSql = connection.prepareStatement(sql) ;
			preparedSql.setString(1, usuario.getNome() );
			preparedSql.setString(2, usuario.getLogin() );
			preparedSql.setString(3, usuario.getSenha() );
			preparedSql.setString(4, usuario.getEmail() );
			preparedSql.setBoolean(5, usuario.getIsAdministrador());
			preparedSql.setBoolean(6, usuario.getIsAtivo());
			preparedSql.setLong(7, usuario.getId());
	        preparedSql.executeUpdate();
			connection.commit();
		}
		
		return this.consultaUsuario(usuario.getLogin());
	}
	
	public Usuario consultaUsuario( String varLogin ) throws Exception {
		
		Usuario usuario = new Usuario();
		
		String sql = "SELECT id, login, nome, email, is_administrador, is_ativo FROM usuario WHERE upper(login) = upper( ? ) " ;
		
		PreparedStatement preparedSql = connection.prepareStatement(sql);
		preparedSql.setString(1, varLogin );
		
		ResultSet resultSet = preparedSql.executeQuery();
		while (resultSet.next()) {  /* Se tem resultado */
			usuario.setId( resultSet.getLong("id"));
			usuario.setLogin(resultSet.getString("login"));
			usuario.setNome( resultSet.getString("nome"));
			usuario.setEmail(resultSet.getString("email"));
			usuario.setAdministrador(resultSet.getBoolean("is_Administrador"));
			usuario.setAtivo(resultSet.getBoolean("is_ativo")) ;
			
		}
		
		return usuario;
		
		
	}
	
	public boolean UsuarioExists( String varLogin ) throws Exception {
		
		String sql = "SELECT count(1) > 0 AS isExists FROM usuario WHERE upper(login) = upper( ? ) AND is_Ativo = true" ;
		
		PreparedStatement preparedSql = connection.prepareStatement(sql);
		preparedSql.setString(1, varLogin );
		
		ResultSet resultSet = preparedSql.executeQuery();
		resultSet.next() ; /* Se tem resultado */
		return resultSet.getBoolean("isExists") ;

		
	}
	
	public void deleteUsuario( String id) throws Exception {
		
		String sql = "UPDATE usuario SET  is_ativo = false WHERE id = ? AND is_administrador is false" ;
		
		PreparedStatement preparedSql = connection.prepareStatement(sql);
		preparedSql.setLong(1,Long.parseLong(id ));
        preparedSql.executeUpdate();
		connection.commit();

		
	}
	
	public void expurgarUsuario( String id) throws Exception {
		
		String sql = "DELETE FROM usuario WHERE id = ? AND is_administrador is false" ;
		
		PreparedStatement preparedSql = connection.prepareStatement(sql);
		preparedSql.setLong(1,Long.parseLong(id ));
        preparedSql.executeUpdate();
		connection.commit();

		
	}
	
	public List<Usuario> pesquisarPorNome( String nome) throws Exception {
		
		List<Usuario> listUsuario = new ArrayList<Usuario>();
		
		String sql = "SELECT id, login, nome, email, is_administrador, is_ativo FROM usuario WHERE upper(nome) LIKE upper( ? ) AND is_administrador is false" ;
		
		PreparedStatement preparedSql = connection.prepareStatement(sql);
		preparedSql.setString(1, '%'+nome+'%' );
		
		ResultSet resultSet = preparedSql.executeQuery();
		while (resultSet.next()) {  /* Se tem resultado */
		    Usuario usuario = new Usuario();
		    usuario.setId(resultSet.getLong("id"));
		    usuario.setLogin(resultSet.getString("login"));
		    usuario.setNome(resultSet.getString("nome"));
		    usuario.setEmail(resultSet.getString("email"));
		    usuario.setAtivo(resultSet.getBoolean("is_Ativo"));
		    usuario.setAdministrador(resultSet.getBoolean("is_Administrador"));
		    
			listUsuario.add(usuario) ;
		}

		return listUsuario ;
	}
	
	public Usuario pesquisarPorId( Long id) throws Exception {
		
		 Usuario usuario = new Usuario();
		 
		String sql = "SELECT id, login, nome, email, is_administrador, is_ativo FROM usuario WHERE id = ? AND is_administrador is false" ;
		
		PreparedStatement preparedSql = connection.prepareStatement(sql);
		preparedSql.setLong(1, id );
		
		ResultSet resultSet = preparedSql.executeQuery();
		
		while (resultSet.next()) {  /* Se tem resultado */
		   
		    usuario.setId(resultSet.getLong("id"));
		    usuario.setLogin(resultSet.getString("login"));
		    usuario.setNome(resultSet.getString("nome"));
		    usuario.setEmail(resultSet.getString("email"));
		    usuario.setAtivo(resultSet.getBoolean("is_Ativo"));
		    usuario.setAdministrador(resultSet.getBoolean("is_Administrador"));

		}

		return usuario ;
	}
	
	public List<Usuario> listarUsuarios() throws Exception {
		
		List<Usuario> listUsuario = new ArrayList<Usuario>();
		
		String sql = "SELECT id, login, nome, email, is_administrador, is_ativo FROM usuario WHERE is_ativo = true AND is_administrador is false" ;
		
		PreparedStatement preparedSql = connection.prepareStatement(sql);
		
		ResultSet resultSet = preparedSql.executeQuery();
		
		while (resultSet.next()) {  /* Se tem resultado, percorre as linhas de resultado do SQL */
			
		    Usuario usuario = new Usuario();
		    
		    usuario.setId(resultSet.getLong("id"));
		    usuario.setLogin(resultSet.getString("login"));
		    usuario.setNome(resultSet.getString("nome"));
		    usuario.setEmail(resultSet.getString("email"));
		    usuario.setAtivo(resultSet.getBoolean("is_Ativo"));
		    usuario.setAdministrador(resultSet.getBoolean("is_Administrador"));
		    
			listUsuario.add(usuario) ;
		}

		return listUsuario ;
	}
	
}

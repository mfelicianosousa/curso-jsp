<!-- 
******************************************************************    
 * Em 18/11/2011 19:41
 * 
 * Responsável :  Marcelino F Sousa
 *  
 * Objetivo : Tela de Cadastro de Usuários de acesso ao sistema 
 *
 *  Manutenção : 27/11/2021 - Modal de Pesquisa de Usuário utilizando Ajax  
 *      28/11/2021 - Incluir tabela
 *      30/11/2021 - Incluir Lista de Usuários 
 **
 -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="head.jsp"/>

  <body>
  <!-- Pre-loader start -->
 <jsp:include page="theme-loader.jsp"/>
  <!-- Pre-loader end -->
  <div id="pcoded" class="pcoded">
      <div class="pcoded-overlay-box"></div>
      <div class="pcoded-container navbar-wrapper">
          <!-- Include navbar -->
          <jsp:include page="navbar.jsp"></jsp:include>

          <div class="pcoded-main-container">
              <div class="pcoded-wrapper">
                  <!-- Include navbar mainmenu -->
                  <jsp:include page="navbar-mainmenu.jsp"></jsp:include>
                  
                  <div class="pcoded-content">
                      <!-- Page-header start -->
                      
                      <jsp:include page="page-header.jsp"></jsp:include>
                      
                      <!-- Page-header end -->
                      
                        <div class="pcoded-inner-content">
                            <!-- Main-body start -->
                            <div class="main-body">
                                <div class="page-wrapper">
                                    <!-- Page-body start -->
                                    <div class="page-body">
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <!-- Basic Form Inputs card start -->
                                                <div class="card">
                                                    <div class="card-header">
                                                        <h4>Usuário</h4>
                
                                                    </div>
                                                    <div class="card-block">
                                                        <h4 class="sub-title">Novo Usuário</h4>
                                                        <!-- Form init -->
                                                        <form action="<%= request.getContextPath() %>/ServletUsuarioController" method="post" id="formUsuario">
                                                            <input type="hidden" name="acao" id="acao" value="">
                                                        
                                                        	<div class="form-group row">
                                                                 <label class="col-sm-1 col-form-label">Id</label>                                                 
	                          									 <div class="col-sm-4">
	                                                                <input type="text" class="form-control" name="id" id="id" value="${ modelLogin.id }"  readonly="readonly"/>
	                                                              </div>
                                                           
                                                            </div>
                                                            
                                                            <div class="form-group row">
                                                                <label class="col-sm-1 col-form-label">Nome</label>   
                                                                <div class="col-sm-4">
                                                                    <input type="text" class="form-control" name="nome" id="nome" value="${ modelLogin.nome }" required placeholder="Informe seu nome"/>
                                                                </div>
                                                             </div> 
                                                             
                                                            <div class="form-group row">
                                                               <label class="col-sm-1 col-form-label">Login (E-mail)</label>   
                                                                <div class="col-sm-4">
                                                                    <input type="text" class="form-control" name="login" id="login" value="${ modelLogin.login }"required placeholder="Informe email válido"/>
                                                                </div>
                                                            </div>
                                                            
                                                            
                                                             
                                                              <div class="form-group row">
                                                            	<label class="col-sm-1 col-form-label">E-Mail</label> 
                                                                <div class="col-sm-4">
                                                                   <input type="email" class="form-control" name="email" id="email" value="${ modelLogin.email }" required autocomplete="off"/>
                                                                </div>
                                                             </div>
                                                             
                                                             
                                                             <div class="form-group row">
                                                                <label class="col-sm-1 col-form-label">Senha</label>
                                                                <div class="col-sm-4">
                                                                    <input type="password" class="form-control" name="senha" id="senha" value="${ modelLogin.senha }" required autocomplete="off" placeholder="Informe uma senha"/>
                                                                </div>
                                                             </div>
                                                             
                                                              
                                                             <div class="form-group row">
                                                                 <span class="col-sm-1 col-form-label"></span>
	                                                             <div class="form-check form-switch col-sm-1" >
																	  <input class="form-check-input" type="checkbox" id="isAdministrador" name=isAdministrador>
																	  <label class="form-check-label" for="isAdministrador">Administrador</label>
																  </div>
															 </div>
                                                             
                                                             <div class="form-group row">
                                                                 <label class="col-sm-1 col-form-label"></label>
	                                                             <div class="form-check form-switch col-sm-3" >
																	  <input class="form-check-input" type="checkbox" id="isAtivo" name=isAtivo>
																	  <label class="form-check-label" for="isAtivo">Ativo</label>
																  </div>
															 </div>
                                                             <button type="button" class="btn btn-primary waves-effect waves-light" onclick="limparForm()">Novo</button>
												             <button type="submit" class="btn btn-success waves-effect waves-light">Salvar</button>
												             <button type="button" class="btn btn-danger waves-effect waves-light" onclick="deleteUsuarioAjax()">Deletar</button>
															 <button type="button" class="btn btn-info waves-effect waves-light" data-toggle="modal" data-target="#usuarioPesquisarModal"> Pesquisar</button>
												       
                                                        </form>
                                                        <!-- Form End  -->
                                                     </div>
                                                  </div>
                                                  <!-- Basic Form Inputs card end -->
                                               </div>
                                        </div>
                                          <span id="msg"> ${ msg }</span>
                                          <!-- Start tabela de Usuário  -->
                                          <div style="height:300px; overflow:scroll;">
											<table class="table" id="tableUsuarioView">
											  <thead>
											    <tr>
											      <th scope="col">Id</th>
											      <th scope="col">Nome</th>
											      <th scope="col">Login</th>
											      <th scope="col">Ativo</th>
											      <th scope="col">Ação</th>
											
											    </tr>
											  </thead>
											  <tbody>
											      <c:forEach items='${listarDadosUsuario}' var="listarDados">
											        <tr> 
											           <td> <c:out value="${listarDados.id }"></c:out>  </td>
											           <td> <c:out value="${listarDados.nome }"></c:out>  </td>
											           <td> <c:out value="${listarDados.login }"></c:out>  </td>
											           <td> <c:out value="${listarDados.isAtivo }"></c:out>  </td>
											           <td> <a class="btn btn-success" href="<%=request.getContextPath()%>/ServletUsuarioController?acao=editarUsuario&id=${listarDados.id}">Ver</a> </td>
											        </tr>
											      </c:forEach>
											  </tbody>
											</table>
										</div>
										 <!-- End tabela de Usuário  -->
                                    </div>
                                    <!-- Page-body end -->
                                </div>
                                <div id="styleSelector"> </div>
                            </div>
                            <!-- Main-body end -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Usuario Pesquisar Modal -->
	<div class="modal fade" id="usuarioPesquisarModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="modalLabel">Pesquisar Usuários</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        <div class="input-group mb-3">
			  <input type="text" class="form-control" id="nomePesquisa" placeholder="Nome do Usuário" aria-label="nomePesquisa" aria-describedby="basic-addon2">
			  <div class="input-group-append">
			    <button class="btn btn-success" type="button" onclick="pesquisaUsuario();">Pesquisar</button>
			  </div>
			</div>
			<div style="height:300px; overflow:scroll;">
				<table class="table" id="tableUsuario">
				  <thead>
				    <tr>
				      <th scope="col">Id</th>
				      <th scope="col">Nome</th>
				      <th scope="col">Login</th>
				      <th scope="col">Ativo</th>
				      <th scope="col">Ação</th>
				
				    </tr>
				  </thead>
				  <tbody>
				    
				  </tbody>
				</table>
			</div>
	      </div>
	      <span id="totalResultados"></span>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
	      </div>
	    </div>
	  </div>
	</div>
    <jsp:include page="jsfile.jsp"/>
    
    <script type="text/javascript">
      function limparForm(){
          /* Limpar os campos value do Formulario */
    	  var elementos = document.getElementById("formUsuario").elements;
    	  for (p = 0; p < elementos.length; p++ ){
    		  elementos[p].value = '';
    	  }
      }
      function deleteUsuario(){
    	  
    	  if (confirm("Deseja realmente excluir os dados? ")){

	    	  document.getElementById("formUsuario").method = "get" ;
	    	  document.getElementById("acao").value = 'deletar' ;
	    	  document.getElementById("formUsuario").submit() ;
    	  }
      }
      function deleteUsuarioAjax(){
    	  
    	  if (confirm("Deseja realmente excluir os dados? ")){

	    	  var urlAction = document.getElementById("formUsuario").action ;
	    	  var id = document.getElementById("id").value ;
	    	  
	    	  $.ajax({
	    		 method: "get",
	    		 url: urlAction ,
	    		 data: "id="+id+"&acao=deletarAjax",
	    		 success : function( response ){
	    			 limparForm();
	    			 /*alert(response ) ;*/
 
	    			 document.getElementById('msg').textContent = response ;
	    		 }
  
	    	  }).fail(function(xhr, status, errorThrown ){
	    		  alert('Erro ao deletar usuario por id :'+xhr.responseText ) ;
	    	  }) ;
	    	  
	    	  
    	  }
      }
      
      function pesquisaUsuario(){
    	  
    	  var urlAction = document.getElementById("formUsuario").action ;
    	  var nomePesquisa = document.getElementById("nomePesquisa").value ;
    	  
    	  if (nomePesquisa != null && nomePesquisa != '' && nomePesquisa.trim() != ''){
    		  /* Validando dados para enviar ao banco de dados */
    		    		  
    		 $.ajax({
	    		 method: "get",
	    		 url: urlAction,
	    		 data: "nomePesquisa="+nomePesquisa+"&acao=pesquisarPorNome",
	    		 success : function( response ){
	    			 
	    	     var json = JSON.parse( response )
	    	     
	    	     // ctrl + shift + J (mostrar no console do navegador)
	    	     console.info( json );
	    	     
	    	     $('#tableUsuario > tbody > tr').remove();
	    	     for (var p=0; p < json.length; p++){
	    	    	 
	    	    	 $('#tableUsuario > tbody').append('<tr><td>'+json[p].id+'</td><td>'+json[p].nome+'</td><td>'+json[p].login+'</td><td>'+json[p].isAtivo+'</td><td> <button type="button" class="btn btn-info" onclick="editarUsuario('+ json[p].id +')">Editar</button> </td></tr>');
	    	     } 
	    	     
	    	     /*Apresenta o total de registro no rodapé da página */
	    	     document.getElementById('totalResultados').textContent = 'Resultados : '+json.length ;
	    		 // alert( json );
	    			
	    		 }

	    	  }).fail(function(xhr, status, errorThrown ){
	    		  alert('Erro ao pesquisar usuario por nome :'+xhr.responseText ) ;
	    	  }) ;
    	  }  
      }
      
      function editarUsuario(id) {
    	  //alert( id ) ;
    	  var urlAction = document.getElementById("formUsuario").action ;
    	  window.location.href = urlAction + '?acao=editarUsuario&id='+id ; /* executa um get */
  
      }
    
    </script>
</body>

</html>

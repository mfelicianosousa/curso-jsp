<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- Bootstrap 5.1.3 CSS -->
	<link rel="stylesheet" type="text/css" href="./resources/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="./resources/bootstrap/css/bootstrap-theme.min.css" />
     <!--Fontawesome CDN-->
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    
     <script src="./resources/bootstrap/js/bootstrap.min.js"></script>
 
 	<!--Custom styles-->
	<link rel="stylesheet" type="text/css" href="./resources/bootstrap/css/styles.css">
	
	<title>Autenticação de Usuário</title>
	
	
</head>
<body>
	<div class="container">
		<div class="d-flex justify-content-center h-100">
			<div class="card">
				<div class="card-header">
					<h3>Autenticação</h3>
					<div class="d-flex justify-content-end social_icon">
						<span><i class="fab fa-facebook-square"></i></span>
						<span><i class="fab fa-google-plus-square"></i></span>
						<span><i class="fab fa-twitter-square"></i></span>
					</div>
				</div>
				<div class="card-body">
					<form class="row g-3 needs-validation" novalidate action="<%=request.getContextPath()%>/ServletLogin" method="post" >
						<input type="hidden" value="<%=request.getParameter("url")%>" name="url">
					
						<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-user"></i></span>
							</div>
							<input type="text" class="form-control" name="login" id="login" placeholder="Username ou email" required>
							<div class="invalid-feedback">
						      Usuário Obrigatório
						    </div>
						</div>
						<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-key"></i></span>
							</div>
							<input type="password" class="form-control" name="senha" id="senha" placeholder="senha" required>
							<div class="invalid-feedback">
						      Senha Obrigatório
						    </div>
						</div>
						<div class="row align-items-center remember">
							<input type="checkbox">Manter Conectado
						</div>
						<div class="form-group">
							<input type="submit" value="Acessar" class="btn float-right login_btn">
						</div>
					 </form>
				 </div>
				 <div class="card-footer">
				    <div class="d-flex justify-content-center links">
					     Não tem uma conta? <a href="#"> Cadastre-se </a>
				    </div>
					<div class="d-flex justify-content-center">
					    <a href="#">Recuperar sua senha? </a>
				    </div>
				</div>
			</div>
		</div>
	</div>
	<h5 class="msg"> ${ msg }</h4>
	<!-- JS  Bootstrap Bundle with Popper -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="./resources/bootstrap/js/bootstrap.min.js"></script>
    
    <script type="text/javascript">
    
    (function () {
    	  'use strict'

    	  // Fetch all the forms we want to apply custom Bootstrap validation styles to
    	  var forms = document.querySelectorAll('.needs-validation')

    	  // Loop over them and prevent submission
    	  Array.prototype.slice.call(forms)
    	    .forEach(function (form) {
    	      form.addEventListener('submit', function (event) {
    	        if (!form.checkValidity()) {
    	          event.preventDefault()
    	          event.stopPropagation()
    	        }

    	        form.classList.add('was-validated')
    	      }, false)
    	    })
    	})()
    
    
    
    </script>
</body>
</html>
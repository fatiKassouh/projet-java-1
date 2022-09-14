<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<section class="vh-100 gradient-custom" >
		<div class="container py-5 h-100">
			<div
				class="row d-flex justify-content-center align-items-center h-100">
				<div class="col-12 col-md-8 col-lg-6 col-xl-5">
					<div class="card shadow-2-strong" style="border-radius: 1rem;">
						<div class="card-body p-5 text-center">

							<h3 class="mb-5">Register</h3>
							<form action="register" method="post">
								<div class="form-outline mb-4">
									<label class="form-label" for="typeEmailX-2">nom </label> <input
										name="nom" type="text" id="typeEmailX-2"
										class="form-control form-control-lg" />
								</div>
								<div class="form-outline mb-4">
									<label class="form-label" for="typePasswordX-2">email </label>
									<input type="text" id="typePasswordX-2" name="email"
										class="form-control form-control-lg" />
								</div>
								<div class="form-outline mb-4">
									<label class="form-label" for="typePasswordX-2">password
										</label> <input type="password" id="typePasswordX-2" name="pwd"
										class="form-control form-control-lg" />
								</div>
								<button class="btn btn-danger btn-lg btn-block gradient-custom" type="submit">Enregister</button>
							</form>
						</div>
					</div>
				</div>
			</div>
	</section>
</body>
</html>
<style>
.gradient-custom {
  /* fallback for old browsers */
  background: #fa709a;

  /* Chrome 10-25, Safari 5.1-6 */
  background: -webkit-linear-gradient(to right, rgba(250, 112, 154, 0.5), rgba(254, 225, 64, 0.5));

  /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
  background: linear-gradient(to right, rgba(250, 112, 154, 0.5), rgba(254, 225, 64, 0.5))
}
</style>
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
	<section class="h-100 gradient-form" style="background-color: #eee;">
		<div class="container py-5 h-100">
			<div
				class="row d-flex justify-content-center align-items-center h-100">
				<div class="col-xl-10">
					<div class="card rounded-3 text-black">
						<div class="row g-0">
							<div class="col-lg-6">
								<div class="card-body p-md-5 mx-md-4">
									<div class="text-center">
										<img
											src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/lotus.webp"
											style="width: 185px;" alt="logo">
										<h4 class="mt-1 mb-4 pb-1">We are Your best Planner</h4>
									</div>
									<form action="Auth" method="post">
										<div class="form-outline mb-4">
											<label class="form-label" for="form2Example11">Email</label>
											<input type="email" id="form2Example11" class="form-control"
												name="email" placeholder="entrer votre email" />
										</div>
										<div class="form-outline mb-3">
											<label class="form-label" for="form2Example22">Password</label>
											<input type="password" id="form2Example22"
												class="form-control" name="pwd" placeholder="entrer votre password"/>
										</div>

										<div class="text-center pt-1 mb-3 pb-1">
											<button
												class="btn btn-warning btn-block fa-lg gradient-custom mb-3"
												type="submit">Se connecter</button>
											<a class="text-muted" href="#!">Forgot password?</a>
										</div>
										<div
											class="d-flex align-items-center justify-content-center pb-4">
											<p class="mb-0 me-2">Vous n'avez pas de compte ?</p>
											<a href="registerForm">
												<button type="button" class="btn btn-outline-danger">Create
													new</button>
											</a>
										</div>
									</form>

								</div>
							</div>
							<div class="col-lg-6 d-flex align-items-center gradient-custom">
								<div class="text-white px-3 py-4 p-md-5 mx-md-4">
									<h4 class="mb-4">On est beaucoup plus qu'un planner</h4>
									<p class="small mb-0">Avec best planner vous pourriez plannifier
									votre journée en spécifiant vos taches dans tous les catégories possible:
									Travail, Education, Skin Care, Sport </p>
								</div>
							</div>
						</div>
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
<?xml version='1.0' encoding='UTF-8' ?>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>Registration</title>
  <style>
    label {
      display: block;
      margin-bottom: 8px;
    }
  </style>
</head>
<body>

  <div class="main">

		<!-- Sign up form -->
		<section class="signup">
			<div class="container">
				<div class="signup-content">
					<div class="signup-form">
						<h2 class="form-title">Sign up</h2>
					
						<form method="post" action="register" class="register-form" id="register-form">
							<div class="form-group">
								<label for="name"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <input
									type="text" name="name" id="name" placeholder="Your Name" />
							</div>
							<div class="form-group">
								<label for="email"><i class="zmdi zmdi-email"></i></label> <input
									type="email" name="email" id="email" placeholder="Your Email" />
							</div>
							<div class="form-group">
								<label for="pass"><i class="zmdi zmdi-lock"></i></label>
								<input type="password" name="password" id="pass" placeholder="Password" />
							</div>
							<div class="form-group">
								<label for="pass"><i class="zmdi zmdi-lock"></i></label> 
								<input type="password" name="confirmPassword" id="pass" placeholder="ConfirmPassword" />
							</div>
							<div class="form-group">
								<label for="sexe"><i class="zmdi zmdi-email"></i></label> <input
									type="text" name="sexe" id="sexe" placeholder="Your sexe" />
							</div>	
							
							<div class="form-group">
								<label for="Age"><i class="zmdi zmdi-lock-outline"></i></label>
								<input type="text" name="Age" id="contact"
									placeholder="Saisi votre age" />
							</div>					
							<div class="form-group">
								<label for="Ville"><i class="zmdi zmdi-lock-outline"></i></label>
								<input type="text" name="ville" id="Language"
									placeholder="Ville" />
							</div>
							<div class="form-group">
								<label for="Contact"><i class="zmdi zmdi-lock-outline"></i></label>
								<input type="text" name="Contact" id="Contact"
									placeholder="Contact no" />
							</div>
							
							<div class="form-group">
								<input type="checkbox" name="agree-term" id="agree-term"
									class="agree-term" /> <label for="agree-term"
									class="label-agree-term"><span><span></span></span>I
									agree all statements in <a href="#" class="term-service">Terms
										of service</a></label>
							</div>
							<div class="form-group form-button">
								<input type="submit" name="signup" id="signup"
									class="form-submit" value="Register" />
							</div>
						</form>
					</div>
					
				</div>
			</div>
		</section>


	</div> 
</body>
</html>

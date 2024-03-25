<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<h:head>
    <meta charset="UTF-8">
    <title>Sign Up Form</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/angular-material/1.1.24/angular-material.min.css">
    
    <!-- Include AngularJS and Angular Material -->
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.9/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.9/angular-messages.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.9/angular-animate.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.9/angular-aria.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-material/1.1.24/angular-material.min.js"></script>
    <style>
        /* CSS for body background */
        body {
            background-image: url('ressources/img/img2.jpg'); /* Replace 'path/to/background-image.jpg' with your image path */
            background-size: cover;
            background-repeat: no-repeat;
            
            margin: 0; /* Remove default body margin */
            padding: 0; /* Remove default body padding */
            /* make background semi-transparent */
            

            
            

        }

        /* CSS for header layout */
        .header {
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 10px 20px;
            background-color: rgba(235, 229, 224, 0.8); /* Semi-transparent background for better readability */
        }

        .logo {
            height: 40px; /* Adjust height as needed */
        }

        .navigation-links ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            display: flex;
            align-items: center;
        }

        .navigation-links ul li {
            margin-left: 20px;
        }

        .navigation-links ul li a {
            text-decoration: none;
            color: #333;
            font-weight: bold;
            transition: color 0.3s ease;
        }

        .navigation-links ul li a:hover {
            color: #63492B; /* Change color on hover */
        }

        .search-bar input[type="text"] {
            padding: 8px;
            border-radius: 5px;
            border: 1px solid #ccc;
            transition: border-color 0.3s ease;
        }

        .search-bar input[type="text"]:focus {
            outline: none;
            border-color: #63492B; /* Change border color on focus */
        }
        /* CSS for form container */
        #SignupContent {
            background-color: rgba(235, 229, 224, 0.9); /* Same color as header */
            border-radius: 10px; /* Add border radius for styling */
            color: black;
        }
    </style>
</h:head>
<h:body ng-app="MyApp" ng-controller="DemoCtrl" ng-cloak>
<!-- Header -->
<div class="header">
    <img src="ressources/img/logo.jpg" alt="Logo" class="logo">
    <div class="navigation-links">
        <ul>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="#">Contact</a></li>
            <li class="search-bar"><input type="text" placeholder="Search"></li>
            <li><a href="login">Je suis guide</a></li>
        </ul>
    </div>
</div>
<!-- Form Container -->
<div style="background-color: rgba(235, 229, 224, 0.4);">
<div class="md-inline-form" layout="column" layout-sm="row" layout-align="center center" layout-align-sm="start start" layout-fill>
    <p style="font-style: italic; color:black; text-align: center;font-size: 20px; ">Vous etes un guide touristique? </p>
    <p>Renseignez ces informations pour rejoindre notre communaute!</p>
    
        <md-content id="SignupContent" class="md-whiteframe-10dp "  flex-sm>
           
            <div layout-padding>
                <div></div>
                <form name="userForm" method="POST" action="signup" ng-submit="user.submit(userForm.$valid)" >
                <input type="hidden" name="action" value="signup" />
                <div layout="row" layout-sm="column">
                    <md-input-container flex-gt-sm>
                <!-- First Name -->
                <div layout="row" layout-sm="column">
                    <md-input-container flex-gt-sm>
                        <label>First name</label>
                        <input ng-model="user.firstName" name="firstName" required type="text" ng-pattern="/^[a-zA-Z'. -]+$/" placeholder="Your First Name">
                        <div ng-if="userForm.firstName.$dirty" ng-messages="userForm.firstName.$error" role="alert">
                            <div ng-message="required" class="my-message">First Name is Required.</div>
                            <div ng-message="pattern" class="my-message">Enter correct First Name.</div>
                        </div>
                    </md-input-container>
                    <!-- Last Name -->
                
                    <md-input-container flex-gt-sm>
                        <label>Last Name</label>
                        <input ng-model="user.lastName" name="lastName" required type="text" ng-pattern="/^[a-zA-Z'. -]+$/" placeholder="Your Last Name">
                        <div ng-if="userForm.lastName.$dirty" ng-messages="userForm.lastName.$error" role="alert">
                            <div ng-message="required" class="my-message">Last Name is Required.</div>
                            <div ng-message="pattern" class="my-message">Enter correct Last Name.</div>
                        </div>
                    </md-input-container>
                </div>
                <!-- Gender -->
                <div layout="row" layout-sm="column">
                    <p style="font-size: 12px; margin-left: 3px; margin-top: 18px;">Gender: </p>
                    <input type="hidden" name="sex" value="{{user.sex}}" />
                    <md-radio-group style="margin: 12px 0 19px;" ng-model="user.sex" required>
                        <md-radio-button value="Male" class="md-primary">Male</md-radio-button>
                        <md-radio-button value="Female">Female</md-radio-button>
                    </md-radio-group>
                    <!-- Age -->
                    <md-input-container flex-gt-sm="60">
                        <label>Age</label>
                        <input required type="number" step="any" name="age" ng-model="user.age" min="13" max="100" placeholder="20" />
                        <div ng-if="userForm.age.$dirty" ng-messages="userForm.age.$error" role="alert" multiple>
                            <div ng-message="required">Age is Required.</div>
                            <div ng-message="min">Only Above 13 years Old are allowed.</div>
                            <div ng-message="max">Sorry {{userForm.age.$viewValue}} years old are not Allowed.</div>
                        </div>
                    </md-input-container>
                </div>
                <!-- City -->
                <div layout="row" layout-sm="column"> 
                    <md-input-container flex-gt-sm>
                        <label>City</label>
                        <input required type="text" name="cityName" ng-model="user.cityName" placeholder="Your City" />
                        <div ng-if="userForm.cityName.$dirty" ng-messages="userForm.cityName.$error" role="alert">
                            <div ng-message="required" class="my-message">City is Required.</div>
                        </div>
                        
                    </md-input-container>
                    <!-- Zip Code -->
                    <md-input-container flex-gt-sm>
                        <label>Zip Code</label>
                        <input required type="number" name="zipCode" ng-model="user.zipCode" placeholder="Zip code" />
                        <div ng-if="userForm.zipCode.$dirty" ng-messages="userForm.zipCode.$error" role="alert">
                            <div ng-message="required" class="my-message">Zip code is Required.</div>
                        </div>
                    </md-input-container>
                </div>
                <!-- languages -->
                <div layout="row" layout-sm="column">
                    <md-input-container flex-gt-sm>
                        <label>Languages</label>
                        <input required type="text" name="languages" ng-model="user.languages" placeholder="English, Hindi, French" />
                        <div ng-if="userForm.languages.$dirty" ng-messages="userForm.languages.$error" role="alert">
                            <div ng-message="required" class="my-message">Languages are Required.</div>
                        </div>
                    </md-input-container>
                    
                    <!-- price -->
                    <md-input-container flex-gt-sm>
                        <label>Price</label>
                        <input required type="number" name="price" ng-model="user.price" placeholder="100" />
                        <div ng-if="userForm.price.$dirty" ng-messages="userForm.price.$error" role="alert">
                            <div ng-message="required" class="my-message">Price is Required.</div>
                        </div>
                    </md-input-container>
                </div>
                <!-- Profile Picture -->
                <div layout="row" layout-sm="column">
                     
                     <md-input-container flex-gt-sm>
                        <label>Profile Picture</label>
                        <input type="file" name="profilePic" ng-model="user.profilePic"  />
                        <div ng-if="userForm.profilePic.$dirty" ng-messages="userForm.profilePic.$error" role="alert">
                            <div ng-message="required" class="my-message">Profile Picture is Required.</div>
                        </div>
                    </md-input-container>
                </div>
            </md-input-container>
            <md-input-container flex-gt-sm>
                <!-- Email -->
                <div layout="row" layout-sm="column">
                    <md-input-container flex-gt-sm>
                        <label>Email</label>
                        <input required type="email" name="email" ng-model="user.email" ng-pattern="/^[_a-z0-9-+]+(\.[_a-z0-9-+]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,4})$/" placeholder="someone@example.com" />
                        <div ng-if="userForm.email.$dirty" ng-messages="userForm.email.$error" role="alert">
                            <div ng-message="required" class="my-message">Email Address is Required.</div>
                            <div ng-message="pattern" class="my-message">Enter Correct Email Address. </div>
                            <div ng-message="email" class="my-message">Enter Correct Email Address. </div>
                        </div>
                    </md-input-container>
                </div>
                <!-- Phone Number -->
                <div layout="row" layout-sm="column">
                    <md-input-container flex-gt-sm>
                        <label>Phone Number</label>
                        <input required type="tel" name="phone" ng-model="user.phone" ng-pattern="/^\d{10}$/" placeholder="1234567890" />
                        <div ng-if="userForm.phone.$dirty" ng-messages="userForm.phone.$error" role="alert">
                            <div ng-message="required" class="my-message">Phone Number is Required.</div>
                            <div ng-message="pattern" class="my-message">Enter Correct Phone Number.</div>
                        </div>
                    </md-input-container>
                    
                    
               
                </div>   
                <!-- New Password -->
                <div layout="row" layout-sm="column">
                    <md-input-container flex-gt-sm>
                        <label>New Password</label>
                        <input name="password" ng-model="user.password" type="password" minlength="8" maxlength="100" ng-pattern="/(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}/" required placeholder="********">
                        <div ng-if="userForm.password.$dirty" ng-messages="userForm.password.$error" role="alert" multiple>
                            <div ng-message="required">Password is Required.</div>
                            <div ng-message="pattern">Password should contain at least one number, one lowercase and one uppercase character.</div>
                            <div ng-message="minlength">Password should be greater than 8 letters.</div>
                            <div ng-message="maxlength">Password Can't be more than 100 letters.</div>
                        </div>
                    </md-input-container>
                    <!-- Confirm Password -->
                    <md-input-container flex-gt-sm>
                        <label>Confirm Password</label>
                        <input name="confmPassword" ng-model="user.confmPassword" type="password" minlength="8" maxlength="100" ng-pattern="/(?=.*\d)(?=.*[a-z])(?=.*[ A-Z]).{8,}/" required placeholder="********">
						<div ng-if="userForm.confmPassword.$dirty" ng-messages="userForm.confmPassword.$error" role="alert" multiple>
							<div ng-message="required">Confirm Password is Required.</div>
							<div ng-message="pattern">Password should contain at least one number, one lowercase and one uppercase character.</div>
							<div ng-message="minlength">Password should be greater than 8 letters.</div>
							<div ng-message="maxlength">Password Can't be more than 100 letters.</div>
							<div ng-message="mdNotEqual">Passwords do not match!</div>
						</div>
					</md-input-container>
				</div>
                <!-- Submit Button -->
				<div layout="row" layout-sm="column">
					<md-button class="md-raised md-primary" type="submit" ng-disabled="userForm.$invalid" name="submit">Submit</md-button>
					<md-button type="reset" class="md-raised md-warn">Reset</md-button>
				</div>
            </md-input-container>
            </div>
				
            
			
			</form>
		</div>
	</md-content>

</div>
</div>
<script>
    angular.module('MyApp', ['ngMaterial', 'ngMessages']).controller('DemoCtrl', function ($scope, $http) {
        
      
       
    });
</script>

</h:body>
</html>



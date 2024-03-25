<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/angular-material/1.1.24/angular-material.min.css">
    <!-- Include AngularJS and Angular Material -->
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.9/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.9/angular-messages.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.9/angular-animate.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.9/angular-aria.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-material/1.1.24/angular-material.min.js"></script>
</head>
<body ng-app="MyApp" ng-controller="DemoCtrl" ng-cloak>

<div class="md-inline-form" layout="column" layout-sm="row" layout-align="center center" layout-align-sm="start start" layout-fill>
    <md-content id="SignupContent" class="md-whiteframe-10dp" flex-sm>
        <md-toolbar flex id="materialToolbar">
            <div class="md-toolbar-tools">
                <span flex></span>
                <span class="md-headline" align="center">Login </span>
                <span flex></span>
            </div>
        </md-toolbar>
        <div layout-padding>
            <div></div>
            <form name="userForm" method="POST" action="login" ng-submit="user.submit(userForm.$valid)" >
                <input type="hidden" name="action" value="login" />
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
                </div>
                <!-- Other form fields ... -->
                <md-button class="md-raised md-primary" style="width:100%; margin: 15px 0px 0px 0px;" type="submit" ng-disabled="userForm.$invalid" name="submit">Submit</md-button>
                <md-button class="md-raised md-primary" ng-href="https://codepen.io/faizanrupani/pen/QjzMJp" target="_blank" style="width:100%; margin: 15px 0px 0px 0px;">Code Pen Login Form</md-button>
            </form>
        </div>
    </md-content>
</div>

<script>
    angular.module('MyApp', ['ngMaterial', 'ngMessages']).controller('DemoCtrl', function ($scope) {
       
        });
        
</script>

</body>
</html>

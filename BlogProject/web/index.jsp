<%--
  Created by IntelliJ IDEA.
  User: Юзер
  Date: 03.02.2020
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie-edge">
    <link rel="stylesheet" type="text/css" href="style/signup.css">
    <title>Blog Platform - Sign up and sign in</title>
  </head>
  <body>
  <section class="signup">
    <div class="container">
      <div class="signup_inner">
        <div>
<form action="/home" method="post">
  <table>
    <tr>
      <td><h2>Sign up form:</h2></td>
    </tr>
    <tr>
      <td><label>E-mail: </label></td>
      <td><input type="email" name="email_signup" placeholder="Enter e-mail"></td>
    </tr>
    <tr>
      <td><label>Password: </label></td>
      <td><input type="password" name="password_signup"></td>
    </tr>
    <tr>
      <td><label>Full name: </label></td>
      <td><input type="text" name="full_name_signup" placeholder="Enter full name"></td>
    </tr>
    <tr>
      <td><button>Sign up</button></td>
    </tr>
  </table>
</form>
        </div>
        <div>
<form action="/auth" method="post">
  <table>
    <tr>
      <td><h2>Sign in form:</h2></td>
    </tr>
    <tr>
      <td><label>E-mail: </label></td>
      <td><input type="email" name="email_signin" placeholder="Enter e-mail"></td>
    </tr>
    <tr>
      <td><label>Password: </label></td>
      <td><input type="password" name="password_signin"></td>
    </tr>
    <tr>
      <td>      <button>Sign in</button>
      </td>
    </tr>
  </table>
</form>
        </div>
      </div>
  <%
  String message = request.getParameter("message");
  if(message!=null){
    if(message.equals("1")){
      out.println("Registration successful!");
    }else if(message.equals("2")){
      out.println("Registration unsuccessful");
    }else if(message.equals("3")){
      out.println("Sign-in failed");
    }else if(message.equals("4")){
      out.println("Signed out");
    }else if(message.equals("5")){
      out.println("Forcibly signed out");
    }
  }
  %>
    </div>
  </section>

  </body>

</html>

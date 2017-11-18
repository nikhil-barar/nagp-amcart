<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template"%>

<template:page pageTitle="${pageTitle}">
  <h1>Amcart App</h1>
  <hr>

  <div class="form">
    <form action="hello" method="post">
      <table>
        <tr>
          <td>Enter Your name</td>
          <td><input id="name" name="name"></td>
          <td><input type="submit" value="Submit"></td>
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </tr>
      </table>
    </form>
  </div>

</template:page>
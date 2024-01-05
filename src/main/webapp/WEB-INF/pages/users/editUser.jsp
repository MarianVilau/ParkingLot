<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Edit User">
  <h1>Edit User</h1>
  <form method="POST" action="${pageContext.request.contextPath}/EditUser">
    <input type="hidden" name="id" value="${user.id}"/>
    <div class="form-group">
      <label for="username">Username</label>
      <input type="text" class="form-control" id="username" name="username" value="${user.username}" required>
    </div>
    <div class="form-group">
      <label for="email">Email</label>
      <input type="email" class="form-control" id="email" name="email" value="${user.email}" required>
    </div>
    <div class="form-group">
      <label for="password">Password (leave empty to keep current password)</label>
      <input type="password" class="form-control" id="password" name="password">
    </div>
    <button type="submit" class="btn btn-primary">Save</button>
  </form>
</t:pageTemplate>

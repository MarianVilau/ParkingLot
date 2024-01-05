<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Add User">
  <h1>Add User</h1>
  <form method="POST" action="${pageContext.request.contextPath}/AddUser">
    <div class="mb-3">
      <label for="username" class="form-label">Username</label>
      <input type="text" class="form-control" id="username" name="username" required>
      <div class="invalid-feedback">
        Username is required.
      </div>
    </div>
    <div class="mb-3">
      <label for="email" class="form-label">Email</label>
      <input type="email" class="form-control" id="email" name="email" required>
      <div class="invalid-feedback">
        Email is required.
      </div>
    </div>
    <div class="mb-3">
      <label for="password" class="form-label">Password</label>
      <input type="password" class="form-control" id="password" name="password" required>
      <div class="invalid-feedback">
        Password is required.
      </div>
    </div>
    <div class="mb-3">
      <label for="groups" class="form-label">Groups</label>
      <select class="form-select" id="groups" name="user_groups" multiple>
        <c:forEach var="user_group" items="${userGroups}" varStatus="status">
          <option value="${user_group}">${user_group}</option>
        </c:forEach>
      </select>
      <div class="invalid-feedback">
        Please select at least one group.
      </div>
    </div>
    <button class="btn btn-primary" type="submit">Save</button>
  </form>
</t:pageTemplate>
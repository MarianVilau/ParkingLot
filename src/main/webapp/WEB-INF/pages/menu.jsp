<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<div class="container">

    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="${pageContext.request.contextPath}">Parking Lot</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault"
                    aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarsExampleDefault">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link"
                        ${pageContext.request.requestURI.substring(pageContext.request.requestURI.lastIndexOf("/")) eq '/about.jsp' ? ' active' : ''}
                           aria-current="page" href="${pageContext.request.contextPath}/about.jsp">About</a>
                    </li>

                        <li class="nav-item ${activePage eq 'Cars' ? ' active' : ''}">
                            <a class="nav-link" href="${pageContext.request.contextPath}/Cars">Cars</a>
                        </li>


                        <li class="nav-item ${activePage eq 'Users' ? ' active' : ''}">
                            <a class="nav-link" href="${pageContext.request.contextPath}/Users">Users</a>
                        </li>


                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="#">Link</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="#">Disabled</a>
                    </li>
                </ul>
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/Login">Login</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</div>
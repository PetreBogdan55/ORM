<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.tpd_client.controllers.HomeServlet" %>
<%@ page import="com.example.tpd_client.models.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.tpd_client.data_access.UserBookDAO" %>
<%@ page import="com.example.tpd_client.data_access.BookDAO" %>
<html>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
    <%@include file="/css/home.css" %>
</style>
<head>
    <title>Title</title>
</head>
<body>

<div class="center">
    <h1>

        Hello,
        <%= request.getSession().getAttribute("username")%>
    </h1>
</div>

<div class="books-list">
    <h2>You own the following books:</h2>
    <div class="table">

        <div class="row header blue">
            <div class="cell">
                #
            </div>
            <div class="cell">
                Title
            </div>
            <div class="cell">
                Author
            </div>
            <div class="cell">
                Year
            </div>
            <div class="cell">
                Delete
            </div>
        </div>
        <%
            if (request.getSession().getAttribute("errorMessage") == null) {
                int i = 1;
                int userId = (int) request.getSession().getAttribute("userId");
                List<Book> books = UserBookDAO.getBooksForUser(userId);
                for (Book book : books) {
        %>
        <div class="row">
            <div class="cell" data-title="#"><%= i++%>
            </div>
            <div class="cell" data-title="Title"><%= book.getTitle()%>
            </div>
            <div class="cell" data-title="Author"><%= book.getAuthor()%>
            </div>
            <div class="cell" data-title="Year"><%= book.getYear()%>
            </div>
            <div class="cell" data-title="Delete">
                <form method="delete" action="${pageContext.request.contextPath}/home">
                    <button name="delete" value="<%= book.getId() %>" type="submit"><span class="delete-span"> X </span></button>
                </form>
            </div>
        </div>
        <%
            }
        } else {
        %>
        <div>
            <h1><%=request.getSession().getAttribute("errorMessage") %>
            </h1>
        </div>

        <%
            }
        %>


    </div>
</div>
<div class="buttons">
    <form method="post" class="buttons-form" action="${pageContext.request.contextPath}/home">
        <button role="button" class="blue-button" name="button" type="submit" value="logout">Log out</button>
        <button role="button" class="button-orange" type="submit" name="button" value="manage-books">Manage
            Books
        </button>
    </form>
</div>
</body>
</html>

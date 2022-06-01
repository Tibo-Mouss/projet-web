<%@ page import="org.jboss.as.*" %>


<html>
<head>
<!--  <meta http-equiv="Refresh" content="0; URL=HelloWorld">   -->
  <meta charset="UTF-8">  
  <title>Scans Reader Categories</title>
  <link rel="stylesheet" href="Styles/Main_style.css">
  <link rel="stylesheet" href="Styles/Categories.css">
  <link rel="icon" type="image/png" sizes="16x16" href="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRKRTzM01UrruPkwsMJQP66NNDtkJ_A_fdCLQ&usqp=CAU">

  <%
    int nb_boutons_max_ligne = 7; //Nombre de boutons max par ligne

    FacadeTheComeback facade = (FacadeTheComeback) request.getAttribute("facade");

  %>

</head>
<body>

<div class="main_title">
  <a href="index.html">Scans Reader</a>
</div>

<script src="top_bar_text.js"></script>

<br> <br><br> <br>

<table class="centered_table">
  <tbody>
    <% for (int j = 0; j < 2; j++) { %>
      <tr>
        <% for (int i = 0; i < nb_boutons_max_ligne; i++) { %>
          <th>
            <form action="ServletCategories" method="get">
              <button type="submit" value="register" name="op"> <%=org.jboss.as.Genre.values()[i+7*j].toString()%> </button>
            </form>
          </th>
        <% } %>
      </tr>
    <% } %>
  </tbody>
</table>

<br> <br>



</body>

</html>

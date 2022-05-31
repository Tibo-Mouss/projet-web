<%@
  import="java.org.jboss.as.User"
%>

<html>
<head>
<!--  <meta http-equiv="Refresh" content="0; URL=HelloWorld">   --> 
  <meta charset="UTF-8">
  <title>Scans Reader Homepage</title>
  <link rel="stylesheet" href="Styles/Main_style.css">
  <link rel="stylesheet" href="Styles/HomePage.css">
  <link rel="icon" type="image/png" sizes="16x16" href="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRKRTzM01UrruPkwsMJQP66NNDtkJ_A_fdCLQ&usqp=CAU">

  <script src="script.js"></script>

  <%
    int max_mangas_ligne = 5; //Nombre de mangas max par ligne
    int max_nb_lignes = 4; //nombre de lignes de mangas max affichés 

  %>

</head>
<body>


<div class="main_title">
  <a href="home.jsp">Scans Reader</a>
</div>

<!-- <script src="top_bar_text.js"></script> -->

<table class="centered_table top_bar_text">
  <tbody>
    <tr>
      <th>
        <a href="index.html">
          <img class="house_homepage" src="https://cdn-icons-png.flaticon.com/512/25/25694.png"/>
        </a>
      </th>
      <th>
        <a id="categories" href="categories.html">Cat&eacutegories</a>
      </th>
      <% 
      User user = (User) facade.getLoggedUser();
      String username = user.getUsername();
        if (username != null) { %>
          <th>
            <a href="ma_liste.html">Ma liste</a>
          </th>

          <th>
            <a id="login_register" href="home.jsp"> <%=username%> - Deconnexion </a>
          </th>
      <% } else { %>
        <th>
          <a id="login_register" href="login.html">Login / Register</a>
        </th>
      <% } %>
    </tr>
  </tbody>
</table>

<h2>Suggestions</h2>

<% for (int j = 0; j < max_nb_lignes; j++) { %>

<table class="centered_table" id="tableHomePage">
    <tr>
      <% for (int i = 0; i < max_mangas_ligne; i++) { %>
        <td class="box"> <a href="manga_description.html">
        <div class="mouse_hover_image">
            <img class="manga_cover" src="http://images1.fanpop.com/images/photos/2500000/Death-note-manga-covers-death-note-2531412-691-1024.jpg">
            <div class="text_on_image">NOM MANGA</div>
            <table class="stars">
              <td><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/9/9c/Golden_star.svg/640px-Golden_star.svg.png"></td>
              <td><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/9/9c/Golden_star.svg/640px-Golden_star.svg.png"></td>
              <td><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/9/9c/Golden_star.svg/640px-Golden_star.svg.png"></td>
              <td><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/9/9c/Golden_star.svg/640px-Golden_star.svg.png"></td>
              <td><img src="https://upload.wikimedia.org/wikipedia/commons/7/78/BlackStar.PNG"></td>
            </table>
        </div>
        </a></td>
      <% } %>
    </tr>
</table>

<% } %>


<br><br>
</body>


</html>

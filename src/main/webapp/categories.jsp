<%@ page import="org.jboss.as.*, java.util.ArrayList" %>


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
    int max_mangas_ligne = 5; //Nombre de mangas max par ligne
    int max_nb_lignes = 4; //nombre de lignes de mangas max affichés 

    ArrayList<Genre> liste_genres = (ArrayList<Genre>) request.getAttribute("liste_genres");

    ArrayList<Manga> liste_mangas = (ArrayList<Manga>) request.getAttribute("mangas");

    FacadeTheComeback facade = (FacadeTheComeback) request.getAttribute("facade");

  %>

</head>
<body>

<div class="main_title">
  <a href="Servlet/?op=homepage">Scans Reader</a>
</div>

<table class="centered_table top_bar_text">
  <tbody>
    <tr>
      <th>
        <a href="Servlet/?op=homepage">
          <img class="house_homepage" src="https://cdn-icons-png.flaticon.com/512/25/25694.png"/>
        </a>
      </th>
      <th>
        <a id="categories" href="Servlet/?op=categories">Cat&eacutegories</a>
      </th>
      <% if (facade != null) {
        User user = (User) facade.getLoggedUser();
        String username = user.getUsername();
       %>
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

<br> <br><br> <br>

<table class="centered_table">
  <tbody>
    <% for (int j = 0; j < 2; j++) { %>
      <tr>
        <% for (int i = 0; i < nb_boutons_max_ligne; i++) { 
          String genre = org.jboss.as.Genre.values()[i+7*j].toString();
          %>
          <th>
            <form action="Servlet" method="get">
              <button type="submit" value="<%=genre%>" name="genre"> <%=genre%>
              <% if(liste_genres != null && liste_genres.contains(org.jboss.as.Genre.valueOf(genre))) { %>
              &#10003;
              <% } %> </button>
            </form>
          </th>
        <% } %>
      </tr>
    <% } %>
  </tbody>
</table>

<br> <br>

<% 
 for (int j = 0; j < max_nb_lignes; j++) { %>

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


</body>

</html>

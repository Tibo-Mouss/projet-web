document.write(`


<table class="centered_table top_bar_text">
  <tbody>
    <tr>
      <th>
        <a href="Servlet/?op=homepage">
          <img class="house_homepage" src="https://cdn-icons-png.flaticon.com/512/25/25694.png"/>
        </a>
      </th>
      <th>
        <a id="categories" href="categories.jsp">Cat&eacutegories</a>
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

`);
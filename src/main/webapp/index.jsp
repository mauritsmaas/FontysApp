<html>
<head><title>FontysApp</title></head>
<body>
<h1>Registratie pagina</h1>
<h2>Fontys App</h2>
<form action="${pageContext.request.contextPath}/" method="post">
    <table>
        <tr>
            <td>Username</td>
            <td><input type="text" name="username"></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" id="pass" name="password"></td>
        </tr>
    </table>
    <br> <input type="submit" value="Submit">
</form>
</form>
</body>
</html>
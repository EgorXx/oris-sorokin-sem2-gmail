<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Вход</title>
</head>
<body>
<h1>Вход</h1>

<#if error??>
    <p style="color: red;">${error}</p>
</#if>

<form action="/login" method="post">
    <div>
        <label for="username">Username</label>
        <input id="username" type="text" name="username" required>
    </div>

    <div>
        <label for="password">Password</label>
        <input id="password" type="password" name="password" required>
    </div>

    <div>
        <button type="submit">Войти</button>
    </div>
</form>
</body>
</html>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
</head>
<body>
<h2>Регистрация нового пользователя</h2>

<#if errorMessage??>
    <p class="error">${errorMessage}</p>
</#if>

<form action="/register" method="POST">
    <div>
        <label for="username">Имя пользователя:</label>
        <input type="text" id="username" name="username" required />
    </div>
    <br/>

    <div>
        <label for="password">Пароль:</label>
        <input type="password" id="password" name="password" required />
    </div>
    <br/>

    <button type="submit">Зарегистрироваться</button>
</form>

<p>Уже есть аккаунт? <a href="/login">Войти</a></p>
</body>
</html>

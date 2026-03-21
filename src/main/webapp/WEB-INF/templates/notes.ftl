<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Мои заметки</title>
</head>
<body>

<h1>Заметки пользователя ${user.username}</h1>

<a href="/notes/create">Создать заметку</a>

<#if notes?has_content>
    <#list notes as note>
        <div>
            <h2>${note.title}</h2>
            <p>${note.content}</p>
            <p>Создано: ${note.createdAt}</p>
            <p>Видимость: ${note.publicNote?then("Публичная", "Приватная")}</p>

            <a href="/notes/${note.id}/edit">Редактировать</a>
            <form action="/notes/${note.id}/delete" method="post">
                <button type="submit">Удалить</button>
            </form>
        </div>
    </#list>
<#else>
    <p>У вас пока нет заметок</p>
</#if>

</body>
</html>
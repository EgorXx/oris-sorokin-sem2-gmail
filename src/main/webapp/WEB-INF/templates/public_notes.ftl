<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Публичные заметки</title>
</head>
<body>

<h1>Публичные заметки</h1>

<#if notes?has_content>
    <#list notes as note>
        <div>
            <h2>${note.title}</h2>
            <p>${note.content}</p>
            <p>Автор: ${note.author.username}</p>
            <p>Создано: ${note.createdAt}</p>
        </div>
    </#list>
<#else>
    <p>Публичных заметок пока нет</p>
</#if>

</body>
</html>
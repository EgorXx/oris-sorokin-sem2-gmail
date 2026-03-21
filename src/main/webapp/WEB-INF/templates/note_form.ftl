<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Создать заметку</title>
</head>
<body>

<h1>Создать заметку</h1>

<#if error??>
    <p style="color: red;">${error}</p>
</#if>

<form action="${formAction}" method="post">
    <div>
        <label for="title">Заголовок</label>
        <input type="text"
               id="title"
               name="title"
               value="${(note.title)!''}"
               required/>
    </div>

    <div>
        <label for="content">Содержание</label>
        <textarea id="content"
                  name="content"
                  required>${(note.content)!''}</textarea>
    </div>

    <div>
        <label>
            <input type="checkbox"
                   name="publicNote"
                   value="true"
                   <#if note.publicNote>checked</#if>/>
            Публичная заметка
        </label>
    </div>

    <button type="submit">Сохранить</button>
    <a href="/notes">Отмена</a>
</form>

</body>
</html>
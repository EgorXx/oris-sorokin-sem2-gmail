<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Произошла ошибка</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f8f9fa;
            color: #333;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
        }
        .error-container {
            background: white;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
            max-width: 700px;
            width: 100%;
            text-align: center;
        }
        h1 {
            color: #dc3545;
            margin-bottom: 10px;
        }
        .message {
            font-size: 18px;
            margin-bottom: 20px;
        }
        .tech-details {
            background: #f1f3f5;
            padding: 15px;
            border-radius: 5px;
            text-align: left;
            margin-top: 20px;
            border-left: 4px solid #dc3545;
        }
        .tech-details p {
            margin: 5px 0;
            font-family: monospace;
            font-size: 14px;
            word-wrap: break-word;
        }
        details {
            margin-top: 15px;
            text-align: left;
            font-family: monospace;
            font-size: 13px;
            background: #212529;
            color: #20c997;
            padding: 10px;
            border-radius: 5px;
        }
        summary {
            cursor: pointer;
            color: #fff;
            font-weight: bold;
        }
        .btn {
            display: inline-block;
            margin-top: 25px;
            padding: 10px 25px;
            background-color: #0d6efd;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: background 0.3s;
        }
        .btn:hover {
            background-color: #0b5ed7;
        }
    </style>
</head>
<body>

<div class="error-container">
    <h1>Упс! Что-то пошло не так</h1>
    <p class="message">К сожалению, при обработке вашего запроса произошла непредвиденная ошибка.</p>

    <#if exception??>
        <div class="tech-details">
            <strong>Техническая информация:</strong>
            <p><b>Тип:</b> ${exception.class.simpleName!"Неизвестный тип"}</p>
            <p><b>Сообщение:</b> ${exception.message!"Сообщение отсутствует"}</p>
        </div>

        <details>
            <summary>Показать Stack Trace (для разработчиков)</summary>
            <div style="margin-top: 10px; overflow-x: auto; white-space: pre-wrap;">
                <#list exception.stackTrace as traceElement>
                    ${traceElement}
                </#list>
            </div>
        </details>
    </#if>

    <a href="/hello" class="btn">Вернуться на главную</a>
</div>

</body>
</html>
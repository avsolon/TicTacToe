### 📌 [TicTacToe](https://github.com/avsolon/tictactoe)
💡 **Описание:** WEB-приложение игра Крести-Нолики. В основе логики ходов соперника(компьютера) используется алгоритм "Минимакс".<br>
🛠️ **Технологии:** Java, Gradle, Spring Boot, PostgreSQL, HTML, CSS, JS, Docker<br>

## Структура проекта

Каждый слой является отдельным пакетом.
Структура проекта имеет следующие слои: web, domain, datasource.
Слой web должен включает в себя пакеты model, controller, mapper для взаимодействия с клиентом.
Слой domain включает в себя пакеты model, service для реализации бизнес-логики приложения.
Слой datasource включает в себя пакеты model, repository, mapper для реализации работы с БД.


# skillbox-spring-practice
### О программе

Список студентов

## Запуск из Docker

Для запуска из Docker выполнить следующие команды:

> docker build -t students .

> docker run -it students

### Список команд

**add FIRST_NAME LAST_NAME AGE** -- позволяет добавить нового студента в список  
**show** -- выводит полный список студентов на текущий момент  
**delete ID** -- удаляет студента с указанным ID  
**cl** -- полностью очищает список студентов

### Настройки

Хранятся в файле *application.properties*

+ app.students.init-default -- флаг для предзагрузки списка студентов из файла, по умолчанию = true
+ app.students.default-list -- путь к файлу, из которого нужно брать список студентов, по умолчанию = /default-students-list

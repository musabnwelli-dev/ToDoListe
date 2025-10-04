# ToDo App (Java Swing)

Einfache ToDo-Anwendung mit GUI: Aufgaben anlegen, abhaken, zeitlich begrenzen, speichern & laden.

## Features
- ToDos anlegen (Titel, Beschreibung)
- Erledigt-Status per Checkbox
- Optionales Ablaufdatum (x Stunden)
- Abgelaufene ToDos werden beim Start entfernt
- Persistenz in ,,todo-savefile.txt''

## 🚀 Build & Run
```bash
javac -d out src/de/musab/todo/*.java
java -cp out de.musab.todo.Main

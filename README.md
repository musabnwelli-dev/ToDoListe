# ToDo App (Java Swing)

Einfache ToDo-Anwendung mit GUI: Aufgaben anlegen, abhaken, zeitlich begrenzen, speichern & laden.

## Features
- ToDos anlegen (Titel, Beschreibung)
- Erledigt-Status per Checkbox
- Optionales Ablaufdatum (x Stunden)
- Abgelaufene ToDos werden beim Start entfernt
- Persistenz in ,,todo-savefile.txt''

  ## Screenshoots
  
  ### ToDo-Liste mit Einträgen
  ![Liste](https://github.com/musabnwelli-dev/ToDoListe/blob/e7398421109d963ecd3b1f881192eb8c55b36875/docs/images/docs/images/list.png)

  ### Nach Entfernen erledigter Aufgaben
  ![remove done](https://github.com/musabnwelli-dev/ToDoListe/blob/6cbac426bf3efcae948cf8406be70ccb61ae3471/docs/images/docs/images/remove.png)



## 🚀 Build & Run
```bash
javac -d out src/de/musab/todo/*.java
java -cp out de.musab.todo.Main

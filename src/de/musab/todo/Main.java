package de.musab.todo;

/**
 * Einstiegspunkt der de.musab.todo.ToDo-Anwendung.
 *
 * Initialisiert den de.musab.todo.ToDoManager, lädt gespeicherte ToDos,
 * entfernt abgelaufene Einträge und öffnet anschließend das GUI-Fenster.
 */
public class Main {
    public static void main(String[] args) {
        final ToDoManager toDoManager = new ToDoManager("todo-savefile.txt");
        toDoManager.laden();
        toDoManager.removeExpiredToDo();


        ToDoWindow window = new ToDoWindow(toDoManager);


    }
}
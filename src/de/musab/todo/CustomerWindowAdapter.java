package de.musab.todo;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 * Fenster-Adapter, der beim Schließen des Fensters alle ToDos speichert.
 */
public class CustomerWindowAdapter extends WindowAdapter {
    final ToDoManager toDoManager;

    /**
     * Erstellt einen neuen Adapter.
     *
     * @param toDoManager der zu verwendende de.musab.todo.ToDoManager
     */
    public CustomerWindowAdapter(ToDoManager toDoManager)
    {
        this.toDoManager = toDoManager;
    }

    /**
     * Speichert ToDos, wenn das Fenster geschlossen wird.
     *
     * @param e Window-Event des Schließens
     */
    @Override
    public void windowClosing(WindowEvent e) {
        toDoManager.saveToDos();
    }
}

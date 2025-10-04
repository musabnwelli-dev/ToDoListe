package de.musab.todo;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Verwaltet eine Sammlung von de.musab.todo.ToDo-Objekten.
 * Diese Klasse ermöglicht das Hinzufügen, Entfernen, Filtern sowie das
 * Speichern und Laden von ToDos (normal und zeitlich begrenzt).
 */
public class ToDoManager {
    private final ArrayList<ToDo> toDos = new ArrayList<>();
    private String path;
    /**
     * Erstellt einen neuen de.musab.todo.ToDoManager.
     *
     * @param path Dateipfad, in dem die ToDos gespeichert/geladen werden
     */
    public ToDoManager(String path)
    {
        this.path = path;
    }

    /**
     * Fügt ein neues de.musab.todo.ToDo hinzu
     *
     * @param todo das hinzuzufügende de.musab.todo.ToDo
     */
    public void hinzufuegen(ToDo todo)
    {
        toDos.add(todo);
    }

    /**
     * Entfernt ein de.musab.todo.ToDo
     *
     * @param todo das zu entfernende de.musab.todo.ToDo
     */
    public void entfernen(ToDo todo)
    {
        toDos.remove(todo);
    }

    /**
     * Gibt alle zeitlich begrenzten ToDos zurück.
     *
     * @return Liste aller de.musab.todo.TimedToDo-Objekte
     */
    public ArrayList<TimedToDo> getTimedToDo()
    {
        ArrayList<TimedToDo> timedToDo = new ArrayList<>();
        for (int i = 0; i < toDos.size(); i++)
        {
            ToDo myToDo = toDos.get(i);
            if (myToDo instanceof TimedToDo)
            {
                timedToDo.add((TimedToDo) myToDo);
            }
        }
        return timedToDo;
    }

    /**
     * Gibt alle nicht zeitlich begrenzten ToDos zurück.
     *
     * @return Liste aller de.musab.todo.ToDo-Objekte ohne Ablaufdatum
     */
    public ArrayList<ToDo> getNormalToDo()
    {
        ArrayList<ToDo> timedToDo = new ArrayList<>();
        for (int i = 0; i < toDos.size(); i++)
        {
            ToDo myToDo = toDos.get(i);
            if (!(myToDo instanceof TimedToDo))
            {
                timedToDo.add(myToDo);
            }
        }
        return timedToDo;

    }


    /**
     * Gibt alle ToDos zurück.
     *
     * @return Liste aller ToDos
     */
    public ArrayList<ToDo> getToDos() {
        return toDos;
    }

    /**
     * Entfernt alle abgelaufenen zeitlich begrenzten ToDos.
     */
    public void removeExpiredToDo()
    {
        ArrayList<TimedToDo> timedToDos = getTimedToDo();
        for (int i = 0; i < timedToDos.size(); i++)
        {
            if (timedToDos.get(i).getEndet().isBefore(LocalDateTime.now()))
            {
                entfernen(timedToDos.get(i));
            }
        }
    }

    /**
     * Speichert alle ToDos in einer Datei im Textformat.
     */
    public void saveToDos() {
        try {
            final File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            final FileWriter writer = new FileWriter(file);
            for (int i = 0; i < toDos.size(); i++) {
                final ToDo todo = toDos.get(i);
                String saveString = todo.getTitle() + "_" + todo.getBeschreibung() + "_" + todo.isErledigt();
                if (todo instanceof TimedToDo) {
                    saveString += "_" + ((TimedToDo) todo).getEndet();
                }
                writer.write(saveString + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lädt ToDos aus einer Datei und fügt sie dem Manager hinzu.
     */
    public void laden()
    {
        try {
            final File file = new File(path);
            if (!file.exists()) return;

            FileReader reader = new FileReader(file);
            String text = "";
            int zeichenInteger = 0;


            while ((zeichenInteger = reader.read()) != -1) {
                text += (char) zeichenInteger;
            }
            String[] zeile = text.split("\n");
            for (int i = 0; i < zeile.length; i++) {
                String[] einzelneZeile = zeile[i].split("_");
                if (einzelneZeile.length == 3) {
                    hinzufuegen(new ToDo(einzelneZeile[0], einzelneZeile[1], Boolean.valueOf(einzelneZeile[2])));
                } else if (einzelneZeile.length == 4) {
                    hinzufuegen(new TimedToDo(einzelneZeile[0], einzelneZeile[1], Boolean.valueOf(einzelneZeile[2]), LocalDateTime.parse(einzelneZeile[3])));
                }
            }
            reader.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }


    }
}

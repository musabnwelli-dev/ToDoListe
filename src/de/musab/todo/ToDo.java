package de.musab.todo;

/**
 * Repräsentiert ein einzelnes de.musab.todo.ToDo ohne Ablaufdatum.
 *
 * Ein de.musab.todo.ToDo besitzt einen Titel, eine Beschreibung und einen Erledigstatus.
 */
public class ToDo {
    private String title;
    private String beschreibung;
    private boolean erledigt;

    /**
     * Erstellt ein neues de.musab.todo.ToDo.
     *
     * @param title Titel des ToDos
     * @param beschreibung Beschreibung des ToDos
     * @param erledigt Initialer Erledigstatus
     */
    public ToDo(String title, String beschreibung, boolean erledigt)
    {
        this.title = title;
        this.beschreibung = beschreibung;
        this.erledigt = erledigt;
    }

    /**
     *  @return Titel des ToDos
     */

    public String getTitle() {
        return title;
    }

    /**
     * Setzt den Titel.
     * @param title neuer Titel
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return Beschreibung des ToDos
     */
    public String getBeschreibung() {
        return beschreibung;
    }

    /**
     * Setzt die Beschreibung.
     * @param beschreibung neue Beschreibung
     */
    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    /**
     * @return true, wenn erledigt; sonst false
     */
    public boolean isErledigt() {
        return erledigt;
    }

    /**
     * Setzt den Erledigstatus.
     * @param erledigt neuer Status
     */
    public void setErledigt(boolean erledigt) {
        this.erledigt = erledigt;
    }

    @Override
    public String toString() {
        return "de.musab.todo.ToDo{" +
                "title='" + title + '\'' +
                ", beschreibung='" + beschreibung + '\'' +
                ", erledigt=" + erledigt +
                '}';
    }
}

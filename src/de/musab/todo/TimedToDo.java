package de.musab.todo;

import java.time.LocalDateTime;
/**
 * Ein de.musab.todo.ToDo mit Ablaufzeitpunkt.
 *
 * Zusätzlich zu Titel, Beschreibung und Status speichert dieses de.musab.todo.ToDo
 * das Datum/Zeit, wann es abläuft.
 */
public class TimedToDo extends ToDo {
    private LocalDateTime endet;

    /**
     * Erstellt ein neues zeitlich begrenztes de.musab.todo.ToDo.
     *
     * @param title Titel
     * @param beschreibung Beschreibung
     * @param erledigt Initialer Erledigstatus
     * @param endet Ablaufzeitpunkt
     */
    public TimedToDo(String title, String beschreibung, boolean erledigt, LocalDateTime endet) {
        super(title, beschreibung, erledigt);
        this.endet = endet;
    }

    /** @return Ablaufzeitpunkt */
    public LocalDateTime getEndet() {
        return endet;
    }

    /**
     * Setzt den Ablaufzeitpunkt.
     * @param endet neues Datum/Zeit
     */
    public void setEndet(LocalDateTime endet) {
        this.endet = endet;
    }

    @Override
    public String toString() {
        return "de.musab.todo.ToDo{" +
                "title='" + getTitle() + '\'' +
                ", beschreibung='" + getBeschreibung()+ '\'' +
                ", erledigt=" + isErledigt() +
                '\'' +
                ", endet=" + getEndet()+
                '}';
    }
}

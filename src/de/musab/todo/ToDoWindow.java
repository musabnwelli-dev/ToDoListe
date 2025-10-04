package de.musab.todo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
/**
 * Hauptfenster der de.musab.todo.ToDo-App basierend auf Swing.
 *
 * Bietet eine einfache Oberfläche zum Hinzufügen, Anzeigen, Abhaken
 * und Entfernen von ToDos (inklusive zeitlich begrenzter ToDos).
 */
public class ToDoWindow extends JFrame {
    private ToDoManager toDoManager;
    private JPanel toDoArea;

    /**
     * Erstellt das de.musab.todo.ToDo-Hauptfenster.
     *
     * @param toDoManager der zu verwendende de.musab.todo.ToDoManager
     */
    public ToDoWindow(ToDoManager toDoManager)
    {
        this.toDoManager = toDoManager;
        setTitle("de.musab.todo.ToDo App");
        setSize(500, 500);
        setLayout(new BorderLayout());
        add((toDoArea = createToDoArea()), BorderLayout.CENTER);
        add(createButtonRow(), BorderLayout.SOUTH);
        pack();

        addWindowListener(new CustomerWindowAdapter(toDoManager));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    /**
     * Erzeugt die Button-Leiste am unteren Fensterrand.
     * @return fertiges Panel mit Buttons
     */
    public JPanel createButtonRow()
    {
        final JPanel panel = new JPanel(new FlowLayout());
        final JButton addButton = new JButton("Hinzufügen");
        // Hinzufügen-Listener
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               final String titel = getInput("Gib einen Titel ein");
                if (titel.isEmpty()) return;

                final String beschreibung = getInput("Gib eine Beschreibung ein");
                if (beschreibung.isEmpty()) return;
                final String hours = getInput("In wievielen Stunden soll das de.musab.todo.ToDo ablaufen?");
                if (hours.isEmpty()) return;
                try
                {
                    final int hoursInteger = Integer.parseInt(hours);
                    if (hoursInteger == 0)
                    {
                        toDoManager.hinzufuegen(new ToDo(titel, beschreibung, false));

                    }
                    else {
                        toDoManager.hinzufuegen(new TimedToDo(titel, beschreibung, false, LocalDateTime.now().plusHours(hoursInteger)));
                    }

                }
                catch (Exception exception)
                {
                    toDoManager.hinzufuegen(new ToDo(titel, beschreibung, false));
                }
                // UI refresher
                remove(toDoArea);
                add((toDoArea = createToDoArea()), BorderLayout.CENTER);

                pack();
            }
        });

        final JButton removeAll = new JButton("Alle entfernen");

        removeAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toDoManager.getToDos().clear();
                remove(toDoArea);
                add((toDoArea = createToDoArea()), BorderLayout.CENTER);
                pack();
            }

        });

        final JButton removeCompletet = new JButton("Erledigte entfernen");

        removeCompletet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<ToDo> todo = toDoManager.getToDos();
                for (int i = todo.size() - 1; i >= 0; i--)
                {
                    if (todo.get(i).isErledigt())
                    {
                        toDoManager.entfernen(todo.get(i));
                    }
                    remove(toDoArea);
                    add((toDoArea = createToDoArea()), BorderLayout.CENTER);
                    pack();
                }
            }
        });




        panel.add(addButton);
        panel.add(removeAll);
        panel.add(removeCompletet);

        return panel;

    }

    /**
     * Baut einen UI-Block für ein einzelnes de.musab.todo.ToDo.
     *
     * @param toDo das darzustellende de.musab.todo.ToDo
     * @return Panel mit Titel, Beschreibung, Checkbox und ggf. Ablaufzeitpunkt
     */
    public JPanel createToDoBlock(ToDo toDo)
    {

        final JPanel panel = new JPanel(new BorderLayout());

       panel.add(new JLabel(toDo.getTitle()), BorderLayout.NORTH);
       panel.add(new JLabel(toDo.getBeschreibung()), BorderLayout.CENTER);

       JCheckBox checkBox = new JCheckBox();
       panel.add(checkBox, BorderLayout.EAST);
       checkBox.setSelected(toDo.isErledigt());
       checkBox.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               toDo.setErledigt(checkBox.isSelected());
           }
       });
       if (toDo instanceof TimedToDo)
       {
           panel.add(new JLabel(((TimedToDo) toDo).getEndet().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM))), BorderLayout.SOUTH);
       }

       return panel;

    }

    /**
     * Erzeugt den zentralen Bereich, in dem alle ToDos untereinander stehen.
     *
     * @return Panel mit einer vertikalen Liste der ToDos
     */
    public JPanel createToDoArea()
    {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        ArrayList<ToDo> todo = toDoManager.getToDos();
        for (int i = 0; i < todo.size(); i++)
        {
            panel.add(createToDoBlock(todo.get(i)));
        }
        return panel;
    }

    /**
     * Erzeugt den zentralen Bereich, in dem alle ToDos untereinander stehen.
     *
     * @return Panel mit einer vertikalen Liste der ToDos
     */
    public String getInput(String eingabeAufforderung)
    {
        while(true)
        {
            String input = JOptionPane.showInputDialog(eingabeAufforderung);
            if (input == null)
            {
                return "";
            }
            if (!input.isEmpty())
            {
                return input;
            }
        }
    }
}

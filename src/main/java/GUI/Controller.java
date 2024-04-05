package GUI;

import BusinessLogic.SimulationManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Controller implements ActionListener {

    View view;

    Controller(View view) {
        this.view = view;
    }

    ///public void actionPerformed(ActionEvent e)
    //Metoda din interfata ActionListener. Cand utilizatorul apasa butonul de „Start” al
    // simularii, datele sunt preluate din interfata grafica
    @Override
    public void actionPerformed(ActionEvent e) {

        String buton = e.getActionCommand();

        if (Objects.equals(buton, "Start")) {
            if (validInput()) {
                int numarGhisee = Integer.parseInt(view.campGhisee.getText());
                int numarPersoane = Integer.parseInt(view.campClienti.getText());
                int timpSosireMin = Integer.parseInt(view.camptimpSosireMin.getText());
                int timpSosireMax = Integer.parseInt(view.camptimpSosireMax.getText());
                int timpServireMin = Integer.parseInt(view.camptimpServireMin.getText());
                int timpServireMax = Integer.parseInt(view.camptimpServireMax.getText());
                int timpMaxim = Integer.parseInt(view.camptimpSimulareMax.getText());

                SimulationManager simulationManager = new SimulationManager(numarGhisee, numarPersoane, timpSosireMin,
                        timpSosireMax, timpServireMin, timpServireMax, timpMaxim, view);

                view.zonaDesfasurator.setText(simulationManager.infoActualDesfasurator());

                Thread thread = new Thread(simulationManager);
                thread.start();

            } else {
                view.zonaDesfasurator.setText("Intrari Invalide!");
            }
        }
    }

    boolean validInput() {
        return isInteger(view.campGhisee.getText()) &&
                isInteger(view.campClienti.getText()) &&
                isInteger(view.camptimpSosireMin.getText()) &&
                isInteger(view.camptimpSosireMax.getText()) &&
                isInteger(view.camptimpServireMin.getText()) &&
                isInteger(view.camptimpServireMax.getText()) &&
                isInteger(view.camptimpSimulareMax.getText());
    }

    boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

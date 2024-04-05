package BusinessLogic;

import GUI.View;
import Model.Server;
import Model.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimulationManager implements Runnable {
    ///date din GUI
    int timpMax;
    int timpSosireMin;
    int timpSosireMax;
    int timpServireMin;
    int timpServireMax;
    int numarGhisee;
    int numarClienti;
    double timpMediuAsteptare;
    double timpMediuServire;
    int perOra;
    String desfasuratorTotal;
    SelectionPolicy selectionPolicy;

    View view;
    //gestionarea cozilor de asteptare si distribuirea clientilor
    Scheduler scheduler;
    List<Task> generatedTasks;
    ///Clasa SimulationManager este responsabila cu stocarea datelor inserate de utilizator,
    // generarea celor N clienti folosing aceste date, si trimiterea clientilor in cozi cand este
    // timpul. Aceasta clasa stocheaza de asemenea log-ul, care este afisat in interfata
    // grafica sau in fisiere.

    public SimulationManager(int numarGhisee, int numarClienti, int timpSosireMin, int timpSosireMax,
                             int timpServireMin, int timpServireMax, int timpMax, View view) {
        this.numarGhisee = numarGhisee;
        this.numarClienti = numarClienti;
        this.timpSosireMin = timpSosireMin;
        this.timpSosireMax = timpSosireMax;
        this.timpServireMin = timpServireMin;
        this.timpServireMax = timpServireMax;
        this.timpMax = timpMax;
        this.view = view;

        this.selectionPolicy = SelectionPolicy.timeStrategy;
        this.timpMediuAsteptare = 0;
        this.timpMediuServire = 0;
        this.perOra = 0;
        this.desfasuratorTotal = "";
        this.generatedTasks = null;
        this.scheduler = new Scheduler(numarGhisee);

        generateNRandomTasks();
    }

    void generateNRandomTasks() {
        generatedTasks = new ArrayList<>();

        int timpAsteptareTotal = 0;
        int timpServireTotal = 0;

        for (int i = 0; i < numarClienti; i++) {
            Task t = new Task();
            t.ID = i + 1;
            t.timpSosire = timpSosireMin + (int) (Math.random() * (timpSosireMax - timpSosireMin));
            t.timpServire = timpServireMin + (int) (Math.random() * (timpServireMax - timpServireMin));
            generatedTasks.add(t);

            timpAsteptareTotal += t.timpSosire;
            timpServireTotal += t.timpServire;
        }
        Collections.sort(generatedTasks);

        timpMediuAsteptare = (double) timpAsteptareTotal / numarClienti;
        timpMediuServire = (double) timpServireTotal / numarClienti;
    }

    @Override
    public void run() {
        int timpCurent = 0;
        int perioadaAsteptareMaxima = 0;

        scheduler.runThreads();

        while (timpCurent < timpMax && !toateCozileTerminate()) {

            timpCurent++;
            String pasTimp = "\n Timp: " + timpCurent + "\n";

            List<Task> newGeneratedTasks = new ArrayList<>();

            for (Task t : generatedTasks) {
                if (t.timpSosire == timpCurent) {
                    // cand il pun in server nu vreau sa se decrementeze timpServire
                    t.timpServire++;
                    scheduler.dispatchTask(t);
                } else {
                    newGeneratedTasks.add(t);
                }
                generatedTasks = newGeneratedTasks;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int timpAsteptareTotalCurent = 0;

            for (Server s : scheduler.servers)
                timpAsteptareTotalCurent += s.perioadaAsteptare.get();


            if (perioadaAsteptareMaxima < timpAsteptareTotalCurent) {
                perioadaAsteptareMaxima = timpAsteptareTotalCurent;
                perOra = timpCurent;
            }

            pasTimp += infoActualDesfasurator();
            desfasuratorTotal += pasTimp;

            view.zonaDesfasurator.setText(desfasuratorTotal);
        }
        desfasuratorTotal += " Timp mediu de asteptare : " + timpMediuAsteptare + "\n";
        desfasuratorTotal += " Timp mediu de servire : " + timpMediuServire + "\n";
        desfasuratorTotal += " peak_h : " + perOra + "\n";

        view.zonaDesfasurator.setText(desfasuratorTotal);

        generareTxt(desfasuratorTotal);
    }

    boolean toateCozileTerminate() {
        if (!generatedTasks.isEmpty())
            return false;

        for (Server s : scheduler.servers)
            if (s.tasks.size() != 0)
                return false;

        return true;
    }

    public String infoActualDesfasurator() {
        String jurnal = "";
        jurnal += "Clientii care asteapta:\n";
        for (Task t : generatedTasks) {
            jurnal += t.toString() + " ";
        }
        jurnal += "\n";
        int numarGhiseu = 1;
        for (Server s : scheduler.servers) {
            jurnal += "Ghiseu " + numarGhiseu + ": ";
            if (s.tasks.isEmpty()) {
                jurnal += "inchis";
            } else {
                jurnal += s.toString();
            }

            numarGhiseu++;
            jurnal += "\n";
        }
        jurnal += "\n";

        return jurnal;
    }

    void generareTxt(String fullLog) {
        new File("creareOutputSimulare.txt");
        try {
            FileWriter fileWriter = new FileWriter("creareOutputSimulare.txt");
            fileWriter.write(fullLog);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

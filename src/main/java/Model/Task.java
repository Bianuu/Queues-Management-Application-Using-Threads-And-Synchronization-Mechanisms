package Model;

public class Task implements Comparable<Task> {
    public int ID;
    public int timpSosire;
    public int timpServire;

    public Task() {
        ID = 0;
        timpSosire = 0;
        timpServire = 0;
    }

    ///  Metoda din interfața Comparable<>. Aceasta este folosită pentru sortarea clienților(după
    ////generarea lor) în funcție de timpul de ajungere a fiecăruia
    @Override
    public int compareTo(Task obj) {
        return timpSosire - obj.timpSosire;
    }

    @Override
    public String toString() {
        return "{id:" + ID + ",tS:" + timpSosire + ",tA:" + timpServire + "}";
    }
}

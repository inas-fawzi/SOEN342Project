public class Sensor {
    private static double globalID = 0;
    private double ID;

    public Sensor(){
        this.ID = globalID;
        globalID++;
        //maintaining unique IDs for every sensor by using a static variable
        //which gets incremented with the creation of a new sensor
    }

    public String toString(){
        String id = String.valueOf(ID);
        return id;
    }
}

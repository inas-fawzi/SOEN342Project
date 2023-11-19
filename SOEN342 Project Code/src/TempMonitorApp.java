import java.util.ArrayList;
public class TempMonitorApp {
    public static void main(String[] args) throws Exception {
        //initalizing a TempMonitor object, note that this also creates all the possible sensors
        //to exist within the system
        TempMonitor tempMonitor = new TempMonitor();

        //get the list of sensors that exist
        ArrayList<Sensor> sensors = tempMonitor.getSensorList();

        System.out.println("\n *** Testing deploySensor method ***");
        System.out.println("...deploying a new sensor...");
        //deploy a sensor for the very frst time, at this point there are no deployed sensors so this must succeed
        tempMonitor.deploySensor(sensors.get(0), new Location("Constantinos Basement"), new Temperature());
        System.out.println("...deploying a sensor which has already been deployed...");
        //deploy the exact same sensor you just deployed, this should fail
        tempMonitor.deploySensor(sensors.get(0), new Location("Constantinos Basement"), new Temperature());
        System.out.println("...deploying a new sensor but the location has already been covered...");
        //deploy a different sensor but at the same location, this should fail
        tempMonitor.deploySensor(sensors.get(1), new Location("Constantinos Basement"), new Temperature());

        System.out.println("\n *** Testing readTemperature method ***");

        Location L1 = new Location("Denis' House");
        Location L2 = new Location("Place with no Sensor");

        Temperature T1 = new Temperature(20);
        Sensor S1 = sensors.get(2);
        
        tempMonitor.deploySensor(S1, L1, T1);
        System.out.println("...attempting to get the temperature of a location with a sensor...");
        tempMonitor.readTemperature(L1); //location with a sensor
        System.out.println("...attempting to get the temperature of a location with a no sensor...");
        tempMonitor.readTemperature(L2); //location with NO sensor

        // testing replaceSensor()
        System.out.println("\n *** Testing replaceSensor method ***");
        System.out.println("...Printing all existing sensors...");
        System.out.println(sensors);
        System.out.println("...Replacing the sensor with ID = 2...");
        tempMonitor.replaceSensor(sensors.get(2));
        System.out.println("...Printing all existing sensors...");
        System.out.println(sensors);

        // testing readAll()
        System.out.println("\n *** Testing readAll method ***");
        System.out.println(tempMonitor.readAll());

        // testing printReadAll()
        System.out.println("\n *** Testing printReadAll method ***");
        tempMonitor.printReadAll();

    }
}
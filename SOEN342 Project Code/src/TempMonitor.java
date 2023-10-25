import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TempMonitor {
    private ArrayList<Sensor> sensorList = new ArrayList<Sensor>();
    private ArrayList<Sensor> deployed = new ArrayList<Sensor>();
    private HashMap<Sensor, Location> map = new HashMap<Sensor, Location>();
    private HashMap<Sensor, Temperature> read = new HashMap<Sensor, Temperature>();

    //constructor of TempMonitor class, it is responsible for initalizing all the sensors
    //that will exist within our system, and adding them to the sensorList array list
    public TempMonitor(){
        sensorList.add(new Sensor());//1
        sensorList.add(new Sensor());//2
        sensorList.add(new Sensor());//3
        sensorList.add(new Sensor());//4
        sensorList.add(new Sensor());//5
        sensorList.add(new Sensor());//6
        sensorList.add(new Sensor());//7
        sensorList.add(new Sensor());//8
        sensorList.add(new Sensor());//9
        sensorList.add(new Sensor());//10
    }

    //returns the sensorList so it can be used in from the main() method in TempMonitorApp
    public ArrayList<Sensor> getSensorList(){
        return sensorList;
    }

    //this method is public since it gets called from the main() method
    public void deploySensor(Sensor sensor, Location location, Temperature temperature){
        //these strings store the outputs from the SensorAlreadyDeployed method and the
        //LocationAlreadyCovered method, since the output of those methods are simply Strings
        String sensorDeployed = SensorAlreadyDeployed(sensor);
        String locationCovered = LocationAlreadyCovered(location);

        //checking to see if the output of SensorAlreadyDeployed is "Sensor Deployed", in which  case
        //we must NOT deploy the sensor
        if (sensorDeployed.equals("Sensor Deployed")){
            System.out.println("The sensor could not be deployed because it is already in use");
        }
        //checking to see if the output of LocationAlreadyCovered is "Location already covered", in which  case
        //we must NOT deploy the sensor
        else if(locationCovered.equals("Location already covered")){
            System.out.println("The sensor could not be deployed because the location has already been covered");
        }
        //if the sensor has not yet been deployed and the location has not been covered yet, we can actually
        //deploy the sensor, which will be done by the DeploySensorOK method
        else{
            DeploySensorOK(sensor, location, temperature);
            System.out.println(Success());
        }
    }

    //is responsible for actually deploying a sensor
    private void DeploySensorOK(Sensor sensor, Location location, Temperature temperature){
        deployed.add(sensor);
        map.put(sensor, location);
        read.put(sensor, temperature);
    }

    //simply a success message
    private String Success(){
        return "ok (the sensor was deployed successfully)";
    }

    //checks if the location you wish to depploy a sensor to has already been covered
    private String LocationAlreadyCovered(Location location){
        //the address you wish to cover
        String address = location.toString();
        //initializing a string to contain addresses that already got covered, used in the for loop
        String existingAddress;
        //boolean to track if the address you wish to cover matches one that is already covered
        Boolean locationCovered = false;
        //comparing the address you wish to cover with all the addresses in the range of the map hashmap
        for (Location i : map.values()){
            existingAddress = i.toString();
            if (address.equals(existingAddress)){
                locationCovered = true;
                break;
            }
        }
        //if a matching address was found that means the location has already been covered
        if (locationCovered == true){
            return "Location already covered";
        }
        else{
            return "";
        }
    }


    // If given Location has been covered, print the temperature
    public void readTemperature(Location location) { // Done: input(Location) output(prints string)
        if (LocationAlreadyCovered(location) == "Location already covered") {
            String temperature = readTemperatureOK(location).toString();

            System.out.println(this.Success() + ": Temperature at this location is " + temperature);
        } else {
            System.out.println(locationUnknown(location));
        }
    }

    // Loop through the "map" HashMap to return the Temperature of a given Location
    private Temperature readTemperatureOK(Location location) {

        for (Map.Entry<Sensor, Location> entry : map.entrySet()) {
            if (entry.getValue().equals(location)) {
                Sensor sensor = entry.getKey();
                return read.get(sensor);
            }
        }
        return null;
    }

}

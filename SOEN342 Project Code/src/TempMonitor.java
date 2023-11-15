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
    //checks if  the sensor you wish to deploy has been deployed yet or not
    private String SensorAlreadyDeployed(Sensor sensor){
        //the id of the sensor you wwish to deploy
        String sensorID = sensor.toString();
        //initializing a string which will contain the id of already deployed sensors, it is used within the for loop
        String deployedSensorID = "";
        //this boolean will track if the sensor id you wish to deploy matches any deployed sensor ids
        Boolean sensorAlreadyDeployed = false;

        //goes through all the deployed sensor ids and compares them to the one you wish to deploy to check for a match
        for (int i = 0; i < deployed.size(); i++){
            deployedSensorID = deployed.get(i).toString();
            if (sensorID.equals(deployedSensorID)){
                sensorAlreadyDeployed = true;
                break;
            }
        }
        
        //if a match was found that means the sensor you wish to deploy is already deployed
        if (sensorAlreadyDeployed == true){
            return "Sensor Deployed";
        }
        else{
            return "";
        }
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
    
    //Will check if a location is not covered by a sensor and display a warning message
    private String locationUnknown (Location location){
        if(!map.containsValue(location)){
            return "Location not covered";
        }
        else {
            return "";
        }
    }

    public HashMap<Location, Temperature> readAll(){
        HashMap<Location, Temperature> readMap = new HashMap<Location, Temperature>();
        for (Sensor s : map.keySet()){
            Location l = map.get(s);
            Temperature t = null;

            for (Sensor s2 : read.keySet()){
                if (s2.equals(s)){
                    t = read.get(s2);
                }
            }
            
            readMap.put(l, t);
        }

        return readMap;
    }

    public void printReadAll(){
        HashMap<Location, Temperature> allLocTemp = this.readAll();

        System.out.println("\nLocation & Temperature");
        System.out.println("------------------------------------------");

        for(Location l : allLocTemp.keySet()){
            String loc = l.toString();
            String temp = allLocTemp.get(l).toString();

            System.out.println(String.format("%-25s || %s", loc, temp));
        }        
    }

    public void replaceSensor(Sensor oldsensor){
        if (!deployed.contains(oldsensor)){
            System.out.println("Sensor"+ oldsensor.toString() +"unknown");
        }
        else{
            //get location and temp for the new sensor
            Location address= map.get(oldsensor);
            Temperature temp= read.get(oldsensor);

            //remove old sensor
            map.remove(oldsensor);
            read.remove(oldsensor);
            deployed.remove(oldsensor);
            sensorList.remove(oldsensor);

            //create a new sensor and deploy it
            Sensor s = new Sensor();
            sensorList.add(s);
            deploySensor(s, address, temp);
        }
    }
}


import java.util.ArrayList;
import java.util.HashMap;

public class TempMonitor {
    private ArrayList<Sensor> sensorList = new ArrayList<Sensor>();
    private ArrayList<Sensor> deployed = new ArrayList<Sensor>();
    private HashMap<Sensor, Location> map = new HashMap<Sensor, Location>();
    private HashMap<Sensor, Temperature> read = new HashMap<Sensor, Temperature>();

    public TempMonitor(){
        
    } 
}

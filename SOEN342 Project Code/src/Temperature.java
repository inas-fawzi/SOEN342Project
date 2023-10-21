import java.util.Random;

public class Temperature {
    private int temperature;
    private String unit;

    public Temperature(){
        Random ran = new Random();
        this.temperature = ran.nextInt(100);
        this.unit = "degrees celsius";
    }

    public String toString(){
        return temperature + unit;
    }
}
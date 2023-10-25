public class Location {
    private String address;

    public Location(String location){
        this.address = location;
    }

    public String toString(){
        return this.address;
    }

    public Boolean equals(Location location){
        return this.address.equals(location.toString());
    }

}
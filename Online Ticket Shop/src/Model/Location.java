package Model;

public class Location {
    //private static int uniqueId = 1;
    private int idLocation;
    private String venue;
    private String country;
    private String city;
    private String locationName; //like "Sala Palatului", "TNB", "Opera Nationala Bucuresti"

    public Location() {
    //    this.idLocation = uniqueId;
    //    uniqueId++;
    }

    public Location(String venue, String country, String city, String locationName) {
    //    this.idLocation = uniqueId;
    //    uniqueId++;
        this.venue = venue;
        this.country = country;
        this.city = city;
        this.locationName = locationName;
    }

    public Location(int idLocation, String venue, String country, String city, String locationName) {
        this.idLocation = idLocation;
        this.venue = venue;
        this.country = country;
        this.city = city;
        this.locationName = locationName;
    }
/*
    public static int getUniqueId() {
        return uniqueId;
    }

    public static void setUniqueId(int uniqueId) {
        Location.uniqueId = uniqueId;
    }
*/
    public String getVenue() {

        return venue;
    }

    public void setVenue(String venue) {

        this.venue = venue;
    }

    public String getCountry() {

        return country;
    }

    public void setCountry(String country) {

        this.country = country;
    }

    public String getCity() {

        return city;
    }

    public void setCity(String city) {

        this.city = city;
    }

    public String getLocationName() {

        return locationName;
    }

    public void setLocationName(String locationName) {

        this.locationName = locationName;
    }

    public int getIdLocation() {

        return idLocation;
    }

    public void setIdLocation(int idLocation) {

        this.idLocation = idLocation;
    }

    @Override
    public String toString() {
        return "Location{" +
                "idLocation=" + idLocation +
                ", venue='" + venue + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", locationName='" + locationName + '\'' +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Location)) {
            return false;
        }
        Location loc = (Location) o;

        // Compare the data members and return accordingly
        return idLocation == loc.getIdLocation() && venue.equals(loc.getVenue()) && country.equals(loc.country)
                && city.equals(loc.getCity()) && locationName.equals(loc.getLocationName());
    }
}

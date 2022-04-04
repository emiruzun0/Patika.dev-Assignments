public class HomeAddress implements Address{

    private String neighbourhood;
    private String streetName;
    private String buildingName;
    private int floorNumber;
    private int flatNumber;
    private String postCode;     // not must
    private String town;         // not must
    private String district;     // not must
    private String province;
    private String country;
    private static int totalId = 1;
    private int id;


    public HomeAddress(String neighbourhood, String streetName, String buildingName, int floorNumber, int flatNumber,
                       String postCode, String town, String district, String province, String country) {

        this.neighbourhood = neighbourhood;
        this.streetName = streetName;
        this.buildingName = buildingName;
        this.floorNumber = floorNumber;
        this.flatNumber = flatNumber;
        this.postCode = postCode;
        this.town = town;
        this.district = district;
        this.province = province;
        this.country = country;
        this.id = totalId++;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        if (this.getClass().getName().equals("HomeAddress")) {
            HomeAddress homeAddress = (HomeAddress) obj;
            return (homeAddress.getId() != this.getId());
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        return this.id;
    }

    @Override
    public void printAddress() {

    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public int getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(int flatNumber) {
        this.flatNumber = flatNumber;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getId() {
        return id;
    }
}

package user;

public class Address {

    private String city;
    private String street;
    private String buldingNumber;
    private String zipCode;

    public Address(String city, String street, String buildingNumber, String zipCode) {
        this.city = city;
        this.street = street;
        this.buldingNumber = buildingNumber;
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuldingNumber() {
        return buldingNumber;
    }

    public void setBuldingNumber(String buldingNumber) {
        this.buldingNumber = buldingNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getZipCodeWithFormat() {
        return zipCode.substring(0, 2) + "-" + zipCode.substring(2);
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}

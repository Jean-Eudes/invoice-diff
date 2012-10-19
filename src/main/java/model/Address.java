package model;

public class Address {

    private String road1;
    private String road2;
    private String postalCode;
    private String city;
    private String country;

    public String getRoad1() {
        return road1;
    }

    public void setRoad1(String road1) {
        this.road1 = road1;
    }

    public String getRoad2() {
        return road2;
    }

    public void setRoad2(String road2) {
        this.road2 = road2;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        if (road1 != null) {
            sb.append(road1).append(" ");
        }
        if (road2 != null) {
            sb.append(road2).append(" ");
        }
        if (postalCode != null) {
            sb.append(postalCode).append(" ");
        }
        if (city != null) {
            sb.append(city);
        }
        if (country != null) {
            sb.append(country);
        }
        return sb.toString();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Josh
 */
public class Customer {
    private int customerId, addressId, cityId, countryId;
    private String customerName, address, address2, postalCode, phone,
            city, country;
    public Customer(int customerId, int addressId, int cityId, int countryId,
            String customerName, String address, String address2, String postalCode,
            String phone, String city, String country){
        this.customerId = customerId;
        this.addressId = addressId;
        this.cityId = cityId;
        this.countryId = countryId;
        this.customerName = customerName;
        this.address = address;
        this.address2 = address2;
        this.postalCode = postalCode;
        this.phone = phone;
        this.city = city;
        this.country = country;
    }
    
    public Customer(int customerId, String customerName){
        this.customerId = customerId;
        this.customerName = customerName;
    }
    
    public Customer(String address, String address2, String city, String postalCode, String country){
        this.address = address;
        this.address2 = address2;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }
    public int getCustomerId() {
        return customerId;
    }

    public int getAddressId() {
        return addressId;
    }

    public int getCityId() {
        return cityId;
    }

    public int getCountryId() {
        return countryId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getAddress() {
        return address;
    }

    public String getAddress2() {
        return address2;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPhone() {
        return phone;
    }
    
    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    
}

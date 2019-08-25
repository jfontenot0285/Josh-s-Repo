/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.UpdateRecordController;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Josh
 */
public class DataProvider {
    private static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    private static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    private static ObservableList<User> allUsers = FXCollections.observableArrayList();
    
    public static void addCustomer(Customer newCustomer){
        allCustomers.add(newCustomer);
    }

    public static void updateCustomer(int index, Customer selectedCustomer){
        allCustomers.set(index, selectedCustomer);
    }

    public static void deleteCustomer(Customer selectedCustomer){
        allCustomers.remove(selectedCustomer);
    }

    public static ObservableList<Customer> getAllCustomers(){
        return allCustomers;
    }
    
    public static void addAppointment(Appointment newAppointment){
        allAppointments.add(newAppointment);
    }

    public static void updateAppointment(int index, Appointment selectedAppointment){
        allAppointments.set(index, selectedAppointment);
    }

    public static void deleteAppointment(Appointment selectedAppointment){
        allAppointments.remove(selectedAppointment);
    }

    public static ObservableList<Appointment> getAllAppointments(){
        return allAppointments;
    }
    
    public static void addUser(User newUser){
        allUsers.add(newUser);
    }

    public static void updateUser(int index, User selectedUser){
        allUsers.set(index, selectedUser);
    }

    public static void deleteUser(User selectedUser){
        allUsers.remove(selectedUser);
    }

    public static ObservableList<User> getAllUsers(){
        return allUsers;
    }
    
    public static void updateLocalCache(){
        allCustomers.clear();
        allAppointments.clear();
        
        String getCustomersSqlStatement = "SELECT customer.customerId, customer.customerName, " +
                "address.addressId, address.address, address.address2, address.postalCode, " +
                "address.phone, city.cityId, city.city, country.countryId, country.country " +
                "FROM customer LEFT JOIN address ON customer.addressId = address.addressId " +
                "LEFT JOIN city ON address.cityId = city.cityId LEFT JOIN country "+
                "ON city.countryId = country.countryId";
        
        String getAppointmentsSqlStatement = "SELECT appointment.appointmentId, customer.customerId, "+
                "user.userId, appointment.title, appointment.description, appointment.location, "+
                "appointment.contact, appointment.type, appointment.start, appointment.end FROM appointment "+
                "LEFT JOIN customer ON appointment.customerId = customer.customerId RIGHT JOIN user ON "+
                "appointment.userId = user.userId;";
        
        String getUserSqlStatement = "SELECT userId FROM user;";
        
        Query.makeQuery(getCustomersSqlStatement);
        ResultSet customerResult = Query.getResult();
        
        Query.makeQuery(getAppointmentsSqlStatement);
        ResultSet appointmentResult = Query.getResult();
        
        Query.makeQuery(getUserSqlStatement);
        ResultSet userResult = Query.getResult();
        
        try {
            while(customerResult.next()){
                int customerId = customerResult.getInt("customerId");
                int addressId = customerResult.getInt("addressId");
                int cityId = customerResult.getInt("cityId");
                int countryId = customerResult.getInt("countryId");
                String customerName = customerResult.getString("customerName");
                String address = customerResult.getString("address");
                String address2 = customerResult.getString("address2");
                String postalCode = customerResult.getString("postalCode");
                String phone = customerResult.getString("phone");
                String city = customerResult.getString("city");
                String country = customerResult.getString("country");
                 
                Customer customer = new Customer(customerId, addressId, cityId, countryId,
                customerName, address, address2, postalCode, phone, city, country);
                addCustomer(customer);
                
            }
        } catch (SQLException ex) {
        Logger.getLogger(UpdateRecordController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            while(appointmentResult.next()){
                int appointmentId = appointmentResult.getInt("appointmentId");
                int customerId = appointmentResult.getInt("customerId");
                int userId = appointmentResult.getInt("userId");
                String title = appointmentResult.getString("title");
                String description = appointmentResult.getString("description");
                String location = appointmentResult.getString("location");
                String contact = appointmentResult.getString("contact");
                String type = appointmentResult.getString("type");
                String start = appointmentResult.getString("start");
                String end = appointmentResult.getString("end");
                  
                if (appointmentId != 0) { //checking for null entries
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
                    ZonedDateTime startLdt = LocalDateTime.parse(start, formatter)
                            .atOffset(ZoneOffset.UTC).atZoneSameInstant(ZoneId.of(ZoneOffset.systemDefault().toString()));
                    ZonedDateTime endLdt = LocalDateTime.parse(end, formatter)
                            .atOffset(ZoneOffset.UTC).atZoneSameInstant(ZoneId.of(ZoneOffset.systemDefault().toString()));
                    
                    DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    String localStart = customFormatter.format(startLdt);
                    String localEnd = customFormatter.format(endLdt);
                    
                    Appointment appointment = new Appointment(appointmentId, customerId,
                            userId, title, description, location, contact, type, localStart, localEnd);
                    addAppointment(appointment);
                }
                
            }
        } catch (SQLException ex) {
        Logger.getLogger(UpdateRecordController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            while(userResult.next()){
                int userId = userResult.getInt("userId");
                User user = new User(userId);
                addUser(user);
            }
        } catch (SQLException ex) {
        Logger.getLogger(UpdateRecordController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

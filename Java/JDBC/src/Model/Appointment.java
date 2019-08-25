/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.LocalTime;

/**
 *
 * @author Josh
 */
public class Appointment {
    private int customerId, appointmentId, userId, typeCount;
    String title, description, location, contact, type, startTime, endTime;
    
    public Appointment(int customerId, int appointmentId, int userId, String title,
            String description, String location, String contact, String type,
            String startTime, String endTime){
        this.appointmentId = appointmentId;
        this.customerId = customerId;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.contact = contact;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    public Appointment(int typeCount, String type, String startTime){
        this.typeCount = typeCount;
        this.type = type;
        this.startTime = startTime;
    }
    
    public Appointment(String startTime, String endTime){
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void setTypeCount(int typeCount) {
        this.typeCount = typeCount;
    }

    public int getTypeCount() {
        return typeCount;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getContact() {
        return contact;
    }

    public String getType() {
        return type;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
   
}

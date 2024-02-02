

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.HashMap;

/**
 *
 * @author phuct
 */
public class DoctorList {
    HashMap<String , Doctor> doctorMap;

    public DoctorList() {
    }

    public DoctorList(HashMap<String, Doctor> doctorMap) {
        this.doctorMap = doctorMap;
    }

    public HashMap<String, Doctor> getDoctorMap() {
        return doctorMap;
    }

    public void setDoctorMap(HashMap<String, Doctor> doctorMap) {
        this.doctorMap = doctorMap;
    }
    
    
}

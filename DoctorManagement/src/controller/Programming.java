/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import model.Doctor;
import model.DoctorList;
import view.Menu;

/**
 *
 * @author phuct
 */
public class Programming extends Menu {

    DoctorList doctorList = new DoctorList();
    Scanner sc = new Scanner(System.in);
    HashMap<String, Doctor> doctorMap;
    Doctor doctor = new Doctor();

    public Programming(String td, String[] mc) {
        super(td, mc);
        doctorMap = new HashMap<>();

    }

    @Override
    public void execute(int n) {
        switch (n) {
            case 1:
            try {
                addDoctor(doctorMap);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            break;
            case 2:
            try {
                updateDoctor(doctorMap, doctor);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            break;
            case 3:
            try {
                deleteDoctor(doctorMap, doctor);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            break;
            case 4:
               try {
                   searchDoctor(doctorMap);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            } 
            break;
            case 5:
                System.exit(0);
        }
    }

    public boolean addDoctor(HashMap<String, Doctor> doctorMap) throws Exception {
        if (doctorMap == null) {
            throw new Exception("Database does not exist");
        }
        System.out.println("--------- Add Doctor ----------");
        System.out.print("Enter code: ");
        String code = Validation.checkInputString();

        if (code == null || code.isEmpty()) {
            throw new Exception("Code does not exist");
        }
        if (doctorMap.containsKey(code)) {
            throw new Exception("Doctor code " + code + " is duplicate");
        }
        System.out.print("Enter name: ");
        String name = Validation.checkInputString();
        if (name == null || name.isEmpty()) {
            throw new Exception("Name does not exist");
        }
        System.out.print("Enter specialization: ");
        String specialization = Validation.checkInputString();
        if (specialization == null || specialization.isEmpty()) {
            throw new Exception("Specialization does not exist");
        }
        System.out.print("Enter availability: ");
        int availability = Validation.checkInputInt();
        Doctor doctor = new Doctor(code, name, specialization, availability);
        doctorMap.put(code, doctor);
        return true;
    }

    public static Doctor getDoctorByCode(HashMap<String, Doctor> doctorMap, String code) {
        for (Doctor doctor : doctorMap.values()) {
            if (doctor.getCode().equalsIgnoreCase(code)) {
                return doctor;
            }
        }
        return null;
    }

    public boolean updateDoctor(HashMap<String, Doctor> doctorMap, Doctor doctorToUpdate) throws Exception {
        if (doctorMap == null) {
            throw new Exception("Database does not exist");
        }

        if (doctorToUpdate == null) {
            throw new Exception("Data does not exist");
        }
        System.out.println("--------- Update Doctor -------");
        System.out.print("Enter code of the doctor you want to update: ");
        String code = sc.nextLine();

        if (!doctorMap.containsKey(code)) {
            throw new Exception("Doctor code doesn’t exist");
        }

        System.out.print("Enter new code: ");
        String newCode = Validation.checkInputString();
        System.out.print("Enter new name: ");
        String name = Validation.checkInputString();
        System.out.print("Enter new specialization: ");
        String specialization = Validation.checkInputString();
        System.out.print("Enter new availability: ");
        int availability = Validation.checkInputInt();
        if (newCode.equals(doctorToUpdate.getCode())
                && name.equals(doctorToUpdate.getName())
                && specialization.equals(doctorToUpdate.getSpecialization())
                && availability == doctorToUpdate.getAvailability()) {
            System.err.println("No change.");
            return false;
        }

        Doctor updatedDoctor = new Doctor(newCode, name, specialization, availability);
        doctorMap.put(newCode, updatedDoctor);
        doctorMap.remove(code);

        System.err.println("Update successful.");
        return true;
    }

    public boolean deleteDoctor(HashMap<String, Doctor> doctorMap, Doctor doctorToDelete) throws Exception {
        if (doctorMap == null) {
            throw new Exception("Database does not exist");
        }

        if (doctorToDelete == null) {
            throw new Exception("Data does not exist");
        }
        System.out.println("--------- Delete Doctor -------");
        System.out.print("Enter code: ");
        String code = Validation.checkInputString();
        if (!doctorMap.containsKey(code)) {
            throw new Exception("Doctor code doesn’t exist");
        } else {
            Doctor doctor = doctorMap.get(code);
            doctorMap.remove(code);
            return true;
        }
    }
    
    public static HashMap<String, Doctor> listFoundByName(HashMap<String, Doctor> doctorMap, String name) {
    HashMap<String, Doctor> listFoundByName = new HashMap<>();
    for (Map.Entry<String, Doctor> entry : doctorMap.entrySet()) {
        Doctor doctor = entry.getValue();
        if (doctor.getName().contains(name)) {
            listFoundByName.put(entry.getKey(), doctor);
        }
    }
    return listFoundByName;
}

    
public HashMap<String, Doctor> searchDoctor(HashMap<String, Doctor> doctorMap) throws Exception {
    if (doctorMap == null) {
        throw new Exception("Database does not exist");
    }
    System.out.println("---------- Search Doctor --------");
    System.out.print("Enter name: ");
    String nameSearch = Validation.checkInputString();
    HashMap<String, Doctor> result = listFoundByName(doctorMap, nameSearch);
    if (result.isEmpty()) {
        System.err.println("List empty.");
    } else {
        System.out.println("--------- Result ------------");
        System.out.printf("%-10s%-15s%-25s%-20s\n", "Code", "Name",
                "Specialization", "Availability");
        for (Map.Entry<String, Doctor> entry : result.entrySet()) {
            Doctor doctor = entry.getValue();
            System.out.printf("%-10s%-15s%-25s%-20d\n", doctor.getCode(),
                    doctor.getName(), doctor.getSpecialization(),
                    doctor.getAvailability());
        }
    }
    return result;
}

    
     
}
   
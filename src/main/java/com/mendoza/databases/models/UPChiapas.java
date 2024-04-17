package com.mendoza.databases.models;

public class UPChiapas {
    private ICRUD crud;

    public UPChiapas(ICRUD crud) {
        this.crud = crud;
    }

    public void agregarEstudiante(int id, String nombre, String apellido, int edad) {
        Student student = new Student(nombre, apellido, edad, id);
        this.crud.guardarStudent(student);
    }

    public void actualizarEstudiante(String nombre, String apellido, int id, int edad) {
        Student student = new Student(nombre, apellido, edad, id);
        this.crud.actualizarStudent(student);
    }
}


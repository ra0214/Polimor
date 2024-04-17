package com.mendoza.databases.models;

import java.util.Objects;

public class Student {
    private String nombre;
    private String apellido;
    private int edad;
    private int id;
    private ICRUD crud;

    public Student(String nombre, String apellido, int edad, int id) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return this.edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return "Student{nombre='" + this.nombre + "', apellido='" + this.apellido + "', id=" + this.id + "}";
    }

    public boolean equals(Object c) {
        if (this == c) {
            return true;
        } else if (c != null && this.getClass() == c.getClass()) {
            Student student = (Student)c;
            return this.id == student.id && Objects.equals(this.nombre, student.nombre) && Objects.equals(this.apellido, student.apellido);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.nombre, this.apellido, this.id});
    }
}


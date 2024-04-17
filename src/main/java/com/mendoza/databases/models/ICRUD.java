package com.mendoza.databases.models;

import java.util.ArrayList;

public interface ICRUD {
    void guardarStudent(Student var1);

    void actualizarStudent(Student var1);

    ArrayList<Student> getStudents();
}

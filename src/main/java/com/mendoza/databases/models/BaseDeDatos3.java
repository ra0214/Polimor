package com.mendoza.databases.models;

import java.util.ArrayList;
import java.util.Iterator;

public class BaseDeDatos3 implements ICRUD {
    private ArrayList<Student> students3 = new ArrayList();

    public BaseDeDatos3() {
    }

    public void guardarStudent(Student student) {
        this.students3.add(student);
    }

    public void actualizarStudent(Student student) {
        Iterator var2 = this.students3.iterator();

        Student e;
        do {
            if (!var2.hasNext()) {
                return;
            }

            e = (Student)var2.next();
        } while(e.getId() != student.getId());

        e.setNombre(student.getNombre());
        e.setApellido(student.getApellido());
    }

    public void printStudents() {
        System.out.println("Estudiantes en Database01:");
        Iterator var1 = this.students3.iterator();

        while(var1.hasNext()) {
            Student s = (Student)var1.next();
            System.out.println(s);
        }

    }

    public ArrayList<Student> getStudents() {
        return new ArrayList(this.students3);
    }
}

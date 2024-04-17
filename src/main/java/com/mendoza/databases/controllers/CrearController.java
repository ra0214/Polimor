package com.mendoza.databases.controllers;

import com.mendoza.databases.models.BaseDeDatos1;
import com.mendoza.databases.models.BaseDeDatos2;
import com.mendoza.databases.models.BaseDeDatos3;
import com.mendoza.databases.models.ICRUD;
import com.mendoza.databases.models.Student;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class CrearController {
    @FXML
    private Button agregarBtn;
    @FXML
    private TextField apellidoTxt;
    @FXML
    private TextField edadTxt;
    @FXML
    private TextField idTxt;
    @FXML
    private TextField nomTxt;
    private Student student;
    private ArrayList<ICRUD> baseDatos;

    public CrearController() {
    }

    public void initialize() {
        this.baseDatos = new ArrayList();
        this.baseDatos.add(new BaseDeDatos1());
        this.baseDatos.add(new BaseDeDatos2());
        this.baseDatos.add(new BaseDeDatos3());
    }

    public void initAtributos(Student student) {
        this.student = student;
        if (student != null) {
            this.nomTxt.setText(student.getNombre());
            this.apellidoTxt.setText(student.getApellido());
            this.edadTxt.setText(String.valueOf(student.getEdad()));
            this.idTxt.setText(String.valueOf(student.getId()));
        }

    }

    @FXML
    void OnActionAgregarBtn(ActionEvent event) {
        try {
            String nombre = this.nomTxt.getText();
            String apellidos = this.apellidoTxt.getText();
            int edad = Integer.parseInt(this.edadTxt.getText());
            int id = Integer.parseInt(this.idTxt.getText());
            Student nuevoStudent = new Student(nombre, apellidos, edad, id);
            boolean exists = false;
            Iterator var8 = this.baseDatos.iterator();

            ICRUD baseDato;
            while(var8.hasNext()) {
                baseDato = (ICRUD)var8.next();
                if (baseDato.getStudents().contains(nuevoStudent)) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                if (this.student != null) {
                    this.student.setNombre(nombre);
                    this.student.setApellido(apellidos);
                    this.student.setEdad(edad);
                    this.student.setId(id);
                    var8 = this.baseDatos.iterator();

                    while(var8.hasNext()) {
                        baseDato = (ICRUD)var8.next();
                        baseDato.guardarStudent(this.student);
                    }
                } else {
                    this.student = nuevoStudent;
                    var8 = this.baseDatos.iterator();

                    while(var8.hasNext()) {
                        baseDato = (ICRUD)var8.next();
                        baseDato.guardarStudent(this.student);
                    }
                }

                this.cerrar();
            } else {
                this.mostrarAlerta("La tarea ya existe en una de las bases de datos.");
            }
        } catch (NumberFormatException var10) {
            this.mostrarAlerta("El ID debe ser un número entero.");
        }

    }

    private void cerrar() {
        Stage stage = (Stage)this.agregarBtn.getScene().getWindow();
        stage.close();
    }

    public void initAttributes(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return this.student;
    }

    private void mostrarAlerta(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setHeaderText((String)null);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }
}

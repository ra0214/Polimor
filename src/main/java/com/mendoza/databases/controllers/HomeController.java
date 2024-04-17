package com.mendoza.databases.controllers;

import com.mendoza.databases.models.BaseDeDatos1;
import com.mendoza.databases.models.BaseDeDatos2;
import com.mendoza.databases.models.BaseDeDatos3;
import com.mendoza.databases.models.ICRUD;
import com.mendoza.databases.models.Student;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HomeController {
    @FXML
    private Button agregarBtn;
    @FXML
    private TableColumn<Student, String> apeliidoColumna;
    @FXML
    private TableColumn<Student, Integer> edadColumna;
    @FXML
    private Button eliminarBtn;
    @FXML
    private TableColumn<Student, Integer> idColumna;
    @FXML
    private TableColumn<Student, String> nombreColumna;
    @FXML
    private Button salirBtn;
    @FXML
    private TableView<Student> tableView;
    private ObservableList<Student> listaStudent = FXCollections.observableArrayList();
    private ArrayList<ICRUD> baseDatos = new ArrayList();
    private BaseDeDatos1 baseDeDatos1;
    private BaseDeDatos2 baseDeDatos2;
    private BaseDeDatos3 baseDeDatos3;

    public HomeController() {
    }

    @FXML
    public void initialize() {
        this.baseDeDatos1 = new BaseDeDatos1();
        this.baseDeDatos2 = new BaseDeDatos2();
        this.baseDeDatos3 = new BaseDeDatos3();
        this.baseDatos.add(this.baseDeDatos1);
        this.baseDatos.add(this.baseDeDatos2);
        this.baseDatos.add(this.baseDeDatos3);
        this.idColumna.setCellValueFactory((cellData) -> {
            return (new SimpleIntegerProperty(((Student)cellData.getValue()).getId())).asObject();
        });
        this.nombreColumna.setCellValueFactory((cellData) -> {
            return new SimpleStringProperty(((Student)cellData.getValue()).getNombre());
        });
        this.apeliidoColumna.setCellValueFactory((cellData) -> {
            return new SimpleStringProperty(((Student)cellData.getValue()).getApellido());
        });
        this.edadColumna.setCellValueFactory((cellData) -> {
            return (new SimpleIntegerProperty(((Student)cellData.getValue()).getEdad())).asObject();
        });
        this.cargarDatos();
    }

    private void cargarDatos() {
        Iterator var1 = this.baseDatos.iterator();

        while(var1.hasNext()) {
            ICRUD baseDato = (ICRUD)var1.next();
            this.listaStudent.addAll(baseDato.getStudents());
        }

        this.tableView.setItems(this.listaStudent);
    }

    @FXML
    void OnActionAgregarBtn(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/com/mendoza/databases/crear-view.fxml"));
            Parent root = (Parent)loader.load();
            CrearController controller = (CrearController)loader.getController();
            controller.initAttributes((Student)null);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            scene.getStylesheets().add(this.getClass().getResource("/Style.css").toExternalForm());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            Student student = controller.getStudent();
            if (student != null) {
                this.listaStudent.add(student);
                this.guardarBase(student);
                this.tableView.refresh();
            }
        } catch (IOException var8) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText((String)null);
            alert.setTitle("Error");
            alert.setContentText("Error al guardar");
            alert.showAndWait();
        }

    }

    @FXML
    void OnActionEliminarBtn(ActionEvent event) {
        Student seleccionar = (Student)this.tableView.getSelectionModel().getSelectedItem();
        if (seleccionar == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText((String)null);
            alert.setTitle("Error");
            alert.setContentText("Debe seleccionar un estudiante");
            alert.showAndWait();
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/com/mendoza/databases/crear-view.fxml"));
                Parent root = (Parent)loader.load();
                CrearController controller = (CrearController)loader.getController();
                controller.initAttributes(seleccionar);
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                scene.getStylesheets().add(this.getClass().getResource("/Style.css").toExternalForm());
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();
                Student modifiedStudent = controller.getStudent();
                if (modifiedStudent != null) {
                    this.updateBase(seleccionar, modifiedStudent);
                    this.tableView.refresh();
                }
            } catch (IOException var9) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText((String)null);
                alert.setTitle("Error");
                alert.showAndWait();
            }
        }

    }

    @FXML
    void OnMouseClickSalirBtn(MouseEvent event) {
        System.exit(1);
    }

    private void guardarBase(Student student) {
        Iterator var2 = this.baseDatos.iterator();

        while(var2.hasNext()) {
            ICRUD database = (ICRUD)var2.next();
            database.guardarStudent(student);
        }

    }

    private void updateBase(Student modifiedStudent, Student student) {
        this.baseDeDatos1.actualizarStudent(student);
        this.baseDeDatos2.actualizarStudent(student);
        this.baseDeDatos3.actualizarStudent(student);
        this.baseDeDatos1.printStudents();
        this.baseDeDatos2.printStudents();
        this.baseDeDatos3.printStudents();
    }
}

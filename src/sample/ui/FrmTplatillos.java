package sample.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.models.TipoPlatilloDAO;

public class FrmTplatillos extends Stage {

    private TextField txtTiplatillo;
    private Button btnGuardar;
    private VBox vBox;
    private Scene escena;
    private TipoPlatilloDAO objTiplatillo;
    private TableView<TipoPlatilloDAO>tbvTiplatillo;

    public FrmTplatillos(TableView<TipoPlatilloDAO> tbvTiplatillo, TipoPlatilloDAO objTiplatillo){

        if(objTiplatillo!=null){
            this.objTiplatillo=objTiplatillo;
        }else{
            this.objTiplatillo = new TipoPlatilloDAO();
        }
        CrearUI();
        this.setTitle("Gestión de Platillos");
        this.setScene(escena);
        this.show();

        this.tbvTiplatillo=tbvTiplatillo;

    }

    private void CrearUI() {
        txtTiplatillo = new TextField();
        txtTiplatillo.setText(objTiplatillo.getDsc_tipo());

        btnGuardar = new Button("Guardar Platillo");
        btnGuardar.setOnAction(event -> Guardar());
        vBox = new VBox();
        vBox.getChildren().addAll(txtTiplatillo,btnGuardar);
        escena = new Scene(vBox,250,250);
    }

    private void Guardar() {

        objTiplatillo.setDsc_tipo(txtTiplatillo.getText());
        //se cambio la comparacion del contenido del contenido del objeto con la comparacion
        //de si existe dentro de la BD
        if( objTiplatillo.getTipo() == null ) { // PROCESO DE NUEVO TIPOPLATILLO
            objTiplatillo.insTipo();
            System.out.println("soy el insertar we");
        }else {                     // PROCESO PARA ACTUALIZAR EL TIPOPLATILLO
            objTiplatillo.udpTipo();
            System.out.println("Quizas el update we");
        }

        tbvTiplatillo.setItems(objTiplatillo.getAllTipo());
        tbvTiplatillo.refresh();
        this.close();
    }
}

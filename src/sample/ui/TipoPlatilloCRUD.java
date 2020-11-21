package sample.ui;

import components.ButtonCustome;
import components.ButtonCustometype;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.models.TipoPlatilloDAO;

public class TipoPlatilloCRUD extends Stage {

    private VBox vBox;
    private TableView<TipoPlatilloDAO> tbvTiplatillos;
    private Button btnNuevo;
    private Scene escena;
    private TipoPlatilloDAO objTiplatillos;

    public TipoPlatilloCRUD() {

        objTiplatillos = new TipoPlatilloDAO();
        CrearUI();
        this.setTitle("Administracion de platillos");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        tbvTiplatillos = new TableView<>();
        CrearTabla();
        btnNuevo = new Button("Nuevo Platillo");
        btnNuevo.setOnAction(event -> { new FrmTplatillos(tbvTiplatillos,null); });
        vBox = new VBox();
        vBox.getChildren().addAll(tbvTiplatillos,btnNuevo);
        escena = new Scene(vBox,350,250);
    }

    private void CrearTabla() {
        TableColumn<TipoPlatilloDAO, Integer> tbcIdTipo = new TableColumn<>("ID");
        tbcIdTipo.setCellValueFactory(new PropertyValueFactory<>("id_tipo"));

        TableColumn<TipoPlatilloDAO, String>tbcDscTipo = new TableColumn<>("Platillo");
        tbcDscTipo.setCellValueFactory(new PropertyValueFactory<>("dsc_tipo"));

        TableColumn<TipoPlatilloDAO, String>tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<TipoPlatilloDAO, String>, TableCell<TipoPlatilloDAO, String>>() {
                    @Override
                    public TableCell<TipoPlatilloDAO, String> call(TableColumn<TipoPlatilloDAO, String> param) {
                        return new ButtonCustometype(1);
                    }
                }
        );

        TableColumn<TipoPlatilloDAO, String>tbcBorrar = new TableColumn<>("Borrar");
        tbcBorrar.setCellFactory(
                new Callback<TableColumn<TipoPlatilloDAO, String>, TableCell<TipoPlatilloDAO, String>>() {
                    @Override
                    public TableCell<TipoPlatilloDAO, String> call(TableColumn<TipoPlatilloDAO, String> param) {
                        return new ButtonCustometype(2);
                    }
                }
        );

        tbvTiplatillos.getColumns().addAll(tbcIdTipo,tbcDscTipo,tbcEditar,tbcBorrar);
        tbvTiplatillos.setItems(objTiplatillos.getAllTipo());
        tbvTiplatillos.setItems(objTiplatillos.getAllTipo());
    }
}


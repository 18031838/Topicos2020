package components;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import sample.models.TipoPlatilloDAO;
import sample.ui.FrmTplatillos;

import java.util.Optional;

public class ButtonCustometype extends TableCell<TipoPlatilloDAO,String> {

    private Button btnCelda;
    private TipoPlatilloDAO objTiplatillo;

    public ButtonCustometype(int opc){
        if (opc==1){
            btnCelda=new Button("Editar");
            btnCelda.setOnAction(event -> {

                objTiplatillo = ButtonCustometype.this.getTableView().getItems().get(ButtonCustometype.this.getIndex());
                new FrmTplatillos(ButtonCustometype.this.getTableView(),objTiplatillo);
            });
        }

        else{
            btnCelda=new Button("Borrar");
            btnCelda.setOnAction(event -> {

                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Mensaje del sistema");
                alerta.setHeaderText("Condirmación de tu acción");
                alerta.setContentText("¿Estas seguro de borrarlo?");
                Optional<ButtonType> result = alerta.showAndWait();
                if(result.get()==ButtonType.OK) {
                    //este te regresa los datos del objeto de esa linea
                    //el primer get llama la tableview, el segundo la lista desplegable y el tercero los datos
                    objTiplatillo = ButtonCustometype.this.getTableView().getItems().get(ButtonCustometype.this.getIndex());
                    //Executa la consulta de borrado
                    objTiplatillo.delTipo();

                    //Vuelve a realizar la consulta para saber el contenido de la tabla
                    ButtonCustometype.this.getTableView().setItems(objTiplatillo.getAllTipo());
                    //Refresca la ventana mostrando el contenido
                    ButtonCustometype.this.getTableView().refresh();
                }
            });
        }
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if(!empty)
            setGraphic(btnCelda);
    }
}

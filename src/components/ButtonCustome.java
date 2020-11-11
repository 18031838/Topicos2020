package components;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import sample.models.PlatillosDAO;

import java.util.Optional;

//< >
public class ButtonCustome extends TableCell<PlatillosDAO,String> {

    private Button btnCelda;
    private PlatillosDAO objPlatillo;

    public ButtonCustome(int opc){
        if (opc==1){
            btnCelda=new Button("Editar");
            btnCelda.setOnAction(event -> {

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
                    objPlatillo = ButtonCustome.this.getTableView().getItems().get(ButtonCustome.this.getIndex());
                    //Executa la consulta de borrado
                    objPlatillo.delPlatillo();

                    //Vuelve a realizar la consulta para saber el contenido de la tabla
                    ButtonCustome.this.getTableView().setItems(objPlatillo.getAllPlatillo());
                    //Refresca la ventana mostrando el contenido
                    ButtonCustome.this.getTableView().refresh();
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

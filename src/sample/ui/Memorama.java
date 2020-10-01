package sample.ui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.events.EventosMemorama;

public class Memorama extends Stage implements EventHandler {


    private String[] arIma= {"cocalata.jpg","fantalata.png","pepsilata.png","sevenup.jpg","manzanita.jpg"};
    private Label lblTarjetas;
    private TextField txtNoTarjetas;
    private Button btnAceptar;
    private HBox hBox;
    private VBox vBox;
    private GridPane gdpMesa;
    private Scene escena;
    private int nPares;
    private Button[][] arTarjetas;
    private String[][] arAsignacion;

    public Memorama(){

        CrearUI();
        this.setTitle("Memorama :D");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        lblTarjetas = new Label("Número de pares");
        txtNoTarjetas = new TextField();
        btnAceptar = new Button("Voltear y revolver");
        btnAceptar.addEventHandler(MouseEvent.MOUSE_CLICKED,this);
        //btnAceptar.addEventHandler(MouseEvent.MOUSE_CLICKED,new EventosMemorama());
        /*btnAceptar.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Terver evento desde una clase anonima");
            }
        });*/


        hBox = new HBox();
        hBox.getChildren().addAll(lblTarjetas,txtNoTarjetas,btnAceptar);
        hBox.setSpacing(10);

        gdpMesa = new GridPane();
        vBox = new VBox();
        vBox.getChildren().addAll(hBox,gdpMesa);

        escena = new Scene(vBox, 500, 500);
    }

    @Override
    public void handle(Event event) {
        //System.out.println("Este es mi primer evento");
        nPares = Integer.parseInt(txtNoTarjetas.getText());

        vBox.getChildren().remove(gdpMesa);
        gdpMesa =new GridPane();
        arAsignacion = new String[2][nPares];
        arTarjetas = new Button[2][nPares];

        for(int i=0;i<2;i++){
            for(int j=0;j<nPares;j++){

                Image img=new Image("sample/assets/fondorojo.jpg");
                ImageView inv=new ImageView(img);
                inv.setFitHeight(80);
                inv.setPreserveRatio(true);

                arTarjetas[i][j]=new Button();
                int finalI = i;
                int finalJ = j;
                arTarjetas[i][j].setOnAction(event1->verTarjeta(finalI, finalJ));
                arTarjetas[i][j].setGraphic(inv);
                arTarjetas[i][j].setPrefSize(30,80);
                gdpMesa.add(arTarjetas[i][j],j,i);

            }
        }

        revolver();
        vBox.getChildren().add(gdpMesa);
    }

    private void verTarjeta(int finalI,int finalJ) {

        Image img = new Image("sample/assets/"+arAsignacion[finalI][finalJ]);
        ImageView imv = new ImageView(img);
        imv.setFitHeight(120);
        imv.setPreserveRatio(true);

        arTarjetas[finalI][finalJ].setGraphic(imv);
    }

    private void revolver() {
        int posx,posy,cont=0;

        for(int i=0;i<2;i++){
            for(int j=0;j<nPares;j++){
                arAsignacion[i][j]= new String();
            }
        }

        for(int i=0;i<nPares;){
            posx = (int)(Math.random()*2);
            posy = (int)(Math.random()*nPares);
            if(arAsignacion[posx][posy].equals("")) {
                arAsignacion[posx][posy] = arIma[i];
                cont++;
            }
            if(cont==2){
                i++;
                cont=0;
            }
        }
    }
}

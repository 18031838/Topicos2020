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


    private String[] arIma= {"cocalata.jpg","fantalata.png","pepsilata.png","sevenup.jpg","manzanita.jpg","dr.pepper.png"};
    private String[] NumIma;
    private Label lblTarjetas;
    private Label lblNintentos;
    private Label lblNintentos2;
    private TextField txtNoTarjetas;
    private Button btnAceptar;
    private HBox hBox;
    private VBox vBox;
    private GridPane gdpMesa;
    private Scene escena;
    private int nPares;
    private Button[][] arTarjetas;
    private String[][] arAsignacion;
    private String[] intentos;

    public Memorama(){
        //Se manda a llamar los metodos que correra el programa
        CrearUI();
        this.setTitle("Memorama :D");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        //se validan los elementos a usar en la ui
        lblTarjetas = new Label("Número de pares");
        txtNoTarjetas = new TextField();
        btnAceptar = new Button("Voltear y revolver");
        lblNintentos=new Label("Número de intentos");
        lblNintentos2=new Label();
        btnAceptar.addEventHandler(MouseEvent.MOUSE_CLICKED,this);

        //se crea el hBox para poder contener y manipular el espacio y diseño de los
        //elementos a usar
        hBox = new HBox();
        hBox.setFillHeight(false);
        hBox.getChildren().addAll(lblTarjetas,txtNoTarjetas,btnAceptar,lblNintentos,lblNintentos2);
        hBox.setSpacing(10);

        gdpMesa = new GridPane();
        vBox = new VBox();
        vBox.getChildren().addAll(hBox,gdpMesa);

        escena = new Scene(vBox,500,500);
    }

    @Override
    public void handle(Event event) {
        nPares = Integer.parseInt(txtNoTarjetas.getText());

        vBox.getChildren().remove(gdpMesa);
        gdpMesa =new GridPane();
        arAsignacion = new String[2][nPares];
        arTarjetas = new Button[2][nPares];

        for(int i=0;i<2;i++){
            for(int j=0;j<nPares;j++){

                Image img=new Image("sample/assets/fondorojo.jpg");
                ImageView inv=new ImageView(img);
                inv.setFitHeight(60);
                inv.setPreserveRatio(true);

                arTarjetas[i][j]=new Button();
                int finalI = i;
                int finalJ = j;
                arTarjetas[i][j].setOnAction(event1->verTarjeta(finalI, finalJ));
                arTarjetas[i][j].setGraphic(inv);
                arTarjetas[i][j].setPrefSize(30,30);
                gdpMesa.add(arTarjetas[i][j],j,i);
                numeroIntentos();
            }
        }

        revolver();
        vBox.getChildren().add(gdpMesa);
    }

    private void numeroIntentos() {
        int intentos=0;
        intentos++;
        String inten=Integer.toString(intentos);
        lblNintentos2.setText(inten);
    }

    private void verTarjeta(int finalI,int finalJ) {
        Image img = new Image("sample/assets/"+arAsignacion[finalI][finalJ]);
        ImageView imv = new ImageView(img);
        imv.setFitHeight(60);
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

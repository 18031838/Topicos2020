package sample.ui;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Taquimecanografo extends Stage implements EventHandler<KeyEvent> {

    //Bandera para detectar cambio de color en las teclas
    boolean banColor = false;

    //Arreglos para etiquetar los botones del tecaldo
    private String arLbBtn1[]= {"ESC","F1","F2","F3","F4","F5","F6","F7","F8","F9","F10","F11","F12","PWR"};
    private String arLbBtn2[]= {"|","1","2","3","4","5","6","7","8","9","0","'","¿","BS"};
    //Elementos para el toolbar
    private ToolBar tlbMenu;
    private Button btnAbrir;

    //Elementos para la escritura
    private TextArea txtContenido,txtEscritura;

    //Elementos para el teclado
    private HBox[] arHBoxTeclas= new HBox[6];
    private VBox VBoxTeclado;
    private Button[] arBtnTeclado1= new Button[14];
    private Button[] arBtnTeclado2= new Button[14];

    //Elementos de agrupación global
    private VBox VBoxPrincipal;
    private Scene escena;


    public Taquimecanografo(){

        CrearUI();
        this.setTitle("Tutor de Taquimecanografia");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        CrearTooblar();
        CrearEscritura();
        CrearTeclado();

        VBoxPrincipal = new VBox();
        VBoxPrincipal.getChildren().addAll(tlbMenu, txtContenido, txtEscritura,VBoxTeclado);
        VBoxPrincipal.setSpacing(10);
        VBoxPrincipal.setPadding(new Insets(10));
        escena = new Scene(VBoxPrincipal, 800,500);
    }

    private void CrearTeclado() {
        VBoxTeclado= new VBox();
        VBoxTeclado.setSpacing(10);
        for(int i=0;i<arHBoxTeclas.length;i++){
            arHBoxTeclas[i]= new HBox();
            arHBoxTeclas[i].setSpacing(10);
        }
        for(int i=0; i< arBtnTeclado1.length;i++){
            arBtnTeclado1[i]= new Button(arLbBtn1[i]);
            arBtnTeclado2[i]= new Button(arLbBtn2[i]);
            arHBoxTeclas[0].getChildren().add(arBtnTeclado1[i]);
            arHBoxTeclas[1].getChildren().add(arBtnTeclado2[i]);
        }
        VBoxTeclado.getChildren().addAll(arHBoxTeclas[0],arHBoxTeclas[1]);
    }

    private void CrearEscritura() {
        txtContenido = new TextArea();
        txtContenido.setEditable(false);
        txtContenido.setPrefRowCount(6);
        txtEscritura = new TextArea();
        txtContenido.setPrefRowCount(6);
        txtEscritura.addEventHandler(KeyEvent.KEY_TYPED,this);
    }

    private void CrearTooblar() {
        tlbMenu = new ToolBar();
        btnAbrir = new Button();
        btnAbrir.setOnAction(event -> eventoTaqui(1));
        btnAbrir.setPrefSize(35,35);

        Image img =new Image("sample/assets/archivoabierto.png");
        ImageView imv = new ImageView(img);
        imv.setFitHeight(16);
        imv.setPreserveRatio(true);
        btnAbrir.setGraphic(imv);
        tlbMenu.getItems().addAll(btnAbrir);
    }

    private void eventoTaqui(int opc) {
        switch (opc){
            case 1:
                FileChooser filechooser = new FileChooser();
                filechooser.setTitle("Abrir archivo");
                File file = filechooser.showOpenDialog(this);
        }
    }

    @Override
    public void handle(KeyEvent event) {
        switch (event.getCode().toString()){
            case "BACK_SPACE":
                if( banColor == false )
                    arBtnTeclado2[14].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado2[14].setStyle("-fx-background-color: #85D4D6;");
                break;
        }

        banColor = !banColor;

    }
}

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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.SQLOutput;

public class Taquimecanografo extends Stage implements EventHandler<KeyEvent> {

    //Bandera para detectar cambio de color en las teclas
    boolean banColor = false;

    //Arreglos para etiquetar los botones del tecaldo
    private String arLbBtn1[]= {"ESC","F1","F2","F3","F4","F5","F6","F7","F8","F9","F10","F11","F12","Print"};
    private String arLbBtn2[]= {"|","1","2","3","4","5","6","7","8","9","0","'","¿","BS"};
    private String arLbBtn3[]= {"tab","q","w","e","r","t","y","u","i","o","p","´","+","enter"};
    private String arLbBtn4[]= {"Mayus","a","s","d","f","g","h","j","k","l","ñ","{","}"};
    private String arLbBtn5[]= {"lshift","<","z","x","c","v","b","n","m",",",".","-","rshift","up"};
    private String arLbBtn6[]= {"ctrl","fn","windows","alt","space","altgr","docu","ctrl","le","down","rig"};
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
    private Button[] arBtnTeclado3= new Button[14];
    private Button[] arBtnTeclado4= new Button[13];
    private Button[] arBtnTeclado5= new Button[14];
    private Button[] arBtnTeclado6= new Button[11];

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
        //Para los arreglos con 14 botones
        for(int i=0; i< arBtnTeclado1.length;i++){
            arBtnTeclado1[i]= new Button(arLbBtn1[i]);
            arBtnTeclado1[i].setStyle("-fx-background-color: #0AECCD");
            arBtnTeclado2[i]= new Button(arLbBtn2[i]);
            arBtnTeclado2[i].setStyle("-fx-background-color: #0AECCD");
            arBtnTeclado3[i]= new Button(arLbBtn3[i]);
            arBtnTeclado3[i].setStyle("-fx-background-color: #0AECCD");
            arBtnTeclado5[i]= new Button(arLbBtn5[i]);
            arBtnTeclado5[i].setStyle("-fx-background-color: #0AECCD");

            arHBoxTeclas[0].getChildren().add(arBtnTeclado1[i]);
            arHBoxTeclas[1].getChildren().add(arBtnTeclado2[i]);
            arHBoxTeclas[2].getChildren().add(arBtnTeclado3[i]);
            arHBoxTeclas[4].getChildren().add(arBtnTeclado5[i]);
        }
        //Arreglo con 13 botones
        for (int i=0;i<arBtnTeclado4.length;i++){
            arBtnTeclado4[i]= new Button(arLbBtn4[i]);
            arBtnTeclado4[i].setStyle("-fx-background-color: #0AECCD");
            arHBoxTeclas[3].getChildren().add(arBtnTeclado4[i]);
        }
        //arreglo con 11 botones
        for (int i=0;i<arBtnTeclado6.length;i++){
            arBtnTeclado6[i]= new Button(arLbBtn6[i]);
            arBtnTeclado6[i].setStyle("-fx-background-color: #0AECCD");
            arHBoxTeclas[5].getChildren().add(arBtnTeclado6[i]);
        }
        VBoxTeclado.getChildren().addAll(arHBoxTeclas[0],arHBoxTeclas[1],arHBoxTeclas[2],arHBoxTeclas[3],arHBoxTeclas[4],arHBoxTeclas[5]);
    }

    private void CrearEscritura() {
        txtContenido = new TextArea();
        txtContenido.setEditable(false);
        txtContenido.setPrefRowCount(6);
        txtEscritura = new TextArea();
        txtEscritura.setPrefRowCount(6);
        txtEscritura.setOnKeyPressed(this);
        txtEscritura.setOnKeyReleased(this);
        //txtEscritura.addEventHandler(KeyEvent.KEY_TYPED,this);
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
                filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(
                        "TXTFiles", "*.txt", "*.doc", "*.docx"));

                //Obtenemos el archivo
                File file = filechooser.showOpenDialog(this);
                //Mostrar el archivo
                if(file != null){
                    //se crean parametros que controlaran el texto
                    FileReader fr = null;
                    BufferedReader br = null;
                    String texto = "";
                    try {
                        //se inicializan
                        fr = new FileReader(file);
                        br = new BufferedReader(fr);
                        String st = br.readLine();
                        //este while se encargara de escribir el texto en ek txtContenido
                        while (st != null) {
                            texto = texto + st + "\n";
                            st = br.readLine();
                        }
                    } catch (Exception e) {
                        txtContenido.appendText(e.toString());
                    } finally {
                        try {
                            fr.close();
                        } catch (Exception e2) {
                            txtContenido.appendText(e2.toString());
                        }
                    }
                    //Muestra el texto
                    txtContenido.appendText(texto);
                }else{
                    System.out.println("No has ingresado nada");
                }
        }
    }

    @Override
    public void handle(KeyEvent event) {
        //Saber el nombre de la tecla para el cambio de color
        //System.out.println(event.getCode().toString());
        switch (event.getCode().toString()){
            //Asignacion a cada tecla
            case "ESCAPE":
                if( banColor == false )
                    arBtnTeclado1[0].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado1[0].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "F1":
                if( banColor == false )
                    arBtnTeclado1[1].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado1[1].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "F2":
                if( banColor == false )
                    arBtnTeclado1[2].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado1[2].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "F3":
                if( banColor == false )
                    arBtnTeclado1[3].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado1[3].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "F4":
                if( banColor == false )
                    arBtnTeclado1[4].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado1[4].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "F5":
                if( banColor == false )
                    arBtnTeclado1[5].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado1[5].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "F6":
                if( banColor == false )
                    arBtnTeclado1[6].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado1[6].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "F7":
                if( banColor == false )
                    arBtnTeclado1[7].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado1[7].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "F8":
                if( banColor == false )
                    arBtnTeclado1[8].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado1[8].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "F9":
                if( banColor == false )
                    arBtnTeclado1[9].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado1[9].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "F10":
                if( banColor == false )
                    arBtnTeclado1[10].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado1[10].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "F11":
                if( banColor == false )
                    arBtnTeclado1[11].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado1[11].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "F12":
                if( banColor == false )
                    arBtnTeclado1[12].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado1[12].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "PRINTSCREEN":
                if( banColor == false )
                    arBtnTeclado1[13].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado1[13].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "DIGIT1":
                if( banColor == false )
                    arBtnTeclado2[1].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado2[1].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "DIGIT2":
                if( banColor == false )
                    arBtnTeclado2[2].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado2[2].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "DIGIT3":
                if( banColor == false )
                    arBtnTeclado2[3].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado2[3].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "DIGIT4":
                if( banColor == false )
                    arBtnTeclado2[4].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado2[4].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "DIGIT5":
                if( banColor == false )
                    arBtnTeclado2[5].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado2[5].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "DIGIT6":
                if( banColor == false )
                    arBtnTeclado2[6].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado2[6].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "DIGIT7":
                if( banColor == false )
                    arBtnTeclado2[7].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado2[7].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "DIGIT8":
                if( banColor == false )
                    arBtnTeclado2[8].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado2[8].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "DIGIT9":
                if( banColor == false )
                    arBtnTeclado2[9].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado2[9].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "DIGIT0":
                if( banColor == false )
                    arBtnTeclado2[10].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado2[10].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "QUOTE":
                if( banColor == false )
                    arBtnTeclado2[11].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado2[11].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "BACK_SPACE":
                if( banColor == false )
                    arBtnTeclado2[13].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado2[13].setStyle("-fx-background-color: #0AECCD;");
            break;
            case "TAB":
                if( banColor == false )
                    arBtnTeclado3[0].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado3[0].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "Q":
                if( banColor == false )
                    arBtnTeclado3[1].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado3[1].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "W":
                if( banColor == false )
                    arBtnTeclado3[2].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado3[2].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "E":if( banColor == false )
                arBtnTeclado3[3].setStyle("-fx-background-color: #1d1d1d;");
            else
                arBtnTeclado3[3].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "R":
                if( banColor == false )
                    arBtnTeclado3[4].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado3[4].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "T":
                if( banColor == false )
                    arBtnTeclado3[5].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado3[5].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "Y":
                if( banColor == false )
                    arBtnTeclado3[6].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado3[6].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "U":
                if( banColor == false )
                    arBtnTeclado3[7].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado3[7].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "I":
                if( banColor == false )
                    arBtnTeclado3[8].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado3[8].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "O":
                if( banColor == false )
                    arBtnTeclado3[9].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado3[9].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "P":
                if( banColor == false )
                    arBtnTeclado3[10].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado3[10].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "DEAD_ACUTE":
                if( banColor == false )
                    arBtnTeclado3[11].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado3[11].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "PLUS":
                if( banColor == false )
                    arBtnTeclado3[12].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado3[12].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "ENTER":
                if( banColor == false )
                    arBtnTeclado3[13].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado3[13].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "CAPS":
                if( banColor == false )
                    arBtnTeclado4[0].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado4[0].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "A":
                if( banColor == false )
                    arBtnTeclado4[1].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado4[1].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "S":
                if( banColor == false )
                    arBtnTeclado4[2].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado4[2].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "D":
                if( banColor == false )
                    arBtnTeclado4[3].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado4[3].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "F":
                if( banColor == false )
                    arBtnTeclado4[4].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado4[4].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "G":
                if( banColor == false )
                    arBtnTeclado4[5].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado4[5].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "H":
                if( banColor == false )
                    arBtnTeclado4[6].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado4[6].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "J":
                if( banColor == false )
                    arBtnTeclado4[7].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado4[7].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "K":
                if( banColor == false )
                    arBtnTeclado4[8].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado4[8].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "L":
                if( banColor == false )
                    arBtnTeclado4[9].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado4[9].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "BRACELEFT":
                if( banColor == false )
                    arBtnTeclado4[11].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado4[11].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "BRACERIGHT":
                if( banColor == false )
                    arBtnTeclado4[12].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado4[12].setStyle("-fx-background-color: #0AECCD;");
                break;
            case "SHIFT":
                if( banColor == false )
                    arBtnTeclado5[0].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado5[0].setStyle("-fx-background-color: #0AECCD");
                break;
            case "LESS":
                if( banColor == false )
                    arBtnTeclado5[1].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado5[1].setStyle("-fx-background-color: #0AECCD");
                break;
            case "Z":
                if( banColor == false )
                    arBtnTeclado5[2].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado5[2].setStyle("-fx-background-color: #0AECCD");
                break;
            case "X":
                if( banColor == false )
                    arBtnTeclado5[3].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado5[3].setStyle("-fx-background-color: #0AECCD");
                break;
            case "C":
                if( banColor == false )
                    arBtnTeclado5[4].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado5[4].setStyle("-fx-background-color: #0AECCD");
                break;
            case "V":
                if( banColor == false )
                    arBtnTeclado5[5].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado5[5].setStyle("-fx-background-color: #0AECCD");
                break;
            case "B":
                if( banColor == false )
                    arBtnTeclado5[6].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado5[6].setStyle("-fx-background-color: #0AECCD");
                break;
            case "N":
                if( banColor == false )
                    arBtnTeclado5[7].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado5[7].setStyle("-fx-background-color: #0AECCD");
                break;
            case "M":
                if( banColor == false )
                    arBtnTeclado5[8].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado5[8].setStyle("-fx-background-color: #0AECCD");
                break;
            case "COMMA":
                if( banColor == false )
                    arBtnTeclado5[9].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado5[9].setStyle("-fx-background-color: #0AECCD");
                break;
            case "PERIOD":
                if( banColor == false )
                    arBtnTeclado5[10].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado5[10].setStyle("-fx-background-color: #0AECCD");
                break;
            case "MINUS":
                if( banColor == false )
                    arBtnTeclado5[11].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado5[11].setStyle("-fx-background-color: #0AECCD");
                break;
            case "UP":
                if( banColor == false )
                    arBtnTeclado5[13].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado5[13].setStyle("-fx-background-color: #0AECCD");
                break;
            case "CONTROL":
                if( banColor == false )
                    arBtnTeclado6[0].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado6[0].setStyle("-fx-background-color: #0AECCD");
                break;
            case "WINDOWS":
                if( banColor == false )
                    arBtnTeclado6[2].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado6[2].setStyle("-fx-background-color: #0AECCD");
                break;
            case "ALT":
                if( banColor == false )
                    arBtnTeclado6[3].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado6[3].setStyle("-fx-background-color: #0AECCD");
                break;
            case "SPACE":
                if( banColor == false )
                    arBtnTeclado6[4].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado6[4].setStyle("-fx-background-color: #0AECCD");
                break;
            case "ALT_GRAPH":
                if( banColor == false )
                    arBtnTeclado6[5].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado6[5].setStyle("-fx-background-color: #0AECCD");
                break;
            case "CONTEXT_MENU":
                if( banColor == false )
                    arBtnTeclado6[6].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado6[6].setStyle("-fx-background-color: #0AECCD");
                break;
            case "LEFT":
                if( banColor == false )
                    arBtnTeclado6[8].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado6[8].setStyle("-fx-background-color: #0AECCD");
                break;
            case "DOWN":
                if( banColor == false )
                    arBtnTeclado6[9].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado6[9].setStyle("-fx-background-color: #0AECCD");
                break;
            case "RIGHT":
                if( banColor == false )
                    arBtnTeclado6[10].setStyle("-fx-background-color: #1d1d1d;");
                else
                    arBtnTeclado6[10].setStyle("-fx-background-color: #0AECCD");
                break;
        }

        banColor = !banColor;

    }
}

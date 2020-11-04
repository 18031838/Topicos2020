package sample.models;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    private static String server = "localhost";
    private static String user = "root";
    private static String pwd = "18031838";
    private static String db = "restaurante";

    public static Connection con;
    public static void crearConexion(){
        try{
            //Configuracion para la conexion desde MariaDB
            Class.forName("org.mariadb.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mariadb://"+server+":3306/"+db,user,pwd);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

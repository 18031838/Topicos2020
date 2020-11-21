package sample.ui;

import javafx.stage.Stage;
import sample.models.TipoPlatilloDAO;

public class Dashboard extends Stage {

    public Dashboard(){
        CrearUI();
        this.setTitle("Panel de administración del restaurante Tacobell");
        this.show();
    }

    private void CrearUI() {

        new TipoPlatilloCRUD();
        new PlatilloCRUD();

    }
}

//Cada clase de Java, va a ser una tabla de myqsl
//Cada objeto de java, va a ser un renglon en las tablas

//Empleados             ok
//Clientes (domicilio)  ok
//Platillos(precio)     ok
//Tipo de platillo      ok
//Insumos (Existencia)
//Proveedores
//Pedidos               ok
//Sucursales
//Tiposdepago
//Reservacion
//Mesas                 ok
//Tipo de pedido (Enviar/en Restaurante/ Llevar)


//                     Mesas
//                      (1)
//                      |
//                      (N)
//Empleado (1) - (N) Pedido  (N) - (1) Clientes
//pendiet  (1) - (1)    (N)  (1) - (1)
//                       |-------------------DetallePedido
//                      (N)
//                   Platillos (N) - (1) Tipo de platillo
//                             (1) - (1)


// DAO= (Object Access Data)

package app.modelo;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    public Connection conectar(){
        String url="jdbc:sqlite:"+new File("C:\\Users\\POO\\Downloads\\prod.db").getAbsolutePath();
        Connection conn=null;

        try{
            conn= DriverManager.getConnection(url);
            System.out.println("Estamos conectados");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        return  conn;

    }
}

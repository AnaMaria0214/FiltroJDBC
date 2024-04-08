package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {
    //1. establecer la variable donde se se ava  encontrar el estado de la conexion
    //Connection
    static Connection objConnection = null;

    //2.crear un metodo para abrir y establecer la conexion entre java y la base de datos

    public static Connection openConnection(){
        //seguir el paso a paso para establecer la conexion
        //DriverManager

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url ="jdbc:mysql://localhost:3306/jdbc";
            String user ="root";
            String password ="";

            //Establecer la conexion
            objConnection=(Connection) DriverManager.getConnection(url,user,password);
            System.out.println("Conexion a la base de datos establecida correctamente");
            //ERROR POR NO TENER EL DRIVER INSTALADO
        }catch (ClassNotFoundException e) {
            System.out.println("ERROR >> DRIVER NO INSTALADO");
            //ERROR POR NO PODER ESTABLECER LA CONEXION
        }catch (SQLException e){
            System.out.println("ERROR >> NO SE PUEDE ESTABLECER CONEXION CON LA BASE DE DATOS");
        }
        return objConnection;
    }
    //3. crear un metodo para cerrar la conexion entre java y la base de datos

    public static void closeConnection(){
        try {
            //condicional para saber si la conexion esta establecida o no
            //si la conexion esta establecida desconectar
            if (objConnection != null) objConnection.close();
            //si no hay conexion o hay un error mostrar mensaje de error
        }catch (SQLException e){
            System.out.println("ERROR" + e.getMessage());
        }
    }
}

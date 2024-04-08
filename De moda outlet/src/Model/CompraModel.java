package Model;

import Database.CRUD;
import Database.ConfigDB;
import Entity.Compra;
import Entity.Producto;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CompraModel implements CRUD {
    @Override
    public Object create(Object object) {
        //1.Abrir la conexión con la BD
        Connection objConnection = ConfigDB.openConnection();
        //2.Castear el objeto
        Compra objCompra = (Compra) object;

        try {
            //3.crear el sql
            String sql = "INSERT INTO compras (id_Cliente,id_Producto,fecha_Compra,cantidad) values (?,?,?,?);";
            //4.Preparar el statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            //5.Asignar los valores de ??
            objPrepare.setInt(1, objCompra.getId_Cliente());
            objPrepare.setInt(2, objCompra.getId_Producto());
            objPrepare.setDate(3, objCompra.getFecha_Compra());
            objPrepare.setInt(4, objCompra.getCantidad());
            //6.Ejecutar el query
            objPrepare.execute();
            //7.Obtener resultados
            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                objCompra.setId_Compra(objResult.getInt(1));
            }
            //8.Cerrar el statement
            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Compra creado correctamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ha sucedido un error al crear la compra,Intentalo de nuevo");
            System.out.println("ERROR " + e.getMessage());
        }
        //9.Cerrar la conexión
        ConfigDB.closeConnection();
        return objCompra;
    }

    @Override
    public List<Object> findAll() {
        //1.Abrir la conexión con la BD
        Connection objConnection = ConfigDB.openConnection();
        //2.Iniciar la lista donde se van a guardar los registros
        List<Object> ListaCompras = new ArrayList<>();
        try {
            //3.Crear el sql
            String sql = "SELECT * FROM compras order BY compras.id_compra ASC;";
            //4.Preparar el statement
            PreparedStatement objPreparedStatement = (PreparedStatement) objConnection.prepareStatement(sql);
            //5.Ejecutar el query y guardamos el resultado en ResultSet
            ResultSet objResult = (ResultSet) objPreparedStatement.executeQuery();
            //6.Extraer el resultado de el ResultSet
            while (objResult.next()) {
                Compra objCompra = new Compra();

                objCompra.setId_Compra(objResult.getInt("id_Compra"));
                objCompra.setId_Cliente(objResult.getInt("id_Cliente"));
                objCompra.setId_Producto(objResult.getInt("id_Producto"));
                objCompra.setFecha_Compra(objResult.getDate("fecha_Compra"));
                objCompra.setCantidad(objResult.getInt("cantidad"));
                ListaCompras.add(objCompra);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR ");
            System.out.println("ERROR " + e.getMessage());
        }
        //7.Cerramos la conexión a la base de datos
        ConfigDB.closeConnection();
        return ListaCompras;
    }
    public List<Object> findByProduct (int id_Producto) {
        //1.Abrir la conexión con la BD
        Connection objConnection = ConfigDB.openConnection();
        //2.Iniciar la lista donde se van a guardar los registros
        List<Object> listaCompras = new ArrayList<Object>();
        Compra objCompra = null;
        try {
            //3.Crear el sql
            String sql = "SELECT * FROM compras WHERE id_Producto =?;";
            //4.Preparar el statement
            PreparedStatement objPreparedStatement = objConnection.prepareStatement(sql);
            //5.Ejecutar el query y guardamos el resultado en ResultSet
            ResultSet objResult = objPreparedStatement.executeQuery();
            //6.Extraer el resultado de el ResultSet
            while (objResult.next()){
                objCompra = new Compra();
                objCompra.setId_Compra(objResult.getInt("id_Compra"));
                objCompra.setId_Cliente(objResult.getInt("id_Cliente"));
                objCompra.setId_Producto(objResult.getInt("id_Producto"));
                objCompra.setFecha_Compra(objResult.getDate("fecha_Compra"));
                objCompra.setCantidad(objResult.getInt("cantidad"));
                listaCompras.add(objCompra);
            }
        }catch (Exception e){
            System.out.println("ERROR" + e.getMessage());
        }
        //7.cerramos la conexión
        ConfigDB.closeConnection();
        return listaCompras;
    }

    @Override
    public boolean update(Object object) {
        //1.Abrir la conexión con la BD
        Connection objConnection = ConfigDB.openConnection();
        //2.Castear el objeto
        Compra objCompra = (Compra) object;
        //3.Crear una variable bandera para saber el estado de la actualización
        boolean idUpdate = false;
        try{
            //4.Crear el sql
            String sql = "UPDATE compras SET id_Cliente = ?,id_Producto = ?echa_Compra= ?, cantidad = ?,  WHERE compras.id_compra = ?;";
            //5.Preparar el statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);
            //6.Dar valores a las llaves
            objPrepare.setInt(1,objCompra.getId_Cliente());
            objPrepare.setInt(2, objCompra.getId_Producto());
            objPrepare.setDate(3,objCompra.getFecha_Compra());
            objPrepare.setInt(4,objCompra.getCantidad());
            objPrepare.setInt(5,objCompra.getId_Compra());
            //7.Ejecutar el query
            int totalRowAffected = objPrepare.executeUpdate();
            if(totalRowAffected>0){
                idUpdate =true;
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"ERROR al actualizar la información de la compra \\n Intentelo de nuevo\"");
            System.out.println("ERROR "+ e.getMessage());
        }
        //8.Cerrar la conexión a la base de datos
        ConfigDB.closeConnection();
        return idUpdate;
    }

    @Override
    public boolean delete(Object object) {
        //1.Abrir la conexión con la BD
        Connection objConnection = ConfigDB.openConnection();
        //2.Castear el objeto
        Compra objCompra = (Compra) object;
        //3.Crear una variable bandera para saber el estado de la eliminaciòn
        boolean isDeleted = false;

        try {
            //4.Crear el sql
            String sql = "DELETE FROM compras WHERE id_compra = ?;";
            //5.Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, objCompra.getId_Compra());
            //6.Ejecutar el query
            int totalAffectedRows = objPrepare.executeUpdate();
            if (totalAffectedRows > 0) {
                isDeleted = true;
            }
        } catch (SQLException e) {
            System.out.println("ERROR " + e.getMessage());
        }
        //7.Cerrar la conexión a la base de datos
        ConfigDB.closeConnection();
        return isDeleted;
    }
}

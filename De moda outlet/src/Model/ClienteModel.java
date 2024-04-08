package Model;

import Database.CRUD;
import Database.ConfigDB;
import Entity.Cliente;
import Entity.Producto;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteModel implements CRUD {
    @Override
    public Object create(Object object) {
        //1.Abrir la conexión con la BD
        Connection objConnection = ConfigDB.openConnection();
        //2.Castear el objeto
        Cliente objCliente = (Cliente) object;

        try {
            //3.crear el sql
            String sql = "INSERT INTO clientes (nombre,apellido,email) values (?,?,?);";
            //4.Preparar el statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            //5.Asignar los valores a las llaves
            objPrepare.setString(1, objCliente.getNombre());
            objPrepare.setString(2, objCliente.getApellido());
            objPrepare.setString(3, objCliente.getEmail());

            //6.Ejecutar el query
            objPrepare.execute();

            //7.Ejecutar el query y guardar el resultado en ResultSet
            ResultSet objResult = objPrepare.getGeneratedKeys();
            //Extraer el resultado de el ResultSet
            while (objResult.next()) {
                objCliente.setId_Cliente(objResult.getInt(1));
            }
            //8.Cerrar el statement
            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Cliente creado correctamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ha sucedido un error al crear el cliente,Intentalo de nuevo");
            System.out.println("ERROR " + e.getMessage());
        }
        //9.Cerrar la conexión
        ConfigDB.closeConnection();
        return objCliente;
    }

    @Override
    public List<Object> findAll() {
        //1.Abrir la conexión con la BD
        Connection objConnection = ConfigDB.openConnection();
        //2.Iniciar la lista donde se van a guardar los registros
        List<Object> ListaClientes = new ArrayList<>();
        try {
            //3.Crear el sql
            String sql = "SELECT * FROM clientes order BY clientes.id_cliente ASC;";
            //4.Preparar el statement
            PreparedStatement objPreparedStatement = (PreparedStatement) objConnection.prepareStatement(sql);
            //5.Ejecutar el query y guardar el resultado en ResultSet
            ResultSet objResult = (ResultSet) objPreparedStatement.executeQuery();
            //6.Extraer el resultado de el ResultSet
            while (objResult.next()) {
                Cliente objCliente = new Cliente();

                objCliente.setId_Cliente(objResult.getInt("id_Cliente"));
                objCliente.setNombre(objResult.getString("nombre"));
                objCliente.setApellido(objResult.getString("apellido"));
                objCliente.setEmail(objResult.getString("email"));

                ListaClientes.add(objCliente);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR ");
            System.out.println("ERROR " + e.getMessage());
        }
        //7.Cerramos la conexión a la base de datos
        ConfigDB.closeConnection();
        return ListaClientes;
    }
    public List<Object> findByName (String nombre) {
        //1.Abrir la conexión con la BD
        Connection objConnection = ConfigDB.openConnection();
        //2.Iniciar la lista donde se van a guardar los registros
        List<Object> listaClientes = new ArrayList<Object>();
        Cliente objCliente = null;
        try {
            //3.Crear el sql
            String sql = "SELECT * FROM clientes WHERE nombre LIKE ?;";
            //4.Preparar el statement
            PreparedStatement objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1,"%"+ nombre + "%");
            //5.Ejecutar el query y guardamos el resultado en ResultSet
            ResultSet objResult = objPreparedStatement.executeQuery();
            //6.Extraer el resultado de el ResultSet
            while (objResult.next()){
                objCliente = new Cliente();
                objCliente.setId_Cliente(objResult.getInt("id_Cliente"));
                objCliente.setNombre(objResult.getString("nombre"));
                objCliente.setApellido(objResult.getString("apellido"));
                objCliente.setEmail(objResult.getString("email"));


                listaClientes.add(objCliente);
            }
        }catch (Exception e){
            System.out.println("ERROR" + e.getMessage());
        }
        //7.cerramos la conexión
        ConfigDB.closeConnection();
        return  listaClientes;
    }

    @Override
    public boolean update(Object object) {
        //1.Abrir la conexión con la BD
        Connection objConnection = ConfigDB.openConnection();
        //2.Castear el objeto
        Cliente objCliente = (Cliente) object;
        //3.Crear una variable bandera para saber el estado de la actualización
        boolean idUpdate = false;
        try {
            //4.Crear el sql
            String sql = "UPDATE clientes SET nombre = ?, apellido = ?, email = ? WHERE clientes.id_cliente = ?;";
            //5.Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            //6.Asignar los valores a las llaves
            objPrepare.setString(1, objCliente.getNombre());
            objPrepare.setString(2, objCliente.getApellido());
            objPrepare.setString(3, objCliente.getEmail());
            objPrepare.setInt(4,objCliente.getId_Cliente());
            //7.Ejecutar el query
            int totalRowAffected = objPrepare.executeUpdate();
            if (totalRowAffected > 0) {
                idUpdate = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR al actualizar la información de el cliente \n Intentelo de nuevo");
            System.out.println("ERROR " + e.getMessage());
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
        Cliente objCliente = (Cliente) object;
        //3.Crear una variable bandera para saber el estado de la eliminaciòn
        boolean isDeleted = false;

        try {
            //4.Crear el sql
            String sql = "DELETE FROM clientes WHERE id_cliente = ?;";
            //5.Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, objCliente.getId_Cliente());
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

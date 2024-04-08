package Model;

import Database.CRUD;
import Database.ConfigDB;
import Entity.Producto;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoModel implements CRUD {
    @Override
    public Object create(Object object) {
        //1.Abrir la conexión con la BD
        Connection objConnection = ConfigDB.openConnection();
        //2.Castear el objeto
        Producto objProducto = (Producto) object;
        try {
            //3.crear el sql
            String sql = "INSERT INTO productos (nombre,precio,stock,id_Tienda) values (?,?,?,?);";
            //4.Preparar el statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            //5.Asignar los valores a las llaves
            objPrepare.setString(1, objProducto.getNombre());
            objPrepare.setDouble(2, objProducto.getId_Producto());
            objPrepare.setInt(3, objProducto.getStock());
            objPrepare.setInt(4, objProducto.getId_tienda());
            //6.Ejecutar el query
            objPrepare.execute();
            //7.Obtener resultados
            ResultSet objResult = objPrepare.getGeneratedKeys();
            //Extraer el resultado de el ResultSet
            while (objResult.next()) {
                objProducto.setId_Producto(objResult.getInt(1));
            }
            //8.Cerrar el statement
            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Producto creado correctamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ha sucedido un error al crear el producto,Intentalo de nuevo");
            System.out.println("ERROR " + e.getMessage());
        }
        //9.Cerrar la conexión
        ConfigDB.closeConnection();
        return objProducto;
    }

    @Override
    public List<Object> findAll() {
        //1.Abrir la conexión con la BD
        Connection objConnection = ConfigDB.openConnection();
        //2.Iniciar la lista donde se van a guardar los registros
        List<Object> ListaProdcutos = new ArrayList<>();
        try {
            //3.Crear el sql
            String sql = "SELECT * FROM productos order BY productos.id_producto ASC;";
            //4.Preparar el statement
            PreparedStatement objPreparedStatement = (PreparedStatement) objConnection.prepareStatement(sql);
            //5.Ejecutar el query y guardamos el resultado en ResultSet
            ResultSet objResult = (ResultSet) objPreparedStatement.executeQuery();
            //6.Extraer el resultado de el ResultSet
            while (objResult.next()) {
                Producto objProducto = new Producto();

                objProducto.setId_Producto(objResult.getInt("id_producto"));
                objProducto.setNombre(objResult.getString("nombre"));
                objProducto.setPrecio(objResult.getDouble("precio"));
                objProducto.setStock(objResult.getInt("stock"));
                objProducto.setId_tienda(objResult.getInt("id_Tienda"));

                ListaProdcutos.add(objProducto);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR ");
            System.out.println("ERROR " + e.getMessage());
        }
        //7.Cerramos la conexión a la base de datos
        ConfigDB.closeConnection();
        return ListaProdcutos;
    }
    public List<Object> findByName (String nombre) {
        //1.Abrir la conexión con la BD
        Connection objConnection = ConfigDB.openConnection();
        //2.Iniciar la lista donde se van a guardar los registros
        List<Object> listaProductos = new ArrayList<Object>();
        Producto objProducto = null;
        try {
            //3.Crear el sql
            String sql = "SELECT * FROM productos WHERE nombre LIKE '%" + nombre + "%';";
            //4.Preparar el statement
            PreparedStatement objPreparedStatement = objConnection.prepareStatement(sql);
            //5.Ejecutar el query y guardamos el resultado en ResultSet
            ResultSet objResult = objPreparedStatement.executeQuery();
            //6.Extraer el resultado de el ResultSet
            while (objResult.next()){
                objProducto = new Producto();
                objProducto.setId_Producto(objResult.getInt("id_Producto"));
                objProducto.setNombre(objResult.getString("nombre"));
                objProducto.setPrecio(objResult.getDouble("precio"));
                objProducto.setStock(objResult.getInt("stock"));
                objProducto.setId_tienda(objResult.getInt("id_Tienda"));

                listaProductos.add(objProducto);
            }
        }catch (Exception e){
            System.out.println("ERROR" + e.getMessage());
        }
        //7.cerramos la conexión
        ConfigDB.closeConnection();
        return listaProductos;
    }
    @Override
    public boolean update(Object object) {
        //1.Abrir la conexión con la BD
        Connection objConnection = ConfigDB.openConnection();
        //2.Castear el objeto
        Producto objProducto = (Producto) object;
        //3.Crear una variable bandera para saber el estado de la actualización
        boolean idUpdate = false;
        try {
            //4.Crear el sql
            String sql = "UPDATE productos SET name = ?,precio = ?,stock = ?,Id_Tienda=? WHERE productos.id_producto = ?;";
            //5.Preparar el statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);
            //6.Asignar los valores a las llaves
            objPrepare.setString(1, objProducto.getNombre());
            objPrepare.setDouble(2, objProducto.getPrecio());
            objPrepare.setInt(3, objProducto.getStock());
            objPrepare.setInt(4, objProducto.getId_tienda());
            objPrepare.setInt(5, objProducto.getId_Producto());
            //7.Ejecutar el query
            int totalRowAffected = objPrepare.executeUpdate();
            if (totalRowAffected > 0) {
                idUpdate = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR al actualizar la información de el producto \\n Intentelo de nuevo\"");
            System.out.println("ERROR " + e.getMessage());
        }
        //8.Cerrar la conexión a la base de datos
        ConfigDB.closeConnection();
        return idUpdate;
    }


    public boolean actualizarStock(int Stock,int id_Producto) {
        //1.Abrir la conexión con la BD
        Connection objConnection = ConfigDB.openConnection();
        //2.Crear una variable bandera para saber el estado de la actualización
        boolean idUpdate = false;
        try {
            //3.Crear el sql
            String sql = "UPDATE productos SET stock = ? WHERE id.producto = ?;";
            //4.Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            //5.Asignar los valores a las llaves
            objPrepare.setInt(1, Stock);
            objPrepare.setInt(2, id_Producto);
            //6.Ejecutar el query
            if (objPrepare.executeUpdate() > 0) {
                idUpdate = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR al actualizar la información el stock \\n Intentelo de nuevo\"");
            System.out.println("ERROR " + e.getMessage());
        }
        //7.Cerramos la conexión a la base de datos
        ConfigDB.closeConnection();
        return idUpdate;

    }
    @Override
        public boolean delete(Object object){
            //1.Abrir la conexión con la BD
            Connection objConnection = ConfigDB.openConnection();
            //2.Castear el objeto
            Producto objProducto = (Producto) object;
            //3.Crear una variable bandera para saber el estado de la eliminaciòn
            boolean isDeleted = false;
            try {
                //4.Crear el sql
                String sql = "DELETE FROM productos WHERE id_Producto = ?;";
                //5.Preparar el statement
                PreparedStatement objPrepare = objConnection.prepareStatement(sql);
                objPrepare.setInt(1, objProducto.getId_Producto());
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



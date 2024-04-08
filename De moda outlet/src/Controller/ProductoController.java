package Controller;

import Entity.Cliente;
import Entity.Producto;
import Model.ProductoModel;

import javax.swing.*;
import java.util.List;

public class ProductoController {
    public ProductoModel instanceModel() {
        return new ProductoModel();
    }

    public StringBuilder getAll(List<Object> objectsList) {
        StringBuilder lista = new StringBuilder("PRODUCTOS REGISTRADOS:\n");
        if (objectsList.isEmpty()) {
            lista.append("Aún no hay productos registrados");
        } else {
            for (Object obj : objectsList) {
                Producto objProducto = (Producto) obj;
                lista.append(objProducto.toString()).append("\n");
            }
        }
        return lista;
    }

    public void getAll() {
        JOptionPane.showMessageDialog(null, getAll((instanceModel().findAll())));
    }

    public void create() {
        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre de el nuevo producto");
        Double precio = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el precio de el nuevo producto"));
        int stock = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el stock de el nuevo producto"));
        int id_Tienda = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la tienda donde estará el producto"));
        instanceModel().create(new Producto(nombre, precio, stock, id_Tienda));

    }

    public void delete() {
        Object[] options = instanceModel().findAll().toArray();
        if (options.length > 0) {
            Producto selectedOption = (Producto) JOptionPane.showInputDialog(
                    null,
                    "Seleccione el producto que desea eliminar de la base de datos:\n",
                    "Eliminar producto",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if (selectedOption == null) {
                JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna opción");
            } else {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Esta seguro de eliminar este producto?");
                if (confirm == 0) {
                    if (instanceModel().delete(selectedOption)) {
                        JOptionPane.showMessageDialog(null, "Producto eliminado exitosamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se ha realizado la eliminación de el producto exitosamente. \n Inténtelo de nuevo.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha eliminado ningún producto");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Aún no hay productos registrados");
        }
    }

    public void update() {
        Object[] options = instanceModel().findAll().toArray();
        if (options.length > 0) {
            Producto selectedOption = (Producto) JOptionPane.showInputDialog(
                    null,
                    "Seleccione el producto al que le desea actualizar la información\n",
                    "Actualizar Producto",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if (selectedOption == null) {
                JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna opción");
            } else {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Esta seguro de actualizar la información este producto?");
                if (confirm == 0) {
                    String nombre = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre de el producto", selectedOption.getNombre());
                    Double precio = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el nuevo precio de el producto", selectedOption.getPrecio()));
                    int stock = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nuevo stock de el producto", selectedOption.getStock()));
                    int id_Tienda = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nuevo id ID de la tienda donde estará el producto", selectedOption.getId_tienda()));
                    selectedOption.setNombre(nombre);
                    selectedOption.setPrecio(precio);
                    selectedOption.setStock(stock);
                    selectedOption.setId_tienda(id_Tienda);

                    if (instanceModel().update(selectedOption)) {
                        JOptionPane.showMessageDialog(null, "Producto actualizado exitosamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se ha realizado la actualización de el producto exitosamente. \n Inténtelo de nuevo.:");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Actualización de datos cancelada");
                }
            }
        }
    }

    public void getByName() {
        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre de el producto");
        StringBuilder lista = new StringBuilder("Filtrada por Nombre: " + nombre + "\n");
        lista.append(getAll(instanceModel().findByName(nombre)));
        JOptionPane.showMessageDialog(null, lista);
    }
}


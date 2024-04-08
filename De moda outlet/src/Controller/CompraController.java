package Controller;

import Entity.Cliente;
import Entity.Compra;
import Entity.Producto;
import Model.ClienteModel;
import Model.CompraModel;
import Model.ProductoModel;

import javax.swing.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class CompraController {
    public CompraModel instanceModel() {
        return new CompraModel();
    }

    public StringBuilder getAll(List<Object> objectsList) {
        StringBuilder lista = new StringBuilder("COMPRAS REGISTRADAS:\n");
        if (objectsList.isEmpty()) {
            lista.append("Aún no hay compras registrados");
        } else {
            for (Object obj : objectsList) {
                Compra objCompra = (Compra) obj;
                lista.append(objCompra.toString()).append("\n");
            }
        }
        return lista;
    }

    public void getAll() {
        JOptionPane.showMessageDialog(null, getAll((instanceModel().findAll())));
    }

    public void create() {
        try {
            Date fecha_Compra = Date.valueOf(JOptionPane.showInputDialog("Ingrese la fecha de compra (YYYY-MM-DD)"));
            int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de producto"));
            Object[] options1 = new ClienteModel().findAll().toArray();
            if (options1.length > 0) {
                Cliente selectedOption1 = (Cliente) JOptionPane.showInputDialog(
                        null,
                        "Seleccione el cliente de la nueva compra:\n",
                        "Seleccione un cliente",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options1,
                        options1[0]);
                if (selectedOption1 == null) {
                    JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna opción");
                } else {
                    Object[] options2 = new ProductoModel().findAll().toArray();
                    if (options2.length > 0) {
                        Producto selectedOption2 = (Producto) JOptionPane.showInputDialog(
                                null,
                                "Seleccione el producto de la nueva compra:\n",
                                "Seleccione un producto",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                options2,
                                options2[0]);
                        if (selectedOption2 == null) {
                            JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna opción");
                        } else {
                            instanceModel().create(new Compra(selectedOption1.getId_Cliente(),selectedOption2.getId_Producto(),fecha_Compra,cantidad));
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Aún no hay compras registradas");
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "opcion  no valida intentelo de nuevo");
        }
    }

    public void delete() {
        Object[] options = instanceModel().findAll().toArray();
        if (options.length > 0) {
            Compra selectedOption = (Compra) JOptionPane.showInputDialog(
                    null,
                    "Seleccione la compra que desea eliminar de la base de datos:\n",
                    "Eliminar compra",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if (selectedOption == null) {
                JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna opción");
            } else {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Esta seguro de eliminar esta compra?");
                if (confirm == 0) {
                    if (instanceModel().delete(selectedOption)) {
                        JOptionPane.showMessageDialog(null, "Compra eliminada exitosamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se ha realizado la eliminación de la compra  exitosamente. \\n Inténtelo de nuevo.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha eliminado ningúna compra");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Aún no hay clientes registrados");
        }
    }

    public void update() {
        Object[] options = instanceModel().findAll().toArray();
        if (options.length > 0) {
           Compra selectedOption = (Compra) JOptionPane.showInputDialog(
                    null,
                    "Seleccione la compra a la que le desea actualizar la información:\n",
                    "Actualizar compra",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if (selectedOption == null) {
                JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna opción");
            } else {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Esta seguro de actualizar la información de esta compra?");
                if (confirm == 0) {
                    Date fecha_Compra = Date.valueOf(JOptionPane.showInputDialog(null, "Ingrese la nueva fecha de el compra (YYYY-MM-DD)", selectedOption.getFecha_Compra()));
                    int cantidad= Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la nueva cantidad de el compra", selectedOption.getCantidad()));
                    Object[] options_id_Cliente = new ClienteModel().findAll().toArray();
                    if (options_id_Cliente.length > 0) {
                            Cliente selectedOption_id_Cliente = (Cliente) JOptionPane.showInputDialog(
                                null,
                                "Seleccione el nuevo cliente de la compra:\n",
                                "Actuar cliente",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                options_id_Cliente,
                                options_id_Cliente[0]);
                        if (selectedOption_id_Cliente == null) {
                            JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna opción");
                        } else {
                            Object[] options_id_Producto = new ProductoModel().findAll().toArray();
                            if (options_id_Producto.length > 0) {
                                Producto selectedOption_id_Producto = (Producto) JOptionPane.showInputDialog(
                                        null,
                                        "Seleccione el nuevo producto de la compra:\n",
                                        "Actualizar productos",
                                        JOptionPane.QUESTION_MESSAGE,
                                        null,
                                        options_id_Producto,
                                        options_id_Producto[0]);
                                if (selectedOption_id_Producto == null) {
                                    JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna opción");
                                } else {
                                    selectedOption.setId_Cliente(selectedOption_id_Cliente.getId_Cliente());
                                    selectedOption.setId_Producto(selectedOption_id_Producto.getId_Producto());
                                    selectedOption.setFecha_Compra(fecha_Compra);
                                    selectedOption.setCantidad(cantidad);

                                    if (instanceModel().update(selectedOption)) {
                                        JOptionPane.showMessageDialog(null, "Compra actualizada exitosamente");
                                    } else {
                                        JOptionPane.showMessageDialog(null, "No se ha realizado la actualización de el cliente exitosamente. \n Inténtelo de nuevo");
                                    }
                                }
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Actualización de datos cancelada");
                    }
                }
            }
        }
    }

    public void getByProduct() {
        int id_Producto = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el id del producto")) ;
        StringBuilder lista = new StringBuilder("Filtrada por podructo: " + id_Producto + "\n");
        lista.append(getAll(instanceModel().findByProduct(id_Producto)));
        JOptionPane.showMessageDialog(null, lista);
    }
}

package Controller;

import Entity.Cliente;
import Model.ClienteModel;

import javax.swing.*;
import java.util.List;

public class ClienteController {
    public ClienteModel instanceModel() {
        return new ClienteModel();
    }

    public StringBuilder getAll(List<Object> objectsList) {
        StringBuilder lista = new StringBuilder("CLIENTES REGISTRADOS:\n");
        if (objectsList.isEmpty()) {
            lista.append("Aún no hay clientes registrados");
        } else {
            for (Object obj : objectsList) {
                Cliente objCliente = (Cliente) obj;
                lista.append(objCliente.toString()).append("\n");
            }
        }
        return lista;
    }

    public void getAll() {
        JOptionPane.showMessageDialog(null, getAll((instanceModel().findAll())));
    }

    public void create() {
        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre de el nuevo cliente");
        String apellido = JOptionPane.showInputDialog(null, "Ingrese el apellido de el nuevo cliente");
        String email = JOptionPane.showInputDialog(null, "Ingrese el email de el nuevo cliente");
        instanceModel().create(new Cliente(nombre,apellido,email));
    }

    public void delete() {
        Object[] options = instanceModel().findAll().toArray();
        if (options.length > 0) {
            Cliente selectedOption = (Cliente) JOptionPane.showInputDialog(
                    null,
                    "Seleccione el cliente que desea eliminar de la base de datos:\n",
                    "Eliminar cliente",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if (selectedOption == null) {
                JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna opción");
            } else {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Esta seguro de eliminar este cliente?");
                if (confirm == 0) {
                    if (instanceModel().delete(selectedOption)) {
                        JOptionPane.showMessageDialog(null, "Cliente eliminado exitosamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se ha realizado la eliminación de el cliente exitosamente. \n Inténtelo de nuevo. ");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha eliminado ningún cliente");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Aún no hay clientes registrados");
        }

    }

    public void update() {
        Object[] options = instanceModel().findAll().toArray();
        if (options.length > 0) {
            Cliente selectedOption = (Cliente) JOptionPane.showInputDialog(
                    null,
                    "Seleccione el cliente al que le desea actualizar la información:\n",
                    "Actualizar Cliente",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if (selectedOption == null) {
                JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna opción");
            } else {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Esta seguro de actualizar la información este cliente?");
                if (confirm == 0) {
                    String nombre = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre de el cliente", selectedOption.getNombre());
                    String apellido = JOptionPane.showInputDialog(null, "Ingrese el nuevo apellido de el cliente", selectedOption.getApellido());
                    String email = JOptionPane.showInputDialog(null, "Ingrese el nuevo email de el cliente", selectedOption.getEmail());
                    selectedOption.setNombre(nombre);
                    selectedOption.setApellido(apellido);
                    selectedOption.setEmail(email);
                    if (instanceModel().update(selectedOption)) {
                        JOptionPane.showMessageDialog(null, "cliente actualizado exitosamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se ha realizado la actualización de el cliente exitosamente. \n Inténtelo de nuevo.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"Actualización de datos cancelada");
                }
            }
        }
    }

    public void getByName() {
        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre de el cliente");
        StringBuilder lista = new StringBuilder("Filtrada por Nombre: " + nombre + "\n");
        lista.append(getAll(instanceModel().findByName(nombre)));
        JOptionPane.showMessageDialog(null, lista);
    }
}

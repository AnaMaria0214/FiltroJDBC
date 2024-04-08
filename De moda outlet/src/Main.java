import Controller.ClienteController;
import Controller.CompraController;
import Controller.ProductoController;
import Database.ConfigDB;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String option = "";
        String option2 = "";

        do {
            option = JOptionPane.showInputDialog("""
                                            
                    DE MODA OUTLET GESTIÓN
                                            
                           ===MENÚ===
                          
                    1.Administrar Clientes
                    2.Administrar Productos
                    3.Administrar Compras
                    4.Salir
                                            
                    Por favor ingrese una opción .
                    """);
            switch (option) {
                case "1":
                    do {
                        option2 = JOptionPane.showInputDialog("""
                                                        
                                ADMINISTRAR CLIENTES
                                                        
                                     ===MENÚ===
                                      
                                1.Listar Clientes
                                2.Crear un nuevo Cliente
                                3.Actualizar Cliente
                                4.Eliminar Cliente
                                5.Filtrar clientes por nombre
                                6.Salir
                                                        
                                Por favor ingrese una opción.
                                """);
                        ClienteController clienteController = new ClienteController();
                        switch (option2) {
                            case "1":
                                clienteController.getAll();
                                break;
                            case "2":
                                clienteController.create();
                                break;
                            case "3":
                                clienteController.update();
                                break;
                            case "4":
                                clienteController.delete();
                                break;
                            case "5":
                                clienteController.getByName();
                                break;
                            case "6":
                                JOptionPane.showMessageDialog(null, "Regresando al menú principal");
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Opción no valida");
                                break;
                        }

                    } while (!option2.equals("6"));
                    break;
                case "2":
                    do {
                        option2 = JOptionPane.showInputDialog("""

                                ADMINISTRAR PRODUCTOS

                                     ===MENÚ===

                                1.Listar Productos
                                2.Crear un nuevo Producto
                                3.Actualizar un Producto
                                4.Eliminar un Producto
                                5.Filtrar productos por nombre
                                6.Salir

                                Por favor ingrese una opción.
                                """);
                        ProductoController productoController = new ProductoController();
                        switch (option2) {
                            case "1":
                                productoController.getAll();
                                break;
                            case "2":
                                productoController.create();
                                break;
                            case "3":
                                productoController.update();
                                break;
                            case "4":
                                productoController.delete();
                                break;
                            case "5":
                                productoController.getByName();
                                break;
                            case "6":
                                JOptionPane.showMessageDialog(null, "Regresando al menú principal");
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Opción no valida");
                                break;
                        }

                    } while (!option2.equals("6"));
                    break;
                case "3":
                    do {
                        option2 = JOptionPane.showInputDialog("""
                                ADMINISTRAR COMPRAS
                                                               
                                      ===MENÚ===
                                                         
                                 1.Listar Compras
                                 2.Crear una nueva Compra
                                 3.Actualizar una Compra
                                 4.Eliminar una Compra
                                 5.Filtrar por producto
                                 6.Salir
                                                                                         
                                Por favor ingrese una opción.
                                 """);
                        CompraController compraController = new CompraController();
                        switch (option2) {
                            case "1":
                                compraController.getAll();
                                break;
                            case "2":
                                compraController.create();
                                break;
                            case "3":
                                compraController.update();
                                break;
                            case "4":
                                compraController.delete();
                                break;
                            case "5":
                                compraController.getByProduct();
                                break;
                            case "6":
                                JOptionPane.showMessageDialog(null, "Regresando al menú principal");
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Opción no valida");
                                break;

                        }
                    } while (!option2.equals("6"));
                    break;
            }
        } while (!option.equals("4"));
    }
    }
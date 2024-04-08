package Entity;

import java.sql.Date;

public class Compra {
    private int id_Compra;
    private int id_Cliente;
    private int id_Producto;
    private Date fecha_Compra;
    private int cantidad;

    public Compra() {
    }

    public Compra(int id_Cliente, int id_Producto, Date fecha_Compra, int cantidad) {
        this.id_Cliente = id_Cliente;
        this.id_Producto = id_Producto;
        this.fecha_Compra = fecha_Compra;
        this.cantidad = cantidad;
    }

    public int getId_Compra() {
        return id_Compra;
    }

    public void setId_Compra(int id_Compra) {
        this.id_Compra = id_Compra;
    }

    public int getId_Cliente() {
        return id_Cliente;
    }

    public void setId_Cliente(int id_Cliente) {
        this.id_Cliente = id_Cliente;
    }

    public int getId_Producto() {
        return id_Producto;
    }

    public void setId_Producto(int id_Producto) {
        this.id_Producto = id_Producto;
    }

    public Date getFecha_Compra() {
        return fecha_Compra;
    }

    public void setFecha_Compra(Date fecha_Compra) {
        this.fecha_Compra = fecha_Compra;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return
                "  - CompraID: " + id_Compra +
                " ClienteID: " + id_Cliente +
                " ProductoID: " + id_Producto +
                " Fecha_Compra: " + fecha_Compra +
                " Cantidad: " + cantidad;
    }
}

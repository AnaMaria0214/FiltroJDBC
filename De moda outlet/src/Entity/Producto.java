package Entity;

public class Producto {
    private int id_Producto;
    private String nombre;
    private Double precio;
    private int stock;
    private int id_tienda;

    public Producto() {
    }

    public Producto(String nombre, Double precio, int stock, int id_tienda) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.id_tienda = id_tienda;
    }

    public int getId_Producto() {
        return id_Producto;
    }

    public void setId_Producto(int id_Producto) {
        this.id_Producto = id_Producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getId_tienda() {
        return id_tienda;
    }

    public void setId_tienda(int id_tienda) {
        this.id_tienda = id_tienda;
    }


    @Override
    public String toString() {
        return
                "  - ProductoID: " + id_Producto +
                        " Nombre: " + nombre +
                        " Precio: " + precio +
                        " Stock: " + stock +
                        " tiendaID:" + id_tienda;
    }
}

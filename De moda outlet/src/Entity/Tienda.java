package Entity;

public class Tienda {
    private int id_Tienda;
    private String nombre;
    private String ubicacion;

    public Tienda() {
    }

    public Tienda(String nombre, String ubicacion) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
    }

    public int getId_Tienda() {
        return id_Tienda;
    }

    public void setId_Tienda(int id_Tienda) {
        this.id_Tienda = id_Tienda;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return
                "  - TiendaID:" + id_Tienda +
                        "  Nombre: " + nombre +
                        "  Ubicación: " + ubicacion;
    }
}

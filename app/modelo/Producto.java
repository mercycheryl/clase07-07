package app.modelo;

public class Producto {
    private int id;
    private String codigo;
    private String nombre;
    private double precio;

    // metodos
    public Producto(int id, String codigo, String nombre, double precio){
        this.id=id;
        this.codigo=codigo;
        this.nombre=nombre;
        this.precio=precio;
    }

    public Producto(String codigo, String nombre, double precio){
        this.codigo=codigo;
        this.nombre=nombre;
        this.precio=precio;
    }



    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", precio=" + precio +
                '}';
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getId() {
        return id;
    }
}

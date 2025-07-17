package app.modelo;

import java.util.Map;

public interface Crud {
    // mostrar todos
    public Map<Integer, Producto>
    seleccionarTodo();

    //mostrar uno
    public Producto buscar(int id);

    //insertar
    public void insertar(Producto producto);

    //actualizar
    public void actualizar(Producto producto);

    //eliminar
    public void eliminar(int id);

}

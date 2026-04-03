package Refactorizacion_Punto5;


import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class Productos {
    public static final String SIN_PERMISOS = "No tiene los permisos necesarios";
    private List<Producto> productos;
    private SecuritySubSystem security;

    public Productos(List<Producto> productos, SecuritySubSystem security) {
        this.security = security;
        this.productos = productos;
    }

/*
* Puedo usar Supplier con 2 metetodos void y uno que devuelve una colleccion
* ya que add y remove devuelven un booleano y se puede utilizar como generico T
* */

    private <T> T ejecutarConPermiso(String userId, Supplier<T> runnable ){
        if (!this.security.checkPermission(userId)) {
            throw new RuntimeException(SIN_PERMISOS);
        }
      return runnable.get();
    }
    public void addProducto(String userId, Producto producto) {

       this.ejecutarConPermiso(userId,()->this.productos.add(producto));

        /*if (!this.security.checkPermission(userId)) {
            throw new RuntimeException(SIN_PERMISOS);
        }
        this.productos.add(producto);*/
    }

    public void removeProducto(String userId, Producto producto) {

        this.ejecutarConPermiso(userId,()->this.productos.remove(producto));

        /*if (!this.security.checkPermission(userId)) {
            throw new RuntimeException(SIN_PERMISOS);
        }
        this.productos.remove(producto);*/
    }

    public List<Producto> listAll(String userId) {

        return this.ejecutarConPermiso(userId, ()->Collections.unmodifiableList(this.productos));

        /*if (!this.security.checkPermission(userId)) {
            throw new RuntimeException(SIN_PERMISOS);
        }
        return Collections.unmodifiableList(this.productos);*/
    }

    int cantidad() {
        return this.productos.size();
    }

    boolean contiene(Producto unProducto) {
        return this.productos.contains(unProducto);
    }
}
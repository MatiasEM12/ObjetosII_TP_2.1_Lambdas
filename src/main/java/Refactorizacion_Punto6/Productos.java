package Refactorizacion_Punto6;


import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Productos {
    public static final String SIN_PERMISOS = "No tiene los permisos necesarios";
    private List<Producto> productos;
    private SecuritySubSystem security;

    public Productos(List<Producto> productos, SecuritySubSystem security) {
        this.security = security;
        this.productos = productos;
    }

    private <T>T ejecutarConPermiso(Supplier<T> runnable, Predicate<T> validacion){
        if (validacion.test()) {
            throw new RuntimeException(SIN_PERMISOS);
        }
        return runnable.get();

    }
    public void addProducto(String userId, Producto producto) {

        this.ejecutarConPermiso(()->this.productos.add(producto),()-> this.security.checkAddPermission(userId));

        /* if (!this.security.checkAddPermission(userId)) {
            throw new RuntimeException(SIN_PERMISOS);
        }
        this.productos.add(producto);*/
    }

    public void removeProducto(String userId, Producto producto) {
      this.ejecutarConPermiso(()->this.productos.remove(producto),()->this.security.checkRemovePermission(userId));

        /*  if (!this.security.checkRemovePermission(userId)) {
            throw new RuntimeException(SIN_PERMISOS);
        }
        this.productos.remove(producto);*/
    }

    public List<Producto> listAll(String userId) {

        return this.ejecutarConPermiso( ()->Collections.unmodifiableList(this.productos),
                ()-> this.security.checkListPermission(userId) );

        /*if (!this.security.checkListPermission(userId)) {
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
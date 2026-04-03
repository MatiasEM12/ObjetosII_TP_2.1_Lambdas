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

    private <T>T ejecutarConPermiso(Supplier<Boolean> checkPermiso, Supplier<T> supplier){
        if (!checkPermiso.get()) {
            throw new RuntimeException(SIN_PERMISOS);
        }
        return supplier.get();

    }
    public void addProducto(String userId, Producto producto) {

        this.ejecutarConPermiso(()->this.security.checkAddPermission(userId),()->this.productos.add(producto));
        /* if (!this.security.checkAddPermission(userId)) {
            throw new RuntimeException(SIN_PERMISOS);
        }
        this.productos.add(producto);*/
    }

    public void removeProducto(String userId, Producto producto) {
      this.ejecutarConPermiso(()->this.security.checkRemovePermission(userId),()->this.productos.remove(producto));

        /*  if (!this.security.checkRemovePermission(userId)) {
            throw new RuntimeException(SIN_PERMISOS);
        }
        this.productos.remove(producto);*/
    }

    public List<Producto> listAll(String userId) {

        return this.ejecutarConPermiso( ()->this.security.checkListPermission(userId),
                ()->Collections.unmodifiableList(this.productos));

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
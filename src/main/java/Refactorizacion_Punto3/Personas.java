package Refactorizacion_Punto3;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Personas {


    private List<Persona>filtrar(Predicate<Persona>criterio,List<Persona>personas){

        List<Persona> resultado = new ArrayList<>();
        for (Persona persona : personas) {
            if (criterio.test(persona)) {
                resultado.add(persona);
            }
        }
        return resultado;

    }

    //filtra la lista de personas devolviendo otra lista con
    //solo aquellas cuyo nombre comienza con E
    public List<Persona> nombresQueEmpiezanConE(List<Persona> p) {

        return this.filtrar( persona ->persona.nombre().startsWith("E"),p);

        /* List<Persona> resultado = new ArrayList<>();
        for (Persona persona : p) {
            if (persona.nombre().startsWith("E")) {
                resultado.add(persona);
            }
        }
        return resultado;*/
    }

    public List<Persona> nombresCuyaCantidadDeLetrasEsPar(List<Persona> p) {

       return this.filtrar( persona ->persona.nombre().length() % 2 == 0,p);

    /*  List<Persona> resultado = new ArrayList<>();
        for (Persona persona : p) {
            if (persona.nombre().length() % 2 == 0) {
                resultado.add(persona);
            }
        }
        return resultado;*/
    }
}
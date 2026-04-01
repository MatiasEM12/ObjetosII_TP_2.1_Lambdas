package org.example;

import Entities.*;

public class Main {
    static void main(String[] args) {

        AprendiendoLambdas a = new AprendiendoLambdas();

        a.unMetodo((b) -> { System.out.println("abcd" + b);});
        /*se invoca unMetodo(B b) porque  Lambda recibe un parámetro y no
        retorna valor, coincidiendo con la interfaz B.
        */

        a.unMetodo(() -> System.out.println("abcd"));
        /*se invoca unMetodo(A a) porque Lambda no recibe un parámetro y no
        retorna un valor,coincidiento con la Interfaz A

        */


        a.unMetodo((variable) -> {System.out.println("abcd");});
        /*se invoca unMetodo(B b) porque  Lambda recibe un parámetro y no
        retorna valor, coincidiendo con la interfaz B.
        */


        //a.unMetodo((variable) -> {System.out.println("abcd"); return true;});

        /*Error de ambiguedad:
        La lambda recibe un parámetro y retorna boolean, por lo que podría
        corresponder tanto a la interfaz C (String -> boolean) como a la
        interfaz D<Long, Long>. El compilador no puede saber el tipo del
        parámetro y no sabe qué metodo a elejir.
        Se soluciona especificando el tipo:
          a.unMetodo((C) (variable) -> {System.out.println("abcd"); return true;});
        */


        a.unMetodo((Long variable) -> {
            System.out.println("abcd");
            return 10L;
        });
        /*Se invoca unMetodo(D<Long, Long> d) porque la lambda recibe un Long
        y retorna un Long, coincidiendo con la interfaz genérica D.
        */
    }
}

package org.example;

import Entities.AprendiendoLambdas2;

public class Main_Punto2 {

    static void main(String[] args) {

        AprendiendoLambdas2 a = new AprendiendoLambdas2();

        //a) true si el largo es par, false si el largo es impar
        a.unMetodo((String c) ->{ return c.length()%2==0; }
        );

        //b) true si el String comienza con "a" minuscula, false si no
        a.unMetodo((String c)->{ return c.charAt(0) }  );

    }
}

package org.example;

import Entities.AprendiendoLambdas;

public class Main {
    static void main() {

        AprendiendoLambdas a = new AprendiendoLambdas();
        a.unMetodo((b) → { System.out.println("abcd" + b);});
        a.unMetodo(() → System.out.println("abcd"));
        a.unMetodo((variable) → {System.out.println("abcd");});
        a.unMetodo((variable) → {System.out.println("abcd"); return true;});
        a.unMetodo((Long variable) -> {
            System.out.println("abcd");
            return 10L;
        });

    }
}

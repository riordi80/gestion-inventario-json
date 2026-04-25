package org.example;

import org.example.controller.GestorProductos;
import org.example.view.Menu;

public class Main {

    public static void main(String[] args) {
        GestorProductos gestor = new GestorProductos();
        Menu menu = new Menu(gestor);
        menu.iniciar();
    }
}

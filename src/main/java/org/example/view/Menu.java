package org.example.view;

import org.example.controller.GestorProductos;
import org.example.model.Producto;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private final GestorProductos gestor;
    private final Scanner sc;

    public Menu(GestorProductos gestor) {
        this.gestor = gestor;
        this.sc = new Scanner(System.in);
    }

    public void iniciar() {
        System.out.println("==============================================");
        System.out.println("   SISTEMA DE GESTIÓN DE INVENTARIO (JSON)  ");
        System.out.println("==============================================");
        System.out.println("Productos cargados: " + gestor.getCantidadProductos());

        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Selecciona una opción: ");
            procesarOpcion(opcion);
        } while (opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println("\n----------------------------------------------");
        System.out.println("  1. Listar todos los productos");
        System.out.println("  2. Añadir producto");
        System.out.println("  3. Modificar producto");
        System.out.println("  4. Eliminar producto");
        System.out.println("  5. Buscar por nombre");
        System.out.println("  6. Buscar por categoría");
        System.out.println("  0. Salir");
        System.out.println("----------------------------------------------");
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> listarTodos();
            case 2 -> añadirProducto();
            case 3 -> modificarProducto();
            case 4 -> eliminarProducto();
            case 5 -> buscarPorNombre();
            case 6 -> buscarPorCategoria();
            case 0 -> System.out.println("\nCerrando aplicación. ¡Hasta pronto!");
            default -> System.out.println("Opción no válida. Inténtalo de nuevo.");
        }
    }

    private void listarTodos() {
        List<Producto> lista = gestor.getTodos();
        System.out.println("\n--- INVENTARIO COMPLETO (" + lista.size() + " productos) ---");
        if (lista.isEmpty()) {
            System.out.println("  No hay productos registrados.");
            return;
        }
        for (Producto p : lista) {
            System.out.println(p);
        }
    }

    private void añadirProducto() {
        System.out.println("\n--- AÑADIR PRODUCTO ---");
        String nombre = leerTexto("Nombre: ");
        int cantidad = leerEntero("Cantidad: ");
        double precio = leerDecimal("Precio (€): ");
        String categoria = leerTexto("Categoría: ");
        gestor.añadir(nombre, cantidad, precio, categoria);
    }

    private void modificarProducto() {
        System.out.println("\n--- MODIFICAR PRODUCTO ---");
        int id = leerEntero("ID del producto a modificar: ");
        Producto existente = gestor.buscarPorId(id);
        if (existente == null) {
            System.out.println("No se encontró ningún producto con ID " + id + ".");
            return;
        }
        System.out.println("Producto encontrado: " + existente);
        System.out.println("Introduce los nuevos datos (deja en blanco para mantener el actual):");

        String nombre = leerTextoOpcional("Nuevo nombre [" + existente.getNombre() + "]: ", existente.getNombre());
        String cantidadStr = leerTextoOpcional("Nueva cantidad [" + existente.getCantidad() + "]: ", String.valueOf(existente.getCantidad()));
        String precioStr = leerTextoOpcional("Nuevo precio [" + existente.getPrecio() + "]: ", String.valueOf(existente.getPrecio()));
        String categoria = leerTextoOpcional("Nueva categoría [" + existente.getCategoria() + "]: ", existente.getCategoria());

        int cantidad;
        double precio;
        try {
            cantidad = Integer.parseInt(cantidadStr);
            precio = Double.parseDouble(precioStr);
        } catch (NumberFormatException e) {
            System.out.println("Error: cantidad o precio no válidos. Modificación cancelada.");
            return;
        }

        if (gestor.modificar(id, nombre, cantidad, precio, categoria)) {
            System.out.println("Producto modificado correctamente.");
        }
    }

    private void eliminarProducto() {
        System.out.println("\n--- ELIMINAR PRODUCTO ---");
        int id = leerEntero("ID del producto a eliminar: ");
        Producto existente = gestor.buscarPorId(id);
        if (existente == null) {
            System.out.println("No se encontró ningún producto con ID " + id + ".");
            return;
        }
        System.out.println("Producto a eliminar: " + existente);
        String confirmacion = leerTexto("¿Confirmas la eliminación? (s/n): ");
        if (confirmacion.equalsIgnoreCase("s")) {
            gestor.eliminar(id);
            System.out.println("Producto eliminado correctamente.");
        } else {
            System.out.println("Eliminación cancelada.");
        }
    }

    private void buscarPorNombre() {
        System.out.println("\n--- BUSCAR POR NOMBRE ---");
        String nombre = leerTexto("Nombre a buscar: ");
        List<Producto> resultado = gestor.buscarPorNombre(nombre);
        mostrarResultadoBusqueda(resultado);
    }

    private void buscarPorCategoria() {
        System.out.println("\n--- BUSCAR POR CATEGORÍA ---");
        String categoria = leerTexto("Categoría a buscar: ");
        List<Producto> resultado = gestor.buscarPorCategoria(categoria);
        mostrarResultadoBusqueda(resultado);
    }

    private void mostrarResultadoBusqueda(List<Producto> resultado) {
        if (resultado.isEmpty()) {
            System.out.println("No se encontraron productos.");
        } else {
            System.out.println("Se encontraron " + resultado.size() + " producto(s):");
            for (Producto p : resultado) {
                System.out.println(p);
            }
        }
    }

    // --- Métodos auxiliares de lectura ---

    private String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }

    private String leerTextoOpcional(String mensaje, String valorActual) {
        System.out.print(mensaje);
        String entrada = sc.nextLine().trim();
        return entrada.isEmpty() ? valorActual : entrada;
    }

    private int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                int valor = sc.nextInt();
                sc.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Error: introduce un número entero válido.");
                sc.nextLine();
            }
        }
    }

    private double leerDecimal(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                double valor = sc.nextDouble();
                sc.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Error: introduce un número decimal válido (usa punto como separador).");
                sc.nextLine();
            }
        }
    }
}

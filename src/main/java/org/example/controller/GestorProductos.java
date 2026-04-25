package org.example.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.model.Producto;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GestorProductos {

    private static final String RUTA_JSON = "recursos/productos.json";

    private List<Producto> productos;
    private final Gson gson;

    public GestorProductos() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        productos = cargarDesdeJson();
    }

    private List<Producto> cargarDesdeJson() {
        File fichero = new File(RUTA_JSON);
        if (!fichero.exists()) {
            System.out.println("Fichero JSON no encontrado. Se iniciará con inventario vacío.");
            return new ArrayList<>();
        }
        try (Reader reader = new FileReader(fichero)) {
            Type tipo = new TypeToken<List<Producto>>() {}.getType();
            List<Producto> lista = gson.fromJson(reader, tipo);
            return lista != null ? lista : new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Error al leer el fichero JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private void guardarEnJson() {
        File fichero = new File(RUTA_JSON);
        fichero.getParentFile().mkdirs();
        try (Writer writer = new FileWriter(fichero)) {
            gson.toJson(productos, writer);
        } catch (IOException e) {
            System.out.println("Error al guardar el fichero JSON: " + e.getMessage());
        }
    }

    private int generarId() {
        return productos.stream().mapToInt(Producto::getId).max().orElse(0) + 1;
    }

    public void añadir(String nombre, int cantidad, double precio, String categoria) {
        Producto nuevo = new Producto(generarId(), nombre, cantidad, precio, categoria);
        productos.add(nuevo);
        guardarEnJson();
        System.out.println("Producto añadido correctamente con ID " + nuevo.getId() + ".");
    }

    public boolean modificar(int id, String nombre, int cantidad, double precio, String categoria) {
        for (Producto p : productos) {
            if (p.getId() == id) {
                p.setNombre(nombre);
                p.setCantidad(cantidad);
                p.setPrecio(precio);
                p.setCategoria(categoria);
                guardarEnJson();
                return true;
            }
        }
        return false;
    }

    public boolean eliminar(int id) {
        boolean eliminado = productos.removeIf(p -> p.getId() == id);
        if (eliminado) {
            guardarEnJson();
        }
        return eliminado;
    }

    public List<Producto> buscarPorNombre(String nombre) {
        List<Producto> resultado = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    public List<Producto> buscarPorCategoria(String categoria) {
        List<Producto> resultado = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getCategoria().toLowerCase().contains(categoria.toLowerCase())) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    public Producto buscarPorId(int id) {
        return productos.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public List<Producto> getTodos() {
        return productos;
    }

    public int getCantidadProductos() {
        return productos.size();
    }
}

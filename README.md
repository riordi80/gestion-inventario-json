# Gestión de Inventario JSON

Aplicación Java por consola que gestiona un inventario de productos utilizando un fichero JSON como sistema de almacenamiento persistente. Desarrollada con Maven y la librería Gson.

## Tecnologías

- Java 21
- Maven
- Gson 2.10.1

## Estructura del proyecto

```
ProyectoInicialJSON/
├── pom.xml
├── recursos/
│   └── productos.json
└── src/main/java/org/example/
    ├── Main.java
    ├── model/
    │   └── Producto.java
    ├── controller/
    │   └── GestorProductos.java
    └── view/
        └── Menu.java
```

## Funcionalidades

- **Listar** todos los productos del inventario
- **Añadir** un nuevo producto (ID generado automáticamente)
- **Modificar** un producto existente por ID
- **Eliminar** un producto por ID (con confirmación)
- **Buscar** por nombre o por categoría

Cada operación de escritura actualiza el fichero JSON inmediatamente, manteniendo la persistencia entre ejecuciones.

## Estructura del JSON

```json
[
  {
    "id": 1,
    "nombre": "Agua",
    "cantidad": 100,
    "precio": 1.5,
    "categoria": "Bebidas"
  }
]
```

## Cómo ejecutar

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/riordi80/gestion-inventario-json.git
   ```

2. Abrir el proyecto en IntelliJ IDEA como proyecto Maven.

3. Ejecutar `Main.java` con el directorio de trabajo en la raíz del proyecto (donde está la carpeta `recursos/`).

## Ejemplo de uso

```
==============================================
   SISTEMA DE GESTIÓN DE INVENTARIO (JSON)
==============================================
Productos cargados: 5

----------------------------------------------
  1. Listar todos los productos
  2. Añadir producto
  3. Modificar producto
  4. Eliminar producto
  5. Buscar por nombre
  6. Buscar por categoría
  0. Salir
----------------------------------------------
```

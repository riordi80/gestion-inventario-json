# Documentación - Proyecto Inicial JSON
## Gestión de Inventario con fichero JSON y Gson

**Asignatura:** Acceso a Datos  
**Unidad:** UT4 - Bases de datos documentales  
**Curso:** 2025/2026  

---

## 1. Descripción del proyecto

Aplicación Java por consola que gestiona un inventario de productos usando un fichero JSON como almacenamiento persistente. Utiliza la librería **Gson** para serializar y deserializar los objetos Java a/desde JSON.

Cada vez que el programa arranca, lee el fichero `recursos/productos.json`. Todas las operaciones (añadir, modificar, eliminar) actualizan el fichero inmediatamente, garantizando la persistencia entre ejecuciones.

---

## 2. Tecnologías utilizadas

- **Java 21**
- **Maven** (gestión de dependencias)
- **Gson 2.10.1** (serialización JSON)

---

## 3. Estructura del proyecto

```
ProyectoInicialJSON/
├── pom.xml
├── recursos/
│   └── productos.json          ← fichero de almacenamiento
└── src/main/java/org/example/
    ├── Main.java               ← punto de entrada
    ├── model/
    │   └── Producto.java       ← entidad de datos
    ├── controller/
    │   └── GestorProductos.java ← lógica CRUD + lectura/escritura JSON
    └── view/
        └── Menu.java           ← interfaz de consola
```

---

## 4. Estructura del JSON

Cada producto tiene los campos: `id`, `nombre`, `cantidad`, `precio` y `categoria`.

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

---

## 5. Funcionamiento del programa

### 5.1 Carga inicial

Al iniciar, el programa lee el fichero JSON y muestra cuántos productos hay cargados.

> **[CAPTURA: pantalla de inicio con el mensaje de productos cargados]**

---

### 5.2 Listar productos

Muestra todos los productos del inventario en formato tabla.

> **[CAPTURA: opción 1 - listado completo]**

**JSON antes:**
```json
[
  {"id": 1, "nombre": "Agua", "cantidad": 100, "precio": 1.5, "categoria": "Bebidas"},
  ...
]
```

---

### 5.3 Añadir producto

Se solicitan los datos del nuevo producto y se añade al final del JSON.

> **[CAPTURA: proceso de añadir un producto nuevo]**

**JSON después de añadir (nuevo producto con ID generado automáticamente):**
```json
[
  ...,
  {
    "id": 6,
    "nombre": "Yogur",
    "cantidad": 80,
    "precio": 0.9,
    "categoria": "Lacteos"
  }
]
```

---

### 5.4 Modificar producto

Se selecciona el producto por ID y se actualizan sus campos. Si se deja en blanco un campo, se mantiene el valor anterior.

> **[CAPTURA: proceso de modificación de un producto]**

**JSON antes de modificar:**
```json
{"id": 2, "nombre": "Pan", "cantidad": 50, "precio": 0.8, "categoria": "Alimentacion"}
```

**JSON después de modificar:**
```json
{"id": 2, "nombre": "Pan Integral", "cantidad": 60, "precio": 1.1, "categoria": "Alimentacion"}
```

---

### 5.5 Eliminar producto

Se selecciona el producto por ID, se muestra confirmación y se elimina del JSON.

> **[CAPTURA: proceso de eliminación con confirmación]**

**JSON antes de eliminar:**
```json
[
  {"id": 1, "nombre": "Agua", ...},
  {"id": 2, "nombre": "Pan", ...},
  {"id": 3, "nombre": "Leche", ...}
]
```

**JSON después de eliminar el ID 2:**
```json
[
  {"id": 1, "nombre": "Agua", ...},
  {"id": 3, "nombre": "Leche", ...}
]
```

---

### 5.6 Buscar producto

Hay dos tipos de búsqueda: por nombre (búsqueda parcial) y por categoría. El JSON no se modifica.

> **[CAPTURA: búsqueda por nombre con resultados]**

> **[CAPTURA: búsqueda por categoría con resultados]**

---

### 5.7 JSON final

> **[CAPTURA: contenido del fichero productos.json al final de la sesión]**

---

## 6. Gestor de tareas (GitHub)

El proyecto se gestionó mediante **GitHub Issues** en el repositorio del proyecto.

**Repositorio:** `https://github.com/[usuario]/ProyectoInicialJSON`

### Issues creadas:

| Issue | Título | Responsable | Estado |
|-------|--------|-------------|--------|
| #1 | Crear estructura Maven y pom.xml con Gson | [Nombre 1] | Cerrada |
| #2 | Implementar clase modelo Producto | [Nombre 1] | Cerrada |
| #3 | Implementar GestorProductos (CRUD + JSON) | [Nombre 2] | Cerrada |
| #4 | Implementar menú por consola | [Nombre 2] | Cerrada |
| #5 | Crear fichero JSON inicial con datos de prueba | [Nombre 1] | Cerrada |
| #6 | Pruebas y corrección de errores | Ambos | Cerrada |

> **[CAPTURA: panel de issues en GitHub mostrando las tareas]**

> **[CAPTURA: ejemplo de issue con descripción y asignado]**

---

## 7. Reparto del trabajo

| Tarea | Responsable |
|-------|-------------|
| `Producto.java` | [Nombre 1] |
| `GestorProductos.java` | [Nombre 2] |
| `Menu.java` | [Nombre 2] |
| `Main.java` + `pom.xml` | [Nombre 1] |
| JSON inicial + pruebas | Ambos |
| Documentación | Ambos |

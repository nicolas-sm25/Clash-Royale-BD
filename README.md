# Clash Royale Database - CRUD en Java con PostgreSQL

Proyecto desarrollado en Java utilizando JDBC y PostgreSQL para gestionar una base de datos de cartas de Clash Royale.  
Solución al segundo parcial de POO de la UD.

---

# Funcionalidades

- Adicionar cartas a la base de datos
- Mostrar todas las cartas registradas
- Buscar cartas por ID
- Buscar cartas por nombre
- Filtrar cartas por:
  - Elixir
  - Rareza
  - Tipo
- Validación de datos ingresados por el usuario
- Menú infinito interactivo

---

# Tecnologías utilizadas

- Java
- JDBC
- PostgreSQL
- Neon Database
- IntelliJ IDEA

---

# Estructura del proyecto

```plaintext
src
│
├── dao
│   ├── ConexionDB.java
│   └── CartaDAO.java
│
├── model
│   └── Carta.java
│
└── ui
    └── Main.java
```

---

# Explicación de paquetes

## model

Contiene la clase `Carta`, utilizada para representar cada registro de la base de datos como un objeto Java.

## dao

Contiene:

- `ConexionDB`: maneja la conexión con PostgreSQL.
- `CartaDAO`: contiene todas las consultas SQL y operaciones CRUD.

## ui

Contiene el menú principal y toda la interacción con el usuario.

---

# Base de datos

Tabla utilizada:

```sql
CREATE TABLE cartas (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50),
    costo INT,
    rareza VARCHAR(30),
    tipo VARCHAR(30)
);
```

---

# Cómo ejecutar el proyecto

1. Clonar el repositorio:

```bash
git clone https://github.com/TU-USUARIO/TU-REPOSITORIO.git
```

2. Abrir el proyecto en IntelliJ IDEA

3. Agregar el driver JDBC de PostgreSQL

4. Configurar las credenciales en `ConexionDB.java`

```java
private static final String URL = "...";
private static final String USER = "...";
private static final String PASS = "...";
```

5. Ejecutar `Main.java`

---

# Ejemplo de uso

```plaintext
===== BASE DE DATOS CARTAS DE CLASH ROYALE =====

1. Insertar carta a la BD
2. Mostrar BD
3. Buscar una carta
4. Filtrar
5. Salir
```

---

# Conceptos aplicados

- Programación Orientada a Objetos
- JDBC
- DAO Pattern
- PreparedStatement
- ResultSet
- Validación de datos
- Mapeo Objeto-Relacional
- Manejo de excepciones
- Separación de responsabilidades

---

# Autor

Nicolás Soriano Medina  
Universidad Distrital Francisco José de Caldas

# Biblioteca Musical - Proyecto de Consulta y Organización Musical

**Biblioteca Musical** es una aplicación de escritorio desarrollada en Java con una arquitectura en capas (Presentación, Negocio, Persistencia, Modelos.)
Su objetivo es permitir a los usuarios **consultar y organizar** una colección musical personalizada, similar a plataformas de música comerciales. 
El sistema integra funcionalidades como perfil de usuario, favoritos, no deseados, filtros por género, e inserción masiva de artistas, álbumes y canciones.
Este proyecto fue desarrollado como parte del curso de **Bases de Datos Avanzadas**.

## Tecnologías utilizadas

- **Java SE 17+**
- **Apache Maven** (gestión de dependencias)
- **MongoDB** (base de datos NoSQL)
- **Java Swing** (interfaz gráfica)
- **BCrypt** (encriptación de contraseñas)

## Características principales

### Gestión de usuarios
- Registro de nuevos usuarios con nombre, correo, contraseña encriptada e imagen de perfil.
- Edición del perfil (sin opción de eliminación de cuenta).
- Gestión de favoritos y géneros restringidos.

### Gestión de artistas
- Inserción masiva de artistas, álbumes, canciones y géneros.
- Información completa para cada artista:
  - Solistas: nombre, imagen, género musical.
  - Bandas: datos generales + lista de integrantes (rol, fechas, estado).

### Gestión de álbumes
- Cada álbum contiene:
  - Nombre, portada, fecha de lanzamiento, género musical.
  - Canciones asociadas.

### Navegación y búsqueda
- Navegación entre módulos: artistas, álbumes, canciones.
- Búsqueda por nombre o género y otros filtros avanzados.
- Opción para mostrar miembros activos/inactivos en bandas.
- Visualización con miniaturas para una experiencia intuitiva.

### Funcionalidades adicionales
- El contenido asociado a géneros bloqueados por el usuario no se muestra en la interfaz durante las consultas o navegación del sistema.
- Sección de **favoritos** para artistas, álbumes y canciones.
- Lista de **géneros restringidos**: los resultados se ajustan dinámicamente y se eliminan favoritos en conflicto.

## Instrucciones de uso e instalación.
Instala MongoDB de forma local en tu equipo y asegúrate de que esté en ejecución antes de iniciar el sistema.
Clona este repositorio en tu máquina local usando el siguiente comando: git clone https://github.com/ang3lfco/instagram-clone.git
Abre el proyecto en NetBeans o en el IDE de tu preferencia.
Ejecuta el archivo Presentacion.java, ubicado en el paquete por defecto del módulo Presentacion, para realizar la carga masiva inicial de datos en la base de datos.
Ejecuta frmInicioSesion.java para abrir la interfaz de inicio de sesión.
Regístrate como nuevo usuario, inicia sesión con tus credenciales y comienza a navegar por la aplicación.

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
- El contenido asociado a **géneros restringidos** por el usuario no se muestra en la interfaz durante las consultas o navegación del sistema.
- Sección de **favoritos** para artistas, álbumes y canciones.

## Capturas de la aplicación

### Inicio de sesión
<img width="1366" height="723" alt="Screenshot (9923)" src="https://github.com/user-attachments/assets/3cc50d36-5bce-4b5f-b484-ea615fa8a1bc" />

### Pantalla principal
<img width="1366" height="725" alt="Screenshot (9924)" src="https://github.com/user-attachments/assets/fdde624d-e2a2-4754-a446-df647c7f9aa8" />

### Pantalla de artistas
<img width="1366" height="725" alt="Screenshot (9926)" src="https://github.com/user-attachments/assets/238d4ed5-69f4-4854-a35b-7c348937f15a" />

### Pantalla de álbumes
<img width="1366" height="723" alt="Screenshot (9927)" src="https://github.com/user-attachments/assets/79c0c87e-546f-4ac1-892a-1b5427e4c2c4" />

### Pantalla de canciones
<img width="1366" height="725" alt="Screenshot (9928)" src="https://github.com/user-attachments/assets/c17b82e3-9059-466c-a8d6-a713386f124d" />

### Opción agregar a favoritos y no deseados
<img width="1366" height="724" alt="Screenshot (9929)" src="https://github.com/user-attachments/assets/bed5cfec-bf63-4503-a0c3-95ad3da0ef94" />

### Pantalla de no deseados
<img width="1366" height="725" alt="Screenshot (9930)" src="https://github.com/user-attachments/assets/7206f521-9115-4952-a539-e6ce86ff9f97" />

### Pantalla de favoritos
<img width="1366" height="727" alt="Screenshot (9931)" src="https://github.com/user-attachments/assets/4b51cf4d-ae59-474e-872a-450053125ebe" />

### Pantalla de edición de perfil
<img width="1366" height="725" alt="Screenshot (9932)" src="https://github.com/user-attachments/assets/15056ccc-874d-4f50-bbed-e7e97fe436b6" />

### Pantalla búsqueda de ejemplo
<img width="1366" height="727" alt="Screenshot (9933)" src="https://github.com/user-attachments/assets/7e626641-fa5a-4089-a1c5-584998d5ba11" />

### Pantalla búsqueda de ejemplo
<img width="1366" height="727" alt="Screenshot (9934)" src="https://github.com/user-attachments/assets/b3789d89-618e-4ba6-b30e-159668248ff8" />


## Instrucciones de uso e instalación.
- Instala MongoDB de forma local en tu equipo y asegúrate de que esté en ejecución antes de iniciar el sistema.

- Clona este repositorio en tu máquina local usando el siguiente comando: git clone https://github.com/ang3lfco/instagram-clone.git.

- Abre el proyecto en NetBeans o en el IDE de tu preferencia.

- Ejecuta el archivo Presentacion.java, ubicado en el paquete por defecto del módulo Presentacion, para realizar la carga masiva inicial de datos en la base de datos.

- Ejecuta frmInicioSesion.java para abrir la interfaz de inicio de sesión.

- Regístrate como nuevo usuario, inicia sesión con tus credenciales y comienza a navegar por la aplicación.

# Meeti

**Meeti** es una plataforma web para gestionar eventos y reuniones. Los usuarios pueden crear meetups, unirse a eventos, dejar comentarios y visualizar información en tiempo real.

Este backend está construido con **Spring Boot** usando **Java 21**, con **MongoDB** como base de datos y autenticación segura mediante **JWT**.

---

## 🚀 Tecnologías Utilizadas

### Backend

- Java 21 y Spring Boot
- Spring Security con JWT para autenticación y autorización
- MongoDB como base de datos NoSQL
- Spring Data MongoDB para acceso a datos
- Maven para gestión de dependencias

### Frontend (integrado)

- Plantillas Thymeleaf
- AJAX para acciones asincrónicas
- SweetAlert2 para notificaciones

### Otros

- Validación de datos con Hibernate Validator (JSR-380)
- Encriptación de contraseñas con BCryptPasswordEncoder
- Gestión de sesiones basada en JWT

---

## ✅ Funcionalidades Clave

- 🧑 Registro, login y gestión de perfiles de usuario con seguridad JWT
- 📅 Creación, edición y visualización de eventos (Meetups)
- 📍 Geolocalización de eventos
- 💬 Comentarios en eventos
- 🔔 Notificaciones en frontend con SweetAlert2
- 🔄 Uso de AJAX para operaciones sin recarga de página
- 📄 Vistas dinámicas (usando Thymeleaf)

---

## 🛡️ Seguridad

- Encriptación segura de contraseñas con BCrypt
- Validación y sanitización de datos con anotaciones de Hibernate Validator
- Autenticación y autorización mediante JWT para APIs REST
- Control de acceso granular con Spring Security

---
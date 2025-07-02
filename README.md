# Meeti - Backend

**Meeti** es una API REST desarrollada con **Spring Boot** y **Java 21**, diseñada para gestionar eventos y reuniones. Este backend proporciona todos los servicios necesarios para el manejo de usuarios, eventos, comentarios y más. La interfaz de usuario se encuentra en una aplicación **React** independiente que consume esta API.

---

## 🚀 Tecnologías Utilizadas

- **Java 21** y **Spring Boot**
- **Spring Security** con **JWT** para autenticación y autorización
- **MongoDB** como base de datos NoSQL
- **Spring Data MongoDB** para acceso y persistencia de datos
- **Maven** para gestión de dependencias
- **Hibernate Validator** (JSR-380) para validación de datos
- **BCryptPasswordEncoder** para encriptación de contraseñas

---

## ✅ Funcionalidades Principales

- 🧑 Registro, login y gestión de perfiles de usuario con seguridad basada en JWT
- 📅 Creación, edición, eliminación y visualización de eventos (meetups)
- 📍 Gestión de ubicaciones y geolocalización de eventos
- 💬 Comentarios en eventos
- 📂 API RESTful lista para integrarse con cualquier frontend (actualmente una app en React)

---

## 🛡️ Seguridad

- Encriptación de contraseñas con **BCrypt**
- Validación de entrada mediante **Hibernate Validator**
- Autenticación y autorización segura mediante **JWT**
- Control de acceso detallado con **Spring Security**

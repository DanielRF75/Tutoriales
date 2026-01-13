# Laravel + Docker (Apache + MariaDB)

Este repositorio define un entorno Docker para **Laravel** usando **Apache** y **MariaDB**, con una fase de instalación inicial mediante **Composer** y una estructura clara de configuración.

---

## 📁 Estructura del proyecto

```
.
├── config
│   └── apache
│       ├── Dockerfile
│       └── 000-default.conf
├── src
├── .env
└── docker-compose.yml
```

### Descripción

* **config/apache/Dockerfile**: Imagen personalizada de Apache con PHP para Laravel.
* **config/apache/000-default.conf**: VirtualHost de Apache con `public` como DocumentRoot.
* **src/**: Código fuente de Laravel.
* **.env**: Variables de entorno (Laravel y base de datos).
* **docker-compose.yml**: Orquestación de servicios (Apache, DB, Composer, phpMyAdmin).

---
## 🗄️ Base de datos

### Configuración

1. Crear un archivo `.env` con la configuración de la base de datos:

```env
DB_CONNECTION=mysql
DB_HOST=mariadb
DB_PORT=3306
DB_DATABASE=laravel
DB_USERNAME=laravel
DB_PASSWORD=secret
```


---

## 🚀 Instalación de Laravel

La instalación de Laravel se realiza usando un contenedor de **Composer** que se ejecuta una sola vez y se elimina al finalizar.

```bash
docker compose --profile install run --rm composer
```

> El contenedor de Composer instala Laravel en `src/` y **se detiene automáticamente** al terminar.

Una vez instalado Laravel:

```bash
docker compose up -d
```

---

## 🔐 Post-instalación: permisos

### Dar permisos al usuario sobre `src`

```bash
sudo chown -R $USER:$USER src
```

### Permisos requeridos por Laravel

```bash
sudo chown -R www-data:www-data src/storage src/bootstrap/cache
sudo chmod -R 775 src/storage src/bootstrap/cache
```

---

## ✅ Inicialización correcta de la base de datos

Ejecutar las migraciones de Laravel dentro del contenedor de Apache:

```bash
docker exec -i laravel_apache php artisan migrate --force
```

---

## 📝 Notas

* El `DocumentRoot` de Apache apunta a `src/public`.
* El contenedor de Composer **no debe mantenerse activo**.

---

## 📌 Requisitos

* Docker
* Docker Compose (v2)

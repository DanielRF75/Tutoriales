FROM php:8.4-apache

RUN apt-get update && apt-get install -y \
    git zip unzip libpng-dev libonig-dev libxml2-dev \
    && docker-php-ext-install pdo pdo_mysql mbstring exif bcmath gd

RUN a2enmod rewrite

WORKDIR /var/www/html

# Apuntar Apache a la carpeta public
COPY 000-default.conf /etc/apache2/sites-available/000-default.conf

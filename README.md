1 - instalar apache y enable el servicio,
2 - configurar el firewall ufw minimo para http y ssh (apache y debian)
3 - instalar php y modulos de apache para php
4 - instalacion de las dependencias ocn composer 
    - instalar composer (sudo apt install composer -y)
5 - instalar mariadb
    -mariadb-seber mariadb-client
    -modulo para php
    -cracion de usuario
6 - instalar larabel usando composer
    - cd /var/www
    - sudo composer create-project laravel/laravel web
7 - cambiar permisos 
    -sudo chown -R www-data:www-data
    -sudo chwon 775
8 - configrar el .env
9 - configurar un virtual host que apunte a public de apache
10 - desactivar el virtual host por defecto
11 - realizar un artisan migrate

Node.js
1 - instalar node con apt
2 - cmabiar permisos y hacer un npm install sin sudo

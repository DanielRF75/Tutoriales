1 -> Instalacion de apache
    - sudo apt update
    - sudo apt upgrade
    
    - sudo apt install apache2
    
    - sudo systemctl status apache2
    
    (el systemctl debe mostrar que el apache esta funcionando correctamente)
    
    
2 -> Configuracion del firewall
    - sudo apt install ufw
    - sudo ufw allow 80/tcp
    - sudo ufw allow 443/tcp
    
    - sudo ufw enable
    
    - sudo status verbose
    (debera mostrar los puertos 80 y 433 abiertos)
    
    - sudo ufw reload
    
    
3 -> Instalacion de php y los modulos de apache para php
    - sudo apt install php libapache2-mod-php
    - sudo apt install php-cli php-common php-curl php-mbstring php-xml php-zip


    - sudo systemctl restart apache2
    
    - creacion del info.php en la raiz 
    "
    <?php
    phpinfo();
    "
    
   
4 -> Instalacion de composer para las dependencias 
    - sudo apt install composer
    
    
5 -> Instalacion de mariadb
    - sudo apt install mariadb-server mariadb-client
    - sudo systemctl start mariadb
    - sudo systemctl enable mariadb
    
    - sudo systemctl status mariadb
    (comprobación de que el servicio esta activo)

    - sudo mariadb-secure-installation
    (
    configurar contraseña de root
    
    Password de root → sí
    Eliminar usuarios anónimos → sí
    Deshabilitar login remoto de root → sí
    Eliminar base de datos test → sí
    Recargar privilegios → sí
    )
    .
    - sudo apt install php-mysql
    - sudo systemctl restart apache2

    - sudo mariadb
    "
    CREATE DATABASE <<app_db>>
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;
    "
    "
    CREATE USER '<<user>>'@'localhost'
    IDENTIFIED BY '<<password>>';
    "
    "
    GRANT ALL PRIVILEGES ON <<app_db>>.* TO '<<user>>'@'localhost';
    FLUSH PRIVILEGES;
    "
    "
    EXIT;
    "
    
    - mariadb -u <<user>> -p <<app_db>>
    
    
6 -> Instalar larabes usando el composer 
.
    - cd /var/www
    - cd sudo composer create-project --prefer-dist laravel/laravel <<mi_proyecto>>

    - sudo chown -R www-data:www-data /var/www/<<mi_proyecto>>
    - sudo chmod -R 775 /var/www/<<mi_proyecto>>/storage
    - sudo chmod -R 775 /var/www/<<mi_proyecto>>/bootstrap/cache
    
    
    - sudo nano /etc/apache2/sites-available/laravel.conf
        "
        <VirtualHost *:80>
            ServerName ejemplo.local
            DocumentRoot /var/www/<<mi_proyecto>>/public
            
            <Directory /var/www/mi_<<proyecto>>/public>
                AllowOverride All
                Require all granted
            </Directory>

            ErrorLog ${APACHE_LOG_DIR}/laravel_error.log
            CustomLog ${APACHE_LOG_DIR}/laravel_access.log combined
        </VirtualHost>
        "
    - sudo a2ensite laravel.conf
    - sudo a2enmod rewrite
    - sudo systemctl restart apache2

    (Elminar los demnas virtual host)
    
    (
    En caso de desarrollo cambiar el 
    - sudo nano /etc/hosts
    "
    127.0.0.1   mi_proyecto.local
    "
    )
    
    

7 -> Configurar el .env
    - cambiar el .evn de la raiz del proyecto
    "
    DB_CONNECTION=mysql
    DB_HOST=127.0.0.1
    DB_PORT=3306
    DB_DATABASE=<<app_db>>
    DB_USERNAME=<<app_user>>
    DB_PASSWORD=<<password_segura>>
    "

8 -> realizar un artisan migrate
    - php artisan migrate

9 -> Instalar php myadmin
    - sudo apt install phpmyadmin
    (seguir el gestor de isntalacion)
    
    - sudo nano /etc/apache2/sites-available/phpmyadmin.conf
    "
    <VirtualHost *:80>
        ServerName phpmyadmin.local
        DocumentRoot /usr/share/phpmyadmin

        <Directory /usr/share/phpmyadmin>
            Options FollowSymLinks
            DirectoryIndex index.php
            AllowOverride All
            # Require all granted
            Require local
        </Directory>

        ErrorLog ${APACHE_LOG_DIR}/phpmyadmin_error.log
        CustomLog ${APACHE_LOG_DIR}/phpmyadmin_access.log combined
    </VirtualHost>
    "
    
    - sudo a2ensite phpmyadmin.conf
    - sudo a2enmod rewrite
    - sudo systemctl reload apache2

    (
    En caso de desarrollo cambiar el 
    - sudo nano /etc/hosts
    "
    127.0.0.1   phpmyadmin.local
    "
    )
    


@echo off
echo ========================================================
echo   SISTEMA MERCADO HOGAR - COMPILACION Y DESPLIEGUE
echo ========================================================
echo.
echo [1/2] Compilando el proyecto usando Maven (a traves de Docker)...
echo Esto puede tomar unos minutos la primera vez mientras descarga dependencias.
echo.
docker run --rm -v "%cd%":/usr/src/mymaven -w /usr/src/mymaven maven:3.9-eclipse-temurin-17-alpine mvn clean package -DskipTests

echo.
echo [2/2] Levantando los contenedores de los Microservicios...
echo.
docker-compose up --build

echo.
echo Proceso finalizado.
pause

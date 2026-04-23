@echo off
echo ===== Iniciando Eureka Server =====
start "eureka" mvn -f eureka spring-boot:run

timeout /t 5 /nobreak > nul

echo ===== Iniciando Microservicios =====
start "ms-usuarios" mvn -f ms-usuarios spring-boot:run
start "ms-catalogo" mvn -f ms-catalogo spring-boot:run
start "ms-inventario" mvn -f ms-inventario spring-boot:run
start "ms-compras" mvn -f ms-compras spring-boot:run
start "ms-pagos" mvn -f ms-pagos spring-boot:run
start "ms-pedidos" mvn -f ms-pedidos spring-boot:run
start "ms-recomendaciones" mvn -f ms-recomendaciones spring-boot:run
start "ms-resenas" mvn -f ms-resenas spring-boot:run
start "ms-notificaciones" mvn -f ms-notificaciones spring-boot:run
start "ms-descuentos" mvn -f ms-descuentos spring-boot:run
rem Agrega aqui los demas microservicios si necesitas

echo Todos los servicios han sido lanzados.

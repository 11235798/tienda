@echo off
echo ===== Iniciando Eureka Server =====
start "EUREKA" java -jar eureka\target\cl.triskeledu-eureka-1.0-SNAPSHOT.jar --spring.profiles.active=test

timeout /t 5 /nobreak > nul

echo ===== Iniciando Microservicios =====
start "MS-USUARIOS" java -jar ms-usuarios\target\cl.triskeledu-usuarios-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
start "MS-CATALOGO" java -jar ms-catalogo\target\cl.triskeledu-catalogo-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
start "MS-INVENTARIO" java -jar ms-inventario\target\cl.triskeledu-inventario-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
start "MS-COMPRAS" java -jar ms-compras\target\cl.triskeledu-compras-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
start "MS-PAGOS" java -jar ms-pagos\target\cl.triskeledu-pagos-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
start "MS-PEDIDOS" java -jar ms-pedidos\target\cl.triskeledu-pedidos-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
start "MS-RECOMENDACIONES" java -jar ms-recomendaciones\target\cl.triskeledu-recomendaciones-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
start "MS-RESENAS" java -jar ms-resenas\target\cl.triskeledu-resenas-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
start "MS-NOTIFICACIONES" java -jar ms-notificaciones\target\cl.triskeledu-notificaciones-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
start "MS-DESCUENTOS" java -jar ms-descuentos\target\cl.triskeledu-descuentos-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
rem Agrega aqui los demas microservicios si necesitas

echo Todos los servicios han sido lanzados.

@echo off
echo.
echo === COMPILANDO MICROSERVICIOS ===
echo.
call cd C:\tienda-test\ms-usuarios
call mvn clean install -U
call cd C:\tienda-test\ms-catalogo
call mvn clean install -U
call cd C:\tienda-test\ms-inventario
call mvn clean install -U
call cd C:\tienda-test\ms-compras
call mvn clean install -U
call cd C:\tienda-test\ms-pagos
call mvn clean install -U
call cd C:\tienda-test\ms-pedidos
call mvn clean install -U
call cd C:\tienda-test\ms-recomendaciones
call mvn clean install -U
call cd C:\tienda-test\ms-resenas
call mvn clean install -U
call cd C:\tienda-test\ms-notificaciones
call mvn clean install -U
call cd C:\tienda-test\ms-descuentos
call mvn clean install -U
echo.
echo === COMPILACION COMPLETADA ===
pause

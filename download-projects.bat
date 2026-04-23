@echo off
echo Descargando microservicios Spring Boot...
echo.
echo Descargando eureka.zip...
curl -o eureka.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=eureka&groupId=cl.triskeledu&artifactId=cl-triskeledu-eureka&name=tienda-eureka&description=servicio-eureka&packageName=cl.triskeledu.eureka&packaging=jar&javaVersion=21&dependencies=cloud-eureka-server,devtools"
echo.
echo Descargando ms-usuarios.zip...
curl -o ms-usuarios.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=ms-usuarios&groupId=cl.triskeledu&artifactId=cl-triskeledu-usuarios&name=tienda-usuarios&description=servicio-usuarios&packageName=cl.triskeledu.usuarios&packaging=jar&javaVersion=21&dependencies=web,data-jpa,lombok,postgresql,cloud-feign"
echo.
echo Descargando ms-catalogo.zip...
curl -o ms-catalogo.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=ms-catalogo&groupId=cl.triskeledu&artifactId=cl-triskeledu-catalogo&name=tienda-catalogo&description=servicio-catalogo&packageName=cl.triskeledu.catalogo&packaging=jar&javaVersion=21&dependencies=web,data-jpa,lombok,postgresql,cloud-feign"
echo.
echo Descargando ms-inventario.zip...
curl -o ms-inventario.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=ms-inventario&groupId=cl.triskeledu&artifactId=cl-triskeledu-inventario&name=tienda-inventario&description=servicio-inventario&packageName=cl.triskeledu.inventario&packaging=jar&javaVersion=21&dependencies=web,data-jpa,lombok,postgresql,cloud-feign"
echo.
echo Descargando ms-compras.zip...
curl -o ms-compras.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=ms-compras&groupId=cl.triskeledu&artifactId=cl-triskeledu-compras&name=tienda-compras&description=servicio-compras&packageName=cl.triskeledu.compras&packaging=jar&javaVersion=21&dependencies=web,data-jpa,lombok,postgresql,cloud-feign"
echo.
echo Descargando ms-pagos.zip...
curl -o ms-pagos.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=ms-pagos&groupId=cl.triskeledu&artifactId=cl-triskeledu-pagos&name=tienda-pagos&description=servicio-pagos&packageName=cl.triskeledu.pagos&packaging=jar&javaVersion=21&dependencies=web,data-jpa,lombok,postgresql,cloud-feign"
echo.
echo Descargando ms-pedidos.zip...
curl -o ms-pedidos.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=ms-pedidos&groupId=cl.triskeledu&artifactId=cl-triskeledu-pedidos&name=tienda-pedidos&description=servicio-pedidos&packageName=cl.triskeledu.pedidos&packaging=jar&javaVersion=21&dependencies=web,data-jpa,lombok,postgresql,cloud-feign"
echo.
echo Descargando ms-recomendaciones.zip...
curl -o ms-recomendaciones.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=ms-recomendaciones&groupId=cl.triskeledu&artifactId=cl-triskeledu-recomendaciones&name=tienda-recomendaciones&description=servicio-recomendaciones&packageName=cl.triskeledu.recomendaciones&packaging=jar&javaVersion=21&dependencies=web,data-jpa,lombok,postgresql,cloud-feign"
echo.
echo Descargando ms-resenas.zip...
curl -o ms-resenas.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=ms-resenas&groupId=cl.triskeledu&artifactId=cl-triskeledu-resenas&name=tienda-resenas&description=servicio-resenas&packageName=cl.triskeledu.resenas&packaging=jar&javaVersion=21&dependencies=web,data-jpa,lombok,postgresql,cloud-feign"
echo.
echo Descargando ms-notificaciones.zip...
curl -o ms-notificaciones.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=ms-notificaciones&groupId=cl.triskeledu&artifactId=cl-triskeledu-notificaciones&name=tienda-notificaciones&description=servicio-notificaciones&packageName=cl.triskeledu.notificaciones&packaging=jar&javaVersion=21&dependencies=web,data-jpa,lombok,postgresql,cloud-feign"
echo.
echo Descargando ms-descuentos.zip...
curl -o ms-descuentos.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=ms-descuentos&groupId=cl.triskeledu&artifactId=cl-triskeledu-descuentos&name=tienda-descuentos&description=servicio-descuentos&packageName=cl.triskeledu.descuentos&packaging=jar&javaVersion=21&dependencies=web,data-jpa,lombok,postgresql,cloud-feign"
echo.
echo Descarga completada.
pause

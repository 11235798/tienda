package cl.triskeledu.recomendaciones.kafka;

//Añadir esto en otros micrioservicios para que escuchen a este:

//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;

//import cl.triskeledu.recomendaciones.kafka.events.RecomendacionCreadaEvent;

//@Service
//public class RecomendacionConsumer {

//    @KafkaListener(
//            topics = "recomendacion-creada",
//            groupId = "notificaciones")
//    public void consumir(
//            RecomendacionCreadaEvent event){

//        System.out.println(
//            "Nueva recomendación recibida");

//    }

//}

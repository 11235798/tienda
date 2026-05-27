package cl.triskeledu.catalogo.event;

// import cl.triskeledu.common.event.LibroCreatedEvent;
// import cl.triskeledu.common.event.LibroDeletedEvent;
// import cl.triskeledu.common.event.LibroEvent;
// import cl.triskeledu.common.event.LibroUpdatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CategoriaEventProducer {

    private static final String TOPIC_BASE = "catalogo.categoria";
    private static final String NOMBRE_NOT_NULL = "El nombre no puede ser null";
    private static final String TOPIC_NOT_NULL = "El topic no puede ser null";

    // private final KafkaTemplate<String, CategoriaEvent> kafkaTemplate;

    // private void send(CategoriaEvent event, String eventType) {
    //     String topic = Objects.requireNonNull
    //         (String.format("%s.%s", TOPIC_BASE, eventType),
    //         TOPIC_NOT_NULL);
    //     String nombre  = Objects.requireNonNull
    //         (event.getIsbn(), NOMBRE_NOT_NULL);

    //     log.debug("********************");
    //     log.debug("");
    //     log.debug("Enviando evento Kafka → topic: {}, key: {}", topic, nombre);
    //     log.debug("");
    //     log.debug("********************");

    //     kafkaTemplate.send(topic, nombre, event);
    // }

    // public void sendCreated(CategoriaCreatedEvent event) {
    //     send(event, "created");
    // }

    //     public void sendUpdated(CategoriaUpdatedEvent event) {
    //     send(event, "updated");
    // }

    // public void sendDeleted(CategoriaDeletedEvent event) {
    //     send(event, "deleted");
    // }
}
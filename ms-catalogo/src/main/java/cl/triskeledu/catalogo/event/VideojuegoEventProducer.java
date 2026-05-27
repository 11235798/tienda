package cl.triskeledu.catalogo.event;

// import cl.triskeledu.common.event.VideojuegoCreatedEvent;
// import cl.triskeledu.common.event.VideojuegoDeletedEvent;
// import cl.triskeledu.common.event.VideojuegoEvent;
// import cl.triskeledu.common.event.VideojuegoUpdatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class VideojuegoEventProducer {
    private static final String TOPIC_BASE = "catalogo.videojuego";
    private static final String SKU_NOT_NULL = "El sku no puede ser null";
    private static final String TOPIC_NOT_NULL = "El topic no puede ser null";
    
    // private final KafkaTemplate<String, VideojuegoEvent> kafkaTemplate;

    // private void send(VideojuegoEvent event, String eventType) {
    //     String topic = Objects.requireNonNull
    //         (String.format("%s.%s", TOPIC_BASE, eventType),
    //         TOPIC_NOT_NULL);
    //     String sku = Objects.requireNonNull
    //         (event.getSku, SKU_NOT_NULL);

    //     log.debug("*******");
    //     log.debug("");
    //     log.debug("Enviando evento Kafka → topic: {}, ket: {}", topic, sku);
    //     log.debug("");
    //     log.debug("*******");

    //     kafkaTemplate.send(topic, sku, event);
    // }

    // public void sendCreated(VideojuegoCreatedEvent event) {
    //     send(event, "created");
    // }

    // public void sendUpdated(VideojuegoUpdatedEvent event) {
    //     send(event, "updated");
    // }

    // public void sendDeleted(VideojuegoDeletedEvent event) {
    //     send(event, "deleted");
    // }
}

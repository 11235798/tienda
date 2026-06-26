package cl.triskeledu.resenas.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import cl.triskeledu.resenas.kafka.events.ResenaActualizadaEvent;
import cl.triskeledu.resenas.kafka.events.ResenaCreadaEvent;
import cl.triskeledu.resenas.kafka.events.ResenaEliminadaEvent;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void enviarResenaCreada(ResenaCreadaEvent event) {

        kafkaTemplate.send(
            "resenas-creadas",
            event
        );
    }

    public void enviarResenaEliminada(ResenaEliminadaEvent event) {

        kafkaTemplate.send(
            "resenas-eliminadas",
            event
        );
    }

    public void enviarResenaActualizada(ResenaActualizadaEvent event) {

        kafkaTemplate.send(
            "resenas-actualizadas",
            event
        );
    }
}
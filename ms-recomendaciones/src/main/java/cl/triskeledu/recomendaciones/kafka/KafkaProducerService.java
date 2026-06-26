package cl.triskeledu.recomendaciones.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import cl.triskeledu.recomendaciones.kafka.events.RecomendacionActualizadaEvent;
import cl.triskeledu.recomendaciones.kafka.events.RecomendacionCreadaEvent;
import cl.triskeledu.recomendaciones.kafka.events.RecomendacionEliminadaEvent;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void enviarRecomendacionCreada(
            RecomendacionCreadaEvent event){

        kafkaTemplate.send(
                "recomendacion-creada",
                event);
    }

    public void enviarRecomendacionActualizada(
            RecomendacionActualizadaEvent event){

        kafkaTemplate.send(
                "recomendacion-actualizada",
                event);
    }

    public void enviarRecomendacionEliminada(
            RecomendacionEliminadaEvent event){

        kafkaTemplate.send(
                "recomendacion-eliminada",
                event);
    }

}
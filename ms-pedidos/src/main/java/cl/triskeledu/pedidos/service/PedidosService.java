package cl.triskeledu.pedidos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.triskeledu.pedidos.dto.PedidosResponse;
import cl.triskeledu.pedidos.repository.PedidosRepository;

@Service
public class PedidosService {
private PedidosRepository pedidosRepository;

    // Obtener todos
    public List<PedidosResponse> findAll() {
        return pedidosRepository.findAll();
    }

    // Buscar por ID
    public PedidosResponse findById(Long id) {
        return pedidosRepository.findById(id);
    }

    // Guardar
    public PedidosResponse save(PedidosResponse pedidos) {
        return pedidosRepository.save(pedidos);
    }

    // Eliminar
    public Boolean deleteById(Long id) {
        pedidosRepository.deleteById(id);
        return true;
    }
}

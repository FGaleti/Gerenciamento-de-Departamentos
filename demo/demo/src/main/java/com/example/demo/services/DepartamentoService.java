package services;

import model.Departamento;
import org.springframework.stereotype.Service;
import repositories.DepartamentoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoService {

    private final DepartamentoRepository departamentoRepository;

    public DepartamentoService(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    public Departamento criar(Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    public List<Departamento> listarTodos() {
        return departamentoRepository.findAll();
    }

    public Optional<Departamento> buscarPorId(Long id) {
        return departamentoRepository.findById(id);
    }

    public boolean deletarPorId(Long id) {
        if (!departamentoRepository.existsById(id)) {
            return false;
        }
        departamentoRepository.deleteById(id);
        return true;
    }
}
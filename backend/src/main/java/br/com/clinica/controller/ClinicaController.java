package br.com.clinica.controller;

import br.com.clinica.model.Animal;
import br.com.clinica.model.Servico;
import br.com.clinica.repository.AnimalRepository;
import br.com.clinica.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClinicaController {

    @Autowired
    private AnimalRepository animalRepo;

    @Autowired
    private ServicoRepository servicoRepo;

    @PostMapping("/animais")
    public Animal cadastrarAnimal(@RequestBody Animal animal) {
        return animalRepo.save(animal);
    }

    @GetMapping("/animais")
    public List<Animal> listarAnimais() {
        return animalRepo.findAll();
    }

    @PostMapping("/servicos")
    public Servico registrarServico(@RequestBody Servico servico) {
        return servicoRepo.save(servico);
    }

    @GetMapping("/animais/{id}/historico")
    public List<Servico> buscarHistorico(@PathVariable Long id) {
        return servicoRepo.findByAnimalIdOrderByDataServicoDesc(id);
    }
}
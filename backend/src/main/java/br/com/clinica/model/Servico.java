package br.com.clinica.model;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

import jakarta.persistence.*;

@Entity
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;

    private String tipoServico;
    private String descricao;
    private String dataServico;
    private Double valor;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Animal getAnimal() { return animal; }
    public void setAnimal(Animal animal) { this.animal = animal; }
    public String getTipoServico() { return tipoServico; }
    public void setTipoServico(String tipoServico) { this.tipoServico = tipoServico; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getDataServico() { return dataServico; }
    public void setDataServico(String dataServico) { this.dataServico = dataServico; }
    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }
    private LocalDate converterDataServico() {
        if (this.dataServico == null || this.dataServico.isBlank()) {
            return null;
        }
        try {
            return LocalDate.parse(this.dataServico.trim());
        } catch (DateTimeParseException e1) {
            try {
                DateTimeFormatter formatoBr = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                return LocalDate.parse(this.dataServico.trim(), formatoBr);
            } catch (DateTimeParseException e2) {
                return null;
            }
        }
    }

    @Transient
    public LocalDate getDataRetornoSugerida() {
        LocalDate dataBase = converterDataServico();
        if (dataBase == null) {
            return null;
        }
        int diasParaRetorno = (this.tipoServico != null && this.tipoServico.toLowerCase().contains("vacina"))
                ? 365
                : 30;
        return dataBase.plusDays(diasParaRetorno);
    }

    @Transient
    public String getStatusRetorno() {
        LocalDate retorno = getDataRetornoSugerida();
        if (retorno == null) {
            return "SEM_PREVISAO";
        }
        long diasRestantes = ChronoUnit.DAYS.between(LocalDate.now(), retorno);
        if (diasRestantes < 0) {
            return "ATRASADO";
        } else if (diasRestantes <= 7) {
            return "RETORNO_PROXIMO";
        } else {
            return "EM_DIA";
        }
    }
}
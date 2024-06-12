package br.uscal.arqui;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long livroId;
    private Long usuarioId;
    private LocalDate emprestimoData;
    private LocalDate devolucaoData;
    public Long getId() {
            return id;
        }
    public void setId(Long id) {
            this.id = id;
        }
    public Long getLivroId() {
            return livroId;
        }
    public void setLivroId(Long livroId) {
            this.livroId = livroId;
        }
    public Long getUsuarioId() {
            return usuarioId;
        }
    public void setUsuarioId(Long usuarioId) {
            this.usuarioId = usuarioId;
        }
    public LocalDate getEmprestimoData() {
            return emprestimoData;
        }
    public void setEmprestimoData(LocalDate emprestimoData) {
            this.emprestimoData = emprestimoData;
        }
    public LocalDate getDevolucaoData() {
            return devolucaoData;
        }
    public void setDevolucaoData(LocalDate devolucaoData) {
            this.devolucaoData = devolucaoData;
        }

}

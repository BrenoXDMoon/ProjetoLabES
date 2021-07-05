package br.com.fatec.chopperhouse.repository;
import br.com.fatec.chopperhouse.models.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Integer> {
}

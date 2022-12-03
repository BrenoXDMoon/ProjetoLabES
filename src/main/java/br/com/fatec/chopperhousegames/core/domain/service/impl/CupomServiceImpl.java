package br.com.fatec.chopperhousegames.core.domain.service.impl;

import br.com.fatec.chopperhousegames.core.domain.entity.Cliente;
import br.com.fatec.chopperhousegames.core.domain.entity.Cupom;
import br.com.fatec.chopperhousegames.core.domain.entity.TipoCupom;
import br.com.fatec.chopperhousegames.core.domain.service.CupomService;
import br.com.fatec.chopperhousegames.core.repository.CupomRepository;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

@Service
public class CupomServiceImpl implements CupomService {

    private final CupomRepository repository;

    private final Random random = SecureRandom.getInstanceStrong();

    public CupomServiceImpl(CupomRepository repository) throws NoSuchAlgorithmException {
        this.repository = repository;
    }

    @Override
    public List<Cupom> listarTodosCupons() {
        return repository.findAll();
    }

    @Override
    public Cupom buscarCupomPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Cupom não encontrado"));
    }

    @Override
    public void salvarCupomTroca(Cliente cliente, Double valor) {
        Cupom cupom = new Cupom();

        String codigo = random.ints(48, 122 + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(10)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        cupom.setCodigo(codigo);
        cupom.setQuantidade(1);
        cupom.setCliente(cliente);
        cupom.setTipoCupom(TipoCupom.TROCA);
        cupom.setValor(valor);

        repository.save(cupom);
    }
}
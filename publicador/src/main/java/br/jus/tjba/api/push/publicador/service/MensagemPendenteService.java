package br.jus.tjba.api.push.publicador.service;

import br.jus.tjba.api.push.publicador.model.MensagemPendente;
import br.jus.tjba.api.push.publicador.repository.MensagemPendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MensagemPendenteService {

    @Autowired
    private MensagemPendenteRepository mensagemPendenteRepository;

    public List<MensagemPendente> findAll() {
        return mensagemPendenteRepository.findAll();
    }

    public MensagemPendente save(MensagemPendente mensagemPendente) {
        return mensagemPendenteRepository.save(mensagemPendente);
    }
}

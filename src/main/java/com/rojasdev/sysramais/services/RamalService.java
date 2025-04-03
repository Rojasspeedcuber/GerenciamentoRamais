package com.rojasdev.sysramais.services;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.rojasdev.sysramais.models.Ramal;
import com.rojasdev.sysramais.models.Usuario;
import com.rojasdev.sysramais.repositories.RamalRepository;
import com.rojasdev.sysramais.repositories.UsuarioRepository;

@Service
public class RamalService {

    @Autowired
    private RamalRepository ramalRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Map<String, String> getAvailableExtension() {
        Optional<Ramal> ramal = ramalRepository.findFirstByDisponivelTrue();
        return ramal.map(r -> Map.of("extension", r.getNumero()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum ramal disponível"));
    }

    public Map<String, String> login(String usuarioNome, String extension) {
        Ramal ramal = ramalRepository.findByNumero(extension)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ramal não encontrado"));

        if (!ramal.isDisponivel()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ramal indisponível");
        }

        // Verifica se o usuário já existe, senão cria um novo
        Usuario usuario = usuarioRepository.findByNome(usuarioNome)
                .orElseGet(() -> usuarioRepository.save(new Usuario(usuarioNome)));

        // Associa apenas o nome do usuário ao ramal (MongoDB não usa relações diretas entre documentos)
        ramal.setUsuario(usuario.getNome());
        ramal.setDisponivel(false);
        ramalRepository.save(ramal);

        return Map.of("message", "Usuário logado com sucesso", "extension", ramal.getNumero());
    }


    public Map<String, String> logout(String extension) {
        Ramal ramal = ramalRepository.findByNumero(extension)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ramal não encontrado"));

        if (ramal.isDisponivel()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ramal já está disponível");
        }


        ramal.setUsuario(null);
        ramal.setDisponivel(true);
        ramalRepository.save(ramal);

        return Map.of("message", "Usuário deslogado com sucesso");
    }
}


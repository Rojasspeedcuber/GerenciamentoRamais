package com.rojasdev.sysramais.repositories;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.rojasdev.sysramais.models.Ramal;

@Repository
public interface RamalRepository extends MongoRepository<Ramal, String> {
    Optional<Ramal> findFirstByDisponivelTrue();
    Optional<Ramal> findByNumero(String numero);
}

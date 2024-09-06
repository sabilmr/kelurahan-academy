package org.project.kelurahanacademy.kelurahan.service;

import lombok.extern.slf4j.Slf4j;
import org.project.kelurahanacademy.kelurahan.model.entity.RwEntity;
import org.project.kelurahanacademy.kelurahan.model.request.RWReq;
import org.project.kelurahanacademy.kelurahan.model.response.KelurahanRes;
import org.project.kelurahanacademy.kelurahan.model.response.RWRes;
import org.project.kelurahanacademy.kelurahan.repository.RWRepo;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RWServiceImpl implements RWService {
    private final RWRepo rwRepo;

    public RWServiceImpl(RWRepo rwRepo) {
        this.rwRepo = rwRepo;
    }

    @Override
    public List<RWRes> get() {
        List<RwEntity> rwEntities = rwRepo.findAll();
        if (rwEntities.isEmpty()) {
            return Collections.emptyList();
        }
        return rwEntities.stream()
                .map(RWRes::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RWRes> getById(String id) {
        RwEntity rwEntity = rwRepo.findById(id).orElse(null);
        if (rwEntity == null) {
            return Optional.empty();
        }

        RWRes result = new RWRes(rwEntity);
        return Optional.of(result);
    }

    @Override
    public Optional<RWRes> save(RWReq request) {
        return Optional.empty();
    }

    @Override
    public Optional<RWRes> update(RWReq request, String id) {
        return Optional.empty();
    }

    @Override
    public Optional<RWRes> delete(String id) {
        return Optional.empty();
    }
}

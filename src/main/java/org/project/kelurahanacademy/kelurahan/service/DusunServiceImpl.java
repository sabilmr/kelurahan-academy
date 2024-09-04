package org.project.kelurahanacademy.kelurahan.service;

import lombok.extern.slf4j.Slf4j;
import org.project.kelurahanacademy.kelurahan.model.entity.DusunEntity;
import org.project.kelurahanacademy.kelurahan.model.request.DusunReq;
import org.project.kelurahanacademy.kelurahan.model.response.DusunRes;
import org.project.kelurahanacademy.kelurahan.repository.DusunRepo;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DusunServiceImpl implements DusunService {
    private final DusunRepo dusunRepo;

    public DusunServiceImpl(DusunRepo dusunRepo) {
        this.dusunRepo = dusunRepo;
    }

    @Override
    public List<DusunRes> get() {
        List<DusunEntity> result = dusunRepo.findAll();
        if (result.isEmpty()) {
            return Collections.emptyList();
        }
        return result.stream()
                .map(DusunRes::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DusunRes> getById(String id) {
        DusunEntity dusunEntity = dusunRepo.findById(id).orElse(null);
        if (dusunEntity == null) {
            return Optional.empty();
        }
        DusunRes dusunRes = new DusunRes(dusunEntity);
        return Optional.of(dusunRes);
    }

    @Override
    public Optional<DusunRes> save(DusunReq request) {
        return Optional.empty();
    }

    @Override
    public Optional<DusunRes> update(DusunReq request, String id) {
        return Optional.empty();
    }

    @Override
    public Optional<DusunRes> delete(String id) {
        return Optional.empty();
    }
}

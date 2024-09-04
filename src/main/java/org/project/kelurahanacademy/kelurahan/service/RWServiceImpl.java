package org.project.kelurahanacademy.kelurahan.service;

import lombok.extern.slf4j.Slf4j;
import org.project.kelurahanacademy.kelurahan.model.request.RWReq;
import org.project.kelurahanacademy.kelurahan.model.response.RWRes;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RWServiceImpl implements RWService{
    @Override
    public List<RWRes> get() {
        return List.of();
    }

    @Override
    public Optional<RWRes> getById(String id) {
        return Optional.empty();
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

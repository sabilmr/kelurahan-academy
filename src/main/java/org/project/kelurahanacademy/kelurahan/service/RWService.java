package org.project.kelurahanacademy.kelurahan.service;

import org.project.kelurahanacademy.kelurahan.model.request.RWReq;
import org.project.kelurahanacademy.kelurahan.model.response.RWRes;

import java.util.List;
import java.util.Optional;

public interface RWService {
    List<RWRes> get();
    Optional<RWRes> getById(String id);
    Optional<RWRes> save(RWReq request);
    Optional<RWRes> update(RWReq request, String id);
    Optional<RWRes> delete(String id);
}

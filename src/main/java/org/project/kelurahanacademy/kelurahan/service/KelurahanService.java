package org.project.kelurahanacademy.kelurahan.service;

import org.project.kelurahanacademy.kelurahan.model.request.KelurahanReq;
import org.project.kelurahanacademy.kelurahan.model.response.KelurahanRes;

import java.util.List;
import java.util.Optional;

public interface KelurahanService {
    List<KelurahanRes> get();
    Optional<KelurahanRes> getById(String id);
    Optional<KelurahanRes> save(KelurahanReq request);
    Optional<KelurahanRes> update(KelurahanReq request, String id);
    Optional<KelurahanRes> delete(String id);
}

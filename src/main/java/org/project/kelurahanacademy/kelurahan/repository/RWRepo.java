package org.project.kelurahanacademy.kelurahan.repository;

import org.project.kelurahanacademy.kelurahan.model.entity.RwEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RWRepo extends JpaRepository<RwEntity, String> {
}

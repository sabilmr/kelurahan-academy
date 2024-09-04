package org.project.kelurahanacademy.kelurahan.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.kelurahanacademy.kelurahan.model.entity.RwEntity;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RWReq {
    private String id;
    private String name;
    private String nameRW;

    public RWReq(RwEntity entity) {
        BeanUtils.copyProperties(entity, this);
    }
}

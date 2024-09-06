package org.project.kelurahanacademy.kelurahan.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.kelurahanacademy.kelurahan.model.entity.DusunEntity;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DusunReq {
    private String id;
    private String name;
    private String kelurahanId;
    private List<RWReq> rw = new ArrayList<>();

    public DusunReq(DusunEntity entity) {
        BeanUtils.copyProperties(entity, this);

        if (!entity.getRwEntities().isEmpty()) {
            this.rw = entity.getRwEntities().stream()
                    .map(RWReq::new)
                    .collect(Collectors.toList());
        }
    }
}

package org.project.kelurahanacademy.kelurahan.model.response;

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
public class DusunRes {
    private String id;
    private String name;
    private String kelurahanId;
    private String kelurahanName;
    private List<RWRes>  rw = new ArrayList<>();

    public DusunRes(DusunEntity entity){
        BeanUtils.copyProperties(entity, this);

        if (!entity.getRwEntities().isEmpty()) {
            this.rw = entity.getRwEntities().stream()
                    .map(RWRes::new)
                    .collect(Collectors.toList());
        }
    }
}

package kz.kegoc.bln.entity.dict.translate;

import kz.kegoc.bln.entity.adm.User;
import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.EnergySource;
import kz.kegoc.bln.entity.dict.Substation;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(of= {"id"})
public class EnergySourceTranslate implements HasId, HasName, HasLang, HasDates, HasUser {
    private Long id;

    @NotNull
    private Lang lang;

    @NotNull @Size(max = 100)
    private String name;

    @NotNull @Size(max = 10)
    private String shortName;

    @Size(max = 300)
    private String address;

    @NotNull
    private EnergySource energySource;

    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private User createBy;
    private User lastUpdateBy;
}

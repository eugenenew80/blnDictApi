package kz.kegoc.bln.entity.dict.translate;

import kz.kegoc.bln.entity.common.HasId;
import kz.kegoc.bln.entity.common.HasLang;
import kz.kegoc.bln.entity.common.HasName;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.EnergySource;
import kz.kegoc.bln.entity.dict.Substation;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(of= {"id"})
public class EnergySourceTranslate implements HasId, HasName, HasLang {
    private Long id;

    @NotNull
    private Lang lang;

    @NotNull
    private EnergySource energySource;

    @NotNull @Size(max = 100)
    private String name;

    @NotNull @Size(max = 10)
    private String shortName;

    private String address;
}

package kz.kegoc.bln.entity.dict.translate;

import kz.kegoc.bln.entity.common.HasId;
import kz.kegoc.bln.entity.common.HasLang;
import kz.kegoc.bln.entity.common.HasName;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.EnergyNode;
import kz.kegoc.bln.entity.dict.PowerLine;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(of= {"id"})
public class PowerLineTranslate implements HasId, HasName, HasLang {
    private Long id;

    @NotNull
    private Lang lang;

    @NotNull @Size(max = 30)
    private String shortName;

    @NotNull @Size(max = 100)
    private String name;

    @Size(max = 300)
    private String propertyBoundary;

    @NotNull
    private PowerLine powerLine;
}
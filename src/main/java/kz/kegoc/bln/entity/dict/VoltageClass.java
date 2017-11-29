package kz.kegoc.bln.entity.dict;

import kz.kegoc.bln.entity.common.HasId;
import kz.kegoc.bln.entity.common.HasName;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.translate.VoltageClassTranslate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Map;

@Data
@EqualsAndHashCode(of= {"id"})
public class VoltageClass implements HasId, HasName {
	private Long id;

	@NotNull @Size(max = 10)
	private String name;

	@NotNull
	private Double value;

	private Map<Lang, VoltageClassTranslate> translations;
	private Lang lang;
}

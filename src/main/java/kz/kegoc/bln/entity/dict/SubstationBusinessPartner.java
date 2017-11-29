package kz.kegoc.bln.entity.dict;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import kz.kegoc.bln.entity.common.HasId;
import kz.kegoc.bln.entity.common.HasLang;
import kz.kegoc.bln.entity.common.Lang;
import lombok.*;

@Data
@EqualsAndHashCode(of= {"id"})
public class SubstationBusinessPartner implements HasId, HasLang {
	private Long id;
	
	@NotNull
	private Substation substation;

	@NotNull
	private BusinessPartner businessPartner;
	
	private LocalDate startDate;
	private LocalDate endDate;
	private Lang lang;
}

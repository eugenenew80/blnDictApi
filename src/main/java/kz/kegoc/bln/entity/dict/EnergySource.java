package kz.kegoc.bln.entity.dict;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.*;

import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.translate.EnergySourceTranslate;
import lombok.*;
import kz.kegoc.bln.entity.adm.User;

@Data
@EqualsAndHashCode(of= {"id"})
public class EnergySource implements HasId, HasName, HasLang, HasDates, HasUser, HasOrg {
	private Long id;

	@NotNull @Size(max = 100)
	private String name;

	@NotNull @Size(max = 30)
	private String shortName;

	@Size(max = 300)
	private String address;

	private Double installedPower;
	private Boolean largeElectricityProducer;

	@NotNull
	private EnergySourceType energySourceType;

	@NotNull
	private VoltageClass voltageClass;

	@NotNull
	private Organization org;

	private EnergyNode energyNode;
	private EnergyDistrict energyDistrict;
	private BusinessPartner businessPartner;
	private LocalDateTime createDate;
	private LocalDateTime lastUpdateDate;
	private User createBy;
	private User lastUpdateBy;
	private List<EnergySourceMeteringPoint> meteringPoints;
	private Map<Lang, EnergySourceTranslate> translations;
	private Lang lang;
}

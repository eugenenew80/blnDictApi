package kz.kegoc.bln.entity.dict.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import kz.kegoc.bln.entity.common.Lang;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnergySourceDto {
	private Long id;
	private String name;
	private String shortName;
	private String address;
	private Double installedPower;
	private Boolean largeElectricityProducer;
	private Long energySourceTypeId;
	private String energySourceTypeName;
	private Long voltageClassId;
	private String voltageClassName;
	private Double voltageClassValue;
	private Long orgId;
	private String orgName;
	private Long businessPartnerId;
	private String businessPartnerName;
	private Lang lang;
}

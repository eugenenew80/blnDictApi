package kz.kegoc.bln.entity.dict.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import kz.kegoc.bln.entity.common.Lang;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubstationDto {
	private Long id;
	private String name;
	private String shortName;
	private Long substationTypeId;
	private String substationTypeName;
	private Long voltageClassId;
	private Long voltageClassName;
	private Double voltageClassValue;
	private String address;
	private Lang lang;
}

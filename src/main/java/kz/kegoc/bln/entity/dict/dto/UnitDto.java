package kz.kegoc.bln.entity.dict.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import kz.kegoc.bln.entity.common.Lang;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UnitDto {
	private Long id;
	private Lang lang;
	private String code;
	private String name;
	private String shortName;
	private Long baseUnitId;
	private String baseUnitName;
	private Double factor;
}

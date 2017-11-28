package kz.kegoc.bln.entity.dict.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.common.OrganizationType;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrganizationDto {
	private Long id;
	private Lang lang;
	private String name;
	private String bin;
	private OrganizationType orgType;
	private Long parentOrganizationId;
	private String parentOrganizationName;
}
package kz.kegoc.bln.entity.dict.dto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import kz.kegoc.bln.entity.common.Lang;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MeterDto {
	private Long id;
	private String name;
	private String manufacturer;
	private String serialNumber;
	private Double ratedCurrent;
	private Double ratedVoltage;
	private Double accuracyClass;
	private Double minimumLoad;
	private Long businessPartnerId;
	private String businessPartnerShortName;
	private LocalDate lastVerificationDate;
	private LocalDate nextVerificationDate;
	private Long totalDigitsNumber;
	private Long digitsAfterDecimalPoint;
	private Boolean parameterAp;
	private Boolean parameterAm;
	private Boolean parameterRp;
	private Boolean parameterRm;
	private Boolean withdrawn;
	private Long orgId;
	private String orgName;
	private Lang lang;
}

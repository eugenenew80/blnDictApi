package kz.kegoc.bln.ejb.converter.jpa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Converter(autoApply = true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Date> { 
	
	public Date convertToDatabaseColumn(LocalDateTime localDateTime) {
		return (localDateTime == null ? null : Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()));
	}
	
	public LocalDateTime convertToEntityAttribute(Date date) {
		return (date == null ? null : LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
	}
}
package kz.kegoc.bln.entity.common;

import java.time.LocalDateTime;

public interface HasDates  {
	LocalDateTime getCreateDate();
	LocalDateTime getLastUpdateDate();
	
	void setCreateDate(LocalDateTime createDate);
	void setLastUpdateDate(LocalDateTime updateDate) ;
}

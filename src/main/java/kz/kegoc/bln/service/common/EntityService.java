package kz.kegoc.bln.service.common;

import java.util.List;
import kz.kegoc.bln.entity.common.HasId;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.repository.common.query.Query;

public interface EntityService<T extends HasId> {
	List<T> findAll();
	
	List<T> find(Query query);
	
	T findById(Object entityId);
    
	T findByCode(String entityCode);
	
	T findByName(String entityName);
	
	T create(T entity);

	T update(T entity);

    boolean delete(Long entityId);

    void setLang(Lang lang);
}

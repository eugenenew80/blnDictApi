package kz.kegoc.bln.service.common;

import java.util.*;
import java.util.stream.Collectors;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.validation.*;
import kz.kegoc.bln.webapi.filters.SessionContext;
import kz.kegoc.bln.entity.common.HasId;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.exception.EntityNotFoundException;
import kz.kegoc.bln.exception.InvalidArgumentException;
import kz.kegoc.bln.exception.RepositoryNotFoundException;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.repository.common.query.Query;
import kz.kegoc.bln.translator.Translator;

public abstract class AbstractEntityService<T extends HasId> implements EntityService<T> {
    public AbstractEntityService() {}

    public AbstractEntityService(Repository<T> repository) {
        this.repository = repository;
    }
    
    public AbstractEntityService(Repository<T> repository, Validator validator) {
        this(repository);
        this.validator = validator;
    }

	public AbstractEntityService(Repository<T> repository, Validator validator, Filter<T> prePersistFilter) {
		this(repository);
		this.validator = validator;
		this.prePersistFilter = prePersistFilter;
	}

	public AbstractEntityService(Repository<T> repository, Validator validator, Filter<T> prePersistFilter, Translator<T> translator) {
		this(repository);
		this.validator = validator;
		this.prePersistFilter = prePersistFilter;
		this.translator = translator;
	}


	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<T> findAll(SessionContext context) {
		if (repository==null)
			throw new RepositoryNotFoundException();

		Lang lang = context!=null && context.getLang()!=null ? context.getLang() : Lang.RU;

		List<T> list = repository.selectAll();
		if (translator!=null && lang!=null) {
			return list.stream()
				.map(t -> translator.translate(t, lang))
				.collect(Collectors.toList());
		}
		return list;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<T> find(Query query, SessionContext context) {
		if (repository==null)
			throw new RepositoryNotFoundException();

		Lang lang = context!=null && context.getLang()!=null ? context.getLang() : Lang.RU;

		List<T> list = repository.select(query);
		if (translator!=null && lang!=null) {
			return list.stream()
				.map(t -> translator.translate(t, lang))
				.collect(Collectors.toList());
		}
		return list;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public T findById(Object entityId, SessionContext context) {
		if (repository==null)
			throw new RepositoryNotFoundException();

		if (entityId==null)
			throw new InvalidArgumentException();

		T entity = repository.selectById(entityId);
		if (entity==null)
			throw new EntityNotFoundException(entityId);

		Lang lang = context!=null && context.getLang()!=null ? context.getLang() : Lang.RU;
		if (translator!=null && lang!=null)
			entity = translator.translate(entity, lang);

		return entity;
	}


	public T create(T entity, SessionContext context) {
		if (repository==null)
			throw new RepositoryNotFoundException();

		if (entity==null)
			throw new InvalidArgumentException();

		if (entity.getId()!=null)
			throw new InvalidArgumentException(entity);

		if (prePersistFilter !=null)
			entity = prePersistFilter.filter(entity, context);

		if (validator!=null)
			validate(entity);

		return repository.insert(entity);
	}


	public T update(T entity, SessionContext context) {
		if (repository==null)
			throw new RepositoryNotFoundException();

		if (entity==null)
			throw new InvalidArgumentException();

		if (entity.getId()==null)
			throw new InvalidArgumentException(entity);

		if (prePersistFilter !=null)
			entity = prePersistFilter.filter(entity, context);

		if (validator!=null)
			validate(entity);

		return repository.update(entity);
	}

	public boolean delete(Long entityId, SessionContext context) {
		if (repository==null)
			throw new RepositoryNotFoundException();

		if (entityId==null)
			throw new InvalidArgumentException();

		return repository.delete(entityId);
	}

	private void validate(T entity) {
		Set<ConstraintViolation<T>> violations =  validator.validate(entity);
		if (violations.size()>0) {
			ConstraintViolation<T> violation = violations.iterator().next();
			throw new ValidationException(violation.getPropertyPath() + ": " + violation.getMessage());
		}
	}


	protected Repository<T> repository;
	protected Validator validator;
	protected Filter<T> prePersistFilter;
	protected Translator<T> translator;
}

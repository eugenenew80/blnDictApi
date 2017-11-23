package kz.kegoc.bln.webapi.dict;

import java.util.*;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.translator.Translator;
import org.dozer.DozerBeanMapper;
import kz.kegoc.bln.entity.dict.AccountingType;
import kz.kegoc.bln.entity.dict.dto.AccountingTypeDto;
import kz.kegoc.bln.repository.common.query.*;
import kz.kegoc.bln.service.dict.AccountingTypeService;

import static org.apache.commons.lang3.StringUtils.*;

@Stateless
@Path("/dict/dictAccountingType")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class AccountingTypeResourceImpl {

	@GET 
	public Response getAll(@QueryParam("code") String code, @QueryParam("name") String name, @QueryParam("lang") Lang lang) {
		final Lang userLang = (lang!=null ? lang : defLang);

		Query query = QueryImpl.builder()
			.setParameter("code", isNotEmpty(code) ? new MyQueryParam("code", code + "%", ConditionType.LIKE) : null)
			.setParameter("name", isNotEmpty(name) ? new MyQueryParam("name", name + "%", ConditionType.LIKE) : null)
			.setOrderBy("t.id")
			.build();		
		
		List<AccountingTypeDto> list = service.find(query)
			.stream()
			.map(it -> translator.translate(it, userLang))
			.map( it-> mapper.map(it, AccountingTypeDto.class) )
			.collect(Collectors.toList());
		
		return Response.ok()
				.entity(new GenericEntity<Collection<AccountingTypeDto>>(list){})
				.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id, @QueryParam("lang") Lang lang) {
		final Lang userLang = (lang!=null ? lang : defLang);

		AccountingType entity = service.findById(id);
		return Response.ok()
			.entity(mapper.map(translator.translate(entity, userLang), AccountingTypeDto.class))
			.build();		
	}
	

	@GET
	@Path("/byCode/{code}")
	public Response getByCode(@PathParam("code") String code, @QueryParam("lang") Lang lang) {
		final Lang userLang = (lang!=null ? lang : defLang);

		AccountingType entity = service.findByCode(code);
		return Response.ok()
			.entity(mapper.map(translator.translate(entity, userLang), AccountingTypeDto.class))
			.build();
	}
	
	
	@GET
	@Path("/byName/{name}")
	public Response getByName(@PathParam("name") String name, @QueryParam("lang") Lang lang) {
		final Lang userLang = (lang!=null ? lang : defLang);

		AccountingType entity = service.findByName(name);
		return Response.ok()
			.entity(mapper.map(translator.translate(entity, userLang), AccountingTypeDto.class))
			.build();
	}

	
	@POST
	public Response create(AccountingTypeDto entityDto) {
		if (entityDto.getLang()==null)
			entityDto.setLang(defLang);

		AccountingType newEntity = service.create(mapper.map(entityDto, AccountingType.class));
		return Response.ok()
			.entity(mapper.map(translator.translate(newEntity, entityDto.getLang()), AccountingTypeDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, AccountingTypeDto entityDto ) {
		if (entityDto.getLang()==null)
			entityDto.setLang(defLang);

		AccountingType newEntity = service.update(mapper.map(entityDto, AccountingType.class));
		return Response.ok()
			.entity(mapper.map(translator.translate(newEntity, entityDto.getLang()), AccountingTypeDto.class))
			.build();
	}
	
	
	@DELETE 
	@Path("{id : \\d+}") 
	public Response delete(@PathParam("id") Long id) {
		service.delete(id);		
		return Response.noContent()
			.build();
	}
	

	@Inject
	private AccountingTypeService service;

	@Inject
	private DozerBeanMapper mapper;


	@Inject
	private Translator<AccountingType> translator;

	@Inject
	private Lang defLang;
}

package kz.kegoc.bln.webapi.dict;

import kz.kegoc.bln.ejb.mapper.BeanMapper;
import kz.kegoc.bln.webapi.filters.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.BusinessPartner;
import kz.kegoc.bln.entity.dict.dto.BusinessPartnerDto;
import kz.kegoc.bln.service.dict.BusinessPartnerService;
import kz.kegoc.bln.webapi.common.CustomPrincipal;
import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Path("/dict/dictBusinessPartner")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class BusinessPartnerResourceImpl {

	@GET 
	public Response getAll(
		@QueryParam("searchValue") String searchValue,
		@QueryParam("shortName") String shortName,
		@QueryParam("name") String name,
		@HeaderParam("lang") Lang lang
	) {
		if (StringUtils.isNotEmpty(searchValue))
			shortName=searchValue;

		List<BusinessPartnerDto> list = service.find(null, shortName, name, buildSessionContext(lang))
			.stream()
			.map(it-> mapper.getMapper().map(it, BusinessPartnerDto.class))
			.collect(Collectors.toList());
		
		return Response.ok()
			.entity(new GenericEntity<Collection<BusinessPartnerDto>>(list){})
			.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id, @HeaderParam("lang") Lang lang) {
		BusinessPartner entity = service.findById(id, buildSessionContext(lang));
		return Response.ok()
			.entity(mapper.getMapper().map(entity, BusinessPartnerDto.class))
			.build();		
	}
	

	@POST
	public Response create(BusinessPartnerDto entityDto, @HeaderParam("lang") Lang lang) {
		BusinessPartner entity = mapper.getMapper().map(entityDto, BusinessPartner.class);
		BusinessPartner newEntity = service.create(entity, buildSessionContext(lang));

		return Response.ok()
			.entity(mapper.getMapper().map(newEntity, BusinessPartnerDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, BusinessPartnerDto entityDto, @HeaderParam("lang") Lang lang) {
		BusinessPartner entity = mapper.getMapper().map(entityDto, BusinessPartner.class);
		BusinessPartner newEntity = service.update(entity, buildSessionContext(lang));

		return Response.ok()
			.entity(mapper.getMapper().map(newEntity, BusinessPartnerDto.class))
			.build();
	}
	
	
	@DELETE 
	@Path("{id : \\d+}") 
	public Response delete(@PathParam("id") Long id, @HeaderParam("lang") Lang lang) {
		service.delete(id, buildSessionContext(lang));
		return Response.noContent()
			.build();
	}


	@Path("/{businessPartnerId : \\d+}/dictBusinessPartnerContact")
	public ContactResourceImpl getContactResource() {
		return contactResource;
	}

	@Path("/{businessPartnerId : \\d+}/dictBusinessPartnerBankAccount")
	public BankAccountResourceImpl getBankAccountResource() {
		return bankAccountResource;
	}

	@Path("/{businessPartnerId : \\d+}/dictBusinessPartnerContent")
	public BusinessPartnerContentResourceImpl getContentResource() {
		return contentResource;
	}


	private SessionContext buildSessionContext(Lang lang) {
		SessionContext context = new SessionContext();
		context.setLang(lang!=null ? lang : defLang);
		context.setUser(((CustomPrincipal)securityContext.getUserPrincipal()).getUser());
		return context;
	}


	@Inject
	private ContactResourceImpl contactResource;

	@Inject
	private BankAccountResourceImpl bankAccountResource;

	@Inject
	private BusinessPartnerContentResourceImpl contentResource;

	@Inject
	private BusinessPartnerService service;

	@Inject
	private BeanMapper mapper;

	@Inject
	private Lang defLang;

	@Context
	private SecurityContext securityContext;
}

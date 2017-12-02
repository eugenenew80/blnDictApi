package kz.kegoc.bln.webapi.dict;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.Contact;
import kz.kegoc.bln.entity.dict.dto.ContactDto;
import kz.kegoc.bln.service.dict.BusinessPartnerService;
import kz.kegoc.bln.service.dict.ContactService;
import org.dozer.DozerBeanMapper;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class ContactResourceImpl {

	@GET
	public Response getAll(@PathParam("businessPartnerId") Long businessPartnerId, @QueryParam("lang") Lang lang) {
		final Lang userLang = (lang!=null ? lang : defLang);
		service.setLang(userLang);

		List<ContactDto> list = businessPartnerService.findById(businessPartnerId)
			.getContacts()
			.stream()
			.map( it-> mapper.map(it, ContactDto.class) )
			.collect(Collectors.toList());		
	
		return Response.ok()
			.entity(new GenericEntity<Collection<ContactDto>>(list){})
			.build();
	}


	@GET
	@Path("/{id : \\d+}")
	public Response getById(@PathParam("id") Long id, @QueryParam("lang") Lang lang) {
		final Lang userLang = (lang!=null ? lang : defLang);
		service.setLang(userLang);

		Contact entity = service.findById(id);
		return Response.ok()
			.entity(mapper.map(entity, ContactDto.class))
			.build();
	}


	@POST
	public Response create(ContactDto entityDto) {
		final Lang userLang = (entityDto.getLang()!=null ? entityDto.getLang() : defLang);
		service.setLang(userLang);

		Contact entity = mapper.map(entityDto, Contact.class);
		Contact newEntity = service.create(entity);

		return Response.ok()
			.entity(mapper.map(newEntity, ContactDto.class))
			.build();
	}


	@PUT
	@Path("{id : \\d+}")
	public Response update(@PathParam("id") Long id, ContactDto entityDto ) {
		final Lang userLang = (entityDto.getLang()!=null ? entityDto.getLang() : defLang);
		service.setLang(userLang);

		Contact entity = mapper.map(entityDto, Contact.class);
		Contact newEntity = service.update(entity);

		return Response.ok()
			.entity(mapper.map(newEntity, ContactDto.class))
			.build();
	}


	@DELETE
	@Path("{id : \\d+}")
	public Response delete(@PathParam("id") Long id) {
		service.delete(id);
		return Response.noContent()
			.build();
	}


	@Path("/{contactId : \\d+}/dictContactEmail")
	public ContactEmailResourceImpl getContactEmailResource(@PathParam("contactId") Long id) {
		return contactEmailResource;
	}


	@Path("/{contactId : \\d+}/dictContactPhoneNumber")
	public ContactPhoneNumberResourceImpl getContactPhoneNumberResource(@PathParam("contactId") Long id) {
		return contactPhoneNumberResource;
	}


	@Inject
	private ContactEmailResourceImpl contactEmailResource;

	@Inject
	private ContactPhoneNumberResourceImpl contactPhoneNumberResource;

	@Inject
	private BusinessPartnerService businessPartnerService;

	@Inject
	private ContactService service;

	@Inject
	private DozerBeanMapper mapper;

	@Inject
	private Lang defLang;
}
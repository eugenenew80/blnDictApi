package kz.kegoc.bln.webapi.dict;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.PowerLinePart;
import kz.kegoc.bln.entity.dict.dto.PowerLinePartDto;
import kz.kegoc.bln.service.dict.PowerLineService;
import kz.kegoc.bln.service.dict.PowerLinePartService;
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
public class PowerLinePartResourceImpl {

	@GET
	public Response getAll(@PathParam("powerLineId") Long powerLineId, @QueryParam("lang") Lang lang) {
		final Lang userLang = (lang!=null ? lang : defLang);
		service.setLang(userLang);

		List<PowerLinePartDto> list = powerLineService.findById(powerLineId)
			.getPowerLineParts()
			.stream()
			.map( it-> mapper.map(it, PowerLinePartDto.class) )
			.collect(Collectors.toList());		
	
		return Response.ok()
			.entity(new GenericEntity<Collection<PowerLinePartDto>>(list){})
			.build();
	}


	@GET
	@Path("/{id : \\d+}")
	public Response getById(@PathParam("id") Long id, @QueryParam("lang") Lang lang) {
		final Lang userLang = (lang!=null ? lang : defLang);
		service.setLang(userLang);

		PowerLinePart entity = service.findById(id);
		return Response.ok()
			.entity(mapper.map(entity, PowerLinePartDto.class))
			.build();
	}


	@POST
	public Response create(PowerLinePartDto entityDto) {
		Lang userLang = (entityDto.getLang()==null ? entityDto.getLang() : defLang);
		service.setLang(userLang);

		PowerLinePart entity = mapper.map(entityDto, PowerLinePart.class);
		PowerLinePart newEntity = service.create(entity);

		return Response.ok()
			.entity(mapper.map(newEntity, PowerLinePartDto.class))
			.build();
	}


	@PUT
	@Path("{id : \\d+}")
	public Response update(@PathParam("id") Long id, PowerLinePartDto entityDto ) {
		Lang userLang = (entityDto.getLang()==null ? entityDto.getLang() : defLang);
		service.setLang(userLang);

		PowerLinePart entity = mapper.map(entityDto, PowerLinePart.class);
		PowerLinePart newEntity = service.update(entity);

		return Response.ok()
			.entity(mapper.map(newEntity, PowerLinePartDto.class))
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
	private PowerLineService powerLineService;

	@Inject
	private PowerLinePartService service;

	@Inject
	private DozerBeanMapper mapper;

	@Inject
	private Lang defLang;
}

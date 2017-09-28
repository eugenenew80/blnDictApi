package kz.kegoc.bln.service.dict.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

import kz.kegoc.bln.entity.dict.Company;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.CompanyService;


@Stateless
public class CompanyServiceImpl extends AbstractEntityService<Company> implements CompanyService {
    
	@Inject
    public CompanyServiceImpl(Repository<Company> repository, Validator validator) {
        super(repository, validator);
    }
}

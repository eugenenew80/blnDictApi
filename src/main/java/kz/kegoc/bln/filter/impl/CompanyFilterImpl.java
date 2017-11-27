package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.Organization;
import kz.kegoc.bln.entity.dict.translate.OrganizationTranslate;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.CompanyService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class CompanyFilterImpl implements Filter<Organization> {
    public Organization filter(Organization entity) {
        if (entity.getId()!=null) {
            Organization curEntity = companyService.findById(entity.getId());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }
        return translate(entity);
    }

    private Organization translate(Organization entity) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;
        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        OrganizationTranslate translate = entity.getTranslations().getOrDefault(lang, new OrganizationTranslate());
        translate.setLang(lang);
        translate.setCompany(entity);
        translate.setName(entity.getName());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private CompanyService companyService;

    @Inject
    private Lang defLang;
}

package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.ContactEmail;
import kz.kegoc.bln.entity.dict.translate.ContactEmailTranslate;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.ContactEmailService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class ContactEmailFilterImpl implements Filter<ContactEmail> {
    public ContactEmail filter(ContactEmail entity) {
        if (entity.getId()!=null) {
            ContactEmail curEntity = service.findById(entity.getId());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }
        return translate(entity);
    }

    private ContactEmail translate(ContactEmail entity) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;
        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        ContactEmailTranslate translate = entity.getTranslations().getOrDefault(lang, new ContactEmailTranslate());
        translate.setLang(lang);
        translate.setContactEmail(entity);
        translate.setDescription(entity.getDescription());
        translate.setDescription(entity.getDescription());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private ContactEmailService service;

    @Inject
    private Lang defLang;
}
package kz.kegoc.bln.filter.dict;

import kz.kegoc.bln.webapi.filters.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.MeteringPointCurrentTrans;
import kz.kegoc.bln.entity.dict.translate.MeteringPointCurrentTransTranslate;
import kz.kegoc.bln.filter.AbstractFilter;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.MeteringPointCurrentTransService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class MeteringPointCurrentTransFilterImpl extends AbstractFilter<MeteringPointCurrentTrans> implements Filter<MeteringPointCurrentTrans> {
    public MeteringPointCurrentTrans filter(MeteringPointCurrentTrans entity, SessionContext context) {
        return translate(prepare(entity, context), context);
    }

    private MeteringPointCurrentTrans prepare(MeteringPointCurrentTrans entity, SessionContext context) {
        if (entity.getId()!=null) {
            MeteringPointCurrentTrans curEntity = service.findById(entity.getId(), context);
            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }

        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        entity = addUpdateInfo(entity, context);
        return entity;
    }

    private MeteringPointCurrentTrans translate(MeteringPointCurrentTrans entity, SessionContext context) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        MeteringPointCurrentTransTranslate translate = entity.getTranslations().getOrDefault(lang, new MeteringPointCurrentTransTranslate());
        translate = addUpdateInfo(translate, context);
        translate.setLang(lang);
        translate.setMeteringPointCurrentTrans(entity);
        translate.setName(entity.getName());
        translate.setManufacturer(entity.getManufacturer());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private MeteringPointCurrentTransService service;

    @Inject
    private Lang defLang;
}

package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.MeteringPoint;
import kz.kegoc.bln.entity.dict.translate.MeteringPointTranslate;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.MeteringPointService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class MeteringPointFilterImpl implements Filter<MeteringPoint> {
    public MeteringPoint filter(MeteringPoint entity) {
        if (entity.getId()!=null) {
            MeteringPoint curEntity = meteringPointService.findById(entity.getId());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }
        return translate(entity);
    }

    private MeteringPoint translate(MeteringPoint entity) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;
        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        MeteringPointTranslate translate = entity.getTranslations().getOrDefault(lang, new MeteringPointTranslate());
        translate.setLang(lang);
        translate.setMeteringPoint(entity);
        translate.setName(entity.getName());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private MeteringPointService meteringPointService;

    @Inject
    private Lang defLang;
}
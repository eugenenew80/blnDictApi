package kz.kegoc.bln.repository.dict;

import kz.kegoc.bln.entity.dict.MeteringPointCharacteristic;
import kz.kegoc.bln.repository.common.JpaRepository;
import javax.ejb.Local;

@Local
public interface MeteringPointCharacteristicRepository extends JpaRepository<MeteringPointCharacteristic> {}

package kz.kegoc.bln.repository.dict;

import kz.kegoc.bln.entity.dict.Bank;
import kz.kegoc.bln.repository.common.JpaRepository;
import javax.ejb.Local;

@Local
public interface BankRepository extends JpaRepository<Bank> {}

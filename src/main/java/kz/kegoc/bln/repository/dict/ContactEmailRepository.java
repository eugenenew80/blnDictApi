package kz.kegoc.bln.repository.dict;

import kz.kegoc.bln.entity.dict.ContactEmail;
import kz.kegoc.bln.repository.common.JpaRepository;
import javax.ejb.Local;

@Local
public interface ContactEmailRepository extends JpaRepository<ContactEmail> {}

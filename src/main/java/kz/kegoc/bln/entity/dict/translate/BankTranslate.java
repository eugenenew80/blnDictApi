package kz.kegoc.bln.entity.dict.translate;

import kz.kegoc.bln.entity.common.*;
import kz.kegoc.bln.entity.dict.Bank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import kz.kegoc.bln.entity.adm.User;

@Data
@EqualsAndHashCode(of= {"id"})
public class BankTranslate implements HasId, HasName, HasLang, HasDates, HasUser {
    private Long id;

    @NotNull
    private Lang lang;

    @NotNull @Size(max = 100)
    private String name;

    @NotNull
    private Bank bank;

    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private User createBy;
    private User lastUpdateBy;
}

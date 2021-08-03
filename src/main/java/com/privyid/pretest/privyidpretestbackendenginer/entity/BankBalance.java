package com.privyid.pretest.privyidpretestbackendenginer.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bank_balance")
public class BankBalance implements Serializable {

    private static final long serialVersionUID = -5322976242301671993L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bank_balance_id_seq")
    @SequenceGenerator(name = "bank_balance_id_seq", sequenceName = "bank_balance_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "balance")
    private Integer balance;

    @Column(name = "balance_achieve")
    private Integer balanceAchieve;

    @Column(name = "code")
    private String code;

    @Column(name = "enable")
    private Boolean enable;

    @OneToMany(mappedBy = "bankBalance", fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    List<BankBalanceHistory> bankBalanceHistories;
}

package com.privyid.pretest.privyidpretestbackendenginer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
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
@Table(name = "user_balance")
public class UserBalance implements Serializable {
    private static final long serialVersionUID = -1345899042614540514L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_balance_id_seq")
    @SequenceGenerator(name = "user_balance_id_seq", sequenceName = "user_balance_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "balance")
    private String balance;

    @Column(name = "balance_achieve")
    private Integer balanceAchieve;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "userBalance", fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    List<UserBalanceHistory> userBalanceHistories;
}

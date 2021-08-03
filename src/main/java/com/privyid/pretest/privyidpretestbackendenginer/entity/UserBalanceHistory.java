package com.privyid.pretest.privyidpretestbackendenginer.entity;

import com.privyid.pretest.privyidpretestbackendenginer.statval.EActivity;
import com.privyid.pretest.privyidpretestbackendenginer.statval.EType;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_balance_history")
public class UserBalanceHistory implements Serializable {

    private static final long serialVersionUID = 5995768187916160245L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_balance_history_id_seq")
    @SequenceGenerator(name = "user_balance_history_id_seq", sequenceName = "user_balance_history_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "balance_before")
    private Integer balanceBefore;

    @Column(name = "balance_after")
    private Integer balanceAfter;

    @Column(name = "activity")
    @Enumerated(EnumType.STRING)
    private EActivity activity;

    @Column(name = "ip")
    private String ip;

    @Column(name = "location")
    private String location;

    @Column(name = "user_agent")
    private String userAgent;

    @Column(name = "author")
    private String author;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private EType type;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "user_balance_id", referencedColumnName = "id")
    private UserBalance userBalance;
}

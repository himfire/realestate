package com.wrfxx.demo10.domain.entity;

import com.wrfxx.demo10.domain.value.enumurator.ApplicationRoleUser;
import com.wrfxx.demo10.domain.value.enumurator.Authority;
import com.wrfxx.demo10.domain.value.enumurator.JobClassification;
import com.wrfxx.demo10.domain.value.enumurator.JobType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
//Admin,Customer,Developer
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sys_users")
@Builder
public class User extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String username;

    @Embedded
    @Column(unique = true)
    private String phone;

    private String password;
    private String address;
    private JobType jobType;
    private JobClassification jobClassification;
    private LocalDate birthDate;
    private String job;
    private String nationalId;
    private String nationalAddress;

    @OneToMany
    private List<Reservation> reservations = new ArrayList<>();
    private ApplicationRoleUser authority;
    private boolean isSubscribedToNewsletter = true;
    private boolean isActive = false;
    private boolean isApproved = false;
}

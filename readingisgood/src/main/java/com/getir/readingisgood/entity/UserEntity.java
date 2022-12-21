package com.getir.readingisgood.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "USER", indexes = {@Index(name = "IDX_EMAIL", columnList = "EMAIL")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends Auditable<String> {

	@Id
	@Column(name="ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "EMAIL", length = 100, unique = true)
    private String email;

    @Column(name = "PASSWORD", length = 200)
    private String password;
    
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private List<AuthorityEntity> authorities;
    
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<OrderEntity> orders;

}
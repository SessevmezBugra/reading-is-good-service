package com.getir.readingisgood.entity;

import com.getir.readingisgood.enums.ReadingIsGoodRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "AUTHORITY")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorityEntity extends Auditable<String> {

	@Id
	@Column(name="ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USERNAME", referencedColumnName = "EMAIL")
	private UserEntity user;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ROLE", length = 100)
    private ReadingIsGoodRole role;

}

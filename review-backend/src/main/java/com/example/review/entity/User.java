package com.example.review.entity;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

//+-----------+-------------+------+-----+---------+----------------+
//| Field     | Type        | Null | Key | Default | Extra          |
//+-----------+-------------+------+-----+---------+----------------+
//| id        | int         | NO   | PRI | NULL    | auto_increment |
//| user_name | varchar(50) | YES  |     | NULL    |                |
//| password  | varchar(50) | YES  |     | NULL    |                |
//| role      | varchar(10) | YES  |     | NULL    |                |
//+-----------+-------------+------+-----+---------+----------------+

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
    @NonNull
	@Column(name="user_name")
	private String userName;

    @NonNull
	@Column(name="password")
	private String password;

    @NonNull
	@Column(name="role")
	private String role;

}

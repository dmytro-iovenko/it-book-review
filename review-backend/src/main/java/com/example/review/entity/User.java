package com.example.review.entity;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

//+-----------+-------------+------+-----+---------+----------------+
//| Field     | Type        | Null | Key | Default | Extra          |
//+-----------+-------------+------+-----+---------+----------------+
//| id        | bigint      | NO   | PRI | NULL    | auto_increment |
//| user_name | varchar(50) | NO   |     | NULL    |                |
//| password  | varchar(50) | NO   |     | NULL    |                |
//| role      | varchar(10) | NO   |     | NULL    |                |
//+-----------+-------------+------+-----+---------+----------------+

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
    @NonNull
	@Column(nullable = false, unique = true)
	private String username;

    @NonNull
	@Column(nullable = false)
	private String password;

    @NonNull
	@Column(nullable = false)
	private String role;

}

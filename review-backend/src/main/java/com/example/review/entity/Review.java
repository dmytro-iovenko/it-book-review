package com.example.review.entity;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

//+--------+---------------+------+-----+---------+----------------+
//| Field  | Type          | Null | Key | Default | Extra          |
//+--------+---------------+------+-----+---------+----------------+
//| id     | int           | NO   | PRI | NULL    | auto_increment |
//| isbn   | bigint        | YES  |     | NULL    |                |
//| name   | varchar(30)   | YES  |     | NULL    |                |
//| review | varchar(2000) | YES  |     | NULL    |                |
//| date   | bigint        | YES  |     | NULL    |                |
//| rate   | int           | YES  |     | NULL    |                |
//+--------+---------------+------+-----+---------+----------------+

@Data
@NoArgsConstructor
@Entity
@Table(name="reviews")
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="isbn")
	private long isbn;
	
	@Column(name="name")
	private String name;
	
	@Column(name="review")
	private String review;
	
	@Column(name="date")
	private long date;
	
	@Column(name="rate")
	private int rate;
	
	public void updateFields(Review r) {
		this.setId(r.getId());
		this.setIsbn(r.getIsbn());
		this.setName(r.getName());
		this.setReview(r.getReview());
		this.setDate(r.getDate());
		this.setRate(r.getRate());
	}
}

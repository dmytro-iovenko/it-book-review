package com.example.review.entity;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

//+--------+---------------+------+-----+---------+----------------+
//| Field  | Type          | Null | Key | Default | Extra          |
//+--------+---------------+------+-----+---------+----------------+
//| id     | bigint        | NO   | PRI | NULL    | auto_increment |
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
	private Long id;
	
	@Column(name="isbn")
	private Long isbn;
	
	@Column(name="name")
	private String name;
	
	@Lob
	@Column(name="review")
	private String review;
	
	@Column(name="date")
	private Long date;
	
	@Column(name="rate")
	private Integer rate;
	
	public Review(Long isbn, String name, String review, Long date, Integer rate) {
		this.isbn = isbn;
		this.name = name;
		this.review = review;
		this.date = date;
		this.rate = rate;
	}

	public void updateFields(Review r) {
		this.setIsbn(r.getIsbn());
		this.setName(r.getName());
		this.setReview(r.getReview());
		this.setRate(r.getRate());
	}
}

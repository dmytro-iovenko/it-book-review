package com.example.review.entity;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

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
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="reviews")
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
    @NonNull
	@Column(name="isbn")
	private Long isbn;
	
    @NonNull
	@Column(name="name")
	private String name;
	
    @NonNull
	@Lob
	@Column(name="review")
	private String review;
	
    @NonNull
	@Column(name="date")
	private Long date;
	
    @NonNull
	@Column(name="rate")
	private Integer rate;
	
	public void updateFields(Review r) {
		this.setId(r.getId());
		this.setIsbn(r.getIsbn());
		this.setName(r.getName());
		this.setReview(r.getReview());
		this.setDate(r.getDate());
		this.setRate(r.getRate());
	}
}

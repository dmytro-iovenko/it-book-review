package com.example.review;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.review.entity.Review;
import com.example.review.entity.User;
import com.example.review.repository.ReviewRepository;
import com.example.review.repository.UserRepository;

import lombok.AllArgsConstructor;

@SpringBootApplication
@AllArgsConstructor
public class ReviewBackendApplication implements CommandLineRunner {
	UserRepository userRepository;
	ReviewRepository reviewRepository;

	public static void main(String[] args) {
		SpringApplication.run(ReviewBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User[] users = new User[] {
            new User("user", "user", "ROLE_USER"),
            new User("admin", "admin", "ROLE_ADMIN")
        };
		for (int i = 0; i < users.length; i++) {
			users[i].setPassword(bCryptPasswordEncoder().encode(users[i].getPassword()));
			userRepository.save(users[i]);
		}
		Review[] reviews = new Review[] {
			new Review(1001643027241L, "Michael Scott", "Disappointing read. The content lacks coherence, and explanations are convoluted. Too much jargon without sufficient context. Felt more like a disjointed compilation of facts rather than a cohesive guide for someone trying to grasp IT fundamentals.", 1646947328536L, 2),
			new Review(9781718501089L, "Meredith Grey", "An indispensable guide for aspiring IT professionals! This book seamlessly blends theory with real-world examples, providing a solid foundation for understanding complex concepts. A must-read for anyone entering the dynamic field of information technology.", 1646947722346L, 4),
			new Review(1001643027241L, "Teresa Lisbon", "Exceptional! This IT book stands out with its engaging writing style and hands-on approach. It not only demystifies intricate technical topics but also instills problem-solving skills. A reliable companion for anyone seeking to navigate the ever-evolving landscape of IT.", 1646947947194L, 5),
        };
		for (int i = 0; i < reviews.length; i++) {
			reviewRepository.save(reviews[i]);
		}
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}	

}

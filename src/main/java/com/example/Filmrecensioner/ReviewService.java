package com.example.Filmrecensioner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final WebClient webClient;

    @Value("${movieservice.url}")
    private String movieServiceUrl;

    public ReviewService(ReviewRepository reviewRepository, WebClient.Builder webClientBuilder) {
        this.reviewRepository = reviewRepository;
        this.webClient = webClientBuilder.baseUrl(movieServiceUrl).build();
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review getReviewById(Long id) {
        return reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review not found"));
    }

    public Review createReview(Review review) {
        Mono<Movie> movieMono = webClient.get()
                .uri("/movies/" + review.getMovieId())
                .retrieve()
                .bodyToMono(Movie.class);

        Movie movie = movieMono.block();
        if (movie == null) {
            throw new RuntimeException("Movie not found");
        }
        //review.setMovieTitle(movie.getTitle());
        return reviewRepository.save(review);
    }

    public Review updateReview(Long id, Review updatedReview) {
        Review existingReview = getReviewById(id);
        existingReview.setRating(updatedReview.getRating());
        existingReview.setComment(updatedReview.getComment());
        return reviewRepository.save(existingReview);
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}

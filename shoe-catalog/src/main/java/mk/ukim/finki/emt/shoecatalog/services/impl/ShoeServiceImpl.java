package mk.ukim.finki.emt.shoecatalog.services.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.shoecatalog.domain.exceptions.ShoeNotFound;
import mk.ukim.finki.emt.shoecatalog.domain.models.Brand;
import mk.ukim.finki.emt.shoecatalog.domain.models.Review;
import mk.ukim.finki.emt.shoecatalog.domain.models.Shoe;
import mk.ukim.finki.emt.shoecatalog.domain.models.ids.ReviewId;
import mk.ukim.finki.emt.shoecatalog.domain.models.ids.ShoeId;
import mk.ukim.finki.emt.shoecatalog.domain.repository.BrandRepository;
import mk.ukim.finki.emt.shoecatalog.domain.repository.ReviewRepository;
import mk.ukim.finki.emt.shoecatalog.domain.repository.ShoeRepository;
import mk.ukim.finki.emt.shoecatalog.services.ShoeService;
import mk.ukim.finki.emt.shoecatalog.services.forms.ShoeForm;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class ShoeServiceImpl implements ShoeService {

    private final ShoeRepository shoeRepository;

    private final BrandRepository brandRepository;

    private final ReviewRepository reviewRepository;

    @Override
    public Brand findBrandById(ShoeId id) {
 Shoe s = findById(id);
 return brandRepository.findById(s.getBrand().getId()).orElseThrow(ShoeNotFound::new);

    }

    @Override
    public Double getRatingAvg(ShoeId id) {
       Shoe s = findById(id);
       List<Review> reviews = s.getReviews();
       Double sum = 0.0;
        for (int i = 0; i < reviews.size(); i++) {
            sum += reviews.get(i).getRating();
        }
       return reviews.size()/sum;
    }

    @Override
    public List<Review>  findReviewsById(ShoeId id) {
        Shoe s = findById(id);
        List<ReviewId> reviewIds = s.getReviews().stream()
                .map(r ->  r.getId())
                .collect(Collectors.toList());
        return reviewRepository.findAllById(reviewIds);
    }

    @Override
    public Shoe findById(ShoeId id) {

        return shoeRepository.findById(id).orElseThrow(ShoeNotFound::new);
    }

    @Override
    public Shoe createProduct(ShoeForm form) {
        Shoe p = Shoe.build(form.getProductName(),form.getPrice(),form.getSales(),form.getGender(),form.getCategory(),form.getBrand(),new ArrayList<>());
        shoeRepository.save(p);
        return p;
    }

    @Override
    public Shoe orderItemCreated(ShoeId productId, int quantity) {
        Shoe p = findById(productId);
        p.addSales(quantity);
        shoeRepository.saveAndFlush(p);
        return p;

    }

    @Override
    public Shoe orderItemRemoved(ShoeId productId, int quantity) {
        Shoe p = shoeRepository.findById(productId).orElseThrow(ShoeNotFound::new);
        p.removeSales(quantity);
        shoeRepository.saveAndFlush(p);
        return p;

    }

    @Override
    public List<Shoe> getAll() {
        return shoeRepository.findAll();
    }

    @Override
    public Shoe addReview(ShoeId productId,Review review) {
        Shoe s = findById(productId);
        s.getReviews().add(review);
        review.addRating(review.getRating());
        return s;
    }

    @Override
    public Shoe removeReview(ShoeId productId,Review review) {
        Shoe s = findById(productId);
        s.getReviews().remove(review);
        review.removeRating(review.getRating());
        return s;
    }


}

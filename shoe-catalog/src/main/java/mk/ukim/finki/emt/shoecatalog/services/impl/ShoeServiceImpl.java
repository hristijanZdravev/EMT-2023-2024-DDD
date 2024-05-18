package mk.ukim.finki.emt.shoecatalog.services.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.shoecatalog.domain.exceptions.ShoeNotFound;
import mk.ukim.finki.emt.shoecatalog.domain.models.Brand;
import mk.ukim.finki.emt.shoecatalog.domain.models.Review;
import mk.ukim.finki.emt.shoecatalog.domain.models.Shoe;
import mk.ukim.finki.emt.shoecatalog.domain.models.ids.ShoeId;
import mk.ukim.finki.emt.shoecatalog.domain.repository.BrandRepository;
import mk.ukim.finki.emt.shoecatalog.domain.repository.ReviewRepository;
import mk.ukim.finki.emt.shoecatalog.domain.repository.ShoeRepository;
import mk.ukim.finki.emt.shoecatalog.services.ShoeService;
import mk.ukim.finki.emt.shoecatalog.services.forms.ShoeForm;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Double getRatingAvg() {
       List<Review> reviews = reviewRepository.findAll();
Double sum = 0.0;
        for (int i = 0; i < reviews.size(); i++) {
            sum += reviews.get(i).getRating();
        }
       return reviews.size()/sum;
    }

    @Override
    public Review findReviewById(ShoeId id) {
        Shoe s = findById(id);
        return reviewRepository.findById(s.getReview().getId()).orElseThrow(ShoeNotFound::new);
    }

    @Override
    public Shoe findById(ShoeId id) {

        return shoeRepository.findById(id).orElseThrow(ShoeNotFound::new);
    }

    @Override
    public Shoe createProduct(ShoeForm form) {
        Shoe p = Shoe.build(form.getProductName(),form.getPrice(),form.getSales(),form.getGender(),form.getCategory(),form.getBrand(),form.getReview());
        shoeRepository.save(p);
        return p;
    }

    @Override
    public Shoe orderItemCreated(ShoeId productId, int quantity) {
        Shoe p = findById(productId);
        p.addSales(quantity);
        Review review = findReviewById(p.getId());
        p.getReview().addRating(review.getRating());
        shoeRepository.saveAndFlush(p);
        return p;

    }

    @Override
    public Shoe orderItemRemoved(ShoeId productId, int quantity) {
        Shoe p = shoeRepository.findById(productId).orElseThrow(ShoeNotFound::new);
        p.removeSales(quantity);
        Review review = findReviewById(p.getId());
        p.getReview().removeRating(review.getRating());
        shoeRepository.saveAndFlush(p);
        return p;

    }

    @Override
    public List<Shoe> getAll() {
        return shoeRepository.findAll();
    }
}

package mk.ukim.finki.emt.shoecatalog.services;

import mk.ukim.finki.emt.shoecatalog.domain.models.Brand;
import mk.ukim.finki.emt.shoecatalog.domain.models.Review;
import mk.ukim.finki.emt.shoecatalog.domain.models.Shoe;
import mk.ukim.finki.emt.shoecatalog.domain.models.ids.ShoeId;
import mk.ukim.finki.emt.shoecatalog.services.forms.ShoeForm;

import java.util.List;

public interface ShoeService {

    Brand findBrandById(ShoeId id);

    Double getRatingAvg(ShoeId id);
    List<Review> findReviewsById(ShoeId id);
    Shoe findById(ShoeId id);
    Shoe createProduct(ShoeForm form);
    Shoe orderItemCreated(ShoeId productId, int quantity);
    Shoe orderItemRemoved(ShoeId productId, int quantity);
    List<Shoe> getAll();

    Shoe addReview(ShoeId productId, Review review);
    Shoe removeReview(ShoeId productId,Review review);
}

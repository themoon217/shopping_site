package model;

import java.util.List;

import dao.ItemsDAO;
import dao.ReviewsDAO;

public class GetItemData {
    public ItemData execute(int itemId) {
        ItemsDAO dao = new ItemsDAO();
        return dao.getItemDataDAO(itemId);
    }

    public CategoryData executeCategory(int itemId) {
        ItemsDAO dao = new ItemsDAO();
        return dao.getCategoryList(itemId);
    }

    public ColorData executeColor(int itemId) {
        ItemsDAO dao = new ItemsDAO();
        return dao.getColorList(itemId);
    }

    public Subimg executeImg(int itemId) {
        ItemsDAO dao = new ItemsDAO();
        return dao.getImgList(itemId);
    }

    public List<Review> executeReviews(int itemId) {
        ReviewsDAO reviewsDAO = new ReviewsDAO();
        return reviewsDAO.getReviews(itemId);
    }
}

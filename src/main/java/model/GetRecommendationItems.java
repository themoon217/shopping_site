package model;

import java.util.List;

import dao.ItemsDAO;

public class GetRecommendationItems {
    public List<Recommendation> execute() {
        ItemsDAO dao = new ItemsDAO();
        List<Recommendation> findRecom = dao.findRecom();
        return findRecom;
    }
    public List<Recommendation> executeNot() {
        ItemsDAO dao = new ItemsDAO();
        List<Recommendation> findRecom = dao.findNotRecom();
        return findRecom;
    }
}

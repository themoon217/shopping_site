package model;

import dao.SubImgDAO;

public class AddSubImgMethod {
    public void execute(AddSubImgData addSubImgData) {
        SubImgDAO dao = new SubImgDAO();
        dao.insertSubImg(addSubImgData);
    }
}

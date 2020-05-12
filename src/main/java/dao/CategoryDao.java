package dao;

import objects.category;

import java.util.List;

public interface CategoryDao {

    //create

    void add(category newCategory);

    //read
    List<category> getAll();

    //update
    void updateCategoryById(int id,category updatedCategory);

    //delete
    void deleteCategoryById(int id);
    void clearAll();
}

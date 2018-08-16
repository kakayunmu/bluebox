package pers.kakayunmu.bluebox.controller;

import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pers.kakayunmu.bluebox.entity.Category;
import pers.kakayunmu.bluebox.model.CategoryModel;
import pers.kakayunmu.bluebox.model.common.RetDataModel;
import pers.kakayunmu.bluebox.repositorys.CategoryRepository;

@RestController
@RequestMapping(value = "/api")

public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(value = "/category/save", method = RequestMethod.POST)
    public Object save(@NonNull CategoryModel categoryModel) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryModel, category);
        category = categoryRepository.save(category);
        return new RetDataModel(0, "保存成功", category);

    }
}

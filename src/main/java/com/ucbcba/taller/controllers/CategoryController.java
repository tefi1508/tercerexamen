package com.ucbcba.taller.controllers;

import com.ucbcba.taller.entities.Category;
import com.ucbcba.taller.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/category", method = RequestMethod.POST)
    String save(Category category) {

        categoryService.saveCategory(category);
        return "redirect:/Categories";
    }

    @RequestMapping("/newCategory")
    String newRestaurant(Model model) {
        Iterable<Category> categories = categoryService.listAllCategories();
        model.addAttribute("categories", categories);
        return "newCategory";
    }

    @RequestMapping(value = "/Categories" ,method = RequestMethod.GET)
    String showCat(Model model) {
        Iterable<Category> cateList = categoryService.listAllCategories();
        model.addAttribute("cateList",cateList);
        return "showCategories";
    }

    @RequestMapping("/showCategory/{id}")
    String showRes(@PathVariable Integer id, Model model) {
        Category cate = categoryService.getCategory(id);
        model.addAttribute("cate", cate);
        return "showCategory";
    }


    @RequestMapping("/deleteCategory/{id}")
    String delete(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        return "redirect:/Categories";
    }

    @RequestMapping("/editCategory/{id}")
    String editPost(@PathVariable Integer id, Model model) {
        Category cate = categoryService.getCategory(id);
        model.addAttribute("cate", cate);
        return "editCategory";
    }
}

package com.ecommerce.category.controller;

import com.ecommerce.category.dao.CategoryDao;
import com.ecommerce.category.exception.CategoryNotFoundException;
import com.ecommerce.category.model.Category;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Api(description = "Category management")
@RestController
public class CategoryController {

    @Autowired
    private CategoryDao categoryDao;

    @ApiOperation(value = "List all categories")
    @GetMapping(value = "categories")
    public List<Category> categoryList() {
        return categoryDao.findAll();
    }

    @ApiOperation(value = "Display a category")
    @GetMapping(value = "categories/{id}")
    public Category displayCategory(@PathVariable int id) throws CategoryNotFoundException {
        Category category = categoryDao.findById(id);
        if (category == null) throw new CategoryNotFoundException("Category with id " + id + " doesn't exist");
        return category;
    }

    @ApiOperation(value = "Create a category")
    @PostMapping(value = "categories")
    public ResponseEntity<Void> addCategory(@Valid @RequestBody Category category) {
        Category category1 = categoryDao.save(category);
        if (category1 == null) {
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(category1.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}

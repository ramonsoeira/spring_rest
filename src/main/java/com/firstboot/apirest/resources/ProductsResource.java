package com.firstboot.apirest.resources;

import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.firstboot.apirest.models.Products;
import com.firstboot.apirest.repository.ProductRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API Rest")
@CrossOrigin(origins = "*")
public class ProductsResource {
	@Autowired
	ProductRepository productRepository;

	@GetMapping("/products")
	@ApiOperation(value = "Return a products list")
	public List<Products> productsList() {
		return productRepository.findAll();
	}

	@GetMapping("/products/{id}")
	@ApiOperation(value = "Return a product by id")
	public Products productsFilterById(@PathVariable(value = "id") long id) {
		return productRepository.findById(id);
	}

	@PostMapping("/products")
	@ApiOperation(value = "Return saves a product")
	public Products saveProduct(@RequestBody Products product) {
		return productRepository.save(product);

	}

	@PutMapping("/products/{id}")
	@ApiOperation(value = "Update a product")
	@Transactional
	public Products putProduct(@PathVariable(value = "id") long id, @RequestBody Products product) {
		Optional<Products> getProd = Optional.of(productRepository.findById(id));

		if (Objects.nonNull(getProd)) {

			getProd.get().setId(id);
			getProd.get().setName(product.getName());
			getProd.get().setQuantity(product.getQuantity());
			getProd.get().setValue(product.getValue());
			return getProd.get();

		} else {
			return (Products) ResponseEntity.notFound();
		}

	}

	@DeleteMapping("/products/{id}")
	@ApiOperation(value = "Delete a product")
	public void deleteProduct(@PathVariable(value = "id") long id) {
		Products prod = productRepository.findById(id);
		productRepository.delete(prod);
	}

}

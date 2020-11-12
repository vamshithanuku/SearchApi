package com.example.search;

import java.io.IOException;
import java.util.List;

import com.example.search.searchProduct;
import com.example.search.searchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class searchController
{
@Autowired
private searchService search;

@RequestMapping("/searchProducts")
public List<searchProduct> getAllProducts()
{
	return search.getAllProducts();
}

@RequestMapping("/searchProducts/{search_by_keyword}")
public List<searchProduct> getProduct(@PathVariable String search_by_keyword) throws IOException
{
	return search.getProduct(search_by_keyword);
}
@RequestMapping(method= RequestMethod.POST, value="/searchProducts")
public void addProduct(@RequestBody searchProduct product)
{
	search.addProduct(product);
}
}

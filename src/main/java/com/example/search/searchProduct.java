package com.example.search;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class searchProduct {

 @Id
 private String url;
 private String title;
 private String price;

public searchProduct(String url, String title, String price)
{
	this.url = url;
	this.title = title;
	this.price = price;
}
public String getUrl()
{
	return url;
}
public void setUrl(String url)
{
	this.url = url;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getPrice() {
	return price;
}
public void setPrice(String price) {
	this.price = price;
}
 
 
}

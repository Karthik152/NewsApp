package com.example.newspaper.Model;

public class NewsModel{
        String id,name,descriptionn,language,country,url,category;

public NewsModel(String id, String name, String descriptionn, String language, String country, String url, String category) {
        this.id = id;
        this.name = name;
        this.descriptionn = descriptionn;
        this.language = language;
        this.country = country;
        this.url = url;
        this.category = category;
        }


public String getId() {
        return id;
        }

public void setId(String id) {
        this.id = id;
        }

public String getName() {
        return name;
        }

public void setName(String name) {
        this.name = name;
        }

public String getDescriptionn() {
        return descriptionn;
        }

public void setDescriptionn(String descriptionn) {
        this.descriptionn = descriptionn;
        }

public String getLanguage() {
        return language;
        }

public void setLanguage(String language) {
        this.language = language;
        }

public String getCountry() {
        return country;
        }

public void setCountry(String country) {
        this.country = country;
        }

public String getUrl() {
        return url;
        }

public void setUrl(String url) {
        this.url = url;
        }

public String getCategory() {
        return category;
        }

public void setCategory(String category) {
        this.category = category;
        }

        }
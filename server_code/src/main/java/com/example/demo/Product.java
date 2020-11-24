package com.example.demo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

@SerializedName("code")
@Expose
private Integer code;
@SerializedName("name")
@Expose
private String name;
@SerializedName("price")
@Expose
private Integer price;
@SerializedName("picture")
@Expose
private String picture;
@SerializedName("description")
@Expose
private String description;
@SerializedName("type")
@Expose
private Integer type;

public Integer getCode() {
return code;
}

public void setCode(Integer pcode) {
this.code = pcode;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public Integer getPrice() {
return price;
}

public void setPrice(Integer price) {
this.price = price;
}

public String getPicture() {
return picture;
}

public void setPicture(String picture) {
this.picture = picture;
}

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
}

public Integer getType() {
return type;
}

public void setType(Integer type) {
this.type = type;
}

}
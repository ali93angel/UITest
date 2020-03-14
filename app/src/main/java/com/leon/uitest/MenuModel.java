package com.leon.uitest;

public class MenuModel {
    String item;
    int number;
    int price;
    int imageSrc;

    public MenuModel(String item, int price, int imageSrc) {
        this.item = item;
        this.price = price;
        this.imageSrc = imageSrc;
        number = 0;
    }

    public int getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(int imageSrc) {
        this.imageSrc = imageSrc;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

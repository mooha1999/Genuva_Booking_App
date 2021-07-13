package com.example.weesofirebase;

public class TicketsModel {
    private String name,date,price,numbers,count;

    public TicketsModel(String name, String date, String price, String numbers, String count) {
        this.name = name;
        this.date = date;
        this.price = price;
        this.numbers = numbers;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}

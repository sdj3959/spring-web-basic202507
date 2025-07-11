package com.spring.basic.chap2_3.controller;

import com.spring.basic.chap2_3.entity.Product;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController     // JSON 처리
@RequestMapping("/products")
public class ProductController {

    // 가상의 메모리 상품 저장소
    private Map<Long, Product> productMap = new HashMap<>();

    // 상품의 시리얼 넘버를 순차생성
    public long nextId = 1;

    public ProductController() {
        productMap.put(nextId, new Product(nextId, "에어컨", 1000000));
        nextId++;
        productMap.put(nextId, new Product(nextId, "세탁기", 1500000));
        nextId++;
        productMap.put(nextId, new Product(nextId, "공기청정기", 300000));
        nextId++;
    }

    // 특정 상품 조회 : GET
//    @GetMapping
//    public Product getProduct(HttpServletRequest req) {
//        String id = req.getParameter("id");
//        return productMap.get(Long.parseLong(id));
//    }

    // 쿼리스트링 읽기 ?id=xxx&price=4000
    @GetMapping
    public Product getProduct(
            @RequestParam("id") long id,
            @RequestParam(value = "price", required = false, defaultValue = "1000") int price
    ) {
        System.out.println("id = " + id);
        System.out.println("price = " + price);
        return productMap.get(id);
    }
}

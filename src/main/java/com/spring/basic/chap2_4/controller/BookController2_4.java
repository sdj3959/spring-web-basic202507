package com.spring.basic.chap2_4.controller;

import com.spring.basic.chap2_4.entity.Book;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2-4/books")
public class BookController2_4 {

    // 데이터베이스 대용으로 책들을 모아서 관리
    Map<Long, Book> bookStore = new HashMap<>();

    private Long nextId = 1L;

    public BookController2_4() {
        bookStore.put(nextId, new Book(nextId, "클린코드", "로버트 마틴", 20000));
        nextId++;
        bookStore.put(nextId, new Book(nextId, "해리포터", "조앤 롤링", 10000));
        nextId++;
        bookStore.put(nextId, new Book(nextId, "삼국지", "나관중", 15000));
        nextId++;
    }

    // 1. 목록 조회
//    @RequestMapping(method = RequestMethod.GET)
    /*
        ?sort=[ id | title | price ]
        - 기본값은 id 오름차, title은 가나다순, price는 내림차
     */
    @GetMapping
    public Map<String, Object> list(@RequestParam(defaultValue = "id") String sort) {
//        List<Book> bookList = new ArrayList<>();
//        for (Long key : bookStore.keySet()) {
//            bookList.add(bookStore.get(key));
//        }

        List<Book> bookList = new ArrayList<>(bookStore.values())
                .stream()
                .sorted(getComparator(sort))
                .collect(Collectors.toList());

        int count = bookStore.size();

        return Map.of(
                "count", count,
                "bookList", bookList
        );

    }

    private Comparator<Book> getComparator(String sort) {

        Comparator<Book> comparing = null;

        switch (sort) {
            case "title":
                comparing = Comparator.comparing(Book::getTitle);
                break;
            case "price":
                comparing = Comparator.comparing(Book::getPrice).reversed();
                break;
            default:
                comparing = Comparator.comparing(Book::getId);
        }

        return comparing;
    }


    // 2. 개별 조회
    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {

        Book foundBook = bookStore.get(id);

        return foundBook;
    }

    // 3. 도서 생성
    @PostMapping
    public String createBook(String title, String author, int price) {

        // 새 도서 객체 생성
        Book book = new Book(nextId++, title, author, price);

        // 맵에 저장
        bookStore.put(book.getId(), book);

        return "도서 추가 완료: " + book.getId();
    }

    // 삭제 요청   /api/v2-4/books/99  -> 삭제실패 메시지 응답
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {

        Book removed = bookStore.remove(id);

        if (removed == null) {
            return id + "번 도서는 존재하지 않습니다. 삭제 실패!";
        }
        return "도서 삭제 완료! - " + id;
    }

    @PutMapping("/{id}")
    public String updateBook(
            String title,
            String author,
            int price,
            @PathVariable Long id
    ) {
        Book foundBook = bookStore.get(id);

        if (foundBook == null) {
            return id + "번 도서는 존재하지 않습니다.";
        }

        foundBook.updateBookInfo(title, author, price);

        return "도서 수정 완료: id - " + id;
    }

    // 책이 몇권 저장됐는지 알려주기
    @GetMapping("/count")
    public String count() {
        return "현재 저장된 도서의 개수: " + bookStore.size() + "권";
    }
}

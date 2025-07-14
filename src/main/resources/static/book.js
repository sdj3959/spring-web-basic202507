const URL = '/api/v2-4/books';

// 렌더링 함수 정의
function render({ bookList, count }) {

  const $bookList = document.querySelector('.book-list');

  document.querySelector('.book-count').textContent = count;

  bookList.forEach(book => {
    const $newLi = document.createElement('li');
    $newLi.innerHTML = `
      <h2>제목 : ${book.title}</h2>
      <h2>저자 : ${book.author}</h2>
      <span>가격 : ${book.price}원</span>
      <button class="del-btn">삭제</button>
    `;
    $bookList.append($newLi);
  });
}


// 비동기 서버요청 함수 정의
async function fetchGetBook() {
  const res = await fetch(URL);
  const result = await res.json();

  // 렌더링
  render(result);
}

fetchGetBook();
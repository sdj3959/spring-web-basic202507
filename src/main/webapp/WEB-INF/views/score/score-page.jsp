<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${title} 애플리케이션</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body { background-color: #f8f9fa; font-family: 'Malgun Gothic', sans-serif; }
        .container { max-width: 960px; }
        .error { color: #dc3545; font-size: 0.875em; }
        .del-btn { cursor: pointer; }
    </style>
</head>
<body>
    <div class="container py-5">
        <header class="text-center mb-5">
            <h1>${title} 애플리케이션</h1>
        </header>

        <section class="register-form mb-5 p-4 border rounded bg-light shadow-sm">
            <form id="score-form">
                <div class="row g-3">
                    <div class="col-12">
                        <label for="name-input" class="form-label">이름</label>
                        <input type="text" id="name-input" name="studentName" class="form-control">
                        <p class="error" id="studentName"></p>
                    </div>
                    <div class="col-md-4">
                        <label for="kor-input" class="form-label">국어</label>
                        <input type="number" id="kor-input" name="korean" class="form-control">
                        <p class="error" id="korean"></p>
                    </div>
                    <div class="col-md-4">
                        <label for="eng-input" class="form-label">영어</label>
                        <input type="number" id="eng-input" name="english" class="form-control">
                        <p class="error" id="english"></p>
                    </div>
                    <div class="col-md-4">
                        <label for="math-input" class="form-label">수학</label>
                        <input type="number" id="math-input" name="math" class="form-control">
                        <p class="error" id="math"></p>
                    </div>
                    <div class="col-12">
                        <button type="submit" class="btn btn-primary w-100">등록하기</button>
                    </div>
                </div>
            </form>
        </section>

        <section class="list-section">
            <header class="d-flex justify-content-between align-items-center mb-3">
                <h3 class="mb-0">총 학생 수: <span id="student-count">0</span>명</h3>
                <div class="sort-links">
                    <a href="#" class="text-decoration-none me-3" data-sort="id">학번순</a>
                    <a href="#" class="text-decoration-none me-3" data-sort="name">이름순</a>
                    <a href="#" class="text-decoration-none" data-sort="average">평균순</a>
                </div>
            </header>
            <ul id="score-list-container" class="list-group">
                <!-- 학생 목록이 여기에 동적으로 렌더링됩니다. -->
            </ul>
        </section>
    </div>

    <script>
      // 모든 HTML 문서가 로드된 후 스크립트 실행을 보장합니다.
      document.addEventListener('DOMContentLoaded', () => {

        // 1. 전역 상수 및 DOM 요소 선택
        const API_BASE_URL = '/api/v1/scores';
        const $scoreListContainer = document.getElementById('score-list-container');
        const $studentCount = document.getElementById('student-count');
        const $form = document.getElementById('score-form');

        // 2. 렌더링 함수
        const renderScores = (scores) => {
          $scoreListContainer.innerHTML = '';
          $studentCount.textContent = scores.length;
          scores.forEach(({ id, maskingName, sum, avg, rank }) => {
            const listItemHTML = `
                        <li class="list-group-item d-flex justify-content-between align-items-center" data-id="${id}">
                            <div>
                                <a href="/score/detail/${id}" class="text-decoration-none fw-bold">${maskingName}</a>
                                <span class="text-muted ms-3">
                                    총점: ${sum}점, 평균: ${avg.toFixed(2)}점, 석차: ${rank}위
                                </span>
                            </div>
                            <button class="btn btn-sm btn-outline-danger del-btn">삭제</button>
                        </li>
                    `;
            $scoreListContainer.insertAdjacentHTML('beforeend', listItemHTML);
          });
        };

        // 3. 서버 통신 함수
        const fetchScores = async (sortBy = 'id') => {
          const url = `${API_BASE_URL}?sort=${sortBy}`;
          console.log(`[Request] GET ${url}`); // 요청 URL 확인
          try {
            const res = await fetch(url);
            if (!res.ok) throw new Error(`서버 응답 오류: ${res.status}`);
            const scoreData = await res.json();
            renderScores(scoreData);
          } catch (err) {
            console.error('목록 조회 실패:', err);
            alert('데이터를 불러오는 데 실패했습니다.');
          }
        };

        const postScore = async (payload) => {
          document.querySelectorAll('.error').forEach(el => el.textContent = '');
          console.log(`[Request] POST ${API_BASE_URL}`, payload);
          try {
            const res = await fetch(API_BASE_URL, {
              method: 'POST',
              headers: { 'Content-Type': 'application/json' },
              body: JSON.stringify(payload)
            });
            if (res.ok) {
              $form.reset();
              await fetchScores();
            } else if (res.status === 400) {
              const errorData = await res.json();
              for (const field in errorData) {
                document.getElementById(field).textContent = errorData[field];
              }
            } else {
              throw new Error(`서버 등록 오류: ${res.status}`);
            }
          } catch (err) {
            console.error('등록 실패:', err);
            alert('등록에 실패했습니다.');
          }
        };

        const deleteScore = async (id) => {
          if (!confirm('정말 삭제하시겠습니까?')) return;
          const url = `${API_BASE_URL}/${id}`;
          console.log(`[Request] DELETE ${url}`);
          try {
            const res = await fetch(url, { method: 'DELETE' });
            if (!res.ok) throw new Error(`서버 삭제 오류: ${res.status}`);
            await fetchScores();
          } catch (err) {
            console.error('삭제 실패:', err);
            alert('삭제에 실패했습니다.');
          }
        };

        // 4. 이벤트 리스너 설정
        document.querySelector('.sort-links').addEventListener('click', e => {
          e.preventDefault();
          if (e.target.matches('a')) {
            fetchScores(e.target.dataset.sort);
          }
        });

        $form.addEventListener('submit', e => {
          e.preventDefault();
          const payload = {
            studentName: $form.studentName.value,
            korean: +$form.korean.value,
            english: +$form.english.value,
            math: +$form.math.value,
          };
          postScore(payload);
        });

        $scoreListContainer.addEventListener('click', e => {
          if (e.target.matches('.del-btn')) {
            const id = e.target.closest('li').dataset.id;
            deleteScore(id);
          }
        });

        // 5. 초기 데이터 로딩
        fetchScores();
      });
    </script>
</body>
</html>
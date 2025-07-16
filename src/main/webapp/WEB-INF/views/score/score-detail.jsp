<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>성적 상세 정보</title>

    <!-- 외부 라이브러리 및 스타일 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" defer></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <style>
        body { font-family: 'Malgun Gothic', sans-serif; }
        li { list-style: none; margin-bottom: 10px; padding: 0; font-size: 1.2em; }
        section.score-main {
            width: 450px;
            margin: 100px auto;
            padding: 30px;
            border: 1px solid #ddd;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }
        h1 { font-size: 1.8em; font-weight: bold; margin-bottom: 20px; text-align: center; }
        .btn-group { display: flex; justify-content: flex-end; margin-top: 20px; }
        a.btn, button.btn {
            display: inline-block;
            text-decoration: none;
            border-radius: 5px;
            padding: 8px 15px;
            margin-left: 10px;
            color: white;
            border: none;
            cursor: pointer;
            font-weight: 600;
        }
        .list-btn { background: #6c757d; }
        .mod-btn { background: #ffc107; color: #212529; }
        .save-btn { background: #198754; }
        .cancel-btn { background: #dc3545; }

        /* 수정 모드에서 사용할 입력창 스타일 */
        li input { width: 80px; text-align: right; padding: 5px; font-size: 1em; border: 1px solid #ccc; border-radius: 4px; }
        /* 에러 메시지 스타일 */
        .error-msg { font-size: 0.8em; color: red; margin-left: 10px; }
    </style>
</head>

<body>
    <div class="wrap">
        <section class="score-main">
            <h1><span id="fullName"></span>님 성적 정보</h1>
            <ul id="score-details-list">
                <li># 국어: <span class="score-view" id="kor"></span><span class="score-edit" style="display: none;"><input type="number" id="kor-input"><span class="error-msg"></span></span> 점</li>
                <li># 영어: <span class="score-view" id="eng"></span><span class="score-edit" style="display: none;"><input type="number" id="eng-input"><span class="error-msg"></span></span> 점</li>
                <li># 수학: <span class="score-view" id="math"></span><span class="score-edit" style="display: none;"><input type="number" id="math-input"><span class="error-msg"></span></span> 점</li>
                <li class="summary"># 총점: <span id="total"></span>점</li>
                <li class="summary"># 평균: <span id="average"></span>점</li>
                <li class="summary"># 석차: <span id="rank"></span> / <span id="totalCount"></span></li>
            </ul>
            <div class="btn-group" id="view-mode-btns">
                <a class="btn list-btn" href="/score/list">목록</a>
                <a class="btn mod-btn" href="#">수정</a>
            </div>
            <div class="btn-group" id="edit-mode-btns" style="display: none;">
                <button class="btn cancel-btn">취소</button>
                <button class="btn save-btn">저장</button>
            </div>
        </section>
    </div>

    <script>
      // ========== 1. DOM 요소 및 전역 변수 ==========
      // 컨트롤러가 Model에 담아 보낸 studentId 값을 JavaScript 변수로 받음
      const studentId = '${studentId}';
      const API_URL = `/api/v1/scores/${studentId}`;

      // 각 모드에 대한 버튼 그룹
      const $viewModeBtns = document.getElementById('view-mode-btns');
      const $editModeBtns = document.getElementById('edit-mode-btns');

      // 원본 데이터를 저장할 변수 (수정 취소 시 복구용)
      let originalScoreData = {};

      // ========== 2. 모드 전환 함수 ==========
      /**
       * 화면을 '보기 모드' 또는 '수정 모드'로 전환하는 함수
       * @param {('view'|'edit')} mode - 전환할 모드
       */
      function switchMode(mode) {
        if (mode === 'edit') {
          // 보기 모드 숨기고, 수정 모드 보이기
          $viewModeBtns.style.display = 'none';
          $editModeBtns.style.display = 'flex';

          // 각 점수 <span>을 숨기고, <input>을 보이게 함
          document.querySelectorAll('.score-view').forEach(span => span.style.display = 'none');
          document.querySelectorAll('.score-edit').forEach(span => span.style.display = 'inline');

          // 총점, 평균, 석차 정보 숨기기
          document.querySelectorAll('.summary').forEach(li => li.style.display = 'none');

          // input에 현재 점수 채워넣기
          document.getElementById('kor-input').value = originalScoreData.kor;
          document.getElementById('eng-input').value = originalScoreData.eng;
          document.getElementById('math-input').value = originalScoreData.math;
        } else { // 'view' 모드
          // 수정 모드 숨기고, 보기 모드 보이기
          $viewModeBtns.style.display = 'flex';
          $editModeBtns.style.display = 'none';

          // <input> 숨기고, <span> 보이게 함
          document.querySelectorAll('.score-view').forEach(span => span.style.display = 'inline');
          document.querySelectorAll('.score-edit').forEach(span => span.style.display = 'none');

          // 총점, 평균, 석차 정보 보이기
          document.querySelectorAll('.summary').forEach(li => li.style.display = 'block');
        }
      }

      // ========== 3. 데이터 렌더링 함수 ==========
      /**
       * 서버에서 받은 데이터로 화면을 렌더링하는 함수
       * @param {object} data - 상세 정보 데이터
       */
      function renderDetails(data) {
        // 원본 데이터 저장
        originalScoreData = data;

        // 화면에 데이터 뿌리기
        document.getElementById('fullName').textContent = data.fullName;
        document.getElementById('kor').textContent = data.kor;
        document.getElementById('eng').textContent = data.eng;
        document.getElementById('math').textContent = data.math;
        document.getElementById('total').textContent = data.total;
        document.getElementById('average').textContent = data.average;
        document.getElementById('rank').textContent = `${data.rank} / ${data.totalCount}`;
      }

      // ========== 4. 서버 통신 함수 ==========
      /**
       * 서버에서 상세 정보를 가져오는 함수 (GET)
       */
      async function fetchDetail() {
        try {
          const res = await fetch(API_URL);
          if (!res.ok) throw new Error('학생 정보를 찾을 수 없습니다.');
          const data = await res.json();
          renderDetails(data);
        } catch (error) {
          alert(error.message);
          window.location.href = '/score/list'; // 목록 페이지로 이동
        }
      }

      /**
       * 수정된 정보를 서버로 전송하는 함수 (PUT)
       */
      async function fetchUpdateScore() {
        // 수정된 점수 가져오기
        const updatedData = {
          kor: +document.getElementById('kor-input').value,
          eng: +document.getElementById('eng-input').value,
          math: +document.getElementById('math-input').value,
          name: originalScoreData.fullName, // 이름은 수정하지 않음
          id: studentId
        };

        try {
          const res = await fetch(API_URL, {
            method: 'PUT', // 또는 'PATCH'
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(updatedData)
          });

          if (!res.ok) {
            // 유효성 검증 실패 시
            if (res.status === 400) {
              const errorData = await res.json();
              // 에러 메시지 표시
              document.querySelectorAll('.error-msg').forEach(span => span.textContent = '');
              for (const field in errorData) {
                document.querySelector(`#${field}-input + .error-msg`).textContent = errorData[field];
              }
            } else {
              throw new Error('수정에 실패했습니다.');
            }
            return; // 실패 시 함수 종료
          }

          // 성공 시, 다시 상세 정보를 조회하여 화면 갱신하고 보기 모드로 전환
          await fetchDetail();
          switchMode('view');

        } catch (error) {
          alert(error.message);
        }
      }

      // ========== 5. 이벤트 리스너 설정 ==========
      // '목록' 버튼 클릭
      document.querySelector('.list-btn').addEventListener('click', e => {
        e.preventDefault();
        window.location.href = '/score/list';
      });

      // '수정' 버튼 클릭
      document.querySelector('.mod-btn').addEventListener('click', e => {
        e.preventDefault();
        switchMode('edit');
      });

      // '취소' 버튼 클릭
      document.querySelector('.cancel-btn').addEventListener('click', e => {
        // 에러 메시지 초기화
        document.querySelectorAll('.error-msg').forEach(span => span.textContent = '');
        switchMode('view');
      });

      // '저장' 버튼 클릭
      document.querySelector('.save-btn').addEventListener('click', e => {
        fetchUpdateScore();
      });

      // ========== 6. 초기 실행 ==========
      // 페이지 로드 시 상세 정보 가져오기
      fetchDetail();

    </script>
</body>
</html>
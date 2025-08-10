const inputBox = document.getElementById("inputBox");
const warningMessage = document.getElementById("warningMessage");
const totoNo = document.getElementById('toto-data').getAttribute('data-toto');
const taskNo = document.getElementById('task-data').getAttribute('data-task');
let formTitle = document.getElementById('inputTask-title');

if (taskNo) {
   formTitle.innerHTML = "할 일 수정하기"
}

// 초기 상태 설정
inputBox.classList.remove('error');
warningMessage.style.visibility = 'hidden';

// input 중 자리수 초과시 알림
inputBox.addEventListener('input', () => {
  inputValue = inputBox.value;
  if (inputValue.length > 20) {
    inputBox.classList.add('error');
    warningMessage.textContent = '입력한 내용이 20자를 초과했습니다.';
    warningMessage.style.visibility = 'visible';
  }
  else if (inputValue.length === 0) {
    inputBox.classList.add('error');
    warningMessage.textContent = '내용을 입력하세요.';
    warningMessage.style.visibility = 'visible';
  }
  else {
    inputBox.classList.remove('error');
    warningMessage.style.visibility = 'hidden';
  }
});

// 확인 클릭시 입력값을 제출
function submitTask() {
    event.preventDefault();  // 폼 기본 동작을 막음 (새로고침 방지)
    const taskNm = inputBox.value.trim(); // 공백 제거
    
    // 빈값 체크 추가
    if (!taskNm || taskNm.length === 0) {
        inputBox.classList.add('error');
        warningMessage.textContent = '내용을 입력하세요.';
        warningMessage.style.visibility = 'visible';
        inputBox.focus();
        return; // 함수 종료
    }

    let submitValue = {};
    let url = '';

    if (!taskNo) {  // 신규일 때 서버로 전송할 데이터와 주소
        submitValue =
            {
                "totoNo": parseInt(totoNo),
                "taskNm": taskNm
            };
        url = '/totos/tasks';
    }
    else {          // 수정일 때 서버로 전송할 데이터와 주소
        submitValue =
            {
                "totoNo": parseInt(totoNo),
                "taskNo": parseInt(taskNo),
                "taskNm": taskNm
            };
        url = `/totos/${totoNo}/tasks/${taskNo}/edit`;
    }

        // 요청 보내기 전에 콘솔에 전송 데이터 출력
    fetch(url, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(submitValue),  // JSON 형식으로 데이터 전송
        redirect: 'follow'  // 리다이렉트를 자동으로 따르도록 설정
    })
        .then(response => {
            if(response.redirected) {
                window.parent.postMessage('closeModal', '*'); // 모달 닫기
                // 부모 페이지 새로고침은 부모에서 처리
            }

        })
        .catch((error) => {
            console.error('Error:', error);
        });
}

function cancelInput() {
    window.parent.postMessage('closeModal', '*'); // 모달 닫기
}


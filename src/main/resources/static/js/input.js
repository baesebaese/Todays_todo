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
    warningMessage.style.visibility = 'visible';
  }
  else {
    inputBox.classList.remove('error');
    warningMessage.style.visibility = 'hidden';
  }
});

// 확인 버튼 클릭시 입력값을 제출
function submitTask() {
    event.preventDefault();  // 폼 기본 동작을 막음 (새로고침 방지)
    const taskNm = inputBox.value;

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

    // 확인한 데이터와 주소로 데이터 전송
    fetch(url, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(submitValue),  // JSON 형식으로 데이터 전송
        redirect: 'follow'  // 리다이렉트를 자동으로 따르도록 설정
    })
        .then(response => {
            if(response.redirected) {
                window.location.href = response.url;
            }

        })
        .catch((error) => {
            console.error('Error:', error);
        });
}

// 취소 버튼 클릭시 이전으로 돌아가기
function cancelInput() {
    window.history.back();
}
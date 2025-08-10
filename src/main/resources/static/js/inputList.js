const inputBox = document.getElementById("inputBox");
const warningMessage = document.getElementById("warningMessage");

// 초기 상태 설정
inputBox.classList.remove('error');
warningMessage.style.visibility = 'hidden';

// input 중 오류 시 알림
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

function submitTask() {
    event.preventDefault();  // 폼 기본 동작을 막음 (새로고침 방지)
    const totoNm = inputBox.value.trim(); // 공백 제거
    
    // 빈값 체크 추가
    if (!totoNm || totoNm.length === 0) {
        inputBox.classList.add('error');
        warningMessage.textContent = '내용을 입력하세요.';
        warningMessage.style.visibility = 'visible';
        inputBox.focus();
        return; // 함수 종료
    }
    
    const submitValue =
        {
            "totoNm": totoNm
        };

    // 요청 보내기 전에 콘솔에 전송 데이터 출력
    fetch('/totos/new', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(submitValue),  // JSON 형식으로 데이터 전송
        redirect: 'follow'  // 리다이렉트를 자동으로 따르도록 설정
    })
        .then(response => response.json())
        .then(data => {
            if (data.status === 'success') {
                window.parent.postMessage('closeModal', '*')  // 성공 시 리다이렉트
            } else {
                alert("저장에 실패했습니다.");
                window.parent.postMessage('closeModal', '*')  // 실패 시에도 해당 페이지로 리다이렉트
            }
        })
        .catch((error) => {
        //    console.error('Error:', error);
        }
    );
}

function closeModal() {
    inputBox.value = '';
    window.parent.postMessage('closeModal', '*');
}


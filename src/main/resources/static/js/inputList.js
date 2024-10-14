const inputBox = document.getElementById("inputBox");
const warningMessage = document.getElementById("warningMessage");

// 초기 상태 설정
inputBox.classList.remove('error');
warningMessage.style.visibility = 'hidden';

// input 중 자리수 초과시 알림
inputBox.addEventListener('input', () => {
  inputValue = inputBox.value;
  if (inputValue.length > 10) {
    inputBox.classList.add('error');
    warningMessage.style.visibility = 'visible';
  }
  else {
    inputBox.classList.remove('error');
    warningMessage.style.visibility = 'hidden';
  }
});

function submitTask() {
    event.preventDefault();  // 폼 기본 동작을 막음 (새로고침 방지)
    const totoNm = inputBox.value;
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
        .then(response => {
            if (response.redirected) {
                window.location.href = response.url;  // 리다이렉트 처리
            } else {
                return response.json();  // JSON 응답 처리
            }
        })
        .catch((error) => {
            console.error('Error:', error);
        });

}

function cancelInput() {
    alert(totoNm)
}
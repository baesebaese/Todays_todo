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

function submitValue() {

      alert(inputValue)
}

function cancelInput() {
    alert("창닫기")
}
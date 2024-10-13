const inputBox = document.getElementById("inputBox");
const warningMessage = document.getElementById("warningMessage");
const totoNo = document.getElementById('toto-data').getAttribute('data-toto')

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
    taskNm = inputBox.value;
    const submitValue= {"totoNo": totoNo, "taskNm": taskNm}

    alert(JSON.stringify(submitValue))

    fetch('totos/tasks', {
          method: 'POST',
          body: submitValue
        })
        .then(response => response.json())
        .then(data => {
          console.log('Success:', data);
        })
        .catch((error) => {
          console.error('Error:', error);
        });
};

function cancelInput() {
    alert(totoNo)
}
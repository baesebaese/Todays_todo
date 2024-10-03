  function submitValue() {
    const inputBox = document.getElementById("inputBox");
    const inputValue = inputBox.value;

    if (inputValue.includes('*')) {
      alert("특수문자 금지!")
    }
    else if (inputValue == '') {
      alert("빈값은 안 돼요")
    }
    else {
      alert(InputValue)
    }
  }

  function cancelInput() {
    alert("창닫기")
  }
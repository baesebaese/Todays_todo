// 마우스 오버시 수정/삭제 버튼 표시
document.querySelectorAll('.tr-tasknm, .tr-taskicons').forEach(item => {
    const taskRow = item.closest('tr');
    const closeIcon = taskRow.querySelector('.tr-taskicons #close-icon');
    const editIcon = taskRow.querySelector('.tr-taskicons #edit-icon');

    const showIcons = () => {
        closeIcon.style.visibility = 'visible';
        editIcon.style.visibility = 'visible';
    };

    const hideIcons = () => {
        closeIcon.style.visibility = 'hidden';
        editIcon.style.visibility = 'hidden';
    };

    item.addEventListener('mouseover', showIcons);
    item.addEventListener('mouseout', hideIcons);
});

// delete 버튼 클릭시 삭제 처리
function deleteTask(element) {
    event.preventDefault();  // 폼 기본 동작을 막음 (새로고침 방지)
    const totoNo = element.getAttribute('data-toto-no');
    const taskNo = element.getAttribute('data-task-no');

    const url = `/totos/${totoNo}/tasks/${taskNo}`;

    fetch(url, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        },
        redirect: 'follow'  // 리다이렉트를 자동으로 따르도록 설정
    })
    .then(response => {
        if (response.redirected) {
            window.location.href = response.url;
        } else {
            return response.json();  // JSON 응답 처리
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('An error occurred while deleting the task.');
    });
}
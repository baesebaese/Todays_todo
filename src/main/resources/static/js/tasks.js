// 마우스 오버시 수정/삭제 버튼 표시
document.querySelectorAll('.tr-tasknm, .tr-taskicons').forEach(item => {
    const taskRow = item.closest('tr');
    const deleteTaskIcon = taskRow.querySelector('.tr-taskicons #delete-task-icon');
    const editTaskIcon = taskRow.querySelector('.tr-taskicons #edit-task-icon');

    const showIcons = () => {
        deleteTaskIcon.style.visibility = 'visible';
        editTaskIcon.style.visibility = 'visible';
    };

    const hideIcons = () => {
        deleteTaskIcon.style.visibility = 'hidden';
        editTaskIcon.style.visibility = 'hidden';
    };

    item.addEventListener('mouseover', showIcons);
    item.addEventListener('mouseout', hideIcons);
});

// 태스크 삭제 버튼 클릭시 삭제 처리
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

// 리스트 삭제 버튼 클릭시 삭제 처리
function deleteToto(element) {
    event.preventDefault();  // 폼 기본 동작을 막음 (새로고침 방지)
    const totoNo = element.getAttribute('data-toto-no');

    const url = `/유알엘바까주세요}`;

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
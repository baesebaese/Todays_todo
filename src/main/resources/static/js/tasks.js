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

    const url = `/totos/${totoNo}`;

    fetch(url, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        },
        redirect: 'follow'  // 리다이렉트가 필요할 경우 자동으로 처리
    })
        .then(response => {
            if (response.ok) {  // HTTP 상태 코드가 2xx일 경우
                if (response.redirected) {
                    window.location.href = response.url;  // 리다이렉트된 URL로 이동
                } else {
                    return response.json();  // JSON 응답 처리
                }
            } else {
                throw new Error('Failed to delete the task');  // 오류 발생 시 예외 던짐
            }
        })
        .then(data => {
            if (data.status === 'success') {
                // 성공 처리
                window.location.href = data.redirectUrl;  // 서버에서 지정한 URL로 리다이렉트
            } else {
                alert("Failed to delete the task.");
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('An error occurred while deleting the task.');
        });
}
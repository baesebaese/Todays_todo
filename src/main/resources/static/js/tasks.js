// 체크박스 클릭 시 상태값 업데이트 처리
function updateTaskStatus(element) {
    const totoNo = element.getAttribute('data-toto-no');
    const taskNo = element.getAttribute('data-task-no');
    const newStatus = element.checked ? 'Y' : 'N'; // 체크 여부에 따라 상태 설정

    const url = `/totos/${totoNo}/tasks/${taskNo}/status`

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            status: newStatus
        })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Failed to update task status');
        }
        return response.json();
    })
    .then(data => {
        // 상태 업데이트 성공 후 알림 표시
        console.log('Task status updated');
    })
    .catch(error => {
        console.error('Error updating task status');
    });
}

// 태스크에 마우스 오버시 수정/삭제 버튼 표시
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


// 타이틀에 마우스를 올렸을 때 수정 버튼을 표시
const title = document.getElementById('list-title-name');
const editIcon = document.getElementById('edit-list-icon');

title.addEventListener('mouseover', function() {
    editIcon.style.visibility = 'visible';  // 버튼 표시
});

title.addEventListener('mouseout', function() {
    editIcon.style.visibility = 'hidden';  // 버튼 숨김
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

// 모달 열기 - 신규 태스크 추가 버튼 클릭 시
document.addEventListener('DOMContentLoaded', function() {
    const addTaskBtn = document.getElementById('add-task');
    if (addTaskBtn) {
        addTaskBtn.addEventListener('click', function(e) {
            e.preventDefault(); // 기본 링크 동작 방지
            document.getElementById("taskModal").style.display = "block";
        });
    }
});

// 모달 밖 클릭 시 닫기
window.onclick = function(event) {
    if (event.target == document.getElementById("taskModal")) {
        document.getElementById("taskModal").style.display = "none";
    }
}

// 모달에서 'closeModal' 메시지를 받을 경우 모달창 닫기
window.addEventListener('message', function(event) {
    if (event.data === 'closeModal') {
        document.getElementById("taskModal").style.display = "none";  // 모달 닫기
        location.reload(); // 새로고침
    }
});
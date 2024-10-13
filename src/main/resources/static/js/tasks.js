    // 마우스 인/아웃시 close 아이콘 표시/숨김 처리
    document.querySelectorAll('.tr-tasknm, .tr-taskicons').forEach(item => {
        item.addEventListener('mouseover', () => {
            const closeIcon = item.closest('tr').querySelector('.tr-taskicons #close-icon');
            closeIcon.style.visibility = 'visible';
        });

        item.addEventListener('mouseout', () => {
            const closeIcon = item.closest('tr').querySelector('.tr-taskicons #close-icon');
            closeIcon.style.visibility = 'hidden';
        });
    });
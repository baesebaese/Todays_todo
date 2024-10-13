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
document.addEventListener('DOMContentLoaded', function() {
    const totoBody = document.getElementById('toto-body');
    const rows = totoBody.getElementsByTagName('tr');

    // 전체 데이터 줄 수 (추가 버튼을 포함하여)
    let totalRows = rows.length;

    // 공백 라인 추가하기
     if (totalRows < 10) {
        let emptyRowsCount = 10 - totalRows;
        for (let i = 0; i < emptyRowsCount; i++) {
            const emptyRow = document.createElement('tr');
            emptyRow.classList.add('empty-row');
            // 공백 td 추가
            emptyRow.innerHTML = '<td class="tr-listnm"></td><td class="tr-listpg"></td>';
            totoBody.appendChild(emptyRow);
        }
    }
});
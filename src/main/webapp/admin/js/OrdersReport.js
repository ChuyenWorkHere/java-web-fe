function toggleFilterMode() {
  const isYear = document.getElementById("filterYear").checked;
  document.getElementById("yearFilterGroup").style.display = isYear ? "block" : "none";
  document.getElementById("monthFilterGroup").style.display = isYear ? "none" : "block";
}

function submitYear() {
  const year = document.getElementById("filterYearSelect").value;
  window.location.href = `../admin/orders-report?year=${year}`;
}

function submitMonth() {
  const year = document.getElementById("filterMonthYearSelect").value;
  const month = document.getElementById("filterMonthSelect").value;
  window.location.href = `../admin/orders-report?year=${year}&month=${month}`;
}

function togglePdfExportMode() {
  const isYear = document.getElementById("pdfExportYear").checked;
  document.getElementById("pdfExportYearGroup").style.display = isYear ? "block" : "none";
  document.getElementById("pdfExportMonthGroup").style.display = isYear ? "none" : "block";
}

/* nut submit pdf */
function submitPdfExportYear() {
    const yearPdf = document.getElementById("pdfExportYearSelect").value;

    html2canvas(document.querySelector('#lineChart')).then(canvas => {
        const chartImage = canvas.toDataURL('image/png');
        fetch('../admin/exportPdf', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                year: yearPdf,
                mode: 'year',
                chartImage: chartImage
            })
        })
        .then(response => response.blob())
        .then(blob => {
            const url = window.URL.createObjectURL(blob);
            const a = document.createElement('a');
            a.href = url;
            a.download = `report_${yearPdf}.pdf`;
            a.click();
            window.URL.revokeObjectURL(url);
        })
        .catch(error => console.error('Lỗi khi xuất PDF:', error));
    });
}

function submitPdfExportMonth() {
    const yearPdf = document.getElementById("pdfExportMonthYearSelect").value;
    console.log(yearPdf);
    const monthPdf = document.getElementById("pdfExportMonthSelect").value;
    console.log(monthPdf);
    console.log("Năm xuất PDF:", yearPdf, "Tháng:", monthPdf); // Debug: Kiểm tra giá trị
    html2canvas(document.querySelector('#lineChart')).then(canvas => {
        const chartImage = canvas.toDataURL('image/png');
        fetch('../admin/exportPdf', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                year: yearPdf,
                month: monthPdf,
                mode: 'month',
                chartImage: chartImage
            })
        })
        .then(response => response.blob())
        .then(blob => {
            const url = window.URL.createObjectURL(blob);
            const a = document.createElement('a');
            a.href = url;
            a.download = `report_${yearPdf}_month_${monthPdf}.pdf`;
            a.click();
            window.URL.revokeObjectURL(url);
        })
        .catch(error => console.error('Lỗi khi xuất PDF:', error));
    });
}

/* nut submit pdf*/

// Prevent dropdown click from propagating
document.querySelectorAll('.dropdown-menu').forEach(dropdown => {
  dropdown.addEventListener('click', function (e) {
    e.stopPropagation();
  });
});

function getQueryParams() {
  const params = {};
  window.location.search.substring(1).split("&").forEach(pair => {
    if (pair) {
      const [key, value] = pair.split("=");
      params[decodeURIComponent(key)] = decodeURIComponent(value || "");
    }
  });
  return params;
}


window.addEventListener('DOMContentLoaded', () => {
  const params = getQueryParams();

  // --- Filter Dropdown ---
  const filterYearRadio = document.getElementById('filterYear');
  const filterMonthRadio = document.getElementById('filterMonth');
  const yearSelectOnly = document.getElementById('filterYearSelect');
  const monthYearSelect = document.getElementById('filterMonthYearSelect');
  const monthSelect = document.getElementById('filterMonthSelect');

  if (params.month) {
    filterMonthRadio.checked = true;
    toggleFilterMode();
    if (params.year) monthYearSelect.value = params.year;
    monthSelect.value = params.month;
  } else if (params.year) {
    filterYearRadio.checked = true;
    toggleFilterMode();
    yearSelectOnly.value = params.year;
  } else {
    filterYearRadio.checked = true;
    toggleFilterMode();
  }

  // --- PDF Export Dropdown ---
 const pdfExportYearRadio = document.getElementById('pdfExportYear');
 const pdfExportMonthRadio = document.getElementById('pdfExportMonth');
 const pdfExportYearSelect = document.getElementById('pdfExportYearSelect');
 const pdfExportMonthYearSelect = document.getElementById('pdfExportMonthYearSelect');
 const pdfExportMonthSelect = document.getElementById('pdfExportMonthSelect');

 pdfExportYearRadio.checked = true;
 togglePdfExportMode();

 if (params.year) {
   pdfExportYearSelect.value = params.year;
   pdfExportMonthYearSelect.value = params.year;
 }
 if (params.month) {
   pdfExportMonthSelect.value = params.month;
 }

});


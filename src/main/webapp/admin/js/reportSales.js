function toggleFilterMode() {
  const isYear = document.getElementById("filterYear").checked;
  document.getElementById("yearFilterGroup").style.display = isYear ? "block" : "none";
  document.getElementById("monthFilterGroup").style.display = isYear ? "none" : "block";
}

function submitYear() {
  const year = document.getElementById("yearSelectOnly").value;
  window.location.href = `../admin/sales-report?year=${year}`;
}

function submitMonth() {
  const year = document.getElementById("monthYearSelect").value;
  const month = document.getElementById("monthSelect").value;
  window.location.href = `../admin/sales-report?year=${year}&month=${month}`;
}

// Ngăn không cho sự kiện click lan ra ngoài dropdown
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

  const filterYearRadio = document.getElementById('filterYear');
  const filterMonthRadio = document.getElementById('filterMonth');
  const yearSelectOnly = document.getElementById('yearSelectOnly');
  const monthYearSelect = document.getElementById('monthYearSelect');
  const monthSelect = document.getElementById('monthSelect');

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
});

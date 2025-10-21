/* start nút lọc */
function applyOrderFilter() {
  // Lấy giá trị từ các select box
  const priceRange = document.getElementById("priceRange").value;
  const orderStatus = document.getElementById("orderStatus").value;
  const paymentStatus = document.querySelectorAll("#paymentMethod")[0].value; // ô đầu tiên: Đã/Chưa thanh toán
  const paymentMethod = document.querySelectorAll("#paymentMethod")[1].value; // ô thứ hai: COD, chuyển khoản...
  const orderSort = document.getElementById("orderSort").value;

  // Tạo query string
  const params = new URLSearchParams();
  if (priceRange !== "") params.append("priceRange", priceRange);
  if (orderStatus !== "all") params.append("ordersStatus", orderStatus);
  if (paymentStatus !== "all") params.append("paymentStatus", paymentStatus);
  if (paymentMethod !== "all") params.append("paymentMethod", paymentMethod);
  if (orderSort !== "") params.append("orderSort", orderSort);

  // Điều hướng đến Servlet
  window.location.href = "../admin/orders-view?" + params.toString();
}
/* end nút lọc */

// start nút excel
function submitDateRange() {
  const startDate = document.getElementById("startDate").value;
  const endDate = document.getElementById("endDate").value;

  if (!startDate || !endDate) {
    alert("Vui lòng chọn cả ngày bắt đầu và ngày kết thúc.");
    return;
  }

  // Có thể đổi thành submit form hoặc window.location.href
  window.location.href = `../admin/export-order-excel?startDate=${startDate}&endDate=${endDate}`;
}

// Ngăn không cho sự kiện click lan ra ngoài dropdown
document.querySelectorAll('.dropdown-menu').forEach(dropdown => {
  dropdown.addEventListener('click', function (e) {
    e.stopPropagation();
  });
});

// (Tùy chọn) Tự động điền lại ngày đã chọn nếu có trên URL
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
  if (params.startDate) document.getElementById("startDate").value = params.startDate;
  if (params.endDate) document.getElementById("endDate").value = params.endDate;
});
// end nút excel

function submitDateRangePdf() {
    var startDate = document.getElementById("startDatePdf").value;
    var endDate = document.getElementById("endDatePdf").value;

    // Kiểm tra xem người dùng đã nhập ngày chưa
    if (!startDate || !endDate) {
        alert("Vui lòng chọn cả ngày bắt đầu và ngày kết thúc.");
        return;
    }

    // Tạo URL để gửi yêu cầu đến servlet
    var url = "ExportPdfServlet?startDate=" + startDate + "&endDate=" + endDate;

    // Chuyển hướng đến URL để xuất PDF
    window.location.href = url;
}
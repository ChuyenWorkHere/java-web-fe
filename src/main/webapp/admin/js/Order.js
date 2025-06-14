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




$(document).ready(function () {


  /*start nút xoá mềm*/
  $(".deleteIcon").on("click", function () {
    const orderId = $(this).data("order-id");

    Swal.fire({
      title: 'Bạn có chắc chắn?',
      text: 'Bạn có chắc muốn xoá đơn hàng này không?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Xoá',
      cancelButtonText: 'Huỷ',
      confirmButtonColor: '#d33',
      cancelButtonColor: '#6c757d'
    }).then((result) => {
      if (result.isConfirmed) {
        // Gửi orderId đến controller bằng AJAX
        $.ajax({
          url: "../admin/soft-order-delete",
          method: "POST",
          data: { orderId: orderId },
          success: function (response) {
            // Xử lý sau khi xoá
            console.log("Soft Deleted orderId:", orderId);
            if (response.success) {
              Swal.fire(
                'Đã xoá!',
                'Đơn hàng đã được chuyển vào thùng rác.',
                'success'
              ).then(() => {
                location.reload(); // Tải lại trang để cập nhật
              });
            } else {
              Swal.fire(
                'Lỗi!',
                'Xoá không thành công.',
                'error'
              );
            }
          },
          error: function (error) {
            console.error("Lỗi xóa đơn hàng:", error);
            Swal.fire(
              'Lỗi!',
              'Có lỗi xảy ra khi xoá đơn hàng!',
              'error'
            );
          }
        });
      }
    });
  });
  /*end nút xoá mềm*/

  /*start nút xoá*/
  $(".deleteBtn").on("click", function () {
    const orderId = $(this).data("order-id");

    Swal.fire({
      title: 'Bạn có chắc chắn?',
      text: 'Bạn có chắc muốn xoá vĩnh viễn đơn hàng này không?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Xoá',
      cancelButtonText: 'Huỷ',
      confirmButtonColor: '#d33',
      cancelButtonColor: '#6c757d'
    }).then((result) => {
      if (result.isConfirmed) {
        // Gửi orderId đến controller bằng AJAX
        $.ajax({
          url: "../admin/order-delete",
          method: "POST",
          data: { orderId: orderId },
          success: function (response) {
            // Xử lý sau khi xoá
            if (response.success) {
              Swal.fire(
                'Đã xoá!',
                'Đơn hàng đã được xoá thành công.',
                'success'
              ).then(() => {
                location.reload(); // Tải lại trang để cập nhật
              });
            } else {
              Swal.fire(
                'Lỗi!',
                'Xoá không thành công.',
                'error'
              );
            }
          },
          error: function (error) {
            console.error("Lỗi xóa đơn hàng:", error);
            Swal.fire(
              'Lỗi!',
              'Có lỗi xảy ra khi xoá đơn hàng haha!',
              'error'
            );
          }
        });
      }
    });
  });
  /*end nút xoá*/


    // Chọn tất cả checkbox khi nhấn vào checkbox 'Chọn tất cả'
    $('#selectAll').on('change', function() {
        $('.orderCheckbox').prop('checked', this.checked);
    });

    // Xóa đã chọn
    $('#deleteSelected').on('click', function() {
        const selectedOrders = $('.orderCheckbox:checked').map(function() {
            return $(this).data('order-id');
        }).get();

        if (selectedOrders.length === 0) {
            Swal.fire('Thông báo', 'Vui lòng chọn ít nhất một đơn hàng để xóa.', 'warning');
            return;
        }

        Swal.fire({
            title: 'Bạn có chắc chắn?',
            text: 'Bạn có chắc muốn xóa các đơn hàng đã chọn không?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Xóa',
            cancelButtonText: 'Huỷ',
            confirmButtonColor: '#d33',
            cancelButtonColor: '#6c757d'
        }).then((result) => {
            if (result.isConfirmed) {
                $.ajax({
                    url: '../admin/order-delete-all-trash',
                    method: 'POST',
                    data: { orderIds: selectedOrders },
                    traditional: true, // ✅ Rất quan trọng
                    success: function(response) {
                        if (response.success) {
                            Swal.fire('Đã xóa!', 'Các đơn hàng đã được xóa thành công.', 'success').then(() => {
                                location.reload();
                            });
                        } else {
                            Swal.fire('Lỗi!', 'Xóa không thành công.', 'error');
                        }
                    },
                    error: function(error) {
                        console.error('Lỗi xóa đơn hàng:', error);
                        Swal.fire('Lỗi!', 'Có lỗi xảy ra khi xóa đơn hàng!', 'error');
                    }
                });

            }
        });
    });





//  start  nút khôi phục đơn hàng
    $('.restoreBtn').on('click', function () {
        const orderId = $(this).data('order-id');
        Swal.fire({
            title: 'Bạn có chắc chắn?',
            text: 'Bạn có chắc muốn khôi phục đơn hàng này không?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Khôi phục',
            cancelButtonText: 'Huỷ',
            confirmButtonColor: '#28a745',
            cancelButtonColor: '#6c757d'
        }).then((result) => {
            if (result.isConfirmed) {
                $.ajax({
                    url: '../admin/order-restore', // Đường dẫn đến servlet khôi phục
                    method: 'POST',
                    data: { orderId: orderId },
                    success: function (response) {
                        if (response.success) {
                            Swal.fire('Đã khôi phục!', 'Đơn hàng đã được khôi phục thành công.', 'success').then(() => {
                                location.reload(); // Tải lại trang để cập nhật
                            });
                        } else {
                            Swal.fire('Lỗi!', 'Khôi phục không thành công.', 'error');
                        }
                    },
                    error: function (error) {
                        console.error("Lỗi khôi phục đơn hàng:", error);
                        Swal.fire('Lỗi!', 'Có lỗi xảy ra khi khôi phục đơn hàng!', 'error');
                    }
                });
            }
        });
    });

    // Phục hồi đã chọn
    $('#restoreSelected').on('click', function() {
        const selectedOrders = $('.orderCheckbox:checked').map(function() {
            return $(this).data('order-id');
        }).get();

        if (selectedOrders.length === 0) {
            Swal.fire('Thông báo', 'Vui lòng chọn ít nhất một đơn hàng để phục hồi.', 'warning');
            return;
        }

        Swal.fire({
            title: 'Bạn có chắc chắn?',
            text: 'Bạn có chắc muốn phục hồi các đơn hàng đã chọn không?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Phục hồi',
            cancelButtonText: 'Huỷ',
            confirmButtonColor: '#28a745',
            cancelButtonColor: '#6c757d'
        }).then((result) => {
            if (result.isConfirmed) {
                $.ajax({
                    url: '../admin/order-restore-all',
                    method: 'POST',
                    data: { orderIds: selectedOrders },
                    traditional: true, // Rất quan trọng
                    success: function(response) {
                        if (response.success) {
                            Swal.fire('Đã phục hồi!', 'Các đơn hàng đã được phục hồi thành công.', 'success').then(() => {
                                location.reload();
                            });
                        } else {
                            Swal.fire('Lỗi!', 'Phục hồi không thành công.', 'error');
                        }
                    },
                    error: function(error) {
                        console.error('Lỗi phục hồi đơn hàng:', error);
                        Swal.fire('Lỗi!', 'Có lỗi xảy ra khi phục hồi đơn hàng!', 'error');
                    }
                });
            }
        });
    });

});
// end khôi phục đơn hàng


  // start nút excel
//   function submitDateRange() {  // Bỏ comment nếu bạn muốn bật lại chức năng excel
//     const startDate = document.getElementById("startDate").value;
//     const endDate = document.getElementById("endDate").value;
//
//     if (!startDate || !endDate) {
//       alert("Vui lòng chọn cả ngày bắt đầu và ngày kết thúc.");
//       return;
//     }
//
//     // Có thể đổi thành submit form hoặc window.location.href
//     window.location.href = `../admin/export-order-excel?startDate=${startDate}&endDate=${endDate}`;
//   }
//
//   Ngăn không cho sự kiện click lan ra ngoài dropdown
//  document.querySelectorAll('.dropdown-menu').forEach(dropdown => {
//    dropdown.addEventListener('click', function (e) {
//      e.stopPropagation();
//    });
//  });
//
//  // (Tùy chọn) Tự động điền lại ngày đã chọn nếu có trên URL
//  function getQueryParams() {
//    const params = {};
//    window.location.search.substring(1).split("&").forEach(pair => {
//      if (pair) {
//        const [key, value] = pair.split("=");
//        params[decodeURIComponent(key)] = decodeURIComponent(value || "");
//      }
//    });
//    return params;
//  }
//
//  window.addEventListener('DOMContentLoaded', () => {
//    const params = getQueryParams();
//    if (params.startDate) document.getElementById("startDate").value = params.startDate;
//    if (params.endDate) document.getElementById("endDate").value = params.endDate;
//  });
//  // end nút excel
//});
//
//function applyOrderFilter() {
//  // Lấy giá trị từ các select box
//  const priceRange = document.getElementById("priceRange").value;
//  const orderStatus = document.getElementById("orderStatus").value;
//  const paymentStatus = document.querySelectorAll("#paymentMethod")[0].value; // ô đầu tiên: Đã/Chưa thanh toán
//  const paymentMethod = document.querySelectorAll("#paymentMethod")[1].value; // ô thứ hai: COD, chuyển khoản...
//  const orderSort = document.getElementById("orderSort").value;
//
//  // Tạo query string
//  const params = new URLSearchParams();
//  if (priceRange !== "") params.append("priceRange", priceRange);
//  if (orderStatus !== "all") params.append("ordersStatus", orderStatus);
//  if (paymentStatus !== "all") params.append("paymentStatus", paymentStatus);
//  if (paymentMethod !== "all") params.append("paymentMethod", paymentMethod);
//  if (orderSort !== "") params.append("orderSort", orderSort);
//
//  // Điều hướng đến Servlet
//  window.location.href = "../admin/orders-view?" + params.toString();
//}
//
//function submitDateRange() {
//  const startDate = document.getElementById("startDate").value;
//  const endDate = document.getElementById("endDate").value;
//
//  if (!startDate || !endDate) {
//    alert("Vui lòng chọn cả ngày bắt đầu và ngày kết thúc.");
//    return;
//  }
//
//  // Có thể đổi thành submit form hoặc window.location.href
//  window.location.href = `../admin/export-order-excel?startDate=${startDate}&endDate=${endDate}`;
//}

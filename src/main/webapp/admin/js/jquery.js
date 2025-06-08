//$(document).ready(function () {
//  // phần thông báo xác nhận xóa
//  $(".deleteIcon").on("click", function () {
//    $("#confirmDeleteModal").modal("show");
//  });
//
//  $("#confirmDeleteBtn").on("click", function () {
//    $("#confirmDeleteModal").modal("hide");
//    alert("Đã xoá thành công!");
//  });
//});
$(".deleteIcon").on("click", function () {
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
      // Gọi ajax xoá, hoặc thực hiện hành động xoá tại đây
      Swal.fire(
        'Đã xoá!',
        'Đơn hàng đã được xoá thành công.',
        'success'
      );
    }
  });
});

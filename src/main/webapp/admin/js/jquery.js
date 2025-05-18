$(document).ready(function () {
  // phần thông báo xác nhận xóa
  $(".deleteIcon").on("click", function () {
    $("#confirmDeleteModal").modal("show");
  });

  $("#confirmDeleteBtn").on("click", function () {
    $("#confirmDeleteModal").modal("hide");
    alert("Đã xoá thành công!");
  });
});
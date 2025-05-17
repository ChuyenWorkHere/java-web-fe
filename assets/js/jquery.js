$(document).ready(function () {
  $(".deleteIcon").on("click", function () {
    $("#confirmDeleteModal").modal("show");
  });

  $("#confirmDeleteBtn").on("click", function () {
    $("#confirmDeleteModal").modal("hide");
    alert("Đã xoá thành công!");
  });
});

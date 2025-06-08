$(document).ready(function () {
  // phần thông báo xác nhận xóa
  $(".deleteIcon").on("click", function () {
    $("#confirmDeleteModal").modal("show");
  });

  $("#confirmDeleteBtn").on("click", function () {
    $("#confirmDeleteModal").modal("hide");
    alert("Đã xoá thành công!");
  });


//
$("#monthFilter").click(function(){
    $("#monthFilter").show();
    $("#yearFilter").hide();
})
$("#yearFilter").click(function(){
    $("#yearFilter").show();
    $("#monthFilter").hide();
})

});
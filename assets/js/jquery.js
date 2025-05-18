$(document).ready(function () {
  // phần validation login
  $("form").on("submit", function (e) {
    e.preventDefault();

    var username = $("#yourUsername").val().trim();
    var password = $("#yourPassword").val().trim();
    var isValid = true;

    // Reset lỗi
    $("#usernameError").text("");
    $("#passwordError").text("");
    $("#yourUsername").removeClass("is-invalid");
    $("#yourPassword").removeClass("is-invalid");

    // Regex kiểm tra
    var usernameRegex = /^[a-zA-Z0-9]{4,20}$/;
    var passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[^A-Za-z\d]).{6,}$/;

    // Kiểm tra username
    if (username.length < 4 || username.length > 20) {
      $("#usernameError").text("Tài khoản phải từ 4 đến 20 ký tự.");
      $("#yourUsername").addClass("is-invalid");
      isValid = false;
    } else if (!usernameRegex.test(username)) {
      $("#usernameError").text(
        "Tài khoản chỉ được chứa chữ cái và số, không có ký tự đặc biệt."
      );
      $("#yourUsername").addClass("is-invalid");
      isValid = false;
    }

    // Kiểm tra password
    if (password.length < 6) {
      $("#passwordError").text("Mật khẩu phải dài ít nhất 6 ký tự.");
      $("#yourPassword").addClass("is-invalid");
      isValid = false;
    } else if (!passwordRegex.test(password)) {
      $("#passwordError").text(
        "Mật khẩu phải chứa ít nhất 1 chữ cái, 1 số và 1 ký tự đặc biệt."
      );
      $("#yourPassword").addClass("is-invalid");
      isValid = false;
    }

    if (isValid) {
      alert("Đăng nhập thành công!");
    }
  });

  // phần thông báo xác nhận xóa
  $(".deleteIcon").on("click", function () {
    $("#confirmDeleteModal").modal("show");
  });

  $("#confirmDeleteBtn").on("click", function () {
    $("#confirmDeleteModal").modal("hide");
    alert("Đã xoá thành công!");
  });
});

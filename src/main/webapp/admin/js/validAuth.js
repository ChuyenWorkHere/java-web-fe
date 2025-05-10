/**
 * 
 */

document.getElementById("loginForm").addEventListener("submit", function (event) {
  event.preventDefault();

  const email = document.getElementById("email").value.trim();
  const password = document.getElementById("password").value;
  const messageEl = document.getElementById("loginMessage");

  // Kiểm tra không được để trống
  if (!email || !password) {
    showError("Vui lòng nhập đầy đủ email và mật khẩu.");
    document.getElementById("email").focus();
    return;
  }

  // Biểu thức regex kiểm tra định dạng email
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (!emailRegex.test(email)) {
    showError("Email không đúng định dạng.");
    document.getElementById("email").focus();
    return;
  }

  // Kiểm tra độ dài tối thiểu
  if (password.length < 6) {
    showError("Mật khẩu phải có ít nhất 6 ký tự.");
    document.getElementById("password").focus();
    return;
  }

  // Kiểm tra có chữ thường
  if (!/[a-z]/.test(password)) {
    showError("Mật khẩu phải chứa ít nhất 1 chữ thường.");
    document.getElementById("password").focus();
    return;
  }

  // Kiểm tra có chữ hoa
  if (!/[A-Z]/.test(password)) {
    showError("Mật khẩu phải chứa ít nhất 1 chữ hoa.");
    document.getElementById("password").focus();
    return;
  }

  // Kiểm tra có số
  if (!/[0-9]/.test(password)) {
    showError("Mật khẩu phải chứa ít nhất 1 chữ số.");
    document.getElementById("password").focus();
    return;
  }

  // Kiểm tra không chứa khoảng trắng
  if (/\s/.test(password)) {
    showError("Mật khẩu không được chứa khoảng trắng.");
    document.getElementById("password").focus();
    return;
  }
  this.submit();
});

// Hàm hiển thị lỗi
function showError(msg) {
  const messageEl = document.getElementById("loginMessage");
  messageEl.textContent = msg;
  messageEl.style.color = "red";
}

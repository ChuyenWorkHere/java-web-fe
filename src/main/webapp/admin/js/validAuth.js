document.getElementById("loginForm").addEventListener("submit", function (event) {
  event.preventDefault();

  const email = document.getElementById("email").value.trim();
  const password = document.getElementById("password").value;

  if (!email || !password) {
    showError("Vui lòng nhập đầy đủ email và mật khẩu.");
    document.getElementById("email").focus();
    return;
  }

  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (!emailRegex.test(email)) {
    showError("Email không đúng định dạng.");
    document.getElementById("email").focus();
    return;
  }

  if (password.length < 6) {
    showError("Mật khẩu phải có ít nhất 6 ký tự.");
    document.getElementById("password").focus();
    return;
  }

  if (!/[a-z]/.test(password)) {
    showError("Mật khẩu phải chứa ít nhất 1 chữ thường.");
    document.getElementById("password").focus();
    return;
  }

  if (!/[A-Z]/.test(password)) {
    showError("Mật khẩu phải chứa ít nhất 1 chữ hoa.");
    document.getElementById("password").focus();
    return;
  }

  if (!/[0-9]/.test(password)) {
    showError("Mật khẩu phải chứa ít nhất 1 chữ số.");
    document.getElementById("password").focus();
    return;
  }

  if (/\s/.test(password)) {
    showError("Mật khẩu không được chứa khoảng trắng.");
    document.getElementById("password").focus();
    return;
  }

  // ✅ Submit form sau khi kiểm tra hợp lệ
  document.getElementById("loginForm").submit();
  console.log("test login")
});

function showError(msg) {
  const messageEl = document.getElementById("loginMessage");
  messageEl.textContent = msg;
  messageEl.style.color = "red";
}

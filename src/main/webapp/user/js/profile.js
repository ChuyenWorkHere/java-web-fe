// Bật chế độ chỉnh sửa
const editBtn = document.getElementById("editBtn");
const cancelBtn = document.getElementById("cancelBtn");
const profileView = document.getElementById("profileView");
const profileEdit = document.getElementById("profileEdit");
const profileForm = document.getElementById("profileForm");

editBtn.addEventListener("click", (e) => {
    e.preventDefault();
    profileView.style.display = "none";
    profileEdit.style.display = "block";
});

cancelBtn.addEventListener("click", () => {
    profileEdit.style.display = "none";
    profileView.style.display = "block";
});

profileForm.addEventListener("submit", function(event) {
    event.preventDefault();

    const nameInput = document.getElementById("nameInput");
    const phoneInput = document.getElementById("phoneInput");
    const phoneRegex = /^(0[3|5|7|8|9])+([0-9]{8})$/;

    let isValid = true;

    nameInput.classList.remove("is-invalid");
    phoneInput.classList.remove("is-invalid");

    if (nameInput.value.trim() === "") {
        nameInput.classList.add("is-invalid");
        isValid = false;
    }

    if (phoneInput.value.trim() !== "" && !phoneRegex.test(phoneInput.value.trim())) {
        phoneInput.classList.add("is-invalid");
        isValid = false;
    }

    if (isValid) {
        profileForm.submit();
    }
});

// Upload avatar
document.getElementById("avatarUpload").addEventListener("change", function (e) {
  const file = e.target.files[0];
  if (!file) return;

  if (file.size > 1024 * 1024) { // 1MB
    alert("Dung lượng ảnh tối đa 1MB!");
    return;
  }
  const formData = new FormData();
  formData.append('avatar', file);

  fetch('/Furniture/customer/profile/img/update', {
    method: 'POST',
    body: formData
  }).then(() => {
    window.location.reload();
  }).catch(error => {
    console.error('Lỗi khi cập nhật ảnh đại diện:', error);
    alert('Đã xảy ra lỗi, vui lòng thử lại.');
  });

});
document.getElementById("addCategory").addEventListener("click", validFormAddCategory);
document.getElementById("editCategory").addEventListener("click", validFormEditCategory);
document.getElementById("deleteCategory").addEventListener("click", function() {
    const modalEl = document.getElementById('modalDel');
    const modal = bootstrap.Modal.getInstance(modalEl);
    modal.hide();
    showAlert("Xóa sản phẩm Ghế Sofa thành công");
});

function validFormAddCategory(){    
    let isValid = true;

    // Lấy input
    const nameInput = document.getElementById('addNameCategory');
    const detailInput = document.getElementById('addDetailCategory');

    // Lấy thẻ hiển thị lỗi
    const nameMessage = document.getElementById('addNameMessage');
    const detailMessage = document.getElementById('addDetailMessage');

    // Xóa lỗi cũ
    nameMessage.textContent = '';
    detailMessage.textContent = '';

    // Kiểm tra tên danh mục
    if (nameInput.value.trim() === '') {
      nameMessage.textContent = 'Vui lòng nhập tên danh mục.';
      isValid = false;
    }

    // Kiểm tra mô tả
    if (detailInput.value.trim() === '') {
      detailMessage.textContent = 'Vui lòng nhập mô tả.';
      isValid = false;
    }

    // Nếu không hợp lệ, ngăn modal đóng
    if (!isValid) {
      e.stopPropagation(); // Ngăn sự kiện lan ra
      e.preventDefault();  // Ngăn đóng modal
      const modalEl = document.getElementById('modalAdd');
      const modal = bootstrap.Modal.getInstance(modalEl);
      modal.show(); // Giữ modal mở lại nếu có lỗi
    } else {
      // Nếu cần submit form, bạn có thể gọi form.submit() ở đây
      const modalEl = document.getElementById('modalAdd');
      const modal = bootstrap.Modal.getInstance(modalEl);
      modal.hide();
      showAlert("Thêm mới Ghế Sofa thành công");
    }
};

function validFormEditCategory(){    
    let isValid = true;

    // Lấy input
    const nameInput = document.getElementById('editNameCategory');
    const detailInput = document.getElementById('editDetailCategory');

    // Lấy thẻ hiển thị lỗi
    const nameMessage = document.getElementById('editNameMessage');
    const detailMessage = document.getElementById('editDetailMessage');

    // Xóa lỗi cũ
    nameMessage.textContent = '';
    detailMessage.textContent = '';

    // Kiểm tra tên danh mục
    if (nameInput.value.trim() === '') {
      nameMessage.textContent = 'Vui lòng nhập tên danh mục.';
      isValid = false;
    }

    // Kiểm tra mô tả
    if (detailInput.value.trim() === '') {
      detailMessage.textContent = 'Vui lòng nhập mô tả.';
      isValid = false;
    }

    // Nếu không hợp lệ, ngăn modal đóng
    if (!isValid) {
      e.stopPropagation(); // Ngăn sự kiện lan ra
      e.preventDefault();  // Ngăn đóng modal
      const modalEl = document.getElementById('modalEdit');
      const modal = bootstrap.Modal.getInstance(modalEl);
      modal.show(); // Giữ modal mở lại nếu có lỗi
    } else {
      // Nếu cần submit form, bạn có thể gọi form.submit() ở đây
      const modalEl = document.getElementById('modalEdit');
      const modal = bootstrap.Modal.getInstance(modalEl);
      modal.hide();
      showAlert("Chỉnh sửa Ghế Sofa thành công");
    }
};

/* Hiển thị thông báo khi ấn nút modal */
function showAlert(message) {
    // Tạo alert HTML
    const alertHTML = `
      <div id="notifi" class="alert alert-success alert-dismissible fade show position-fixed bottom-0 end-0 m-3" role="alert">
        <i class="bi bi-check-circle"></i>
        ${message}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
      </div>
    `;

    // Thêm alert vào DOM
    document.getElementById('alert-container').innerHTML = alertHTML;

    // Tự động đóng alert sau 1 giây
    setTimeout(() => {
        const notifi = document.getElementById('notifi');
        if (notifi) {
            // Kích hoạt hiệu ứng fade out
            notifi.classList.remove('show');
            notifi.classList.add('hide');

            // Gỡ khỏi DOM sau khi hiệu ứng kết thúc
            setTimeout(() => {
                notifi.remove();
            }, 300); // Thời gian khớp với hiệu ứng fade Bootstrap (300ms)
        }
    }, 1000);
}
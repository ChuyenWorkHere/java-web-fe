document.getElementById("addCategory").addEventListener("click", validFormAddCategory);
document.querySelectorAll(".edit-confirm").forEach(item => item.addEventListener("click", validFormEditCategory));
document.querySelector(".del-confirm").addEventListener("click", function() {
    const modalEl = document.getElementById('modalDel');
    const modal = bootstrap.Modal.getInstance(modalEl);
    const form = this.closest('form');
        if (form) {
          form.submit();
        } else {
          console.error('Không tìm thấy form cha của nút confirm');
        }
    modal.hide();
});

function validFormAddCategory(){    
    let isValid = true;

    //lẤY form

    const form = document.getElementById('formAddCate');

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
      form.submit();
    }
};

function validFormEditCategory(button){
    let isValid = true;

    // Tìm phần tử modal chứa nút được click
    const modal = button.closest('.modal');

    // Lấy input theo ngữ cảnh của modal hiện tại
    const nameInput = modal.querySelector('#editNameCategory');
    const detailInput = modal.querySelector('#editDetailCategory');
    const nameMessage = modal.querySelector('#editNameMessage');
    const detailMessage = modal.querySelector('#editDetailMessage');

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

    if (!isValid) {
      // Không đóng modal
       return;
    }

    // Nếu hợp lệ, bạn có thể lấy form trong modal và submit
    const form = modal.querySelector('form');
    if (form) {
      // Ẩn modal thủ công
      const modalInstance = bootstrap.Modal.getInstance(modal);
      if (modalInstance) {
        modalInstance.hide();
      }

      form.submit();
    }
};

//Xóa sản phẩm
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
function showNotification(message) {
    const notification = document.getElementById("notification");
    notification.textContent = message;
    notification.classList.add("show");

    setTimeout(() => {
        notification.classList.remove("show");
    }, 2000); // Hiển thị trong 3 giây rồi biến mất
}
document.addEventListener("DOMContentLoaded", function () {
    const deleteButtons = document.querySelectorAll(".product-remove a");

    deleteButtons.forEach(function (button) {
        button.addEventListener("click", function (event) {
            event.preventDefault();

            const row = button.closest("tr");
            if (row) {
                row.remove();

                // Sau khi xoá xong, hiển thị thông báo
                showNotification("Đã xoá sản phẩm thành công!");
            }
        });
    });
});

const btnUpdate = document.querySelector(".btn-update-cart");

btnUpdate.addEventListener("click", () => {
    const alert = document.querySelector("#notification-success");
    alert.classList.add("show");

    setTimeout(() => {
        alert.classList.remove("show");
    }, 2000);
})

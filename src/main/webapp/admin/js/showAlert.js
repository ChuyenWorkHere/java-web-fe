// Lấy tham số từ URL
function getQueryParam(param) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(param);
}

/* Hiển thị thông báo khi ấn nút modal */
function showAlert(message, isSuccess) {
    document.getElementById('alert-container').innerHTML = "";
    // Tạo alert HTML
    const alertHTML = `
      <div id="notifi" class="alert ${ isSuccess ? "alert-success" : "alert-danger"} alert-dismissible fade show position-fixed bottom-0 end-0 m-3" role="alert">
        <i class="bi ${isSuccess ? "bi-check-circle" : "bi-exclamation-circle"}"></i>
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

const title = getQueryParam("title");
const action = getQueryParam("action");
const noti = getQueryParam("noti");

if(title && action && noti) {
    switch (title) {
        case "category":
            if (noti === "success" && action === "add") {
                showAlert("Thêm danh mục thành công!", true);
                console.log(1);
            } else if (noti === "failed" && action == "add") {
                showAlert("Thêm danh mục thất bại!", false);
                console.log(2);
            } else if (noti === "success" && action === "edit") {
                showAlert("Chỉnh sửa danh mục thành công", true);
                console.log(3);
            } else if (noti === "failed" && action === "edit") {
                showAlert("Chỉnh sửa danh mục không thành công", false);
                console.log(4);
            } else if (noti === "success" && action === "del") {
                showAlert("Xóa danh mục thành công", true);
                console.log(3);
            } else if (noti === "failed" && action === "del") {
                showAlert("Xóa danh mục không thành công", false);
                console.log(4);
            }
            break;
        case "product":
            if (noti === "success" && action === "add") {
                showAlert("Thêm sản phẩm thành công!", true);
            } else if (noti === "failed" && action == "add") {
                showAlert("Thêm sản phẩm thất bại!", false);
            } else if (noti === "success" && action === "edit") {
                showAlert("Chỉnh sửa sản phẩm thành công", true);
            } else if (noti === "failed" && action === "edit") {
                showAlert("Chỉnh sửa sản phẩm không thành công", false);
            } else if (noti === "success" && action === "del") {
                showAlert("Xóa sản phẩm thành công", true);
            } else if (noti === "failed" && action === "del") {
                showAlert("Xóa sản phẩm không thành công", false);
            }
            break;
        case "order":
            if (noti === "success" && action === "add") {
                showAlert("Thêm sản phẩm thành công!", true);
            } else if (noti === "failed" && action == "add") {
                showAlert("Thêm sản phẩm thất bại!", false);
            } else if (noti === "success" && action === "edit") {
                showAlert("Cập nhật đơn hàng thành công", true);
            } else if (noti === "failed" && action === "edit") {
                showAlert("Cập nhật đơn hàng không thành công", false);
            } else if (noti === "success" && action === "del") {
                showAlert("Xóa đơn hàng thành công", true);
            } else if (noti === "failed" && action === "del") {
                showAlert("Xóa đơn hàng không thành công", false);
            }
            break;
        case "profile":
            if (noti === "success" && action === "edit") {
                showAlert("Chỉnh sửa profile thành công!", true);
            } else if (noti === "failed" && action == "edit") {
                showAlert("Chỉnh sửa profile thất bại!", false);
            }
            break;
        case "material":
            if (noti === "success" && action === "add") {
                showAlert("Thêm chất liệu thành công!", true);
            } else if (noti === "failed" && action == "add") {
                showAlert("Thêm chất liệu thất bại!", false);
            } else if (noti === "success" && action === "edit") {
                showAlert("Chỉnh sửa chất liệu thành công", true);
            } else if (noti === "failed" && action === "edit") {
                showAlert("Chỉnh sửa chất liệu không thành công", false);
            } else if (noti === "success" && action === "del") {
                showAlert("Xóa chất liệu thành công", true);
            } else if (noti === "failed" && action === "del") {
                showAlert("Xóa chất liệu không thành công", false);
            }
            break;
        // Thêm các case khác nếu cần
        default:
            break;
    }
    // (Tuỳ chọn) Xoá tham số khỏi URL để tránh alert khi reload
    window.history.replaceState({}, document.title, window.location.pathname);
}
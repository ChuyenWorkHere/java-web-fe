// Lấy tham số từ URL
function getQueryParam(param) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(param);
}

function showAlert(message, isSuccess) {
    const alertHTML = `
      <div id="notifi" class="alert ${ isSuccess ? "alert-success" : "alert-danger"}
           alert-dismissible fade show position-fixed bottom-0 end-0 m-3 shadow"
           role="alert" style="min-width: 300px; z-index:1055">
        <i class="bi ${isSuccess ? "bi-check-circle" : "bi-exclamation-circle"} me-1"></i>
        ${message}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
      </div>
    `;
    document.body.insertAdjacentHTML('beforeend', alertHTML);

    setTimeout(() => {
        const notifi = document.getElementById('notifi');
        if (notifi) {
            notifi.classList.remove('show');
            notifi.classList.add('hide');
            setTimeout(() => notifi.remove(), 300);
        }
    }, 1500);
}

const title = getQueryParam("title");
const action = getQueryParam("action");
const noti = getQueryParam("noti");
const message = getQueryParam("message");

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
        // Thêm các case khác nếu cần
        default:
            break;
    }
    // (Tuỳ chọn) Xoá tham số khỏi URL để tránh alert khi reload
    window.history.replaceState({}, document.title, window.location.pathname);
}

if(message && noti) {
    showAlert(message, noti === "success" ? true : false);
    window.history.replaceState({}, document.title, window.location.pathname);
}
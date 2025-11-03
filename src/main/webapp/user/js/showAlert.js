// Lấy tham số từ URL
function getQueryParam(param) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(param);
}

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
        case "cart":
            if (noti === "success" && action === "add") {
                showAlert("Thêm sản phẩm vào giỏ hàng thành công!", true);
            } else if (noti === "failed" && action == "add") {
                showAlert("Thêm sản phẩm vào giỏ hàng thất bại!", false);
            } else if (noti === "success" && action === "edit") {
                showAlert("Chỉnh sửa giỏ hàng thành công", true);
            } else if (noti === "failed" && action === "edit") {
                showAlert("Chỉnh sửa giỏ hàng thất bại", false);
            } else if (noti === "success" && action === "del") {
                showAlert("Xóa sản phẩm khỏi giỏ hàng thành công", true);
            } else if (noti === "failed" && action === "del") {
                showAlert("Xóa sản phẩm khỏi giỏ hàng thất bại", false);
            }
            break;
        case "profile":
            if (noti === "success" && action === "edit") {
                showAlert("Chỉnh sửa profile thành công!", true);
            } else if (noti === "failed" && action == "edit") {
                showAlert("Chỉnh sửa profile thất bại!", false);
            }
            break;
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
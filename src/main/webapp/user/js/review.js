document.addEventListener("DOMContentLoaded", function() {
    // Lấy các tham số từ URL
    const urlParams = new URLSearchParams(window.location.search);
    const mode = urlParams.get('mode');

    // Kiểm tra nếu đang ở chế độ review
    if (mode === 'review') {
        // Giả sử ô input của bạn có id là 'review-text'
        const reviewInput = document.getElementById('message');

        if (reviewInput) {
            // Cuộn đến ô input
            reviewInput.scrollIntoView({ behavior: 'smooth', block: 'center' });

            // Focus vào ô input
            reviewInput.focus();
        }
    }
});

function setRating(starCount) {
    // 1. Cập nhật giá trị cho input ẩn để gửi về server
    var ratingInput = document.getElementById('rating-value');
    if(ratingInput) {
        ratingInput.value = starCount;
    }

    // 2. Xử lý hiển thị: Tô màu các sao từ 1 đến starCount
    for (var i = 1; i <= 5; i++) {
        var starIcon = document.getElementById('star-' + i);

        if (starIcon) {
            if (i <= starCount) {
                // Nếu là sao được chọn hoặc nằm trước sao được chọn -> Thành sao đặc (Solid)
                starIcon.classList.remove('far'); // Xóa class sao rỗng
                starIcon.classList.add('fas');    // Thêm class sao đặc
                starIcon.style.color = '#ffc107'; // Màu vàng
            } else {
                // Các sao còn lại -> Trở về sao rỗng (Regular)
                starIcon.classList.remove('fas');
                starIcon.classList.add('far');
                starIcon.style.color = '';        // Bỏ màu vàng
            }
        }
    }
}


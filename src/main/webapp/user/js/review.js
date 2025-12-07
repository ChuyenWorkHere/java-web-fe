document.addEventListener("DOMContentLoaded", function() {
    // --- PHẦN 1: Logic cuộn xuống input (như cũ) ---
    const urlParams = new URLSearchParams(window.location.search);
    const mode = urlParams.get('mode');

    if (mode === 'review') {
        const reviewInput = document.getElementById('message');
        if (reviewInput) {
            reviewInput.scrollIntoView({ behavior: 'smooth', block: 'center' });
            reviewInput.focus();
        }
    }

    // --- PHẦN 2: Logic Validate Form (MỚI) ---
    const reviewForm = document.querySelector('.review-form');

    if (reviewForm) {
        reviewForm.addEventListener('submit', function(event) {
            // 1. Xóa các thông báo lỗi cũ (nếu có) trước khi kiểm tra lại
            removeErrorMessages();

            let isValid = true;

            // Lấy giá trị hiện tại
            const ratingInput = document.getElementById('rating-value');
            const messageInput = document.getElementById('message');
            const ratingContainer = document.querySelector('.rating-list'); // Chỗ để hiển thị lỗi sao

            // 2. Kiểm tra Số sao (Rating)
            // Giá trị mặc định là "0", nếu vẫn là "0" nghĩa là chưa chọn
            if (!ratingInput || ratingInput.value === "0" || ratingInput.value === "") {
                showError(ratingContainer, "Vui lòng chọn số sao đánh giá.");
                isValid = false;
            }

            // 3. Kiểm tra Nội dung (Message)
            if (!messageInput || messageInput.value.trim() === "") {
                showError(messageInput, "Vui lòng nhập nội dung đánh giá.");
                isValid = false;
            }

            // 4. Nếu không hợp lệ thì chặn submit
            if (!isValid) {
                event.preventDefault();
            }
        });
    }
});

/**
 * Hàm hiển thị lỗi ngay sau phần tử bị lỗi
 * @param {HTMLElement} element - Phần tử cần báo lỗi (ví dụ: textarea hoặc div chứa sao)
 * @param {string} message - Nội dung thông báo lỗi
 */
function showError(element, message) {
    if (!element) return;

    // Tạo thẻ div chứa lỗi
    const errorDiv = document.createElement('div');
    errorDiv.className = 'validation-error';
    errorDiv.style.color = 'red';
    errorDiv.style.fontSize = '0.9em';
    errorDiv.style.marginTop = '5px';
    errorDiv.innerText = message;

    // Chèn thông báo lỗi vào sau phần tử input/div
    element.parentNode.insertBefore(errorDiv, element.nextSibling);
}

/**
 * Hàm xóa tất cả các thông báo lỗi đang hiển thị
 */
function removeErrorMessages() {
    const errors = document.querySelectorAll('.validation-error');
    errors.forEach(function(error) {
        error.remove();
    });
}

/**
 * Hàm chọn sao (Logic tô màu như cũ)
 */
function setRating(starCount) {
    // 1. Cập nhật giá trị cho input ẩn
    var ratingInput = document.getElementById('rating-value');
    if(ratingInput) {
        ratingInput.value = starCount;

        // (Tùy chọn) Nếu người dùng chọn sao thì xóa lỗi ngay lập tức để trải nghiệm tốt hơn
        // removeErrorMessages();
    }

    // 2. Xử lý hiển thị
    for (var i = 1; i <= 5; i++) {
        var starIcon = document.getElementById('star-' + i);
        if (starIcon) {
            if (i <= starCount) {
                starIcon.classList.remove('far');
                starIcon.classList.add('fas');
                starIcon.style.color = '#ffc107';
            } else {
                starIcon.classList.remove('fas');
                starIcon.classList.add('far');
                starIcon.style.color = '';
            }
        }
    }
}
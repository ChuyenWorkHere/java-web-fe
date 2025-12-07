document.addEventListener('DOMContentLoaded', function() {
    const form = document.querySelector('form[action*="/customer/checkout"]');

    if (!form) return;

    // Các input cần validate
    const inputs = {
        fullName: form.querySelector('input[name="fullName"]'),
        address: form.querySelector('input[name="address"]'),
        city: form.querySelector('input[name="city"]'),
        district: form.querySelector('input[name="district"]'),
        ward: form.querySelector('input[name="ward"]'),
        phone: form.querySelector('input[name="phone"]'),
        email: form.querySelector('input[name="email"]')
    };

    form.addEventListener('submit', function(e) {
        let isValid = true;

        // Reset lỗi cũ
        document.querySelectorAll('.is-invalid').forEach(el => el.classList.remove('is-invalid'));

        // 1. Validate Họ tên
        if (!inputs.fullName.value.trim()) {
            showError(inputs.fullName, 'Vui lòng nhập họ tên người nhận.');
            isValid = false;
        }

        // 2. Validate Địa chỉ chi tiết
        if (!inputs.address.value.trim()) {
            showError(inputs.address, 'Vui lòng nhập địa chỉ nhà/đường.');
            isValid = false;
        }

        // 3. Validate Tỉnh/Thành phố
        if (!inputs.city.value.trim()) {
            showError(inputs.city, 'Vui lòng nhập Tỉnh/Thành phố.');
            isValid = false;
        }

        // 4. Validate Quận/Huyện
        if (!inputs.district.value.trim()) {
            showError(inputs.district, 'Vui lòng nhập Quận/Huyện.');
            isValid = false;
        }

        // 5. Validate Phường/Xã
        if (!inputs.ward.value.trim()) {
            showError(inputs.ward, 'Vui lòng nhập Phường/Xã.');
            isValid = false;
        }

        // 6. Validate Số điện thoại (VN)
        const phoneRegex = /^(0)(3|5|7|8|9)+([0-9]{8})$/;
        if (!inputs.phone.value.trim()) {
            showError(inputs.phone, 'Vui lòng nhập số điện thoại.');
            isValid = false;
        } else if (!phoneRegex.test(inputs.phone.value.trim())) {
            showError(inputs.phone, 'SĐT không hợp lệ (10 số, bắt đầu bằng 0).');
            isValid = false;
        }

        // 7. Validate Email
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!inputs.email.value.trim()) {
            showError(inputs.email, 'Vui lòng nhập email.');
            isValid = false;
        } else if (!emailRegex.test(inputs.email.value.trim())) {
            showError(inputs.email, 'Email không đúng định dạng.');
            isValid = false;
        }

        // Chặn submit nếu có lỗi
        if (!isValid) {
            e.preventDefault();
            // Cuộn lên đầu để người dùng thấy lỗi
            window.scrollTo({ top: 100, behavior: 'smooth' });
        }
    });

    // Hàm hiển thị lỗi
    function showError(input, message) {
        input.classList.add('is-invalid');
        // Tìm div invalid-feedback ngay sau input
        let feedback = input.nextElementSibling;

        // Nếu chưa có thì tạo mới (phòng trường hợp HTML thiếu)
        if (!feedback || !feedback.classList.contains('invalid-feedback')) {
            feedback = document.createElement('div');
            feedback.className = 'invalid-feedback';
            input.parentNode.appendChild(feedback);
        }
        feedback.innerText = message;
    }

    // Xóa lỗi khi gõ
    Object.values(inputs).forEach(input => {
        if(input) {
            input.addEventListener('input', function() {
                this.classList.remove('is-invalid');
            });
        }
    });
});
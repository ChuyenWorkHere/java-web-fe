document.addEventListener('DOMContentLoaded', function() {
    let isUpdating = false; // Ngăn double click

    function updateCartQuantity(productId, quantity) {
        if (isUpdating) return;
        isUpdating = true;

        const params = new URLSearchParams();
        params.append('productId', productId);
        params.append('quantity', quantity);

        fetch(CONTEXT_PATH + '/customer/cart/update-quantity', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: params
        })
        .then(response => {
            if (!response.ok) throw new Error('Network error');
            return response;
        })
        .then(() => {
            window.location.reload();
        })
        .catch(error => {
            console.error('Lỗi khi cập nhật giỏ hàng:', error);
            alert('Có lỗi xảy ra. Vui lòng thử lại.');
        })
        .finally(() => {
            isUpdating = false;
        });
    }

    // Gắn sự kiện cho tất cả .cart-plus-minus
    document.querySelectorAll('.cart-quantity-update').forEach(function(el) {
        const incButton = el.querySelector('.inc');
        const decButton = el.querySelector('.dec');
        const input = el.querySelector('input');
        const productId = el.getAttribute('data-product-id');

        if (!incButton || !decButton || !input || !productId) {
            console.warn('Thiếu phần tử trong cart-plus-minus', el);
            return;
        }

        // Tăng
        incButton.addEventListener('click', function(e) {
            e.preventDefault();
            const newVal = parseInt(input.value) + 1;
            input.value = newVal;
            updateCartQuantity(productId, newVal);
        });

        // Giảm
        decButton.addEventListener('click', function(e) {
            e.preventDefault();
            const oldVal = parseInt(input.value);
            if (oldVal <= 1) {
                if (confirm('Bạn có muốn xóa sản phẩm này khỏi giỏ hàng không?')) {
                    window.location.href = CONTEXT_PATH + '/customer/cart/delete?productId=' + productId;
                }
                return;
            }
            const newVal = oldVal - 1;
            input.value = newVal;
            updateCartQuantity(productId, newVal);
        });
    });
});
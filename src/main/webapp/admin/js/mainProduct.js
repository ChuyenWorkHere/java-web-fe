//Index productImg & productColor
let index = 0;
function themsanpham(color = "#000000") {
  const danhsachsanpham = document.querySelector(".danhsachsanpham");

  const sanphamHTML = `
        <div class="row " data-index="${index}">
            <div class="col-xl-9">
              <h5 class="card-title">Ảnh minh họa</h5>
              <input type="file" name="productImg[${index}]" class="form-control image-input" multiple accept="image/*">
              <div class="image-preview d-flex flex-wrap mt-2" style="min-height: 100px;"></div>
            </div>
            <div class="col-xl-3 col-md-4">
              <h5 class="card-title">Màu sắc</h5>
              <div class="color-container row">
                <input name="productColor[${index}]" type="color" class="color-box col-xl-2 p-0 color-input" value="${color}">
              </div>
            </div>
          </div>
`;

  danhsachsanpham.insertAdjacentHTML('beforeend', sanphamHTML);
  index++;

  // Tìm input và div preview vừa thêm
  const inputs = danhsachsanpham.querySelectorAll(".image-input");
  const newInput = inputs[inputs.length - 1];
  const previewDiv = newInput.nextElementSibling;

  newInput.addEventListener("change", () => {
    previewDiv.innerHTML = "";
    Array.from(newInput.files).forEach(file => {
      if (file.type.startsWith("image/")) {
        const reader = new FileReader();
        reader.onload = function (e) {
          // Tạo thẻ div chứa ảnh + nút xóa
          const imageWrapper = createImageWrapper(e.target.result);
          previewDiv.appendChild(imageWrapper);
        };
        reader.readAsDataURL(file);
      }
    });
  });

}

function showOldImages(index, imageUrls) {
    const container = document.querySelector(`.row[data-index="${index}"] .image-preview`);
      if (!container) return;

      imageUrls.forEach((url) => {
        const imgWrapper = createImageWrapper(url, true, () => {
          // Gửi thông tin ảnh bị xóa về server
          const hiddenInput = document.createElement("input");
          hiddenInput.type = "hidden";
          hiddenInput.name = `deletedImg[${index}][]`;
          hiddenInput.value = url;
          container.parentElement.appendChild(hiddenInput);
        });
        container.appendChild(imgWrapper);
      });
}

/**
 * Kiểm tra dữ liệu trước form thêm sản phẩm
 */
const formProduct = document.querySelector(".formProduct");
if (formProduct) {
    const buttonCreate = document.querySelector("#product-submit");
    buttonCreate.addEventListener("click", function () {
        let isValid = true;

        // Xóa thông báo lỗi cũ
        const errorFields = document.querySelectorAll("p.text-danger");
        errorFields.forEach(p => p.textContent = "");

        // Lấy dữ liệu từ các trường
        const name = document.getElementById("productName").value.trim();
        const size = document.getElementById("productSize").value.trim();
        const material = document.getElementById("productMaterial").value.trim();
        const category = document.getElementById("category").value;
        const brand = document.getElementById("brand").value;
        const stock = document.getElementById("productStock").value.trim();
        const regularPrice = document.getElementById("regularPrice").value.trim();
        const salePrice = document.getElementById("salePrice").value.trim();
        const detail = tinymce.get("about").getContent({ format: "raw" }).trim(); // TinyMCE

        // Kiểm tra tên sản phẩm
        if (name === "") {
          document.getElementById("nameMessage").textContent = "Tên sản phẩm không được để trống.";
          isValid = false;
        }

         //Kích thước (có thể không bắt buộc, nếu bắt buộc thì bật dòng dưới)
         if (size === "") {
         document.getElementById("sizeMessage").textContent = "Kích thước không được để trống.";
         isValid = false;
         }

        // Chất liệu
        if (material === "") {
          document.getElementById("materialMessage").textContent = "Chất liệu không được để trống.";
          isValid = false;
        }

        // Danh mục
        if (category === "Danh mục") {
          document.getElementById("categoryMessage").textContent = "Vui lòng chọn danh mục.";
          isValid = false;
        }

        // Thương hiệu
        if (brand === "Thương hiệu") {
          document.getElementById("brandMessage").textContent = "Vui lòng chọn thương hiệu.";
          isValid = false;
        }

        // Số lượng
        if (stock === "" || isNaN(stock) || parseInt(stock) < 0) {
          document.getElementById("quantityMessage").textContent = "Vui lòng nhập số lượng hợp lệ.";
          isValid = false;
        }

        // Giá gốc
        if (regularPrice === "" || isNaN(regularPrice) || parseFloat(regularPrice) < 0) {
          document.getElementById("regularMessage").textContent = "Vui lòng nhập giá gốc hợp lệ.";
          isValid = false;
        }

        // Giá bán
        if (salePrice === "" || isNaN(salePrice) || parseFloat(salePrice) < 0) {
          document.getElementById("saleMessage").textContent = "Vui lòng nhập giá bán hợp lệ.";
          isValid = false;
        }

        // Mô tả chi tiết
        if (detail === "") {
          document.getElementById("detailMessage").textContent = "Mô tả chi tiết không được để trống.";
          isValid = false;
        }

        // Nếu hợp lệ thì submit form
        if (isValid) {
          document.querySelector("form.formProduct").submit();
        }
      });
}


/* Hiển thị thông báo khi ấn nút modal */
function showAlert() {
    // Tạo alert HTML
    const alertHTML = `
      <div id="notifi" class="alert alert-success alert-dismissible fade show position-fixed bottom-0 end-0 m-3" role="alert">
        <i class="bi bi-check-circle"></i>
        Xóa sản phẩm Ghế Sofa thành công
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
document.querySelectorAll('.confirm').forEach(item => item.addEventListener('click', showAlert));


/* Hiển thị ảnh tương ứng khi click màu sắc */
document.querySelectorAll('.color-img').forEach(button => {
      button.addEventListener('click', () => {
        const color = button.getAttribute('data-color');
        const productItem = button.closest('.product__item');
        const carousel = productItem.querySelector('.carousel');
        const items = carousel.querySelectorAll('.carousel-item');
        items.forEach(item => {
          item.classList.remove('active');
          if (item.getAttribute('data-color') === color) {
            item.classList.add('active');
          }
        });
      });
    });


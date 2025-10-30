//Preview image khi tải ảnh lên
const input = document.querySelector(".image-input");
const previewDiv = document.querySelector(".image-preview");

input.addEventListener("change", () => {
  previewDiv.innerHTML = "";
  Array.from(input.files).forEach(file => {
    if (file.type.startsWith("image/")) {
      const reader = new FileReader();
      reader.onload = function (e) {
        // Tạo thẻ div chứa ảnh + nút xóa
        const imageWrapper = document.createElement("div");
        imageWrapper.style.position = "relative";
        imageWrapper.classList.add("m-2");

        // Tạo ảnh
        const img = document.createElement("img");
        img.src = e.target.result;
        img.style.maxHeight = "100px";
        img.style.maxWidth = "100px";
        img.style.border = "1px solid #ccc";
        img.style.borderRadius = "4px";
        img.classList.add("me-2");


        // Gắn ảnh và nút vào div
        imageWrapper.appendChild(img);
        previewDiv.appendChild(imageWrapper);
      };
      reader.readAsDataURL(file);
    }
  });
});


function initColorInputs() {
  const colorContainer = document.querySelector('.color-container');
  const addColorBtn = document.querySelector('.add-color');

  if (!colorContainer || !addColorBtn) return;

  addColorBtn.addEventListener('click', () => {
    const colorWrapper = document.createElement('div');
    colorWrapper.className = 'color-wrapper col-auto mb-2';
    colorWrapper.innerHTML = `
        <input type="color" name="productColors[]" class="color-box p-0">
    `;
    colorContainer.insertBefore(colorWrapper, addColorBtn.parentElement);
  });
}

document.addEventListener('DOMContentLoaded', initColorInputs);


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
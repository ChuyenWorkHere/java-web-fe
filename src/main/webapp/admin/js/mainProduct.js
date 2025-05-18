/**
 * Thêm ảnh cho vùng kéo thả tải ảnh lên
 */
    function enableDropZone(dropZone) {
      const preview = dropZone.querySelector(".preview");
      const text = dropZone.querySelector(".dropZoneText");
      const fileInput = dropZone.querySelector(".file-input");

      // Click để chọn ảnh
      dropZone.addEventListener("click", () => {
        fileInput.click();
      });

      // Chọn ảnh từ hộp thoại
      fileInput.addEventListener("change", () => {
        if (fileInput.files.length > 0) {
          handleFiles(fileInput.files);
        }
      });

      //  Kéo vào vùng
      dropZone.addEventListener("dragover", (e) => {
        e.preventDefault();
        dropZone.classList.add("bg-secondary", "text-white");
      });

      // 👉Rời vùng
      dropZone.addEventListener("dragleave", () => {
        dropZone.classList.remove("bg-secondary", "text-white");
      });

      //  Thả vào vùng
      dropZone.addEventListener("drop", (e) => {
        e.preventDefault();
        dropZone.classList.remove("bg-secondary", "text-white");
        const files = e.dataTransfer.files;
        if (files.length > 0) {
          fileInput.files = files; // đồng bộ input
          handleFiles(files);
        }
      });

      //  Hàm xử lý file ảnh
      function handleFiles(files) {
        preview.innerHTML = ""; // clear ảnh cũ
        text.style.display = "none"; // ẩn dòng chữ

        Array.from(files).forEach(file => {
          if (file.type.startsWith("image/")) {
            const reader = new FileReader();
            reader.onload = function (e) {
              const img = document.createElement("img");
              img.src = e.target.result;
              img.style.maxHeight = "100px";
              img.style.maxWidth = "100px";
              img.classList.add("m-2");
              preview.appendChild(img);
            };
            reader.readAsDataURL(file);
          }
        });
      }
    };

    function themsanpham() {
      const danhsachsanpham = document.querySelector(".danhsachsanpham");

      const sanphamHTML = `
            <div class="row">
                <div class="col-xl-9">
                  <h5 class="card-title">Ảnh minh họa</h5>
                  <input type="file" class="form-control image-input" multiple accept="image/*">
                  <div class="image-preview d-flex flex-wrap mt-2" style="min-height: 100px;"></div>
                </div>
                <div class="col-xl-3 col-md-4">
                  <h5 class="card-title">Màu sắc</h5>
                  <div class="color-container row">
                    <div class="color-box col-xl-2" data-color="#1A1A2E" style="background-color: #1a1a2e"
                      onclick="selectColor(this)">
                      <span class="checkmark">✔</span>
                    </div>
                    <div class="color-box col-xl-2" data-color="#0A2E8F" style="background-color: #0a2e8f"
                      onclick="selectColor(this)">
                      <span class="checkmark">✔</span>
                    </div>
                    <div class="color-box col-xl-2" data-color="#6B48FF" style="background-color: #6b48ff"
                      onclick="selectColor(this)">
                      <span class="checkmark">✔</span>
                    </div>
                    <div class="color-box col-xl-2" data-color="#00C4B4" style="background-color: #00c4b4"
                      onclick="selectColor(this)">
                      <span class="checkmark">✔</span>
                    </div>
                    <div class="color-box col-xl-2" data-color="#00FF00" style="background-color: #00ff00"
                      onclick="selectColor(this)">
                      <span class="checkmark">✔</span>
                    </div>
                    <div class="color-box col-xl-2" data-color="#FF4040" style="background-color: #ff4040"
                      onclick="selectColor(this)">
                      <span class="checkmark">✔</span>
                    </div>
                    <div class="color-box col-xl-2" data-color="#FFA500" style="background-color: #ffa500"
                      onclick="selectColor(this)">
                      <span class="checkmark">✔</span>
                    </div>
                    <input type="color" class="color-box col-xl-2 p-0">
                  </div>
                </div>
              </div>
  `;

      danhsachsanpham.insertAdjacentHTML('beforeend', sanphamHTML);

      // Gắn sự kiện cho drop-zone mới thêm
      const dropZones = danhsachsanpham.querySelectorAll(".drop-zone");
      enableDropZone(dropZones[dropZones.length - 1]);
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



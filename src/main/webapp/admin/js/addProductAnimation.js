/**
 * 
 */
const dropZone = document.getElementById("dropZone");
const dropZoneText = document.getElementById("dropZoneText");
const preview = document.getElementById("preview");

// Thêm input file để chọn tệp
const fileInput = document.createElement("input");
fileInput.type = "file";
fileInput.multiple = true; // Cho phép chọn nhiều tệp
fileInput.style.display = "none";
fileInput.accept = "image/*"; // Chỉ chấp nhận file ảnh
dropZone.appendChild(fileInput);

fileInput.addEventListener("change", (e) => {
  handleFiles(e.target.files);
});

// Xử lý sự kiện kéo thả
dropZone.addEventListener("dragover", (e) => {
  e.preventDefault();
  dropZone.classList.add("dragover");
});

dropZone.addEventListener("dragleave", (e) => {
  e.preventDefault();
  dropZone.classList.remove("dragover");
});

dropZone.addEventListener("drop", (e) => {
  e.preventDefault();
  dropZone.classList.remove("dragover");

  const files = e.dataTransfer.files;
  handleFiles(files);
});

// Xử lý nhấp vào dropZone để chọn tệp
dropZone.addEventListener("click", (e) => {
  // Chỉ mở hộp thoại chọn tệp nếu nhấp vào khu vực drop-zone, không phải
	// vào hình ảnh hoặc nút xóa
  if (e.target === dropZone || e.target === dropZoneText) {
    fileInput.click();
  }
});

// Xử lý các tệp được chọn
function handleFiles(files) {
  for (const file of files) {
    if (file.type.startsWith("image/")) {
      const reader = new FileReader();

      reader.onload = function (e) {
        const imgWrapper = document.createElement("div");
        imgWrapper.className = "image-wrapper";

        const img = document.createElement("img");
        img.src = e.target.result;
        img.className = "preview-image";

        const cancelLink = document.createElement("a");
        cancelLink.className = "cancel-upload";
        cancelLink.innerText = "Cancel upload";
        cancelLink.href = "#";
        cancelLink.onclick = (e) => {
          e.preventDefault();
          removeImage(imgWrapper);
        };

        imgWrapper.appendChild(img);
        imgWrapper.appendChild(cancelLink);
        preview.appendChild(imgWrapper);
      };

      reader.readAsDataURL(file);
    }
  }
  dropZoneText.style.display = "none"; // Ẩn thông báo khi có ảnh
}

// Xóa hình ảnh
function removeImage(wrapper) {
  wrapper.remove();
  if (preview.children.length === 0) {
    dropZoneText.style.display = "block"; // Hiển thị lại thông báo khi
											// không còn ảnh
  }
}

// Hàm xử lý nút "Thêm Sản Phẩm"
function themsanpham() {
  const danhsachsanpham = document.querySelector(".danhsachsanpham");
  const sanphamHTML = `
                <div class="row">
              <div class="col-xl-9">
                <h5 class="card-title">Ảnh minh họa</h5>
                <div class="">
                  <div class="drop-zone text-center p-3 bg-light rounded position-relative"
                    style="border: 1px dashed #ccc; min-height: 150px" id="dropZone">
                    <span class="text-muted position-absolute top-50 start-50 translate-middle"
                      id="dropZoneText">Drop files here to upload</span>
                    <div id="preview" class="d-flex flex-wrap justify-content-center align-items-center"
                      style="min-height: 150px"></div>
                  </div>
                </div>
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
}
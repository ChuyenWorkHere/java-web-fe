const accountInput = document.querySelector("#searchInput");
if(accountInput){
    const buttonSearch = document.querySelector(".icon-search");
    accountInput.addEventListener("focus", () => {
        buttonSearch.classList.add("active");
    })

    accountInput.addEventListener("blur", () => {
            buttonSearch.classList.remove("active");
        })
}

function showToastMessage() {
  const toast = document.getElementById("toast-message");
  toast.classList.add("show");

  setTimeout(() => {
    toast.classList.remove("show");
  }, 2000); 
}


//start update information for largeModal
var largeModal = document.getElementById('largeModal');
  largeModal.addEventListener('show.bs.modal', function (event) {
    var button = event.relatedTarget;

    var gender = button.getAttribute('data-gender');
    document.getElementById('modalGender').textContent = gender;

    var profileImg = document.getElementById('modalProfileImage');
    profileImg.src = (gender.toLowerCase() === 'nam') ? "../admin/img/avtboy.png"
                                                      : "../admin/img/avtgirl.jpg";

    document.getElementById('modalName').textContent = button.getAttribute('data-name');
    document.getElementById('modalPhone').textContent = button.getAttribute('data-phone');
    document.getElementById('modalEmail').textContent = button.getAttribute('data-email');
    document.getElementById('modalAddress').textContent = button.getAttribute('data-address');
    document.getElementById('modalCreated').textContent = button.getAttribute('data-created');
    document.getElementById('modalUpdated').textContent = button.getAttribute('data-updated');
    
	var status = button.getAttribute('data-status');
	document.getElementById('modalStatus').textContent = (status === 'true') ? 'Hoạt động' : 'Tạm ngừng';
  });
//end update information for largeModal
  
/*start delete user*/
  let currentDeleteId = null;
  let currentItemElement = null;



  // Khi bấm nút xoá
  document.querySelectorAll(".btn-delete").forEach(button => {
    button.addEventListener("click", function () {
      currentDeleteId = this.getAttribute("data-id");
      currentItemElement = this.closest(".col-xl-3");
    });
  });

  const smallModal = document.getElementById('smallModal');
  if (smallModal) {
    smallModal.addEventListener('show.bs.modal', function (event) {
      const button = event.relatedTarget;
      const fullname = button.getAttribute('data-name');
      smallModal.querySelector('.userFullname').textContent = "Tài Khoản " + fullname;
    });
  }

  // Khi xác nhận xoá
  document.getElementById("confirmDeleteBtn").addEventListener("click", function () {
    if (!currentDeleteId) return;

    fetch('../admin/account-delete', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      body: 'itemId=' + encodeURIComponent(currentDeleteId)
    })
    .then(response => response.json())
    .then(data => {
      if (data.success) {
        // Ẩn modal
        const modal = bootstrap.Modal.getInstance(document.getElementById('smallModal'));
        modal.hide();

		if (currentItemElement) {
		    const statusText = currentItemElement.querySelector(".status-text");
		    if (statusText) {
		      statusText.textContent = "Tạm ngừng";
		      statusText.classList.remove("text-success");
		      statusText.classList.add("text-danger");
			  const deleteButton = currentItemElement.querySelector(".btn-delete");
			  deleteButton.classList.add("d-none");
		    }
		  }

        // Hiển thị thông báo
        showToastMessage();
      } else {
        alert("Xoá thất bại!");
      }
    })
    .catch(error => {
      console.error("Lỗi:", error);
      alert("Đã xảy ra lỗi.");
    });
  });

  /*end delete user*/
  

  /*start restore user*/
      document.querySelectorAll('.btn-restore').forEach(btn => {
          btn.addEventListener('click', function () {
              const userName = this.getAttribute('data-name');
              const userId = this.getAttribute('data-id');

              document.querySelector('#smallModalRestore .userFullname').textContent = userName;
              document.getElementById('confirmRestoreBtn').setAttribute('data-id', userId);
          });
      });

      // Xử lý khi xác nhận kích hoạt
      document.getElementById('confirmRestoreBtn').addEventListener('click', function () {
          const userId = this.getAttribute('data-id');

          // Gửi request đến Servlet
          fetch('../admin/account-restore', {
              method: 'POST',
              headers: {
                  'Content-Type': 'application/x-www-form-urlencoded'
              },
              body: 'userId=' + encodeURIComponent(userId)
          })
          .then(response => {
              if (response.ok) {
                  // Xử lý thành công
                  console.log('Kích hoạt thành công userId: ', userId);
                  location.reload(); // Hoặc cập nhật UI nếu không muốn reload
              } else {
                  console.error('Kích hoạt thất bại');
              }
          })
          .catch(error => {
              console.error('Lỗi khi gửi request: ', error);
          });
      });


  /*end restore user*/
 
  
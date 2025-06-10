$(".deleteIcon").on("click", function () {
  Swal.fire({
    title: "Bạn có chắc chắn?",
    text: "Bạn có chắc muốn xoá đơn hàng này không?",
    icon: "warning",
    showCancelButton: true,
    confirmButtonText: "Xoá",
    cancelButtonText: "Huỷ",
    confirmButtonColor: "#d33",
    cancelButtonColor: "#6c757d",
    customClass: {
      popup: 'animate__animated animate__fadeInDown animate__slower'
    }
  }).then((result) => {
    if (result.isConfirmed) {
      Swal.fire({
        title: "Đã xoá!",
        text: "Đơn hàng đã được xoá thành công.",
        icon: "success",
        timer: 3000, // 3 giây
        timerProgressBar: true,
        showConfirmButton: false,
        customClass: {
          popup: 'animate__animated animate__fadeInUp animate__slower'
        }
      });
    }
  });
});
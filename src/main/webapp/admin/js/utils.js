function createImageWrapper(src, isExisting = false, onDelete = null) {
  const imageWrapper = document.createElement("div");
  imageWrapper.style.position = "relative";
  imageWrapper.classList.add("m-2");

  const img = document.createElement("img");
  img.src = src;
  img.style.maxHeight = "100px";
  img.style.maxWidth = "100px";
  img.style.border = "1px solid #ccc";
  img.style.borderRadius = "4px";
  img.classList.add("me-2");

  imageWrapper.appendChild(img);
  return imageWrapper;
}

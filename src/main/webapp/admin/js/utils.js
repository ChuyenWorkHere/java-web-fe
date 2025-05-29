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

  const removeBtn = document.createElement("button");
  removeBtn.textContent = "❌";
  removeBtn.style.position = "absolute";
  removeBtn.style.top = "0px";
  removeBtn.style.right = "0px";
  removeBtn.style.background = "rgba(0, 0, 0, 0.6)";
  removeBtn.style.color = "white";
  removeBtn.style.border = "none";
  removeBtn.style.cursor = "pointer";
  removeBtn.style.padding = "2px 6px";
  removeBtn.style.fontSize = "14px";
  removeBtn.style.borderRadius = "0 4px 0 4px";

  removeBtn.addEventListener("click", () => {
    imageWrapper.remove();
    if (typeof onDelete === "function") onDelete();
  });

  imageWrapper.appendChild(img);
  imageWrapper.appendChild(removeBtn);
  return imageWrapper;
}

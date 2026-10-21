const logo = document.getElementById("logo");
const dialog = document.getElementById("easter-egg");

let clickCount = 0;
let lastClickTime = 0;

logo.addEventListener("click", () => {
    const currentTime = Date.now();

    // 前回のクリックから1秒以上経過したらリセット
    if (currentTime - lastClickTime >= 1000) {
        clickCount = 0;
    }

    clickCount++;
    lastClickTime = currentTime;

    // 5回クリックでダイアログを表示
    if (clickCount >= 5) {
        dialog.showModal();
        clickCount = 0;
    }
});
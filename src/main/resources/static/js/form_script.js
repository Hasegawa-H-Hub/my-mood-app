// 要素を取得
const memo = document.getElementById("memo");
const memoCount = document.getElementById("memo-count");

// 文字数を更新する関数
function updateMemoCount() {
    memoCount.textContent = memo.value.length;
}

// 入力されたときに文字数を更新
memo.addEventListener("input", updateMemoCount);

// 画面表示時にも文字数を更新
updateMemoCount();
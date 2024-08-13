// アイテム別で数を増減させる
document.addEventListener('DOMContentLoaded', function () {
    // 全てのdecrementボタンにイベントリスナーを追加
    document.querySelectorAll('.quantity-btn2.decrement').forEach(function(button) {
        button.addEventListener('click', function() {
            var input = this.nextElementSibling; // 次の兄弟要素が数量入力ボックス
            var value = parseInt(input.value);
            var min = parseInt(input.min);
            if (value > min) {
                input.value = value - 1;
                quack();
            }
        });
    });

    // 全てのincrementボタンにイベントリスナーを追加
    document.querySelectorAll('.quantity-btn2.increment').forEach(function(button) {
        button.addEventListener('click', function() {
            var input = this.previousElementSibling; // 前の兄弟要素が数量入力ボックス
            var value = parseInt(input.value);
            var max = parseInt(input.max);
            if (value < max) {
                input.value = value + 1;
                quack();
                }
            });
        });
    });
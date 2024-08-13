// 数量増減ボタンにクリックイベント
document.getElementById('increase').addEventListener('click', function() {
    var input = document.querySelector('.number-input');
    var value = parseInt(input.value, 10);
    if (!isNaN(value)) {
        input.value = Math.min(value + 1, 10); // Max value 10
    }
});

document.getElementById('decrease').addEventListener('click', function() {
    var input = document.querySelector('.number-input');
    var value = parseInt(input.value, 10);
    if (!isNaN(value)) {
        input.value = Math.max(value - 1, 1); // Min value 1
    }
});


//collarの選択でのクリックイベント
document.addEventListener("DOMContentLoaded", function() {
var pallets = document.querySelectorAll(".pallet");

    pallets.forEach(function(pallet) {
        pallet.addEventListener("click", function() {
            // すべてのパレットから境界線を削除
            pallets.forEach(function(item) {
                item.style.border = "none";
            });

            // 選択したパレットに境界線を追加
            pallet.style.border = "2px solid white";

            // 隠されたラジオ入力を確認する
            var radioInput = pallet.parentElement.querySelector("input[type='radio']");
            radioInput.checked = true;
            });
        });
    });

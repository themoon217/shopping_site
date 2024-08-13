// おすすめ商品のスライドショー用のコード
document.addEventListener('DOMContentLoaded', () => {
    function setupNewSlider(sliderClass, leftArrowClass, rightArrowClass) {
        let currentIndex = 0;
        const slides = document.querySelectorAll(sliderClass + ' .new-slide');
        const totalSlides = slides.length;

        function showSlide(index) {
            if (index >= totalSlides) {
                currentIndex = 0;
            } else if (index < 0) {
                currentIndex = totalSlides - 1;
            } else {
                currentIndex = index;
            }
            const offset = -currentIndex * 100;
            document.querySelector(sliderClass).style.transform = `translateX(${offset}%)`;
        }

        document.querySelector(leftArrowClass).addEventListener('click', () => {
            showSlide(currentIndex - 1);
        });

        document.querySelector(rightArrowClass).addEventListener('click', () => {
            showSlide(currentIndex + 1);
        });

        showSlide(currentIndex); // 初期表示
    }

    // 新しいスライドショーの設定
    setupNewSlider('.new-slider', '.new-slideShow .left.new-arrow', '.new-slideShow .right.new-arrow');
});

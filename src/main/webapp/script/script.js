// スライドショー
let slideIndex = 0;
let time = 4000;
let a = setInterval(showSlides, time);
function showSlides() {
    const slider = document.querySelector('.slider');
    const slideWidth = document.querySelector('.slide').clientWidth;
    const slides = document.querySelectorAll('.slide');
        slideIndex++;
    if (slideIndex >= slides.length || slideIndex < 0) {
        slideIndex = (slideIndex + slides.length) % slides.length;
        slider.style.transition = 'none';
        slider.style.transform = `translateX(-${slideWidth * slideIndex}px)`;
        showSlides();
    }
    slider.style.transition = 'transform 0.5s ease';
    slider.style.transform = `translateX(-${slideWidth * slideIndex}px)`;
}
function showSlides2() {
    const slider = document.querySelector('.slider');
    const slideWidth = document.querySelector('.slide').clientWidth;
    const slides = document.querySelectorAll('.slide');
    if (slideIndex >= slides.length) {
        slideIndex = (slideIndex + slides.length) % slides.length;
        slider.style.transition = 'none';
        slider.style.transform = `translateX(-${slideWidth * slideIndex}px)`;
        slideIndex++;
        showSlides2();
    } else if(slideIndex < 0) {
        slideIndex = (slideIndex + slides.length) % slides.length;
        slider.style.transition = 'none';
        slider.style.transform = `translateX(-${slideWidth * slideIndex}px)`;
        slideIndex--;
        showSlides2();
    } else {
        slider.style.transition = 'transform 0.5s ease';
        slider.style.transform = `translateX(-${slideWidth * slideIndex}px)`;
    }
}
const leftButton = document.querySelector('.left');
const rightButton = document.querySelector('.right');
leftButton.addEventListener('click', slideLeft);
rightButton.addEventListener('click', slideRight);
function slideLeft() {
    slideIndex--;
    clearInterval(a);
    showSlides2();
    a = setInterval(showSlides, time);
}
function slideRight() {
    slideIndex++;
    clearInterval(a);
    showSlides2();
    a = setInterval(showSlides, time);
}
const slideShow = document.querySelector('.slideShow');
let downX;
let upX;
slideShow.addEventListener('mousedown', function(event) {
    downX = event.offsetX;
});
slideShow.addEventListener('mouseup', function(event) {
    upX = event.offsetX;
    if (upX - downX > 0) {
        slideLeft()
    } else if(upX - downX < 0) {
        slideRight()
    }
});

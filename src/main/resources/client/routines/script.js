const slide1 = document.querySelector('.firstSlide');
const firstSlideCompiler = Handlebars.compile(slide1.innerHTML);
const page = document.querySelector('.main');
const startBtn = document.querySelector('.startBtn');
const showBtn = document.querySelector('.showBtn');
const slide2 = document.querySelector('.secondSlide');
const secondSlideCompiler = Handlebars.compile(slide2.innerHTML);
const slde3 = document.querySelector('.thirdSlide');
const thirdSlideCompiler = Handlebars.compile(slde3.innerHTML);
const slide4 = document.querySelector('.fourthSlide');
const fourthSlideCompiler = Handlebars.compile(slide4.innerHTML);


// showBtn.addEventListener('click', () => {
//     console.log("showing......");
//     $('.tt')
//         .transition({
//             animation: 'scale',
//             duration: '2s',
//             onComplete: function () {
//                 console.log("done next slide");

//                 let html = firstSlideCompiler(slide1);
//                 page.innerHTML = html;
//             }
//         })
//         ;
// })

const startSlide = () => {
    // first slide
    console.log("slide1");
    let html = firstSlideCompiler(slide1);
    page.innerHTML = html;
    const startBtn = document.querySelector('.low');
    window.location
    setTimeout(() => {
        // second slide
        setTimeout(() => {
            // hide main
            $('.main')
                .transition({
                    animation: 'scale',
                    duration: '3s',
                    onComplete: () => {
                        console.log("slide2");
                        let html = secondSlideCompiler(slide2);
                        page.innerHTML = html;


                        $('.main')
                            .transition({
                                animation: 'scale',
                                duration: '3s',
                                onComplete: () => {
                                    console.log("slide3");
                                    let html = thirdSlideCompiler(slide2);
                                    page.innerHTML = html;


                                    $('.main')
                                        .transition({
                                            animation: 'scale',
                                            duration: '3s',
                                            onComplete: function () {
                                                console.log("done next slide");


                                                $('.main')
                                                    .transition({
                                                        animation: 'scale',
                                                        duration: '3s',
                                                        onComplete: function () {
                                                            console.log("here");
                                                            let html = fourthSlideCompiler(slide2);
                                                            page.innerHTML = html;
                                                        }
                                                    })
                                                    ;
                                            }
                                        })
                                        ;

                                }
                            })
                            ;
                    }
                })
                ;
        })

    }, 1000)
}

startSlide();

window.onhashchange = () => {
    let hash = location.hash;
    let url = hash.split('/');
    console.log(url[1]);
    if (url[1] === 'greeted') {
        homeUrl.classList.remove('active');
        greetedUrl.classList.add('active');
        get_names();
    } else if (url[1] === 'home') {
        homeUrl.classList.add('active');
        greetedUrl.classList.remove('active');
        get_counter();
    }
}
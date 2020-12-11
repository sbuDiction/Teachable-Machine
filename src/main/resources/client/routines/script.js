// const slide1 = document.querySelector('.firstSlide');
// const firstSlideCompiler = Handlebars.compile(slide1.innerHTML);
// const page = document.querySelector('.main');
// const startBtn = document.querySelector('.startBtn');
// const showBtn = document.querySelector('.showBtn');
// const slide2 = document.querySelector('.secondSlide');
// const secondSlideCompiler = Handlebars.compile(slide2.innerHTML);
// const slde3 = document.querySelector('.thirdSlide');
// const thirdSlideCompiler = Handlebars.compile(slde3.innerHTML);
// const slide4 = document.querySelector('.fourthSlide');
// const fourthSlideCompiler = Handlebars.compile(slide4.innerHTML);
// const startSlide = document.querySelector('.startSlides');
// const startCompiler = Handlebars.compile(startSlide.innerHTML);


// // showBtn.addEventListener('click', () => {
// //     console.log("showing......");
// //     $('.tt')
// //         .transition({
// //             animation: 'scale',
// //             duration: '2s',
// //             onComplete: function () {
// //                 console.log("done next slide");

// //                 let html = firstSlideCompiler(slide1);
// //                 page.innerHTML = html;
// //             }
// //         })
// //         ;
// // })

// // const startSlide = () => {
// //     // first slide
// //     console.log("slide1");
// //     let html = firstSlideCompiler(slide1);
// //     page.innerHTML = html;
// //     const startBtn = document.querySelector('.low');
// //     window.location
// //     setTimeout(() => {
// //         // second slide
// //         setTimeout(() => {
// //             // hide main
// //             $('.main')
// //                 .transition({
// //                     animation: 'scale',
// //                     duration: '3s',
// //                     onComplete: () => {
// //                         console.log("slide2");
// //                         let html = secondSlideCompiler(slide2);
// //                         page.innerHTML = html;


// //                         $('.main')
// //                             .transition({
// //                                 animation: 'scale',
// //                                 duration: '3s',
// //                                 onComplete: () => {
// //                                     console.log("slide3");
// //                                     let html = thirdSlideCompiler(slide2);
// //                                     page.innerHTML = html;


// //                                     $('.main')
// //                                         .transition({
// //                                             animation: 'scale',
// //                                             duration: '3s',
// //                                             onComplete: function () {
// //                                                 console.log("done next slide");


// //                                                 $('.main')
// //                                                     .transition({
// //                                                         animation: 'scale',
// //                                                         duration: '3s',
// //                                                         onComplete: function () {
// //                                                             console.log("here");
// //                                                             let html = fourthSlideCompiler(slide2);
// //                                                             page.innerHTML = html;
// //                                                         }
// //                                                     })
// //                                                     ;
// //                                             }
// //                                         })
// //                                         ;

// //                                 }
// //                             })
// //                             ;
// //                     }
// //                 })
// //                 ;
// //         })

// //     }, 1000)
// // }

// // startSlide();


// const firstSlide = () => {
//     console.log("slide1");
//     // window.location.href = '/#/slide1/';
//     let html = firstSlideCompiler(slide1);
//     page.innerHTML = html;
//     const startBtn = document.querySelector('.low');
//     startBtn.addEventListener(onhashchange, () => { 

//     })
//     // setTimeout(nextSlide(), 5000)
// }

// const secondSlide = () => {
//     console.log("slide2");
//     let html = secondSlideCompiler(slide2);
//     page.innerHTML = html;

// }

// const thirdSlide = () => {
//     console.log("slide3");
//     let html = thirdSlideCompiler(slide2);
//     page.innerHTML = html;
// }

// const fourthSlide = () => {
//     console.log("slide4");
//     let html = fourthSlideCompiler(slide2);
//     page.innerHTML = html;
// }

// const start = () => {
//     loop_func_call()
// }



// window.onload = () => {
//     window.location.href = '/#/slides/';
//     let html = startCompiler(startSlide);
//     page.innerHTML = html;
// }


// window.onhashchange = () => {
//     let hash = location.hash;
//     let url = hash.split('/');
//     console.log(url[1]);
//     if (url[1] === 'slide1') {
//         console.log(url[1] === 'slide1');
//         setTimeout(firstSlide, 4000);
//         setTimeout(() => {
//             window.location.href = '/#/slide2/';

//         }, 4000)
//     }
//     if (url[1] === 'slide2') {
//         setTimeout(secondSlide, 4000)
//     }

//     if (url[1] === 'slide3') {
//     }




// }

// const nextSlide = () => {
//     $('.main')
//         .transition({
//             animation: 'scale',
//             duration: '2s',
//             onComplete: () => {

//             }
//         })
// }

// const to_slide = () => {
//     console.log("clicked");
//     window.location.href = '/#/slide1/';
//     // firstSlide();
//     start()
// }

// const throt_func = _.throttle(async () => {
//     console.log("throttle running");
//     nextSlide();
// }, 10000)


// const loop_func_call = () => {
//     setTimeout(loop_func_call, 5000);
//     throt_func();
// }
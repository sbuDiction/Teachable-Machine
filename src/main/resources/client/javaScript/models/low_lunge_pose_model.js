const lowBtn = document.querySelector('.low');
const musicPlayer = document.getElementById("gameAudio");
// More API functions here:
// https://github.com/googlecreativelab/teachablemachine-community/tree/master/libraries/pose

// the link to your model2 provided by Teachable Machine export panel
const URL2 = "https://teachablemachine.withgoogle.com/models/NAH-13YdA/";
let model2, webcam2, ctx2, labelContainer2, maxPredictions2;

async function low_lunge() {
    const modelURL = URL2 + "model.json";
    const metadataURL = URL2 + "metadata.json";

    // load the model2 and metadata
    // Refer to tmImage.loadFromFiles() in the API to support files from a file picker
    // Note: the pose library adds a tmPose object to your window (window.tmPose)
    model2 = await tmPose.load(modelURL, metadataURL);

    maxPredictions2 = model2.getTotalClasses();

    // Convenience function to setup a webcam2
    const size = 200;
    const flip = true; // whether to flip the webcam2
    webcam2 = new tmPose.Webcam(size, size, flip); // width, height, flip
    await webcam2.setup(); // request access to the webcam2
    await webcam2.play();
    window.requestAnimationFrame(loop);

    // append/get elements to the DOM
    const canvas = document.getElementById("canvas1");
    canvas.width = size; canvas.height = size;
    ctx2 = canvas.getContext("2d");
    labelContainer2 = document.getElementById("label-container");
    for (let i = 0; i < maxPredictions2; i++) { // and class labels
    }
}

async function loop(timestamp) {
    webcam2.update(); // update the webcam2 frame
    await predict();
    window.requestAnimationFrame(loop);
}



async function predict() {
    // Prediction #1: run input through posenet
    // estimatePose can take in an image, video or canvas html element
    const { pose, posenetOutput } = await model2.estimatePose(webcam2.canvas);
    // Prediction 2: run input through teachable machine classification model2
    const prediction = await model2.predict(posenetOutput);
    for (let i = 0; i < maxPredictions2; i++) {
        // console.log(prediction[i].className === "Low Lunge Pose");
        // console.log(prediction[i].className);
        

        setTimeout(async () => {
            if (prediction[i].className === "Low Lunge Pose") {
                musicPlayer.play();
                // musicPlayer.pause();
                webcam2.stop();
                const next = document.querySelector('.next');
                next.classList.remove('hidden')
                await axios.post("/api/sendUser/", {
                    "name": prediction[i].className,
                    "average": prediction[i].probability.toFixed(2) * 100
                }).then(response => response.data)
            }
        }, 10000)
    }
    drawPose(pose);
}

function drawPose(pose) {
    if (webcam2.canvas) {
        ctx2.drawImage(webcam2.canvas, 0, 0);
        // draw the keypoints and skeleton
        if (pose) {
            const minPartConfidence = 0.5;
            tmPose.drawKeypoints(pose.keypoints, minPartConfidence, ctx2);
            tmPose.drawSkeleton(pose.keypoints, minPartConfidence, ctx2);
        }
    }
}

let href = location.href;
console.log(href + " href");
console.log(href === href);

setTimeout(() => {
    if (href === href) {
        low_lunge();
        nextSlide();

    }
}, 5000)

const nextSlide = () => {
    $('.fetch')
        .transition({
            animation: 'scale',
            duration: '2s',
            onComplete: () => {

            }
        })
}
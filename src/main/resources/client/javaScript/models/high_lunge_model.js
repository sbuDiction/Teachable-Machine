const musicPlayer = document.getElementById("gameAudio");

const URL = "https://teachablemachine.withgoogle.com/models/NAH-13YdA/";
let model, webcam, ctx, labelContainer, maxPredictions;

async function init() {
    const modelURL = URL + "model.json";
    const metadataURL = URL + "metadata.json";

    // load the model and metadata
    // Refer to tmImage.loadFromFiles() in the API to support files from a file picker
    // Note: the pose library adds a tmPose object to your window (window.tmPose)
    model = await tmPose.load(modelURL, metadataURL);

    maxPredictions = model.getTotalClasses();

    // Convenience function to setup a webcam
    const size = 200;
    const flip = true; // whether to flip the webcam
    webcam = new tmPose.Webcam(size, size, flip); // width, height, flip
    await webcam.setup(); // request access to the webcam
    await webcam.play();
    window.requestAnimationFrame(loop);

    // append/get elements to the DOM
    const canvas = document.getElementById("canvas2");
    canvas.width = size; canvas.height = size;
    ctx = canvas.getContext("2d");
    labelContainer = document.getElementById("label-container");
    for (let i = 0; i < maxPredictions; i++) { // and class labels
        // labelContainer.appendChild(document.createElement("div"));
    }



}

async function loop(timestamp) {
    webcam.update(); // update the webcam frame
    await predict();
    window.requestAnimationFrame(loop);
}



async function predict() {
    // Prediction #1: run input through posenet
    // estimatePose can take in an image, video or canvas html element
    const { pose, posenetOutput } = await model.estimatePose(webcam.canvas);
    // Prediction 2: run input through teachable machine classification model
    const prediction = await model.predict(posenetOutput);
    let class_ = ""
    let average = 0;
    for (let i = 0; i < maxPredictions; i++) {
    

        class_ = prediction[i].className;
        average = prediction[i].probability.toFixed(2)
        setTimeout(async () => {
            if (prediction[i].className === "High Lunge") {
                musicPlayer.play();
                // musicPlayer.pause();
                webcam.stop();
                const next = document.querySelector('.next');
                next.classList.remove('hidden')
                await axios.post("/api/sendUser/", {
                    "name": prediction[i].className,
                    "average": prediction[i].probability.toFixed(2) * 100
                }).then(response => response.data)
            }
        }, 10000)


    }

    // poseHandler(class_, average);

    // finally draw the poses
    drawPose(pose);
}

function drawPose(pose) {
    if (webcam.canvas) {
        ctx.drawImage(webcam.canvas, 0, 0);
        // draw the keypoints and skeleton
        if (pose) {
            const minPartConfidence = 0.5;
            tmPose.drawKeypoints(pose.keypoints, minPartConfidence, ctx);
            tmPose.drawSkeleton(pose.keypoints, minPartConfidence, ctx);
        }
    }
}


let href = location.href;
console.log(href + " href");
console.log(href === href);

setTimeout(() => {
    if (href === href) {
        init();
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
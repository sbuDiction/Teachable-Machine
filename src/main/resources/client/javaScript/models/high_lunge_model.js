// More API functions here:
// https://github.com/googlecreativelab/teachablemachine-community/tree/master/libraries/pose

// the link to your model1 provided by Teachable Machine export panel
const URL1 = "https://teachablemachine.withgoogle.com/models/NAH-13YdA/";
let model1, webcam1, ctx1, labelContainer1, maxPredictions1;

async function high_lunge() {
    const modelURL = URL1 + "model.json";
    const metadataURL = URL1 + "metadata.json";

    // load the model1 and metadata
    // Refer to tmImage.loadFromFiles() in the API to support files from a file picker
    // Note: the pose library adds a tmPose object to your window (window.tmPose)
    model1 = await tmPose.load(modelURL, metadataURL);

    maxPredictions1 = model1.getTotalClasses();

    // Convenience function to setup a webcam1
    const size = 200;
    const flip = true; // whether to flip the webcam1
    webcam1 = new tmPose.Webcam(size, size, flip); // width, height, flip
    await webcam1.setup(); // request access to the webcam1
    await webcam1.play();
    window.requestAnimationFrame(loop);

    // append/get elements to the DOM
    const canvas = document.getElementById("canvas2");
    canvas.width = size; canvas.height = size;
    ctx1 = canvas.getContext("2d");
    labelContainer1 = document.getElementById("label-container");
    for (let i = 0; i < maxPredictions1; i++) { // and class labels
    }
}

async function loop(timestamp) {
    webcam1.update(); // update the webcam1 frame
    await predict();
    window.requestAnimationFrame(loop);
}



async function predict() {
    // Prediction #1: run input through posenet
    // estimatePose can take in an image, video or canvas html element
    const { pose, posenetOutput } = await model1.estimatePose(webcam1.canvas);
    // Prediction 2: run input through teachable machine classification model1
    const prediction = await model1.predict(posenetOutput);
    for (let i = 0; i < maxPredictions1; i++) {
        console.log(prediction[i].className);

        setTimeout(async () => {
            if (prediction[i].className === "High Lunge") {
                webcam1.stop();
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
    if (webcam1.canvas) {
        ctx1.drawImage(webcam1.canvas, 0, 0);
        // draw the keypoints and skeleton
        if (pose) {
            const minPartConfidence = 0.5;
            tmPose.drawKeypoints(pose.keypoints, minPartConfidence, ctx1);
            tmPose.drawSkeleton(pose.keypoints, minPartConfidence, ctx1);
        }
    }
}


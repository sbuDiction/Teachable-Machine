// More API functions here:
// https://github.com/googlecreativelab/teachablemachine-community/tree/master/libraries/pose

// the link to your model3 provided by Teachable Machine export panel
const URL3 = "https://teachablemachine.withgoogle.com/models/NAH-13YdA/";
let model3, webcam3, ctx3, labelContainer3, maxPredictions3;

async function mountain_pose() {
    const modelURL = URL3 + "model.json";
    const metadataURL = URL3 + "metadata.json";

    // load the model3 and metadata
    // Refer to tmImage.loadFromFiles() in the API to support files from a file picker
    // Note: the pose library adds a tmPose object to your window (window.tmPose)
    model3 = await tmPose.load(modelURL, metadataURL);

    maxPredictions3 = model3.getTotalClasses();

    // Convenience function to setup a webcam3
    const size = 200;
    const flip = true; // whether to flip the webcam3
    webcam3 = new tmPose.Webcam(size, size, flip); // width, height, flip
    await webcam3.setup(); // request access to the webcam3
    await webcam3.play();
    window.requestAnimationFrame(loop);

    // append/get elements to the DOM
    const canvas = document.getElementById("canvas3");
    canvas.width = size; canvas.height = size;
    ctx3 = canvas.getContext("2d");
    labelContainer3 = document.getElementById("label-container");
    for (let i = 0; i < maxPredictions3; i++) { // and class labels
    }
}

async function loop(timestamp) {
    webcam3.update(); // update the webcam3 frame
    await predict();
    window.requestAnimationFrame(loop);
}



async function predict() {
    // Prediction #1: run input through posenet
    // estimatePose can take in an image, video or canvas html element
    const { pose, posenetOutput } = await model3.estimatePose(webcam3.canvas);
    // Prediction 2: run input through teachable machine classification model3
    const prediction = await model3.predict(posenetOutput);
    for (let i = 0; i < maxPredictions3; i++) {
        console.log(prediction[i].className);
        setTimeout(async () => {
            if (prediction[i].className === "Mountain Pose") {
                webcam3.stop();
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
    if (webcam3.canvas) {
        ctx3.drawImage(webcam3.canvas, 0, 0);
        // draw the keypoints and skeleton
        if (pose) {
            const minPartConfidence = 0.5;
            tmPose.drawKeypoints(pose.keypoints, minPartConfidence, ctx3);
            tmPose.drawSkeleton(pose.keypoints, minPartConfidence, ctx3);
        }
    }
}
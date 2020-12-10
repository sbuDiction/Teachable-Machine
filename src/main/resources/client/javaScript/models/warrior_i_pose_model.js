const warriorBtn = document.querySelector('.warrior');

// More API functions here:
// https://github.com/googlecreativelab/teachablemachine-community/tree/master/libraries/pose

// the link to your model4 provided by Teachable Machine export panel
const URL4 = "https://teachablemachine.withgoogle.com/models/NAH-13YdA/";
let model4, webcam4, ctx4, labelContainer4, maxPredictions4;

async function worrior_pose() {
    const modelURL = URL4 + "model.json";
    const metadataURL = URL4 + "metadata.json";

    // load the model4 and metadata
    // Refer to tmImage.loadFromFiles() in the API to support files from a file picker
    // Note: the pose library adds a tmPose object to your window (window.tmPose)
    model4 = await tmPose.load(modelURL, metadataURL);

    maxPredictions4 = model4.getTotalClasses();

    // Convenience function to setup a webcam4
    const size = 200;
    const flip = true; // whether to flip the webcam4
    webcam4 = new tmPose.Webcam(size, size, flip); // width, height, flip
    await webcam4.setup(); // request access to the webcam4
    await webcam4.play();
    window.requestAnimationFrame(loop);

    // append/get elements to the DOM
    const canvas = document.getElementById("canvas4");
    canvas.width = size; canvas.height = size;
    ctx4 = canvas.getContext("2d");
    labelContainer4 = document.getElementById("label-container");
    for (let i = 0; i < maxPredictions4; i++) { // and class labels
    }
}

async function loop(timestamp) {
    webcam4.update(); // update the webcam4 frame
    await predict();
    window.requestAnimationFrame(loop);
}



async function predict() {
    // Prediction #1: run input through posenet
    // estimatePose can take in an image, video or canvas html element
    const { pose, posenetOutput } = await model4.estimatePose(webcam4.canvas);
    // Prediction 2: run input through teachable machine classification model4
    const prediction = await model4.predict(posenetOutput);
    for (let i = 0; i < maxPredictions4; i++) {
        console.log(prediction[i].className);
        setTimeout(async () => {
            if (prediction[i].className === "Mountain Pose") {
                webcam4.stop();
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
    if (webcam4.canvas) {
        ctx4.drawImage(webcam4.canvas, 0, 0);
        // draw the keypoints and skeleton
        if (pose) {
            const minPartConfidence = 0.5;
            tmPose.drawKeypoints(pose.keypoints, minPartConfidence, ctx4);
            tmPose.drawSkeleton(pose.keypoints, minPartConfidence, ctx4);
        }
    }
}
warriorBtn.addEventListener('click', () => {
    console.log("warrior btn clicked");
    worrior_pose()
})
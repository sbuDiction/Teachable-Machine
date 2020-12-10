console.log("Program running.....");
let classNamesArray = ["Downward Facing Dog",
    "Mountain Pose",
    "High Lunge",
    "Low Lunge Pose",
    "Warrior II"];

let index = 0;
let avarageContainer = [];

const runBtn = document.querySelector('.run');
let predictionCount;
let className = "";


// More API functions here:
// https://github.com/googlecreativelab/teachablemachine-community/tree/master/libraries/pose

// the link to your model provided by Teachable Machine export panel
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
    const canvas = document.getElementById("canvas");
    canvas.width = size; canvas.height = size;
    ctx = canvas.getContext("2d");
    labelContainer = document.getElementById("label-container");
    for (let i = 0; i < maxPredictions; i++) { // and class labels
        labelContainer.appendChild(document.createElement("div"));
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
        const classPrediction =
            prediction[i].className + ": " + prediction[i].probability.toFixed(2);
        classNames = prediction[i].className
        labelContainer.childNodes[i].innerHTML = classPrediction;

        class_ = prediction[i].className;
        average = prediction[i].probability.toFixed(2)
        setTimeout(async () => {
            if (prediction[i].className === "Downward Facing Dog") {
                // console.log(prediction[i].className);
                webcam.stop();
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


// const throt_func = _.throttle(async () => {
//     console.log("throttle running");
//     // clearTimeout(loop_func_call, 5000)
// }, 5000)


// const loop_func_call = () => {
//     setTimeout(loop_func_call, 5);
//     throt_func();
// }
// loop_func_call()

const getPredictionCount = (predictionCount) => {
    let count;

    classNamesArray.push(predictionCount * 100);
    classNamesArray.forEach(element => {
        count = + element
    });

    console.log(classNamesArray);


    let predictedData = {
        "name": classNames,
        "avarage": count
    }

    console.log(predictedData.avarage + " avarage");


}

const getClassName = (className) => {
    return className;
}

// const sendBtn = document.querySelector('.sendBtn');
// const input = document.querySelector('.input')

// sendBtn.addEventListener('click', async () => {
//     console.log("button clicked");

//     let params = {
//         "name": input.value,
//     }

//     await axios.post('/api/sendUser/', params)
// })


const poseHandler = async (className, avarage) => {
    console.log(avarage);




    if (className == "Downward Facing Dog") {
        console.log(className);
        // avarageContainer.push(avarage);
        webcam.stop();
        await axios.post("/api/sendUser/", {
            "name": className,
            "average": avarage
        }).then(response => {
            console.log(response);
        }).catch(error => {
            console.log(error);
        })
        // sendObject();
    }

    // console.log(avarageContainer);


}
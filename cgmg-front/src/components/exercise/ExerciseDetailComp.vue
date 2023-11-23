<template>
    <div class="divv">
        <div>
            <audio ref="bgm" src="@/assets/bgm.mp3"></audio>
            <div class="video-text">{{ bodyPart }}-{{ exercise }}</div>
            <div class="inf" :class="{ 'toggle1': toggle1 }">
                <div class="slider1">
                    <h1 class="slide">한세트당 운동횟수와 간격을 설정하고 시작하세요!!</h1>
                    <h1 class="slide"><span style="color: var(--primary-200);">{{ bodyPart }}</span>운동의 강자 "<span
                            style="color: var(--primary-200);">{{ exercise }}</span>"를 해볼까요?</h1>
                    <h1 class="slide"><span style="color: var(--primary-200);">{{ exercise }}</span>는(은) 우측의 <span
                            style="color: var(--primary-200);">운동영상</span>을 참고해보세요</h1>
                </div>
                <div class="input">
                    <div class="input-div">
                        <label for="maxNumber">세트당 횟수(개)</label>
                        <div class="input-div-div">
                            <img src="../../assets/minus.png" width="30" height="30" @click="maxNumber = maxNumber - 1;">
                            <input type="number" v-model="maxNumber" class="numin" />
                            <img src="../../assets/plus.png" width="30" height="30" @click="maxNumber = maxNumber + 1">
                        </div>
                    </div>

                    <div class="input-div">
                        <label for="setCount">세트 횟수(개)</label>
                        <div class="input-div-div">
                            <img src="../../assets/minus.png" width="30" height="30" @click="totalSets = totalSets - 1;">
                            <input type="number" v-model="totalSets" class="numin" />
                            <img src="../../assets/plus.png" width="30" height="30" @click="totalSets = totalSets + 1">
                        </div>
                    </div>

                    <div class="input-div">
                        <label for="restTime">세트당 간격(초)</label>
                        <div class="input-div-div">
                            <img src="../../assets/minus.png" width="30" height="30" @click="restTime = restTime - 5;">
                            <input type="number" v-model="restTime" class="numin" />
                            <img src="../../assets/plus.png" width="30" height="30" @click="restTime = restTime + 5">
                        </div>
                    </div>

                    <div class="input-div">
                        <label for="setCount">회당 간격(초)</label>
                        <div class="input-div-div">
                            <img src="../../assets/minus.png" width="30" height="30" @click="count = count - 1;">
                            <input type="number" v-model="count" class="numin" />
                            <img src="../../assets/plus.png" width="30" height="30" @click="count = count + 1">
                        </div>
                    </div>

                </div>
                <button @click="startCounting(); toggle1 = !toggle1" class="log" v-if="!toggle1">시작</button>

                <div v-if="toggle1" class="toggle-div">
                    <div v-if="!resting" class="num">{{ currentDisplay }}</div>
                    <div v-if="!resting" class="rest">{{ countDisplay }}</div>
                    <div v-if="end">
                        <button @click="exercise_log" class="log">운동 기록하기</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="video">
            <div class="video-text">추천영상</div>
            <ul v-for="video in store.videos" :key="video.id.videoId" class="video-ul">
                <!-- <ul class="video-ul"> -->
                <li class="video-li">
                    <iframe width="300" height="170" :src="`https://www.youtube.com/embed/${video.id.videoId}`"
                        title="YouTube video player" frameborder="0"
                        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                        allowfullscreen class="vid"></iframe>
                    <div class="video-spandiv">
                        <span>{{ video.snippet.title }}</span>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</template>

<script setup>
import { ref, computed } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { useExerciseStore } from '../../stores/exercise'
import { useYoutubeStore } from '@/stores/youtube';
const { params } = useRoute();
const currentDisplay = ref('');
const countDisplay = ref('');
const bodyPart = ref('');
const exercise = ref('');
const exerciseStore = useExerciseStore();
const store = useYoutubeStore();
const count = ref(3);
const maxNumber = ref(10);
const restTime = ref(30);
const totalSets = ref(3);
const currentNumber = ref(1);
const setCount = ref(1);
const counting = ref(false);
const resting = ref(false);
const end = ref(false);
const toggle1 = ref(false);
const exerciselog = ref({
    bodyPart: params.bodyPart,
    exerciseName: params.exercise,
    cnt: computed(() => totalSets.value),
    userId: JSON.parse(localStorage.getItem("loginUser")).userId,
});

const bgmRef = ref(null);

bodyPart.value = params.bodyPart;
exercise.value = params.exercise;
store.youtubeSearch(`${exercise.value} 방법`);

const num = [
    "영번", "하나", "둘", "셋", "넷", "다섯", "여섯", "일곱", "여덟", "아홉",
    "열", "열하나", "열둘", "열셋", "열넷", "열다섯", "열여섯", "열일곱", "열여덟", "열아홉",
    "스물", "스물하나", "스물둘", "스물셋", "스물넷", "스물다섯", "스물여섯", "스물일곱", "스물여덟", "스물아홉", "서른"
];
function exercise_log() {
    exerciseStore.exerciseLog(exerciselog.value)

}
function updateNumber() {
    if (currentNumber.value <= maxNumber.value) {
        currentDisplay.value = num[currentNumber.value];
        speak(num[currentNumber.value]);

        currentNumber.value++;
        setTimeout(updateNumber, count.value * 1000);
    } else {
        currentDisplay.value = setCount.value === totalSets.value ? "모든세트 완료" : `${setCount.value}세트 완료!`;
        currentNumber.value = 1;
        setCount.value++;
        if (setCount.value <= totalSets.value) {
            speak(`${setCount.value - 1}세트가 끝났어요!`);
            // 쉬는 시간일 때는 30초로 설정
            restCounting(setCount.value)
            setTimeout(updateNumber, restTime.value * 1000);
        } else {
            speak(`모든 세트가 끝났어요. 운동을 기록하세요`);
            end.value = true;
            resting.value = false;
            counting.value = false;
        }
    }
}
function restCounting(set) {
    let countdown = restTime.value;

    const countdownInterval = setInterval(() => {
        if (countdown > 1) {
            countDisplay.value = `${set}세트가 ${countdown - 1}초 뒤에 시작합니다.`;
            countdown--;
        }
        else {
            clearInterval(countdownInterval);
            countDisplay.value = ``;
        }
    }, 1000);
}
function startCounting() {
    playBGM();
    counting.value = true;
    resting.value = false;

    let countdown = 5;

    const countdownInterval = setInterval(() => {
        if (countdown == 5) {
            speak(`${exercise.value}운동을 시작해요!!`);
            currentDisplay.value = `${countdown}초 뒤에 시작합니다.`;
            countdown--;
        } else if (countdown == 4) {
            currentDisplay.value = `${countdown}초 뒤에 시작합니다.`;
            countdown--;
        }
        else if (countdown == 3) {
            currentDisplay.value = `${countdown}초 뒤에 시작합니다.`;
            countdown--;
        }
        else if (countdown == 2) {
            speak("준비");
            currentDisplay.value = `${countdown}초 뒤에 시작합니다.`;
            countdown--;
        }
        else if (countdown == 1) {
            speak("시작!");
            currentDisplay.value = `${countdown}초 뒤에 시작합니다.`;
            countdown--;
        }
        else {
            clearInterval(countdownInterval);
            updateNumber();
        }
    }, 1000);
}
const playBGM = () => {
    const audioElement = bgmRef.value;
    if (audioElement) {
        audioElement.play();
    }
};

const pauseBGM = () => {
    const audioElement = bgmRef.value;

    // 일시 정지
    audioElement.pause();
};
function speak(text) {
    const utterance = new SpeechSynthesisUtterance(text);
    speechSynthesis.speak(utterance);
    // utterance.voice = speechSynthesis.getVoices()[1];
    // utterance.volume = 0;
    // utterance.rate = 3; // 속도
    // utterance.pitch = 10; // 톤
}
</script>
<style>
.inf {
    padding: 20px;
    background-color: var(--bg-500);
    height: 300px;
    border-radius: 20px;
    transition: 0.3s;
    flex-grow: 1;
    margin-top: 10px;
}

.toggle1 {
    height: 500px;
}

.vid {
    overflow: hidden;
    border-radius: 20px 20px 0 0;
}

.input {
    display: flex;
    align-items: center;
    justify-content: center;
    gap: 20px;
}

.input-div {
    text-align: center;
    background-color: var(--primary-200);
    padding: 20px;
    border-radius: 20px;
}

.input-div-div {
    display: flex;
    align-items: center;
    gap: 5px;
}

.divv {
    display: flex;
    width: 1160px;
    margin: 0 auto;
    padding-top: 20px;
    gap: 20px;
}

.video {
    /* background-color: var(--bg-500); */
    border-radius: 20px;
}

.video-text {
    padding: 10px 0 0 20px;
    font-size: 20px;
}

.video-ul {
    width: 320px;
    list-style: none;
    padding: 0.1px 10px;
}

.video-li {
    background-color: var(--text-300);
    height: 230px;
    margin: 10px 10px;
    border-radius: 20px;
    overflow: hidden;
}

.video-spandiv {
    padding: 0 20px;
}

.numin {
    width: 42px;
    height: 56px;
    border-radius: 20px;
    font-size: 25px;
    border: solid 2px var(--text-200);
    background-color: var(--bg-100);
    color: var(--text-200);
    text-align: center;
    padding-left: 15px;
}

.toggle-div {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
}

.num {
    font-size: 80px;
    text-align: center;
    color: var(--text-100);
}

.rest {
    font-size: 40px;
    text-align: center;
}

.log {
    display: block;
    margin: 0 auto;
    padding: 0.75rem 2rem;
    outline: none;
    border: none;
    background-color: var(--primary-200);
    color: #fff;
    font-size: 1rem;
    border-radius: 10px;
    cursor: pointer;
    transition: 0.3s;
}

.input {
    margin-bottom: 20px;
}

.log:hover {
    background-color: var(--primary-100);
}

.slider1 {
    position: relative;
    overflow: hidden;
    width: 100%;
    height: 50px;
    border-radius: 10px;
    margin-bottom: 33px;
    margin: 20px;
}

.slide {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-size: cover;
    background-position: center;
    background-repeat: repeat;
    animation: slider1 7.5s infinite linear;
    font-size: 26px;
}

.slide:nth-child(1) {
    /* background-image: url("../assets/무제-1.png"); */
    animation-delay: 0;
}

.slide:nth-child(2) {
    /* background-image: url("../assets/무제-2.png"); */
    animation-delay: -2.5s;
}

.slide:nth-child(3) {
    /* background-image: url("../assets/무제-3.png"); */
    animation-delay: -5s;
}


@keyframes slider1 {
    0% {
        transform: translateX(0);
    }

    26% {
        transform: translateX(0);
    }

    33% {
        transform: translateX(-200%);
        animation-timing-function: step-end;
    }

    93% {
        transform: translateX(200%);
    }

    100% {
        transform: translateX(0);
    }
}
</style>
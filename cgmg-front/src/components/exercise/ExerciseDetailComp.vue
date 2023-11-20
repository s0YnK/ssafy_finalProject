<template>
    <div>
        <h1>해보자구 운동!</h1>
        <p>운동부위는 {{ bodyPart }}</p>
        <p>이번에 할 운동은 {{ exercise }}</p>
    </div>
    <div>
        <label for="maxNumber">한셋트당 몇개?</label>
        <input type="number" v-model="maxNumber" /><br>

        <label for="restTime">쉬는 시간 (초):</label>
        <input type="number" v-model="restTime" /><br>

        <label for="setCount">반복할 셋트 수:</label>
        <input type="number" v-model="totalSets" /><br>

        <button @click="startCounting">시작</button>

        <div v-if="!resting" class="num">{{ currentDisplay }}</div>
        <div v-if="end">
            <button @click="exercise_log">운동 기록하기</button>
        </div>
    </div>
</template>

<script setup>
import { ref, computed } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { useExerciseStore } from '../../stores/exercise'
const currentDisplay = ref('');

const bodyPart = ref('');
const exercise = ref('');

// 라우터로부터 bodyPart와 exercise 값을 받음
const { params } = useRoute();
bodyPart.value = params.bodyPart;
exercise.value = params.exercise;

const exerciseStore = useExerciseStore();
const maxNumber = ref(5);
const restTime = ref(5);
const totalSets = ref(2);
const currentNumber = ref(1);
const setCount = ref(1);
const counting = ref(false);
const resting = ref(false);
const end = ref(false);
const exerciselog = ref({
    bodyPart: params.bodyPart,
    exerciseName: params.exercise,
    cnt: computed(() => totalSets.value),
    userId: JSON.parse(localStorage.getItem("loginUser")).userId,
});
function exercise_log() {
    exerciseStore.exerciseLog(exerciselog.value)
}

const num = [
    "영번", "하나", "둘", "셋", "넷", "다섯", "여섯", "일곱", "여덟", "아홉",
    "열", "열하나", "열둘", "열셋", "열넷", "열다섯", "열여섯", "열일곱", "열여덟", "열아홉",
    "스물", "스물하나", "스물둘", "스물셋", "스물넷", "스물다섯", "스물여섯", "스물일곱", "스물여덟", "스물아홉", "서른"
];

function updateNumber() {
    if (currentNumber.value <= maxNumber.value) {
        currentDisplay.value = num[currentNumber.value];
        speak(num[currentNumber.value]);

        currentNumber.value++;
        setTimeout(updateNumber, 1500);
    } else {
        currentDisplay.value = setCount.value === totalSets.value ? "모든셋트 완료" : `쉬는 시간 ${setCount.value}`;
        speak(`${setCount.value}셋트가 끝났습니다.`);
        currentNumber.value = 1;
        setCount.value++;
        if (setCount.value <= totalSets.value) {
            // 쉬는 시간일 때는 30초로 설정
            setTimeout(updateNumber, restTime.value * 1000);
        } else {
            end.value = true;
            resting.value = false;
            counting.value = false;
        }
    }
}

function startCounting() {
    counting.value = true;
    resting.value = false;

    let countdown = 5;

    const countdownInterval = setInterval(() => {
        if (countdown == 5) {
            speak("5초뒤에 시작합니다");
            currentDisplay.value = `${countdown}초 뒤에 시작합니다.`;
            countdown--;
        } else if (countdown == 4) {
            currentDisplay.value = `${countdown}초 뒤에 시작합니다.`;
            countdown--;
        }
        else if (countdown == 3) {
            speak("준비");
            currentDisplay.value = `${countdown}초 뒤에 시작합니다.`;
            countdown--;
        }
        else if (countdown == 2) {
            currentDisplay.value = `${countdown}초 뒤에 시작합니다.`;
            countdown--;
        }
        else if (countdown == 1) {
            speak("시작");
            currentDisplay.value = `${countdown}초 뒤에 시작합니다.`;
            countdown--;
        }
        else {
            clearInterval(countdownInterval);
            updateNumber();
        }
    }, 1000);
}

function speak(text) {
    const utterance = new SpeechSynthesisUtterance(text);
    speechSynthesis.speak(utterance);
}
</script>
<style>
.num {
    font-size: 200px;
}
</style>
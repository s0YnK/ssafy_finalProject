<template>
    <div class="exerinf">
        <ExChartComp class="exc" />
        <div class="left-e">
            <div class="slider">
                <h1 class="slide">운동 고르고 시작하면 끝.</h1>
                <h1 class="slide">그동안 {{ store2.profile.totalExerciseCnt }}세트를 완료했어요</h1>
                <h1 class="slide">레벨업까지 {{ 100 - store2.profile.totalExerciseCnt % 100 }}세트 남았습니다.</h1>
            </div>
            <div class="flex-bt">
                <div class="flex-it">
                    <div class="flex-te">할 운동 고르고</div>
                    <img src="../assets/cinema-seat.png" class="flex-im">
                </div>
                <div class="flex-it">
                    <div class="flex-te">세트 설정하고</div>
                    <img src="../assets/settings.png" class="flex-im">
                </div>
                <div class="flex-it">
                    <div class="flex-te">목소리를 따라 운동!</div>
                    <img src="../assets/exercise.png" class="flex-im">
                </div>
            </div>
        </div>
    </div>
    <div class="buttonbox">
        <!-- 어깨 부위 -->
        <div class="btnitm" :class="{ 'toggle': toggle1 }" @click="toggle1 = !toggle1">
            <button class="button" @click="showShoulderExercises = !showShoulderExercises">어깨</button>
            <div v-if="showShoulderExercises" class="itm">
                <router-link
                    :to="{ name: 'ExerciseDetail', params: { bodyPart: '어깨', exercise: '숄더프레스' } }"><button>숄더프레스</button></router-link>
                <router-link :to="{ name: 'ExerciseDetail', params: { bodyPart: '어깨', exercise: '벤트 오버 레이즈' } }"><button>벤트
                        오버
                        레이즈</button></router-link>
                <router-link
                    :to="{ name: 'ExerciseDetail', params: { bodyPart: '어깨', exercise: '사이드 레터럴 레이즈' } }"><button>사이드
                        레터럴 레이즈</button></router-link>
                <router-link
                    :to="{ name: 'ExerciseDetail', params: { bodyPart: '어깨', exercise: '프론트 레터럴 레이즈' } }"><button>프론트
                        레터럴 레이즈</button></router-link>
            </div>
        </div>
        <div class="btnitm" :class="{ 'toggle': toggle2 }" @click="toggle2 = !toggle2">
            <!-- 하체 & 엉덩이 부위 -->
            <button class="button" @click="showLowerBodyExercises = !showLowerBodyExercises">하체 & 엉덩이</button>
            <div v-if="showLowerBodyExercises" class="itm">
                <router-link
                    :to="{ name: 'ExerciseDetail', params: { bodyPart: '하체 & 엉덩이', exercise: '스쿼트' } }"><button>스쿼트</button></router-link>
                <router-link
                    :to="{ name: 'ExerciseDetail', params: { bodyPart: '하체 & 엉덩이', exercise: '런지' } }"><button>런지</button></router-link>
                <router-link
                    :to="{ name: 'ExerciseDetail', params: { bodyPart: '하체 & 엉덩이', exercise: '힙 쓰러스트' } }"><button>힙
                        쓰러스트</button></router-link>
            </div>
        </div>
        <div class="btnitm" :class="{ 'toggle': toggle3 }" @click="toggle3 = !toggle3">
            <!-- 팔 부위 -->
            <button class="button" @click="showArmExercises = !showArmExercises">팔</button>
            <div v-if="showArmExercises" class="itm">
                <router-link :to="{ name: 'ExerciseDetail', params: { bodyPart: '팔', exercise: '덤벨 컬' } }"><button>덤벨
                        컬</button></router-link>
                <router-link
                    :to="{ name: 'ExerciseDetail', params: { bodyPart: '팔', exercise: '트라이셉스 익스텐션' } }"><button>트라이셉스
                        익스텐션</button></router-link>
                <router-link :to="{ name: 'ExerciseDetail', params: { bodyPart: '팔', exercise: '케이블 푸쉬다운' } }"><button>케이블
                        푸쉬다운</button></router-link>
            </div>
        </div>
        <div class="btnitm" :class="{ 'toggle': toggle4 }" @click="toggle4 = !toggle4">
            <!-- 복부 & 코어 부위 -->
            <button class="button" @click="showCoreExercises = !showCoreExercises">복부 & 코어</button>
            <div v-if="showCoreExercises" class="itm">
                <router-link
                    :to="{ name: 'ExerciseDetail', params: { bodyPart: '복부 & 코어', exercise: '크런치' } }"><button>크런치</button></router-link>
                <router-link
                    :to="{ name: 'ExerciseDetail', params: { bodyPart: '복부 & 코어', exercise: '시티드 니업' } }"><button>시티드
                        니업</button></router-link>
                <router-link
                    :to="{ name: 'ExerciseDetail', params: { bodyPart: '복부 & 코어', exercise: '행잉 레그레이즈' } }"><button>행잉
                        레그레이즈</button></router-link>
                <router-link
                    :to="{ name: 'ExerciseDetail', params: { bodyPart: '복부 & 코어', exercise: '플랭크' } }"><button>플랭크</button></router-link>
            </div>
        </div>
        <div class="btnitm" :class="{ 'toggle': toggle5 }" @click="toggle5 = !toggle5">
            <!-- 가슴 부위 -->
            <button class="button" @click="showChestExercises = !showChestExercises">가슴</button>
            <div v-if="showChestExercises" class="itm">
                <router-link
                    :to="{ name: 'ExerciseDetail', params: { bodyPart: '가슴', exercise: '벤치프레스' } }"><button>벤치프레스</button></router-link>
                <router-link
                    :to="{ name: 'ExerciseDetail', params: { bodyPart: '가슴', exercise: '딥스' } }"><button>딥스</button></router-link>
                <router-link
                    :to="{ name: 'ExerciseDetail', params: { bodyPart: '가슴', exercise: '인클라인 벤치프레스' } }"><button>인클라인
                        벤치프레스</button></router-link>
                <router-link :to="{ name: 'ExerciseDetail', params: { bodyPart: '가슴', exercise: '체스트 플라이' } }"><button>체스트
                        플라이</button></router-link>
            </div>
        </div>
        <div class="btnitm" :class="{ 'toggle': toggle6 }" @click="toggle6 = !toggle6">
            <!-- 등 부위 -->
            <button class="button" @click="showBackExercises = !showBackExercises">등</button>
            <div v-if="showBackExercises" class="itm">
                <router-link
                    :to="{ name: 'ExerciseDetail', params: { bodyPart: '등', exercise: '풀업' } }"><button>풀업</button></router-link>
                <router-link
                    :to="{ name: 'ExerciseDetail', params: { bodyPart: '등', exercise: '렛풀다운' } }"><button>렛풀다운</button></router-link>
                <router-link
                    :to="{ name: 'ExerciseDetail', params: { bodyPart: '등', exercise: '덤벨로우' } }"><button>덤벨로우</button></router-link>
                <router-link
                    :to="{ name: 'ExerciseDetail', params: { bodyPart: '등', exercise: '케이블로우' } }"><button>케이블로우</button></router-link>
                <router-link :to="{ name: 'ExerciseDetail', params: { bodyPart: '등', exercise: '데드 리프트' } }"><button>데드
                        리프트</button></router-link>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from 'vue-router';
import ExChartComp from '../components/profile/ExChartComp.vue';
import { useExerciseStore } from '../stores/exercise';
import { useUserStore } from "../stores/user"
const store = useExerciseStore();
const store2 = useUserStore();
const userId = JSON.parse(localStorage.getItem("loginUser")).userId;

const toggle1 = ref(false)
const toggle2 = ref(false)
const toggle3 = ref(false)
const toggle4 = ref(false)
const toggle5 = ref(false)
const toggle6 = ref(false)


onMounted(async () => {
    store.getExercisePart(userId);
    store2.getProfile(userId);
    // maxmin(store.exercisePart)
    // console.log(store.exercisePart)
});
// let minExr = ""
// let maxExr = ""

// const maxmin = (Part) => {
//     console.log(Part)

//     let max = Part[0].cnt
//     let min = Part[0].cnt

//     for (let i = 1; i < 5; i++) {
//         if (min > Part[i].cnt) {
//             min = Part[i].cnt;
//             minExr = Part[i].bodyPart;
//         }
//         if (max > Part[i].cnt) {
//             max = Part[i].cnt;
//             maxExr = Part[i].bodyPart;
//         }
//     }
// };

const showShoulderExercises = ref(false);
const showLowerBodyExercises = ref(false);
const showArmExercises = ref(false);
const showCoreExercises = ref(false);
const showChestExercises = ref(false);
const showBackExercises = ref(false);

</script>

<style scoped>
.flex-bt {
    display: flex;
    gap: 20px;
}

.flex-it {
    background-color: var(--bg-500);
    width: 25%;
    border-radius: 20px;
    height: 170px;
    padding: 20px;
    text-align: center;
}

.flex-im {
    padding: 10px;
    margin-top: 20px;
    width: 60%;
}

.flex-te {
    font-size: 18px;
}

.itm {
    position: absolute;
    left: 250px;
    display: flex;
}

.itm a button {
    /* width: 140px; */
    min-width: 140px;
    padding: 15px;
    margin: 10px;
    border-radius: 10px;
    border: solid 2px var(--primary-100);
    background-color: var(--text-300);
    font-size: 11px;
    font-family: 'AritaDotum';
    color: var(--text-100);
    transition: 0.3s;
}


.btnitm {
    width: 250px;
    display: flex;
    align-items: center;
    transition: 0.4s;
    position: relative;
    overflow: hidden;
    border-radius: 20px;
}

.toggle {
    min-width: 300px;
    width: 1120px;
    background-color: var(--header);
}

.buttonbox {
    width: 1120px;
    padding: 20px;
    border-radius: 20px;
    gap: 20px;
    margin: 0px auto;
    display: flex;
    flex-direction: column;
}

.button {
    width: 200px;
    padding: 20px;
    border-radius: 20px;
    border: solid 2px var(--primary-100);
    margin: 20px;
    background-color: var(--primary-200);
    font-size: 17px;
    font-family: 'AritaDotum';
    color: var(--bg-200);
    transition: 0.3s;
}

button:hover,
a button:hover {
    background-color: var(--primary-100);
    color: var(--bg-100);
}

.exc {
    width: 400px;
}

.exerinf {
    width: 1080px;
    background-color: var(--primary-200);
    margin: 0 auto;
    padding: 20px;
    border-radius: 20px;
    display: flex;
    gap: 20px;
}

.h1 {
    margin: 20px;
}

.slider {
    position: relative;
    overflow: hidden;
    width: 100%;
    height: 50px;
    border-radius: 10px;
    margin-bottom: 33px;
    margin: 20px;
}

.left-e {
    width: 70%;
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
    animation: slider 7.5s infinite linear;
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


@keyframes slider {
    0% {
        transform: translateY(0);
    }

    26% {
        transform: translateY(0);
    }

    33% {
        transform: translateY(-200%);
        animation-timing-function: step-end;
    }

    93% {
        transform: translateY(200%);
    }

    100% {
        transform: translateY(0);
    }
}
</style>
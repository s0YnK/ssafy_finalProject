<template>
    <div>
        <!-- 어깨 부위 -->
        <button @click="showShoulderExercises = !showShoulderExercises">어깨</button>
        <div v-if="showShoulderExercises">
            <router-link
                :to="{ name: 'ExerciseDetail', params: { bodyPart: '어깨', exercise: '숄더프레스' } }"><button>숄더프레스</button></router-link>
            <router-link :to="{ name: 'ExerciseDetail', params: { bodyPart: '어깨', exercise: '벤트 오버 레이즈' } }"><button>벤트 오버
                    레이즈</button></router-link>
            <router-link :to="{ name: 'ExerciseDetail', params: { bodyPart: '어깨', exercise: '사이드 레터럴 레이즈' } }"><button>사이드
                    레터럴 레이즈</button></router-link>
            <router-link :to="{ name: 'ExerciseDetail', params: { bodyPart: '어깨', exercise: '프론트 레터럴 레이즈' } }"><button>프론트
                    레터럴 레이즈</button></router-link>
        </div>

        <!-- 하체 & 엉덩이 부위 -->
        <button @click="showLowerBodyExercises = !showLowerBodyExercises">하체 & 엉덩이</button>
        <div v-if="showLowerBodyExercises">
            <router-link
                :to="{ name: 'ExerciseDetail', params: { bodyPart: '하체 & 엉덩이', exercise: '스쿼트' } }"><button>스쿼트</button></router-link>
            <router-link
                :to="{ name: 'ExerciseDetail', params: { bodyPart: '하체 & 엉덩이', exercise: '런지' } }"><button>런지</button></router-link>
            <router-link :to="{ name: 'ExerciseDetail', params: { bodyPart: '하체 & 엉덩이', exercise: '힙 쓰러스트' } }"><button>힙
                    쓰러스트</button></router-link>
        </div>

        <!-- 팔 부위 -->
        <button @click="showArmExercises = !showArmExercises">팔</button>
        <div v-if="showArmExercises">
            <router-link :to="{ name: 'ExerciseDetail', params: { bodyPart: '팔', exercise: '덤벨 컬' } }"><button>덤벨
                    컬</button></router-link>
            <router-link :to="{ name: 'ExerciseDetail', params: { bodyPart: '팔', exercise: '트라이셉스 익스텐션' } }"><button>트라이셉스
                    익스텐션</button></router-link>
            <router-link :to="{ name: 'ExerciseDetail', params: { bodyPart: '팔', exercise: '케이블 푸쉬다운' } }"><button>케이블
                    푸쉬다운</button></router-link>
        </div>

        <!-- 복부 & 코어 부위 -->
        <button @click="showCoreExercises = !showCoreExercises">복부 & 코어</button>
        <div v-if="showCoreExercises">
            <router-link
                :to="{ name: 'ExerciseDetail', params: { bodyPart: '복부 & 코어', exercise: '크런치' } }"><button>크런치</button></router-link>
            <router-link :to="{ name: 'ExerciseDetail', params: { bodyPart: '복부 & 코어', exercise: '시티드 니업' } }"><button>시티드
                    니업</button></router-link>
            <router-link :to="{ name: 'ExerciseDetail', params: { bodyPart: '복부 & 코어', exercise: '행잉 레그레이즈' } }"><button>행잉
                    레그레이즈</button></router-link>
            <router-link
                :to="{ name: 'ExerciseDetail', params: { bodyPart: '복부 & 코어', exercise: '플랭크' } }"><button>플랭크</button></router-link>
        </div>

        <!-- 가슴 부위 -->
        <button @click="showChestExercises = !showChestExercises">가슴</button>
        <div v-if="showChestExercises">
            <router-link
                :to="{ name: 'ExerciseDetail', params: { bodyPart: '가슴', exercise: '벤치프레스' } }"><button>벤치프레스</button></router-link>
            <router-link
                :to="{ name: 'ExerciseDetail', params: { bodyPart: '가슴', exercise: '딥스' } }"><button>딥스</button></router-link>
            <router-link :to="{ name: 'ExerciseDetail', params: { bodyPart: '가슴', exercise: '인클라인 벤치프레스' } }"><button>인클라인
                    벤치프레스</button></router-link>
            <router-link :to="{ name: 'ExerciseDetail', params: { bodyPart: '가슴', exercise: '체스트 플라이' } }"><button>체스트
                    플라이</button></router-link>
        </div>

        <!-- 등 부위 -->
        <button @click="showBackExercises = !showBackExercises">등</button>
        <div v-if="showBackExercises">
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
</template>

<script setup>
import { ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';
const router = useRouter();

const showShoulderExercises = ref(false);
const showLowerBodyExercises = ref(false);
const showArmExercises = ref(false);
const showCoreExercises = ref(false);
const showChestExercises = ref(false);
const showBackExercises = ref(false);

// // 운동을 선택했을 때 실행되는 함수
// const selectExercise = (bodyPart, exercise) => {
//     router.push({ name: 'ExerciseDetail', params: { bodyPart, exercise } });
// };
</script>

<style scoped>:root {
    --primary-100: #424874;
    --primary-200: #A6B1E1;
    --primary-300: #fdf6fd;
    --accent-100: #D9ACF5;
    --accent-200: #FFCEFE;
    --text-100: #292524;
    --text-200: #64748b;
    --bg-100: #ffffff;
    --bg-200: #f5f5f5;
    --bg-300: #cccccc;
}</style>
<template>
    <div>
        랭킹
    </div>
    <div>
        <input type="button" class="search-btn" :class="{ click: isClick[0].value }" value="전체"
            @click="changePart('total', 0)" />
        <input type="button" class="search-btn" :class="{ click: isClick[1].value }" value="팔"
            @click="changePart('팔', 1)" />
        <input type="button" class="search-btn" :class="{ click: isClick[2].value }" value="어깨"
            @click="changePart('어깨', 2)" />
        <input type="button" class="search-btn" :class="{ click: isClick[3].value }" value="등"
            @click="changePart('등', 3)" />
        <input type="button" class="search-btn" :class="{ click: isClick[4].value }" value="가슴"
            @click="changePart('가슴', 4)" />
        <input type="button" class="search-btn" :class="{ click: isClick[5].value }" value="복부 & 코어"
            @click="changePart('복부 & 코어', 5)" />
        <input type="button" class="search-btn" :class="{ click: isClick[6].value }" value="하체 & 엉덩이"
            @click="changePart('하체 & 엉덩이', 6)" />
    </div>

    <RankComp />
</template>

<script setup>
import { useRoute, useRouter } from 'vue-router';
import { onMounted, ref } from "vue";
import { useExerciseStore } from "../stores/exercise"
import RankComp from '../components/exercise/RankComp.vue';
const store = useExerciseStore();

onMounted(() => {
    const part = 'total'
    store.getLankList(part); //주소에서 가져오기
});
const part = ref('total')
const isClick = ref([ref(true), ref(false), ref(false), ref(false), ref(false), ref(false), ref(false)])

const changePart = function (newPart, a) {
    // console.log(isClick.value[a].value)
    for (let i = 0; i < 7; i++) {
        if (i == a) {
            isClick.value[i].value = true
        } else {
            isClick.value[i].value = false
        }
    }
    part.value = newPart
    store.getLankList(part.value)
}
</script>

<style scoped>
:root {
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
}

.search-btn {
    margin-top: 3px;
    margin-left: 20px;
    margin-bottom: 3px;
    color: #fff;
    width: 50px;
    height: 35px;
    border-radius: 10px;
    border: solid 2px rgb(184, 225, 211);
    background-color: rgb(184, 225, 211);
    transition: 0.5s;
    cursor: pointer;
}

.search-btn:hover {
    margin-top: 0px;
    margin-bottom: 0px;
    width: 60px;
    height: 40px;
    background-color: rgb(55, 182, 140);
    border: solid 2px rgb(55, 182, 140);
}

.click {
    margin-top: 0px;
    margin-bottom: 0px;
    width: 60px;
    height: 40px;
    background-color: rgb(55, 182, 140);
    border: solid 2px rgb(55, 182, 140);
}
</style>
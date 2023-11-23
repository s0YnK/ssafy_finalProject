<template>
    <div class="rank-div">
        <input type="button" class="search-btn" :class="{ click: isClick[0].value }" value="전체랭킹"
            @click="changePart('total', 0)" />
        <input type="button" class="search-btn" :class="{ click: isClick[1].value }" value="팔근육 best"
            @click="changePart('팔', 1)" />
        <input type="button" class="search-btn" :class="{ click: isClick[2].value }" value="어깨근육 best"
            @click="changePart('어깨', 2)" />
        <input type="button" class="search-btn" :class="{ click: isClick[3].value }" value="등근육 best"
            @click="changePart('등', 3)" />
        <input type="button" class="search-btn" :class="{ click: isClick[4].value }" value="가슴근육 best"
            @click="changePart('가슴', 4)" />
        <input type="button" class="search-btn" :class="{ click: isClick[5].value }" value="복부 & 코어 best"
            @click="changePart('복부 & 코어', 5)" />
        <input type="button" class="search-btn" :class="{ click: isClick[6].value }" value="하체 & 엉덩이 best"
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
.rank-div {
    width: 1170px;
    margin: 0 auto;
}

.search-btn {
    margin: 20px;
    margin-bottom: 40px;
    color: #fff;
    height: 35px;
    padding: 0 20px;
    border-radius: 10px;
    border: solid 2px var(--primary-200);
    background-color: var(--primary-200);
    transition: 0.5s;
    cursor: pointer;
    font-size: 14px;
}

.search-btn:hover {
    margin: 10px;
    margin-bottom: 40px;
    height: 40px;
    padding: 0 30px;
    background-color: var(--primary-100);
    border: solid 2px var(--primary-100);
    font-size: 14px;
}

.click {
    margin: 10px;
    margin-bottom: 40px;
    padding: 0 30px;
    height: 40px;
    background-color: var(--primary-100);
    border: solid 2px var(--primary-100);
    font-size: 14px;
}
</style>
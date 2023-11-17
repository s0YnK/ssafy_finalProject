<template>
    <div class="background">
    </div>
    <div class="search-div">
        <h1 class="dis">운동법 가이드</h1>
        <input type="text" id="keywords" class="guide-input" v-model="keyword" placeholder="모르는 운동을 검색해 보세요" />
        <button @click="searchByName" class="guide-btn dis">입력</button>
    </div>

    <div class="a1"></div>
    <div class="a2"></div>
    <div class="a3"></div>
</template>

<script setup>
import { ref } from 'vue'
import { useYoutubeStore } from '@/stores/youtube'
const keyword = ref('')
const store = useYoutubeStore()

const searchByName = () => {
    store.youtubeSearch(keyword.value)
    const searchDiv = document.getElementsByClassName("search-div")[0];
    const dis1 = document.getElementsByClassName("dis")[0];
    const dis2 = document.getElementsByClassName("dis")[1];
    dis1.style.display = 'none';
    dis2.style.display = 'none';
    searchDiv.style.top = '5%';
    searchDiv.style.left = '20%';
    let i = 1;
    const intervalId = setInterval(function () {
        const div = document.getElementsByClassName(`a${i++}`)[0];
        div.style.top = '30%';
        div.style.opacity = '1';

        // 3번 실행 후 clearInterval 호출
        if (i > 3) {
            clearInterval(intervalId);
        }
    }, 500);
}


</script>

<style scoped>
.a1,
.a2,
.a3 {
    width: 18%;
    height: 36%;
    background-color: var(--bg-200);
    position: fixed;
    top: 100%;
    border-radius: 20px;
    opacity: 0;
    transition: 1s;
}

.a1 {
    left: 30%;
}

.a2 {
    left: 50%;
}

.a3 {
    left: 70%;
}

.search-div {
    position: fixed;
    top: 30%;
    left: 50%;
    display: flex;
    flex-direction: column;
    align-items: center;
    transition: 0.4s;
}

.guide-input {
    margin-top: 20px;
    width: 180%;
    height: 70px;
    border-radius: 30px;
    border: solid 3px var(--primary-200);
    padding-left: 50px;
    outline: none;
    color: var(--text-100);
    background-color: rgba(255, 255, 255, 0.3);
    transition: 0.4s;
}

.guide-input:focus {
    /* background-color: azure; */
    border: 3px solid var(--primary-100);
    box-shadow: 0px 0px 5px var(--primary-200);
    background-color: var(--bg-100);
    background-position: 2%;
    transition: 0.4s;
}

.guide-btn {
    margin-top: 20px;
}
</style>

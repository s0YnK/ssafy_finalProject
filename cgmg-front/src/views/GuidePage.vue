<template>
    <div class="background">
    </div>
    <div class="search-div">
        <h1 class="dis">운동법 가이드</h1>
        <input type="text" id="keywords" class="guide-input" v-model="keyword" placeholder="모르는 운동을 검색해 보세요" />
        <button @click="searchByName" class="guide-btn dis">입력</button>
    </div>
    <ul v-for="video, i in store.videos" :key="video.id.videoId" :video="video">
        <li :class="`a${i}`">
            <span>{{ video.snippet.title }}</span>
            <iframe width="560" height="315" :src="`https://www.youtube.com/embed/${video.id.videoId}`"
                title="YouTube video player" frameborder="0"
                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                allowfullscreen></iframe>
        </li>
    </ul>
    <!-- <div class="a0"></div>
    <div class="a1"></div>
    <div class="a2"></div> -->
</template>

<script setup>
import { ref } from 'vue'
import { useYoutubeStore } from '@/stores/youtube'
const keyword = ref('')
const store = useYoutubeStore()

const searchByName = () => {
    store.youtubeSearch(keyword.value)

}


</script>

<style scoped>
.a0,
.a1,
.a2 {
    width: 18%;
    height: 36%;
    background-color: var(--bg-200);
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
    margin: 300px auto;
    display: flex;
    flex-direction: column;
    align-items: center;
    transition: 0.4s;
}

.guide-input {
    font-size: 20px;
    margin-top: 20px;
    width: 240px;
    height: 70px;
    border-radius: 30px;
    border: solid 3px var(--primary-200);
    padding-left: 60px;
    padding-right: 40px;
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

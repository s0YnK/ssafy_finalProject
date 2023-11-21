<template>
    <div class="container">
        <h2>상세글</h2>
        <br>
        <div class="title">
            <div class="title-l">
                <div>{{ store.board.title }}</div>
            </div>

            <div class='img-b' @click="likeAdd"></div>
            <div class='img-a' @click="likeDelete" v-if="!isLike()"></div>
            <div class="title-r">
                <div>작성자 : {{ store.board.writer }}</div>
                <div class="date">작성일 : {{ store.board.regDate }}</div>
            </div>
        </div>
        <div class="content">{{ store.board.content }}</div>

        <div v-if="isUser(store.board.writer)" class="buttonarea">
            <button class="button" @click="deleteBoard">글삭제</button>
            <button class="button">
                <RouterLink :to="`/community/update/${store.board.id}`">
                    글수정
                </RouterLink>
            </button>
        </div>
        <kakaoMapApi />
    </div>
</template>

<script setup>
import { useRoute, useRouter } from 'vue-router'
import { useBoardStore } from "@/stores/community";
import { onMounted, ref, watchEffect, computed } from "vue";
import axios from 'axios'
import kakaoMapApi from './kakaoMapApi.vue';


const store = useBoardStore()
const route = useRoute();
const router = useRouter();
const loginUser = JSON.parse(localStorage.getItem("loginUser")).userId;
const data = ref({
    postId: Number(route.params.id),
    userId: loginUser,
})

onMounted(() => {
    const route = useRoute();
    store.getBoard(route.params.id);
    store.likeLog(data.value);
})
//게시글 삭제
const deleteBoard = function () {
    axios.delete(`http://localhost:8080/board-api/board/${route.params.id}`)
        .then(() => {
            router.push({ name: 'community' })
        })
}

//게시글이 자신인지
const isUser = (a) => {
    return a === loginUser;
}
const isLike = () => {
    return store.LikeLog;
}

function likeAdd() {
    store.likeBoard(data.value)
}
function likeDelete() {
    store.unlikeBoard(data.value)
}

</script>

<style scoped>
.container {
    max-width: 800px;
    margin: 20px auto;
}

.title {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 20px;
}

.title-l {
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 25px;
    width: 500px;
    background: linear-gradient(270deg, rgba(255, 255, 255, 1) 0%, rgb(142, 195, 177) 100%);
    padding: 10px;
    padding-bottom: 5px;
    border-radius: 10px;
}

.title-r {
    display: flex;
    flex-direction: column;
    align-items: end;
}

.date {
    font-size: 12px;
}

.content {
    background-color: rgb(208, 240, 236);
    width: 97%;
    min-height: 200px;
    padding: 20px;
    border-radius: 10px;
    margin-bottom: 20px;
}

a,
button {
    text-decoration: none;
    color: #fff;
}

.buttonarea {
    display: flex;
    justify-content: end;
    gap: 20px;
}

.button {
    padding: 0.75rem 2rem;
    outline: none;
    border: none;
    background-color: rgb(184, 225, 211);
    color: #fff;
    font-size: 1rem;
    border-radius: 10px;
    cursor: pointer;
    transition: 0.3s;
}

.button:hover {
    background-color: rgb(55, 182, 140);
}

.img-b {
    width: 50px;
    height: 50px;
    border-radius: 10px;
    background: url("../../assets/arm2.png");
    background-size: cover;
}

.img-a {
    width: 50px;
    height: 50px;
    border-radius: 10px;
    background: url("../../assets/arm1.png");
    background-size: cover;
}
</style>
<template>
    <div class="container">
        <div class="heee">
            <h2 style="padding: 0 20px 10px 20px; font-size: 25px;">상세글</h2>
            <div v-if="isUser(store.board.writer)" class="buttonarea">
                <button class="button" @click="deleteBoard">글삭제</button>
                <button class="button">
                    <RouterLink :to="`/community/update/${store.board.id}`">
                        글수정
                    </RouterLink>
                </button>
            </div>
        </div>
        <div class="crf">
            <div class="conte">
                <div class="title">
                    <div class="titl">
                        <div style="padding: 20px;">
                            <div class="title-l">{{ store.board.title }}</div>
                            <div class="title-r"> 작성자 : {{ store.board.nickName }} | 작성일 : {{ store.board.regDate }}</div>
                        </div>
                        <div class='img-b' @click="likeAdd" v-if="isLike()"></div>
                        <div class='img-a' @click="likeDelete" v-else></div>
                    </div>

                </div>
                <div class="contenta">
                    <div class="content">{{ store.board.content }}</div>
                </div>
            </div>

            <kakaoMapApi />
        </div>
    </div>
    <div class="commtt">
        <h2 style="padding: 0 20px 10px 20px; font-size: 25px;">댓글</h2>
        <div class="commitm">
            <textarea v-model="newComment.content" placeholder="댓글을 입력하세요" class="commin"></textarea>
            <button @click="addComment" class="btn">댓글 작성</button>
        </div>
        <div class="dddd">
            <comment-component :commentList="store2.commentList" :space="`&nbsp&nbsp`"></comment-component>
        </div>
        <!-- 댓글 입력 폼 -->
    </div>
</template>

<script setup>
import { useRoute, useRouter } from 'vue-router'
import { useBoardStore } from "@/stores/community";
import { useCommentStore } from "@/stores/comment";
import { onMounted, ref, watchEffect, computed } from "vue";
import CommentComponent from './CommentComponent.vue';   // CommentComponent 컴포넌트 가져오기
import axios from 'axios'
import kakaoMapApi from './kakaoMapApi.vue';

const store = useBoardStore()
const store2 = useCommentStore()
const route = useRoute();
const router = useRouter();
const loginUser = JSON.parse(localStorage.getItem("loginUser")).userId;
const data = ref({
    postId: Number(route.params.id),
    userId: loginUser,
})

const newComment = ref({
    content: "",
    parentId: 0,
    postId: Number(route.params.id),
    writer: loginUser,

});


//시작될때
onMounted(() => {
    const route = useRoute();
    store.getBoard(route.params.id);
    store.likeLog(data.value);
    store2.getCommentList(route.params.id)
})

const addComment = () => {
    newComment.value.parentId = 0;
    store2.createComment(newComment.value);
};


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

//내가 좋아요 눌렀었는지
const isLike = () => {
    return !store.LikeLog;
}


//좋아요버튼 누루는 함수
function likeAdd() {
    store.likeBoard(data.value)
}

//좋아요 취소하는 함수
function likeDelete() {
    store.unlikeBoard(data.value)
}


</script>

<style scoped>
.dddd {
    box-shadow: 0 0 0 4px var(--text-200);
    border-radius: 10px;
    padding: 10px;
}

.commin {
    flex-grow: 1;
    background-color: var(--header);
    padding: 15px;
    border-radius: 10px;
    border: solid 2px var(--primary-200);
    font-size: 16px;
}

.btn {
    font-size: 18px;
    color: #fff;
    width: 118px;
    height: 50px;
    border-radius: 10px;
    border: solid 2px var(--primary-100);
    background-color: var(--primary-100);
    transition: 0.2s;
    cursor: pointer;
}

.heee {
    display: flex;
    justify-content: space-between;
}

.container {
    width: 1120px;
    margin: 20px auto;
    background-color: var(--header);
    padding: 20px;
    border-radius: 20px;
}

.crf {
    display: flex;
    gap: 20px;
    margin-bottom: 20px;
    margin-top: 20px;
}

.conte {
    background-color: #fff;
    border-radius: 20px;
    padding: 20px;
    flex-grow: 1;
    display: flex;
    flex-direction: column;

}

.titl {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding-right: 20px;
}

.title-l {
    font-size: 25px;
    color: #000;
}

.title-r {
    color: #000;
}

.contenta {
    padding: 20px;
    background-color: var(--primary-200);
    border-radius: 10px;
    flex-grow: 1;
}

.commtt {
    width: 1120px;
    margin: 20px auto;
    background-color: var(--header3);
    padding: 20px;
    border-radius: 20px;

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
    height: 50px;
}

.button {
    padding: 0.75rem 2rem;
    outline: none;
    border: none;
    background-color: var(--primary-200);
    color: #fff;
    font-size: 1rem;
    border-radius: 10px;
    cursor: pointer;
    transition: 0.3s;
    font-family: 'AritaDotum';
    ;
}

.button:hover {
    background-color: var(--primary-100)
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

.commitm {
    display: flex;
    justify-content: space-between;
    align-items: center;
    gap: 20px;
    margin-bottom: 20px;
}
</style>
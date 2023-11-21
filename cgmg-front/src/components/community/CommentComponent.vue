<template>
    <div>
        <!-- 댓글 입력 폼 -->
        <div>
            <textarea v-model="newComment.content" placeholder="댓글을 입력하세요"></textarea>
            <button @click="addComment">댓글 작성</button>
        </div>

        <!-- 기존 댓글 목록 -->
        <div v-for="comment in commentList" :key="comment.id">
            {{ comment.writer }}
            {{ comment.regDate }}
            {{ comment.content }}
            <button @click="addComment2(comment.id)">대댓글 작성</button>
            <button @click="deleteComment(comment.id)">삭제</button>
            <div>ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ</div>

            <!-- commentComponent.vue(자기자신)을 재귀적으로 렌더링 -->
            <!-- comments props로 comment의 자식 댓글 리스트 전달 -->
            <!-- comment의 자식댓글 리스트가 존재하고, 자식댓글 리스트의 크기가 0보다 크면 렌더링 -->
            <comment-component :commentList="comment.children"
                v-if="comment.children && comment.children.length > 0"></comment-component>
        </div>
    </div>
</template>

<script setup>
import { ref, defineProps } from 'vue';
import { useRoute, useRouter } from 'vue-router'
import { useCommentStore } from "@/stores/comment";

const store2 = useCommentStore()
const route = useRoute();
const loginUser = JSON.parse(localStorage.getItem("loginUser")).userId;

const props = defineProps(['commentList']);
console.log(props)
const newComment = ref({
    content: "",
    parentId: 0,
    postId: Number(route.params.id),
    writer: loginUser,

});

//시작될때

const addComment = () => {
    // console.log(props.commentList[0])
    newComment.value.parentId = 0;
    store2.createComment(newComment.value);
};
const addComment2 = (commentId) => {
    // console.log(props.commentList[0])
    newComment.value.parentId = commentId;
    store2.createComment(newComment.value);
};

const editComment = () => {
    newComment.value.parentId = comment.id;
    store2.updatecomment(newComment.value);
};

const deleteComment = (commentId) => {
    const id = ref({
        id: commentId,
    });
    store2.deletecomment(id.value);
};
</script>
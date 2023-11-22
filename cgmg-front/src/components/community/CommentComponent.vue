<template>
    <div style="background-color: var(--header3); box-shadow: 0 0 10px var(--text-200); padding: 10px;">

        <!-- 기존 댓글 목록 -->
        <div v-for="comment in commentList" :key="comment.id">
            <div v-if="comment.isDeleted === `false`" @click="selectedContent = comment.id">
                {{ space }}
                {{ comment.writer }}
                {{ comment.regDate }}
                {{ comment.content }}
            </div>
            <button v-if="isUser(comment.writer)" @click="deleteComment(comment.id)">삭제</button>
            <div v-if="comment.isDeleted === `true`">삭제된 댓글입니다.</div>
            <div v-if="selectedContent === comment.id">
                <textarea v-model="newComment.content" placeholder="댓글을 입력하세요"></textarea>
                <button @click="addComment2(comment.id)">대댓글 작성</button>
            </div>

            <div>{{ space }}---------------------------------</div>
            <comment-component :commentList="comment.children" :space="`&nbsp&nbsp&nbsp&nbsp&nbsp${space}`"
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

const props = defineProps(['commentList', 'space']);

const isUser = (a) => {
    return loginUser === a;
}
console.log(props)
const newComment = ref({
    content: "",
    parentId: 0,
    postId: Number(route.params.id),
    writer: loginUser,

});

const addComment2 = (commentId) => {
    // console.log(props.commentList[0])
    newComment.value.parentId = commentId;
    store2.createComment(newComment.value);
    selectedContent.value = null
};

const editComment = () => {
    newComment.value.parentId = comment.id;
    store2.updatecomment(newComment.value);
};

const deleteComment = (commentId) => {
    const id = ref({
        id: commentId,
    });
    const postId = ref({
        postId: Number(route.params.id),
    });
    store2.deletecomment(id.value, postId.value);
};
const selectedContent = ref(null);
</script>
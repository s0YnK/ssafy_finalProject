<template>
    <div style="background-color: var(--header3); padding-left: 30px; border-radius: 0 0 0 10px;">

        <div v-for="comment in commentList" :key="comment.id" class="co">
            <div class="ddd">
                <div class="ssss">
                    <div style="display: flex; justify-content: space-between;">
                        <div v-if="comment.isDeleted === 'false'" @click="toggleCommentForm(comment.id)"
                            :class="{ 'active': selectedEditContent === comment.id }" class="aa">
                            작성자 : {{ comment.writer }}
                        </div>
                        <div v-if="comment.isDeleted === 'false'" style="display: flex; justify-content: end; gap: 10px;">
                            <button v-if="isUser(comment.writer)" @click="toggleEditForm(comment.id)"
                                class="bbbtt">수정</button>
                            <button v-if="isUser(comment.writer)" @click="deleteComment(comment.id)"
                                class="bbbtt">삭제</button>
                            <div v-if="comment.isDeleted === 'false'" @click="toggleCommentForm(comment.id)"
                                :class="{ 'active': selectedEditContent === comment.id }" class="aa">
                                {{ comment.regDate }}
                            </div>
                        </div>
                    </div>
                    <div v-if="comment.isDeleted === 'false'" @click="toggleCommentForm(comment.id)"
                        :class="{ 'active': selectedEditContent === comment.id }" class="ccccc">
                        {{ comment.content }}
                    </div>

                    <div v-if="comment.isDeleted === 'true'">삭제된 댓글입니다.</div>
                    <div v-if="selectedContent === comment.id" class="flfff">
                        <textarea v-model="newComment.content" placeholder="대댓글을 입력하세요" class="kkk"></textarea>
                        <button @click="addComment2(comment.id)" class="bbbtt">작성</button>
                        <button @click="cancelCommentForm" class="bbbtt">취소</button>
                    </div>
                    <div v-if="selectedEditContent === comment.id" class="flfff">
                        <textarea v-model="updateComment.content" placeholder="댓글을 수정하세요" class="kkk"></textarea>
                        <button @click="editComment(comment.id)" class="bbbtt">수정</button>
                        <button @click="cancelEditForm" class="bbbtt">취소</button>
                    </div>
                </div>
            </div>

            <comment-component :commentList="comment.children" :space="`${space}`"
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

const newComment = ref({
    content: "",
    parentId: 0,
    postId: Number(route.params.id),
    writer: loginUser,
});

const updateComment = ref({
    content: "",
    id: 0,
    postId: Number(route.params.id),
    writer: loginUser,
});

const addComment2 = (commentId) => {
    newComment.value.parentId = commentId;
    store2.createComment(newComment.value);
    selectedContent.value = null;
};

const editComment = (id) => {
    updateComment.value.id = id;
    store2.updatecomment(updateComment.value);
    cancelEditForm();
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

const cancelCommentForm = () => {
    selectedContent.value = null;
};

const cancelEditForm = () => {
    selectedEditContent.value = null;
};

const toggleCommentForm = (commentId) => {
    selectedContent.value = (selectedContent.value === commentId) ? null : commentId;
};

const toggleEditForm = (commentId) => {
    selectedEditContent.value = (selectedEditContent.value === commentId) ? null : commentId;
};

const selectedContent = ref(null);
const selectedEditContent = ref(null);
</script>
  
<style scoped>
.kkk {
    flex-grow: 1;
    background-color: var(--header);
    padding: 10px;
    border-radius: 10px;
    border: solid 2px var(--primary-200);
}

.flfff {
    display: flex;
    justify-content: end;
    align-items: center;
    gap: 10px;
}

.bbbtt {
    border-radius: 5px;
    border: solid 1px var(--primary-100);
    background-color: var(--primary-100);
    color: var(--bg-100);
    cursor: pointer;
    transition: 0.2s;
    width: 50px;
    height: 25px;
}

.bbbtt:hover {
    border: solid 1px var(--primary-300);
    background-color: var(--primary-300);
    color: var(--text-200);
}

.co {
    margin-top: 5px;
    transition: 0.2s;

}

.ccccc {
    font-size: 17px;
}

.aa {
    color: var(--text-100);
}

.ssss {
    background-color: var(--primary-200);
    padding: 10px;
    border-radius: 10px;
    transition: 0.2s;

}

.ddd {
    padding: 5px;
    padding-right: 10px;
    margin-top: 5px;
    box-shadow: -2px 2px 0 2px var(--text-200);
    border-radius: 0 0 0 10px;

}
</style>
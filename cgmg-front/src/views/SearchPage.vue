<template>
    <h2 style="margin: 50px auto 20px; width: 90px;">
        검색결과
    </h2>
    <div class="sea-div">
        <table>
            <div class="table">
                <div style="width: 70px;">번호</div>
                <div style="width: 100px; text-align: center;">아이디</div>
                <div style="width: 100px; text-align: center;">닉네임</div>
                <div style="width: 70px; text-align: end">티어</div>
            </div>
            <div v-for="search, i in store.searchList" :key="search.userId" class="tablee">
                <div style="width: 70px;">{{ i + 1 }}</div>
                <div style="width: 100px; text-align: center;">
                    <RouterLink :to="isCurrentUser(search.userId) ? '/Profile' : `/otherProfile/${search.userId}`">
                        {{ search.userId }}
                    </RouterLink>
                </div>
                <div style="width: 100px; text-align: center;">{{ search.nickName }}</div>
                <div style="width: 70px; text-align: end;">{{ search.totalExerciseCnt }}</div>
            </div>
        </table>
    </div>
</template>

<script setup>
import { useRoute, useRouter } from 'vue-router';
import { onMounted } from "vue";
import { useUserStore } from "../stores/user"
const store = useUserStore();

onMounted(() => {
    const route = useRoute()
    store.getSearch(route.params.word); // 주소에서 검색어 가져오기
});

// 현재 로그인한 사용자와 검색 결과의 사용자를 비교하는 함수
const isCurrentUser = (userId) => {
    // 로컬 스토리지에서 로그인한 사용자 정보 가져오기
    const loginUser = JSON.parse(localStorage.getItem("loginUser"));
    // 현재 검색 중인 사용자와 로그인한 사용자의 아이디가 일치하는지 확인
    return userId === loginUser.userId;
};
</script>

<style scoped>
.sea-div {
    width: 390px;
    background-color: var(--header3);
    margin: 0 auto;
    padding: 20px;
    border-radius: 10px;
}

.table {
    background-color: var(--bg-500);
    padding: 5px 20px;
    border-radius: 10px;
    font-size: 18px;
    display: flex;
}

.tablee {
    padding: 5px 20px;
    border-radius: 10px;
    font-size: 18px;
    display: flex;
    margin-top: 10px;
}

.tablee:hover {
    background-color: var(--header);
}
</style>

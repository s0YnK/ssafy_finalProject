<template>
    <div>
        검색결과
    </div>
    <table>
        <tr>
            <th>번호</th>
            <th>아이디</th>
            <th>닉네임</th>
            <th>티어</th>
        </tr>
        <!-- 검색 결과를 반복하면서 테이블 행을 생성 -->
        <tr v-for="search, i in store.searchList" :key="search.userId">
            <td>{{ i + 1 }}</td>
            <td>
                <!-- 조건에 따라 프로필 페이지로 이동할지 다른 사용자 프로필 페이지로 이동할지 결정 -->
                <RouterLink :to="isCurrentUser(search.userId) ? '/Profile' : `/otherProfile/${search.userId}`">
                    {{ search.userId }}
                </RouterLink>
            </td>
            <td>{{ search.nickName }}</td>
            <td>{{ search.totalExerciseCnt }}</td>
        </tr>
    </table>
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
</style>

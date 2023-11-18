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
        <tr v-for=" search, i in store.searchList" :key="search.userId">
            <td>{{ i + 1 }}</td>
            <td>
                <RouterLink :to="`/otherProfile/${search.userId}`">{{ search.userId }}</RouterLink>
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
    store.getSearch(route.params.word); //주소에서 가져오기
});
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
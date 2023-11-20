<template>
    <div>
        <tr>
            <th>번호</th>
            <th>아이디</th>
            <th>닉네임</th>
            <th>티어</th>
        </tr>
        <tr v-for=" rank, i in store.rankList" :key="rank.userId">
            <td>{{ i + 1 }}</td>
            <td>
                <RouterLink :to="`/otherProfile/${rank.userId}`">{{ rank.userId }}</RouterLink>
            </td>
            <td>{{ rank.nickName }}</td>
            <td>{{ rank.cnt }}</td>
        </tr>
    </div>
</template>

<script setup>
import { onMounted } from 'vue';
import { useExerciseStore } from '../../stores/exercise'
const store = useExerciseStore()

defineProps({
    rankLint: Array
});

onMounted(() => {
    store.getLankList();
});
</script>

<style scoped></style>
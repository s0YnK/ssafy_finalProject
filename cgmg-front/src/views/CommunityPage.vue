<template>
    <div>
        커뮤니티
    </div>
    <table>
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>글쓴이</th>
            <th>날짜</th>
            <th>조회수</th>
        </tr>
        <!-- 검색 결과를 반복하면서 테이블 행을 생성 -->
        <tr v-for="board, i in store.boardList" :key="board.id">
            <td>{{ i + 1 + (currentPage - 1) * itemsPerPage }}</td>
            <RouterLink :to="`/community/${board.id}`">
                <td>{{ board.title }}</td>
            </RouterLink>
            <td>{{ board.writer }}</td>
            <td>{{ board.regDate }}</td>
            <td>{{ board.viewCnt }}</td>
        </tr>
    </table>
    <div>
        <button @click="prevPage" :disabled="currentPage === 1">이전</button>
        <span>현재 페이지: {{ currentPage }}</span>
        <button @click="nextPage" :disabled="currentPage === totalPages">다음</button>
    </div>
    <RouterLink :to="{ name: 'communityCreate' }">글 작성하러 가기</RouterLink>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { useBoardStore } from '../stores/community';

const store = useBoardStore();

// 페이지네이션 관련 변수
const itemsPerPage = 15; // 한 페이지에 보여줄 아이템 수
const currentPage = ref(1); // 현재 페이지
const totalPages = ref(1); // 전체 페이지 수

// 페이징된 리스트
const paginatedList = ref([]);

onMounted(() => {
    const route = useRoute();
    store.getBoardList();

    // 페이지네이션 초기화
    updatePagination();
});

// 페이지네이션 업데이트 함수
const updatePagination = () => {
    const startIndex = (currentPage.value - 1) * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;

    paginatedList.value = store.boardList.slice(startIndex, endIndex);
    totalPages.value = Math.ceil(store.boardList.length / itemsPerPage);
};

// 이전 페이지로 이동
const prevPage = () => {
    if (currentPage.value > 1) {
        currentPage.value -= 1;
        updatePagination();
    }
};

// 다음 페이지로 이동
const nextPage = () => {
    if (currentPage.value < totalPages.value) {
        currentPage.value += 1;
        updatePagination();
    }
};
</script>

<style scoped></style>
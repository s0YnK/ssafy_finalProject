<template>
    <div class="com">
        <div class="commu">
            <table class="commu-ul-lab">
                <tr class="commu-li">
                    <th class="commu-index">번호</th>
                    <th class="commu-title" @click="sortBy('title')">제목</th>
                    <th class="commu-nickName" @click="sortBy('nickName')">글쓴이</th>
                    <th class="commu-regDate" @click="sortBy('regDate')">날짜</th>
                    <th class="commu-viewCnt" @click="sortBy('viewCnt')">조회수</th>
                    <th class="commu-likeCnt" @click="sortBy('likeCnt')">좋아요</th>
                </tr>
                <!-- 검색 결과를 반복하면서 테이블 행을 생성 -->
            </table>
            <ul class="commu-ul">
                <li v-for="(board, index) in paginatedList" :key="board.id" class="commu-li">
                    <div class="commu-index">{{ index + 1 + (currentPage - 1) * itemsPerPage }}</div>
                    <RouterLink :to="`/community/${board.id}`" class="commu-title">
                        <div>{{ board.title }}</div>
                    </RouterLink>
                    <div class="commu-nickName">{{ board.nickName }}</div>
                    <div class="commu-regDate">{{ board.regDate }}</div>
                    <div class="commu-viewCnt">{{ board.viewCnt }}</div>
                    <div class="commu-likeCnt">{{ board.likeCnt }}</div>
                </li>
            </ul>
            <div class="page">
                <button @click="prevPage" :disabled="currentPage === 1">이전</button>
                <span>현재 페이지: {{ currentPage }}</span>
                <button @click="nextPage" :disabled="currentPage === totalPages">다음</button>
            </div>
        </div>
        <div class="nav">
            <div class="cre">
                <RouterLink :to="{ name: 'communityCreate' }">글 작성하러 가기</RouterLink>
            </div>
            <pop />
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted, watch, watchEffect } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { useBoardStore } from '../stores/community';
import pop from '../components/community/pop.vue';
const store = useBoardStore();
const itemsPerPage = 15; // 한 페이지에 보여줄 아이템 수
const currentPage = ref(1); // 현재 페이지
const totalPages = ref(1); // 전체 페이지 수
const paginatedList = ref([]); // 페이징된 리스트
const search = ref({
    key: null,
    word: null,
    orderBy: "id",
    orderByDir: "DESC",
})

onMounted(() => {
    store.getOrderList(search.value);
    store.getBestList();
    const startIndex = (currentPage.value - 1) * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;

    paginatedList.value = store.OrderList.slice(startIndex, endIndex);
    totalPages.value = Math.ceil(store.OrderList.length / itemsPerPage);
});

const sortBy = async (column) => {
    // 클릭한 헤더에 따라 orderBy 값을 업데이트
    search.value.orderBy = column;
    if (search.value.orderByDir == "DESC") {
        search.value.orderByDir = "ASC"
    } else {
        search.value.orderByDir = "DESC"
    }

    // 데이터 업데이트
    store.getOrderList(search.value);

    // 페이징 처리
    updatePagination();
};

// 페이지네이션 업데이트 함수
const updatePagination = async () => {
    const startIndex = (currentPage.value - 1) * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;

    // 페이징 처리
    paginatedList.value = store.OrderList.slice(startIndex, endIndex);
    totalPages.value = Math.ceil(store.OrderList.length / itemsPerPage);
};
watch(currentPage, () => {
    updatePagination();
});

watchEffect(() => {
    updatePagination();
});
// 이전 페이지로 이동
const prevPage = () => {
    if (currentPage.value > 1) {
        currentPage.value -= 1;
    }
};

// 다음 페이지로 이동
const nextPage = () => {
    if (currentPage.value < totalPages.value) {
        currentPage.value += 1;
    }
};
</script>

<style scoped>
/* .navinf {
    background-color: var(--header);
    height: 520px;
    border-radius: 10px;
    padding: 10px 20px;
    margin-bottom: 10px;
    text-align: center;
} */

.cre {
    background-color: var(--header);
    border-radius: 10px;
    padding: 10px 20px;
    margin-bottom: 10px;
    text-align: center;
}

.nav {
    width: 300px;
    height: 600px;
    background-color: var(--primary-200);
    padding: 20px;
    border-radius: 20px;
}

.com {
    display: flex;
    justify-content: center;
    gap: 20px;
}

.commu {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    gap: 10px;
}

.commu-ul-lab {
    background-color: var(--bg-100);
    border-radius: 10px;
}

.commu-ul {
    min-height: 560px;
    list-style: none;
    padding: 0;
}

.commu-li {
    padding: 5px 20px;
    margin: 3px;
    border-radius: 5px;
    display: flex;
    width: 700px;
}

.commu-li:hover {
    background-color: var(--header);
}

.commu-index {
    width: 40px;
    text-align: center;
}

.commu-title {
    padding-left: 40px;
    flex-grow: 1;
}

.commu-nickName {
    width: 100px;
    text-align: center;
}

.commu-regDate {
    width: 100px;
    text-align: center;
}

.commu-viewCnt {
    width: 60px;
    text-align: end;
}

.commu-likeCnt {
    width: 60px;
    text-align: end;
}

.page {
    display: flex;
    justify-content: center;
    gap: 20px;
}
</style>

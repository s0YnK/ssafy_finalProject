<template>
    <div>
        <div class="pop" v-if="store.BestList[0]">
            <h2 class="pop-h">인기게시물 TOP3</h2>
            <div class="bestd">
                <div class="bestv">{{ store.BestList[0]?.viewCnt }}회</div>
                <div class="bb">
                    <RouterLink :to="`/community/${store.BestList[0].id}`">
                        <div class="bestt">{{ store.BestList[0].title }}</div>
                    </RouterLink>
                    <div class="bestn">글쓴이 : {{ store.BestList[0].nickName }}</div>
                    <div class="bestr">날짜 : {{ store.BestList[0].regDate }}</div>
                </div>
            </div>
            <div class="bestd">
                <div class="bestv">{{ store.BestList[1]?.viewCnt }}회</div>
                <div class="bb">
                    <RouterLink :to="`/community/${store.BestList[1].id}`">
                        <div class="bestt">{{ store.BestList[1].title }}</div>
                    </RouterLink>
                    <div class="bestn">글쓴이 : {{ store.BestList[1].nickName }}</div>
                    <div class="bestr">날짜 : {{ store.BestList[1].regDate }}</div>
                </div>
            </div>
            <div class="bestd">
                <div class="bestv">{{ store.BestList[2]?.viewCnt }}회</div>
                <div class="bb">
                    <RouterLink :to="`/community/${store.BestList[2].id}`">
                        <div class="bestt">{{ store.BestList[2].title }}</div>
                    </RouterLink>
                    <div class="bestn">글쓴이 : {{ store.BestList[2]?.nickName }}</div>
                    <div class="bestr">날짜 : {{ store.BestList[2]?.regDate }}</div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue';
import { useBoardStore } from '../../stores/community';
const store = useBoardStore();

onMounted(async () => {
    store.getBestList();
});
</script>

<style scoped>
.pop-h {
    margin-bottom: 20px;
}

.bestd {
    display: flex;
    gap: 20px;
    margin-bottom: 10px;
}

.bestv {
    font-size: 20px;
    text-align: center;
    padding: 10px;
    background-color: var(--header);
    width: 60px;
    border-radius: 10px;
    height: 40px;
    padding-top: 20px;
}

.bestt {
    font-size: 18px;
    overflow: hidden;
    height: 22px;
    margin-bottom: 5px;
    margin-top: 3px;
}

.bestn {
    font-size: 12px;
}

.bestr {
    font-size: 12px;
}

.bb {
    width: 150px;
}


.pop {
    width: 260px;
    height: 300px;
    background-color: var(--header);
    border-radius: 10px;
    padding: 10px 20px;
}
</style>
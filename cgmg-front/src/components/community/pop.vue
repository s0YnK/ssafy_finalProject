<template>
    <div>
        <div class="pop" v-if="store.BestList[0]">
            <h2>인기게시물 TOP3</h2>
            <div class="bestd">
                <div class="bestv">{{ store.BestList[0].viewCnt }}회</div>
                <div class="bb">
                    <div class="bestt">{{ store.BestList[0].title }}</div>
                    <div class="aa">
                        <div class="bestn">글쓴이 : {{ store.BestList[0].nickName }}</div>
                        <div class="bestr">날짜 : {{ store.BestList[0].regDate }}</div>
                    </div>
                </div>
            </div>
            <hr>
            <div class="bestd">
                <div class="bestv">{{ store.BestList[1].viewCnt }}회</div>
                <div class="bb">
                    <div class="bestt">{{ store.BestList[1].title }}</div>
                    <div class="aa">
                        <div class="bestn">글쓴이 : {{ store.BestList[1].nickName }}</div>
                        <div class="bestr">날짜 : {{ store.BestList[1].regDate }}</div>
                    </div>
                </div>
            </div>
            <hr>
            <div class="bestd">
                <div class="bestv">{{ store.BestList[2]?.viewCnt }}회</div>
                <div class="bb">
                    <div class="bestt">{{ store.BestList[2]?.title }}</div>
                    <div class="aa">
                        <div class="bestn">글쓴이 : {{ store.BestList[2]?.nickName }}</div>
                        <div class="bestr">날짜 : {{ store.BestList[2]?.regDate }}</div>
                    </div>
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
.bestd {
    display: flex;
    flex-direction: column;
}

.bestv {
    font-size: 20px;
    text-align: center;
}

.bestt {}

.bestn {}

.bestr {}

.aa {
    display: flex;
    flex-direction: column;
    font-size: 12px;
    align-items: end;
}

.bb {
    display: flex;
}


.pop {
    width: 260px;
    height: 300px;
    background-color: var(--header);
    border-radius: 10px;
    padding: 10px 20px;
}
</style>
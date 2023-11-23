<template>
    <div>
        <table class="rank-tr">
            <tr>
                <th class="rank-th-b">등수</th>
                <th class="rank-th-i">아이디</th>
                <th class="rank-th-n">닉네임</th>
                <th class="rank-th-t">티어</th>
            </tr>
        </table>
        <ul class="rank-ul">
            <li v-for="(rank, i) in store.rankList" :key="rank.userId" class="rank-li" :class="getRankClass(i)">
                <div class="rank-b">
                    <div v-if="i <= 2" class="rank-medal">{{ getMedalText(i) }}</div>
                    <div v-else>{{ i + 1 }}</div>
                </div>
                <div class="rank-i">
                    <div>
                        <img :src="`src/assets/${rank.profileImg}.png`" width="20" height="20" class="rank-img" />
                    </div>
                    <div>
                        <RouterLink :to="`/otherProfile/${rank.userId}`">{{ rank.userId }}</RouterLink>
                    </div>
                </div>
                <div class="rank-n">
                    <div>{{ rank.nickName }}</div>
                </div>
                <div class="rank-t">
                    <div>{{ rank.cnt }}</div>
                </div>
            </li>
        </ul>
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

const getMedalText = (index) => {
    switch (index) {
        case 0:
            return '1';
        case 1:
            return '2';
        case 2:
            return '3';
    }
};

const getRankClass = (index) => {
    return index <= 2 ? `rank-${getMedalText(index).toLowerCase()}` : 'res';
};
</script>

<style scoped>
.gold {
    padding: 20px;
}

.silver {
    padding: 15px;
}

.bronze {
    padding: 10px;
}

.rank-medal {
    padding: 10px;
}

.rank-medal {
    display: inline-block;
    width: 20px;
    height: 20px;
    text-align: center;
    line-height: 20px;
    border-radius: 50%;
    font-weight: bold;
}

.rank-1 {
    background-color: rgba(255, 217, 0, 0.5);
    border-radius: 10px;
    margin: 10px 0;
}

.rank-2 {
    background-color: rgba(198, 198, 198, 0.5);
    border-radius: 10px;
    margin: 10px 0;
}

.rank-3 {
    background-color: #da873383;
    border-radius: 10px;
    margin: 10px 0;
}

.res {
    background-color: #3a3a3a83;
    border-radius: 10px;
    margin: 10px 0;
}

.rank-tr {
    background-color: var(--bg-100);
    border-radius: 10px;
    margin: 10px auto;
    width: 700px;
    display: flex;
    justify-content: center;
    font-size: large;
    padding: 5px;

}

.rank-th-b {
    width: 50px;
}

.rank-th-i {
    width: 200px;
}

.rank-th-n {
    width: 100px;
}

.rank-th-t {
    width: 70px;
    text-align: end;
}

.rank-ul {
    width: 700px;
    list-style: none;
    margin: 0 auto;
    padding: 0;
    font-size: large;
}

.rank-li {

    display: flex;
    align-items: center;
    justify-content: center;
}

.rank-li:hover {
    background-color: var(--header);
    border-radius: 10px;
}

.rank-img {
    padding-top: 4px;
}

.rank-b {
    width: 50px;
    display: flex;
    justify-content: center;
}

.rank-i {
    width: 130px;
    display: flex;
    /* justify-content: center; */
    margin-left: 70px;
    align-items: center;
    gap: 10px;
}


.rank-n {
    width: 100px;
    display: flex;
    justify-content: center;
}

.rank-t {
    width: 70px;
    display: flex;
    justify-content: end;
}
</style>
<template>
    <div class="header">
        <div class="main-link-div">
            <img src="../../assets/logo1.png" alt="logo" class="logo">
            <router-link class="main-link" :to="{ name: 'main' }">천근만근</router-link>
        </div>
        <div class="sub-link-div">
            <img src="../../assets/home.gif" alt="sub-logo" class="sub-logo">
            <router-link :to="{ name: 'main' }" class="sub-link">홈</router-link>
        </div>
        <div class="sub-link-div">
            <img src="../../assets/fitness.gif" alt="sub-logo" class="sub-logo">
            <router-link :to="{ name: 'exercise' }" class="sub-link">운동하기</router-link>
        </div>
        <div class="sub-link-div">
            <img src="../../assets/society.gif" alt="sub-logo" class="sub-logo">
            <router-link :to="{ name: 'community' }" class="sub-link">커뮤니티</router-link>
        </div>
        <div class="sub-link-div">
            <img src="../../assets/military-medal.gif" alt="sub-logo" class="sub-logo">
            <router-link :to="{ name: 'rank' }" class="sub-link">랭킹</router-link>
        </div>
        <div class="sub-link-div">
            <img src="../../assets/guide.gif" alt="sub-logo" class="sub-logo">
            <router-link :to="{ name: 'guide' }" class="sub-link">운동법 가이드</router-link>
        </div>
    </div>
</template>

<script setup>

</script>

<style scoped>
.header {
    width: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
}

.logo {
    filter: var(--invert);
    width: 100px;
    height: 100px;
}

.sub-logo {
    filter: var(--invert);
    border-radius: 5px;
    width: 30px;
    height: 30px;
    border: solid 1px var(--text-100);
    transition: 0.2s;
}

.main-link-div {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin: 20px;
}

.main-link {
    font-size: 20px;
    margin: 0;
    padding: 0;
    margin-bottom: 30px;

}

.sub-link-div {
    display: flex;
    align-items: center;
    gap: 10px;
    margin: 10px;
    font-size: 16px;
    margin-left: 30px;
}

.sub-link-div:hover .sub-link {
    color: var(--text-200);
}
</style>
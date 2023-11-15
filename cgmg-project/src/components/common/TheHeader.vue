<template>
    <div class="header">
        <div class="main-link-div">
            <router-link class="main-link" :to="{ name: 'main' }">천근만근</router-link>
        </div>
        <div class="sub-link-div">
            <a>운동하기</a>
        </div>
        <div class="sub-link-div">
            <a>커뮤니티</a>
        </div>
        <div class="sub-link-div">
            <a>랭킹</a>
        </div>
        <div class="sub-link-div">
            <a>운동법 가이드</a>
        </div>
        <div class="login-link-div">
            <a>로그인</a>/
            <a>회원가입</a>
        </div>
    </div>
</template>

<script setup>

</script>

<style scoped>
.header {
    position: relative;
    width: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}

.main-link-div {
    height: 260px;
    font-size: 40px;
    margin-bottom: 200px;
}

.sub-link-div {
    margin: 20px;
    font-size: 20px;
}

.login-link-div {
    position: fixed;
    bottom: 80px;

}
</style>
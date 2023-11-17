<template>
    <div class="header2">
        <label class="switch">
            <input class="check" type="checkbox" id="darkModeToggle" />
            <span class="slider"></span>
        </label>

        <div>
            <a class="sub-link">로그인</a>/
            <a class="sub-link">회원가입</a>
        </div>
        <SearchComp />
    </div>
</template>

<script setup>
import SearchComp from '../SearchComp.vue';

</script>

<style scoped>
@import '../../assets/css/toggle.css';

.header2 {
    position: fixed;
    top: 0;
    right: 0;
    padding: 15px 100px;
    display: flex;
    justify-content: flex-end;
    align-items: center;
    gap: 20px;
    background: var(--header);
    backdrop-filter: blur(10px);
    width: 70%;
}

.search-id-div {
    margin-left: 30px;
}

.sub-link {
    color: var(--text-100);
}
</style>
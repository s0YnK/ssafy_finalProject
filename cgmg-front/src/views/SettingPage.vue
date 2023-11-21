<template>
    <div>
        설정페이지
        <div>비밀번호를 입력해주세요</div>
        <input type="password">
        <RouterLink :to="{ name: 'upde' }">확인</RouterLink>
    </div>
</template>

<script setup>
import { useRoute, useRouter } from 'vue-router'
</script>

<style scoped></style>
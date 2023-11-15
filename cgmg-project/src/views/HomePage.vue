<template>
    <div>
        <div class="header-div">
            <TheHeader />
        </div>
        <div class="ma">
            <RouterView />
        </div>
    </div>
</template>

<script setup>
import TheHeader from '@/components/common/TheHeader.vue';
</script>
<style scoped>
.header-div {
    width: 200px;
    border-radius: 20px;
    overflow: scroll;
    background-color: #e2e2e0;
    position: fixed;
    top: 30px;
    bottom: 30px;
    left: 30px;
}
</style>


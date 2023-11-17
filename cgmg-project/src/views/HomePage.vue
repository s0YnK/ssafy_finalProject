<template>
    <div>
        <div class="header2-div">
            <TheHeader2 />
        </div>
        <div class="header-div">
            <TheHeader />
        </div>
        <div class="router-div">
            <RouterView />
        </div>
    </div>
</template>

<script setup>
import TheHeader from '@/components/common/TheHeader.vue';
import TheHeader2 from '@/components/common/TheHeader2.vue';
</script>
<style scoped>
.header-div {
    background-color: var(--bg-200);
    width: 250px;
    overflow: scroll;
    position: fixed;
    top: 0;
    bottom: 0;
    left: 0;
    box-shadow: 0 0 10px var(--bg-300);
}

.header2-div {
    margin-left: 250px;
}

.router-div {
    margin-top: 70px;
    margin-left: 250px;
}
</style>


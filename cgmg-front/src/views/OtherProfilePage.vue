<template>
    <div>
        <h1>마이페이지</h1>
        <ChartComp />
        <div class="ctr-div">
            <StreakComp class="SC" />
            <div class="ctr">스트릭</div>
        </div>
    </div>
</template>

<script setup>
import StreakComp from '../components/profile/OtherStreakComp.vue';
import ChartComp from '../components/profile/OtherChartComp.vue';

</script>

<style scoped>
h1 {
    margin: 40px;
}

.SC {
    width: 90%;
    margin: 20px auto;
    border-radius: 20px;
    padding: 20px;
}

.ctr-div {
    position: relative;
}

.ctr {
    position: absolute;
    top: 20px;
    left: 70px;
}
</style>

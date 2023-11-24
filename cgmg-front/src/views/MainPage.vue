<template>
    <div class="main">
        <div class="inf1">
            <div class="inf1-img">
                <div style="background-color: var(--primary-200); border-radius: 20px; padding: 10px;">
                    <div style="font-size: 30px;">천근만근 (千筋萬筋)</div>
                    <div style="color: var(--text-200);">일천 천, 힘줄 근, 일만 만, 힘줄 근</div>
                    <div
                        style="background-color: var(--text-200); width: 30px; padding: 5px; border-radius: 5px; margin: 10px 0;">
                        명사</div>
                    <div style="font-size: 20px;">근육량이 1000, 10000이 됨을 비유적으로 이르는 말.<br></div>
                </div>
                <div style="display: flex; justify-content: center; align-items: center; gap: 100px; margin: 20px;">
                    <div
                        style="color: var(--bg-500); font-size: 20px; padding: 20px; background-color: var(--primary-200); border-radius: 20px;">
                        운동할 때마다 채워지는 기록으로<br>건강한 운동습관을 쭉~~!
                    </div>
                    <div><img src="../assets/lifestyle.png" alt="" width="200"></div>
                </div>

            </div>
            <div class="inf1-text">
                <div class="inf1-title">
                    천근만근
                </div>
                <div class="inf1-content">
                    하루하루 꾸준히 운동을 기록하며, <br>운동습관을 길러주는 천근만근 페이지입니다. <br>한 세트당 1근씩 올리는 재미를 느껴보세요.
                </div>
            </div>
        </div>
        <hr>
        <div class="inf1">
            <div class="inf1-text">
                <router-link :to="{ name: 'exercise' }" class="inf1-title" @click="islogin">운동하기</router-link>
                <div class="inf1-content">
                    간편하게 운동하고 기록하세요. <br>얼마나 운동할지만 정하면 <br>하나, 둘, 셋! 목소리로 운동을 도와줍니다.
                </div>
            </div>
            <div class="inf1-img" style="height: 230px; padding-top: 70px;">
                <div style="display: flex; justify-content:space-around; align-items: center; gap: 10px; margin: 20px;">
                    <div><img src="../assets/heart-rate.png" alt="" width="140"></div>
                    <div><img src="../assets/calendar.png" alt="" width="140"></div>
                    <div><img src="../assets/calories.png" alt="" width="140"></div>
                </div>
            </div>
        </div>
        <hr>
        <div class="inf1">
            <div class="inf1-img" style="height: 260px; padding-top: 70px;">
                <div style="display: flex; justify-content:space-around; align-items: center; gap: 10px; margin: 20px;">
                    <div><img src="../assets/group (1).png" alt="" width="200"></div>
                    <div><img src="../assets/share-location.png" alt="" width="200"></div>
                </div>
            </div>
            <div class="inf1-text">
                <router-link :to="{ name: 'community' }" class="inf1-title" @click="islogin">커뮤니티</router-link>
                <div class="inf1-content">
                    다른 사람들과 운동경험을 공유하세요. <br>위치공유 기능을 사용해서 <br>함께 운동할 운동메이트를 구해보세요.
                </div>
            </div>
        </div>
        <hr>
        <div class="inf1">
            <div class="inf1-text">
                <router-link :to="{ name: 'rank' }" class="inf1-title" @click="islogin">랭킹</router-link>
                <div class="inf1-content">
                    전체 랭킹, 부위별 랭킹 <br>운동 세트수로 실시간 업데이트!<br>1등을 향해 도전해보세요.
                </div>
            </div>
            <div class="inf1-img" style="display: flex; align-items: center; justify-content: center;">
                <div><img src="@/assets/ranking copy.png" width="200"></div>
            </div>
        </div>
        <hr>
        <div class="inf1">
            <div class="inf1-img" style="display: flex; align-items: center; justify-content: center;">
                <div><img src="../assets/무제-1 [복구됨].png" width="600"></div>
            </div>
            <div class="inf1-text">
                <router-link :to="{ name: 'guide' }" class="inf1-title">운동법 가이드</router-link>
                <div class="inf1-content">
                    모르는 운동을 간단히 검색만 하세요. <br>유튜브알고리즘과, <br>AI가 운동을 자세히 알려드릴게요.
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>

</script>
<style scoped>
:root {
    --primary-100: #424874;
    --primary-200: #A6B1E1;
    --primary-300: #fdf6fd;
    --accent-100: #D9ACF5;
    --accent-200: #FFCEFE;
    --text-100: #292524;
    --text-200: #64748b;
    --bg-100: #ffffff;
    --bg-200: #f5f5f5;
    --bg-300: #cccccc;
}

.main {
    width: 1120px;
    margin: 100px auto;
}

hr {
    margin: 80px 0px;
    border: none;
    border-bottom: dotted 6px var(--accent-100);
}

.inf1 {
    display: flex;
    align-items: center;
    gap: 20px;
}

.inf1-text {
    padding: 20px;
    width: 340px;
}

.inf1-img {
    background-color: var(--primary-100);
    flex-grow: 1;
    height: 400px;
    border-radius: 20px;
    padding: 20px;
}

.inf1-title {
    font-weight: 900;
    font-size: 55px;
    margin-bottom: 20px;
}

.inf1-content {
    font-size: 20px;
    width: 370px;
}
</style>
<template>
    <div class="main">
        <div class="inf">
            <div class="inf-img"></div>
            <div class="inf-text">
                <div class="inf-title">
                    천근만근
                </div>
                <div class="inf-content">
                    하루하루 꾸준히 운동을 기록하며, 운동습관을 길러주는 천근만근 페이지입니다. <br>한 셋트당 1근씩 올리는 재미를 느껴보세요.
                </div>
            </div>
        </div>
        <hr>
        <div class="inf">
            <div class="inf-text">
                <div class="inf-title">
                    운동하기
                </div>
                <div class="inf-content">
                    간편하게 운동하고 기록하세요. <br>셋트횟수와 한 셋트당 운동횟수만 정하면 운동을 도와줍니다.
                </div>
            </div>
            <div class="inf-img"></div>
        </div>
        <hr>
        <div class="inf">
            <div class="inf-img"></div>
            <div class="inf-text">
                <div class="inf-title">
                    커뮤니티
                </div>
                <div class="inf-content">
                    다른 사람들과 운동경험을 공유하세요. <br>위치를 공유하여 함께 운동할 사람을 구하는 것도 가능합니다.
                </div>
            </div>
        </div>
        <hr>
        <div class="inf">
            <div class="inf-text">
                <div class="inf-title">
                    운동하기
                </div>
                <div class="inf-content">
                    간편하게 운동하고 기록하세요. <br>셋트횟수와 한 셋트당 운동횟수만 정하면 운동을 도와줍니다.
                </div>
            </div>
            <div class="inf-img"></div>
        </div>
        <hr>
        <div class="inf">
            <div class="inf-text">
                <div class="inf-title">
                    운동하기
                </div>
                <div class="inf-content">
                    간편하게 운동하고 기록하세요. <br>셋트횟수와 한 셋트당 운동횟수만 정하면 운동을 도와줍니다.
                </div>
            </div>
            <div class="inf-img"></div>
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
    width: 80%;
    margin: 100px auto;
}

hr {
    margin: 80px 0px;
    border: none;
    border-bottom: dotted 6px var(--accent-100);
}

.inf {
    display: flex;
    align-items: center;
    gap: 20px;
}

.inf-text {
    padding: 20px;
    width: 340px;
}

.inf-img {
    background-color: var(--primary-100);
    flex-grow: 1;
    height: 400px;
    border-radius: 20px;
}

.inf-title {
    font-weight: 900;
    font-size: 55px;
    margin-bottom: 20px;
}

.inf-content {
    font-size: 20px;
}
</style>
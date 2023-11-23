<template>
    <div class="mm">
        <div class="info">
            <div class="zz">
                <div class="main">
                    <h2 class="h22"><span style="color: var(--primary-200);">{{ store.profile.nickName }}</span> 님 <br>천근만근
                        득근하세요~!
                    </h2>
                    <div class="not">
                        <div>알림 {{ store.notifyList.length }}개</div>
                        <div style="width: 100%; height: 80px; overflow: scroll;">
                            <table style="overflow: scroll;">
                                <!-- 검색 결과를 반복하면서 테이블 행을 생성 -->
                                <tr v-for="notify, i in store.notifyList" :key="notify.id">
                                    <RouterLink :to="`/community/${notify.postId}`" v-if="notify.notType == 'comment'">
                                        <td style="width: 100px;">{{ notify.regDate }}</td>
                                        <td v-html="notify.message"></td>
                                    </RouterLink>
                                    <RouterLink :to="`/otherProfile/${notify.causedBy}`" v-if="notify.notType == 'follow'">
                                        <td style="width: 100px;">{{ notify.regDate }}</td>
                                        <td v-html="notify.message"></td>
                                    </RouterLink>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="img-pro">
                    <img :src="`src/assets/${store.profile.profileImg}.png`" width="100" height="100" class="iimg">
                    <div class="pro">
                        <progress id="progress" :value="`${store.profile.totalExerciseCnt % 100}`" min="0"
                            max="100"></progress>
                        <div class="proinf">
                            <div>{{ Math.floor(store.profile.totalExerciseCnt / 100) * 100 }}</div>
                            <div style="font-size: 20px;">총 <span style="color: var(--primary-100);">{{
                                store.profile.totalExerciseCnt }}</span>근 획득
                                |
                                승급까지 {{ 100 - store.profile.totalExerciseCnt %
                                    100
                                }}근
                            </div>
                            <div>{{ Math.floor(store.profile.totalExerciseCnt / 100 + 1) * 100 }}</div>
                        </div>
                    </div>
                </div>
                <div class="follow">
                    <div @click="showLikeList = false; showMyList = false; showFollower = true; showFollowing = false;"
                        class="btn1">나의 팔로워
                        {{ store.followerList.length }}명 보기</div>
                    <div @click="showLikeList = false; showMyList = false; showFollower = false; showFollowing = true;"
                        class="btn1">내가 팔로우한
                        {{ store.followingList.length }}명 보기</div>
                    <div @click="showLikeList = true; showMyList = false; showFollower = false; showFollowing = false;"
                        class="btn1">내가
                        좋아요한 게시물
                    </div>
                    <div @click="showMyList = true; showLikeList = false; showFollower = false; showFollowing = false"
                        class="btn1">내가
                        작성한 게시물
                    </div>
                </div>
            </div>
            <div class="cat">
                <ChartComp />
            </div>
        </div>
        <div v-if="showLikeList" class="div">
            <table>
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>글쓴이</th>
                    <th>날짜</th>
                    <th>조회수</th>
                </tr>
                <tr v-for="board, i in store2.LikeList" :key="board.id">
                    <td>{{ i + 1 }}</td>
                    <RouterLink :to="`/community/${board.id}`">
                        <td>{{ board.title }}</td>
                    </RouterLink>
                    <td>{{ board.nickName }}</td>
                    <td>{{ board.regDate }}</td>
                    <td>{{ board.viewCnt }}</td>
                </tr>
            </table>
        </div>
        <div v-if="showMyList" class="div">
            <table>
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>글쓴이</th>
                    <th>날짜</th>
                    <th>조회수</th>
                </tr>
                <tr v-for="board, i in store2.MyList" :key="board.id">
                    <td>{{ i + 1 }}</td>
                    <RouterLink :to="`/community/${board.id}`">
                        <td>{{ board.title }}</td>
                    </RouterLink>
                    <td>{{ board.nickName }}</td>
                    <td>{{ board.regDate }}</td>
                    <td>{{ board.viewCnt }}</td>
                </tr>
            </table>
        </div>

        <div v-if="showFollower" class="div">
            <table>
                <tr>
                    <th>번호</th>
                    <th>아이디</th>
                    <th>닉네임</th>
                    <th>티어</th>
                </tr>
                <!-- 검색 결과를 반복하면서 테이블 행을 생성 -->
                <tr v-for="follow, i in store.followerList" :key="follow.followerId">
                    <td>{{ i + 1 }}</td>
                    <td>
                        <RouterLink :to="`/otherProfile/${follow.followerId}`">{{ follow.followerId }}
                        </RouterLink>
                    </td>
                    <td>{{ follow.nickName }}</td>
                    <td>{{ follow.totalExerciseCnt }}</td>
                </tr>
            </table>
        </div>

        <div v-if="showFollowing" class="div">
            <table>
                <tr>
                    <th>번호</th>
                    <th>아이디</th>
                    <th>닉네임</th>
                    <th>티어</th>
                </tr>
                <!-- 검색 결과를 반복하면서 테이블 행을 생성 -->
                <tr v-for="follow, i in store.followingList" :key="follow.followingId">
                    <td>{{ i + 1 }}</td>
                    <td>
                        <RouterLink :to="`/otherProfile/${follow.followingId}`">{{ follow.followingId }}
                        </RouterLink>
                    </td>
                    <td>{{ follow.nickName }}</td>
                    <td>{{ follow.totalExerciseCnt }}</td>
                </tr>
            </table>
        </div>
        <div class="ctr-div">
            <div class="ctr"> 운동을 <span style="color: var(--primary-200);">{{ store.profile.continuedStreak }}일</span> 연속
                하고있어요!</div>
            <StreakComp />
        </div>
        <div class="comm">
        </div>
    </div>
</template>

<script setup>
import StreakComp from '../components/profile/StreakComp.vue';
import ChartComp from '../components/profile/ChartComp.vue';
import { useRoute, useRouter } from 'vue-router';
import { ref, onMounted } from "vue";
import { useUserStore } from "../stores/user"
import { useBoardStore } from '../stores/community';
const store2 = useBoardStore();
const store = useUserStore();
const userId = JSON.parse(localStorage.getItem("loginUser")).userId;
const showLikeList = ref(false);
const showMyList = ref(false);
const showFollower = ref(false);
const showFollowing = ref(false);

onMounted(() => {
    store2.getMyList(userId);
    store2.getLikeList(userId);
    store.getfollower(userId);
    store.getfollowing(userId);
    store.getProfile(userId);
    store.getNotifyList(userId);
    console.log(store.profile.profileImg)
});

</script>

<style scoped>
.not {
    width: 300px;
    background-color: var(--primary-300);
    border-radius: 20px;
    padding: 20px;
    overflow: scroll;
    height: 110px;
}

.main {
    display: flex;
    gap: 20px;
}

.btn1 {
    background-color: var(--primary-200);
    padding: 10px;
    border-radius: 10px;
    transition: 0.2s;
}

.mm {
    width: 1200px;
    margin: 20px auto 0px;
}

.h22 {
    text-align: center;
    padding: 20px;
    flex-grow: 1;
    background-color: var(--primary-300);
    border-radius: 20px;
    padding-top: 40px;
}

.SC {
    width: 90%;
    margin: 20px auto;
    border-radius: 20px;
    padding: 20px;
    transition: 0.2s;
}

.div {
    width: 1080px;
    margin: 20px auto;
    background-color: var(--primary-300);
    border-radius: 20px;
    transition: 0.2s;
    padding: 20px;
}

.ctr-div {
    width: 1120px;
    margin: 20px auto;
    background-color: var(--bg-500);
    border-radius: 20px;
    transition: 0.2s;
}

.ctr-div:hover {
    box-shadow: 0 0 10px var(--text-200)
}

.ctr {
    padding: 20px 0px 0px 40px;
    font-size: 20px;
}

.info {
    width: 1120px;
    display: flex;
    margin: 20px auto;
    gap: 20px;
}


.follow {
    /* flex-grow: 1; */
    display: flex;
    justify-content: space-between;
    /* background-color: var(--primary-300); */
    border-radius: 20px;
    padding: 20px;
    transition: 0.2s;
}

.zz {
    flex-grow: 1;
    display: flex;
    flex-direction: column;
}

.pro {
    flex-grow: 1;
    margin-top: 15px;
}

.proinf {
    display: flex;
    justify-content: space-between;
    color: var(--text-200);
}

.img-pro {
    display: flex;
    /* justify-content: center; */
    /* align-items: center; */
    gap: 20px;
    margin: 20px;
}

.iimg {
    border-radius: 100px;
    border: 3px solid var(--text-200);
}

.btn1:hover {
    box-shadow: 0 0 10px var(--text-200)
}

.comm {
    width: 1120px;
    display: flex;
    margin: 20px auto;
    gap: 20px;
}


#progress {
    width: 100%;
    margin-top: 20px;
    appearance: none;
    height: 30px;
}

#progress::-webkit-progress-bar {
    background: var(--header3);
    border-radius: 10px;
    box-shadow: inset 3px 3px 10px var(--header2);
}

#progress::-webkit-progress-value {
    border-radius: 10px;
    background: #1D976C;
    background: -webkit-linear-gradient(to right, var(--primary-200), var(--primary-100));
    background: linear-gradient(to right, var(--primary-200), var(--primary-100));
}
</style>
